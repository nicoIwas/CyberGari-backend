package com.cybergari.mvp.report;


import com.cybergari.mvp.analyzer.Analyzer;
import com.cybergari.mvp.analyzer.classifier.FileClassifier;
import com.cybergari.mvp.analyzer.judge.JudgeService;
import com.cybergari.mvp.file.File;
import com.cybergari.mvp.filemanager.FileManager;
import com.cybergari.mvp.report.vo.Report;
import com.cybergari.mvp.report.vo.ReportConfirmation;
import com.cybergari.mvp.storagesizelog.StorageSizeLogService;
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
