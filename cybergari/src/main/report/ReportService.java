package main.report;

import main.analyzer.Analyzer;
import main.analyzer.classifier.FileClassifier;
import main.analyzer.judge.JudgeService;
import main.exception.InvalidReportForUser;
import main.file.File;
import main.filemanager.FileManager;
import main.report.vo.Report;
import main.report.vo.ReportConfirmation;
import main.storagesizelog.StorageSizeLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class ReportService {
    @Autowired
    FileManager fileManager;
    @Autowired
    JudgeService judgeService;
    @Autowired
    StorageSizeLogService storageSizeLogService;
    @Autowired
    ReportRepository repository;
    @Autowired
    FileReportMapper mapper;

    public Report generateReportForUser(final String userId) {
        final var analyser = new Analyzer(judgeService.findJudgesForUser(userId));
        Collection<File> allFiles = fileManager.getAllFiles();
        final var scoredFiles = analyser.analyze(allFiles);
        final var classifiedFiles = FileClassifier.classifyScoredFiles(scoredFiles);

        final var report = new Report(
                UUID.randomUUID().toString(),
                classifiedFiles.getFilesToCompress().stream().map(file -> mapper.mapToReport(file))
                        .collect(Collectors.toList()),
                classifiedFiles.getFilesToDelete().stream().map(file -> mapper.mapToReport(file))
                        .collect(Collectors.toList()),
                fileManager.getFileStructure().getFileCount()
        );

        repository.save(report);
        return report;
    }

    public List<String> executeReport(final ReportConfirmation report) {
//        final var latestReportId = repository.getLatestReportForUser(report.userId());
//
//        if (latestReportId.isEmpty() || !latestReportId.get().equals(report.reportId())) {
//            throw new InvalidReportForUser(report.reportId(), report.userId());
//        }

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
