package main.analyzer.v2;

import main.analyzer.AnalyserService;
import main.analyzer.ClassificationResult;
import main.analyzer.configuration.AnalyserConfigurationService;
import main.analyzer.judge.partial.PartialJudge;
import main.file.File;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.Set;

@Service
public class AnalyserServiceV2 implements AnalyserService {
    @Autowired
    public AnalyserConfigurationService configurationService;

    @Override
    public ClassificationResult analyseFiles(final Set<File> files, final String userId) {
        final var configuration = configurationService.findConfigurationsForUser(userId);
        final var judges = AnalyserV2AlgorithmGenerator.generateAlgorithm(configuration);

        return classifyFiles(files, judges);
    }

    private ClassificationResult classifyFiles(final Set<File> files, final List<PartialJudge> judges) {
        final var toCompress = new LinkedList<File>();
        final var toDelete = new LinkedList<File>();

        for (var file : files) {
            for (var judge : judges) {
                final var result = judge.judgeFile(file);

                if (result == PartialClassification.COMPRESS) {
                    toCompress.add(file);
                    break;
                } else if (result == PartialClassification.DELETE) {
                    toDelete.add(file);
                    break;
                } else if (result == PartialClassification.IGNORE) {
                    break;
                }
            }
        }

        return new ClassificationResult(toCompress, toDelete);
    }
}
