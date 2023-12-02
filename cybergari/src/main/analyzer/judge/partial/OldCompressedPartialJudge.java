package main.analyzer.judge.partial;

import main.analyzer.v2.PartialClassification;
import main.file.File;

import java.time.Instant;
import java.time.temporal.ChronoUnit;

import static main.analyzer.v2.PartialClassification.DELETE;
import static main.analyzer.v2.PartialClassification.INCONCLUSIVE;

public class OldCompressedPartialJudge implements PartialJudge {
    private static final int MIN_DAYS = 30;
    private static final int MAX_BIAS = 45;

    public static OldCompressedPartialJudge oldCompressed() {
        return new OldCompressedPartialJudge();
    }

    @Override
    public PartialClassification judgeFile(final File file, final double bias) {
        if (!file.isCompressed()) {
            return INCONCLUSIVE;
        }

        final var daysSince = ChronoUnit.DAYS.between(Instant.now(), file.getCreatedTime());
        if (daysSince >= MIN_DAYS + MAX_BIAS * bias) {
            return DELETE;
        }

        return INCONCLUSIVE;
    }
}
