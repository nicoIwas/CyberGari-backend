package main.analyzer.judge.partial;

import main.analyzer.v2.PartialClassification;
import main.file.File;

import static main.analyzer.judge.JudgeUtils.bytesToMB;
import static main.analyzer.judge.JudgeUtils.daysSince;
import static main.analyzer.v2.PartialClassification.COMPRESS;
import static main.analyzer.v2.PartialClassification.INCONCLUSIVE;

public class OldAndLargePartialJudge implements PartialJudge {
    private static final int MIN_DAYS = 30;
    private static final int MAX_BIAS_DAYS = 30;
    private static final int MIN_MB = 50;
    private static final int MAX_BIAS_MB = 50;

    public static OldAndLargePartialJudge oldAndLarge() {
        return new OldAndLargePartialJudge();
    }

    @Override
    public PartialClassification judgeFile(final File file, final double bias) {
        if (daysSince(file.getLastSeen()) >= MIN_DAYS + MAX_BIAS_DAYS * bias &&
            bytesToMB(file.getSize()) >= MIN_MB + MAX_BIAS_MB * bias) {
            return COMPRESS;
        }

        return INCONCLUSIVE;
    }
}
