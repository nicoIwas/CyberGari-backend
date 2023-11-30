package main.analyzer.v1;

import main.analyzer.AnalyserService;
import main.analyzer.ClassificationResult;
import main.analyzer.judge.score.SizeScoreJudge;
import main.file.File;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Primary
@Service
public class AnalyserServiceV1 implements AnalyserService {
    @Override
    public ClassificationResult analyseFiles(final Set<File> files, final String userId) {
        final var analyser = new AnalyzerV1(findJudges());
        final var scoredFiles = analyser.analyze(files);
        return FileClassifierV1.classifyScoredFiles(scoredFiles);
    }

    private JudgePackage findJudges() {
        return new JudgePackage(
                List.of(),
                List.of(new SizeScoreJudge(30000, (float) 0.02)),
                List.of()
        );
    }
}
