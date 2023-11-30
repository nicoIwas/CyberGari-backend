package main.analyzer.v2;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import main.analyzer.configuration.AnalyserConfiguration;
import main.analyzer.judge.partial.PartialJudge;
import main.file.File;

import java.util.ArrayList;
import java.util.List;

import static main.analyzer.judge.partial.MaxTagPriorityPartialJudge.maxTagPriority;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class AnalyserV2AlgorithmGenerator {
    public static List<PartialJudge> generateAlgorithm(final AnalyserConfiguration configuration) {
        final Algorithm algorithm = new Algorithm();

        algorithm.addIf(maxTagPriority(), configuration.tags());

        return algorithm.list;
    }

    private static class Algorithm {
        @Getter
        private final List<PartialJudge> list = new ArrayList<>();

        void addIf(final PartialJudge judge, final boolean condition) {
            if (condition) {
                list.add(judge);
            }
        }
    }

    public static double calculateBias(final File file, final AnalyserConfiguration configuration) {
        if (!configuration.tags()) {
            return 0;
        }

        return file.getPriority() != null ? file.getPriority().getPriority() : 0;
    }
}
