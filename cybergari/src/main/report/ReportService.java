package main.report;

import main.analyzer.Analyzer;
import main.analyzer.classifier.FileClassifier;
import main.analyzer.judge.JudgeService;
import main.filemanager.FileManager;
import main.report.vo.Report;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.UUID;
import java.util.stream.Collectors;

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
        final var scoredFiles = analyser.analyze(fileManager.getAllFiles());
        final var classifiedFiles = FileClassifier.classifyScoredFiles(scoredFiles);

        final var report = new Report(
                UUID.randomUUID().toString(),
                classifiedFiles.getFilesToCompress().stream().map(file -> mapper.mapToReport(file))
                        .collect(Collectors.toList()),
                classifiedFiles.getFilesToDelete().stream().map(file -> mapper.mapToReport(file))
                        .collect(Collectors.toList())
        );

        repository.save(report);
        return report;
    }
}
