package main.report;

import main.analyzer.Analyzer;
import main.analyzer.classifier.FileClassifier;
import main.analyzer.judge.JudgeService;
import main.exception.InvalidReportForUser;
import main.file.File;
import main.filemanager.FileManager;
import main.report.vo.Report;
import main.report.vo.ReportConfirmation;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Collection;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ReportService {
    @Autowired
    private FileManager fileManager;
    @Autowired
    private JudgeService judgeService;
    @Autowired
    private ReportRepository repository;
    @Autowired
    private FIleReportMapper mapper;

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
        final var latestReportId = repository.getLatestReportForUser(report.getUserId());

        if (latestReportId.isEmpty() || !latestReportId.get().equals(report.getReportId())) {
            throw new InvalidReportForUser(report.getReportId(), report.getUserId());
        }

        return Stream.concat(
                report.getFilesToCompress().stream().filter(file -> !fileManager.compressFile(file)),
                report.getFilesToDelete().stream().filter(file -> !fileManager.deleteFile(file))
        ).collect(Collectors.toList());
    }
}
