package main.report;

import main.analyzer.AnalyserService;
import main.filemanager.FileManager;
import main.report.vo.Report;
import main.report.vo.ReportConfirmation;
import main.storagesizelog.StorageSizeLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class ReportService {
    @Autowired
    FileManager fileManager;
    @Autowired
    AnalyserService analyserService;
    @Autowired
    StorageSizeLogService storageSizeLogService;
    @Autowired
    ReportRepository repository;
    @Autowired
    FileReportMapper mapper;

    public Report generateReportForUser(final String userId) {
        final var allFiles = fileManager.getAllFiles();
        final var classifiedFiles = analyserService.analyseFiles(new HashSet<>(allFiles), userId);

        final var report = new Report(
                UUID.randomUUID().toString(),
                classifiedFiles.filesToCompress().stream().map(file -> mapper.mapToReport(file))
                        .collect(Collectors.toList()),
                classifiedFiles.filesToDelete().stream().map(file -> mapper.mapToReport(file))
                        .collect(Collectors.toList()),
                fileManager.getFileStructure().getFileCount()
        );

        repository.save(report);
        return report;
    }

    public List<String> executeReport(final ReportConfirmation report) {
        /*
        TODO: remove comment once report cache is implemented
        final var latestReportId = repository.getLatestReportForUser(report.userId());

        if (latestReportId.isEmpty() || !latestReportId.get().equals(report.reportId())) {
            throw new InvalidReportForUser(report.reportId(), report.userId());
        }
         */

        final var oldFolderSize = fileManager.getFileStructure().getSize();
        final var invalidFiles = Stream.concat(
                report.filesToCompress().stream().filter(file -> !fileManager.compressFile(file)),
                report.filesToDelete().stream().filter(file -> !fileManager.deleteFile(file))
        ).collect(Collectors.toList());

        fileManager.refresh();
        final var currentFolderSize = fileManager.getFileStructure().getSize();
        storageSizeLogService.save(oldFolderSize, currentFolderSize, report.userId());

        return invalidFiles;
    }
}
