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
import static main.analyzer.judge.partial.OldAndLargePartialJudge.oldAndLarge;
import static main.analyzer.judge.partial.OldCompressedPartialJudge.oldCompressed;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class AnalyserV2AlgorithmGenerator {
    public static List<PartialJudge> generateAlgorithm(final AnalyserConfiguration configuration) {
        final Algorithm algorithm = new Algorithm();

        algorithm.addIf(maxTagPriority(), configuration.tags());
        algorithm.addIf(oldCompressed(), configuration.lastSeen()); // TODO: use createdAt permission
        algorithm.addIf(oldAndLarge(), configuration.lastSeen());

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

    private static final double MAX_BIAS = 10;
    public static double calculateBias(final File file, final AnalyserConfiguration configuration) {
        if (!configuration.tags()) {
            return 0;
        }

        if (file.getPriority() == null) {
            return 0;
        }
        return file.getPriority().getPriority() == 0 ?
                1 :
                (MAX_BIAS + 1 - file.getPriority().getPriority()) / MAX_BIAS;

        // The +1 means that, even if the priority is 0, it will still be greater than if the file did not
        // have a tag
    }
}
