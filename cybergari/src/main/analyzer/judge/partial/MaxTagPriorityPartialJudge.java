package main.analyzer.judge.partial;

import main.analyzer.v2.PartialClassification;
import main.file.File;

public class MaxTagPriorityPartialJudge implements PartialJudge {
    private final static int MAX_PRIORITY = 0;

    public static MaxTagPriorityPartialJudge maxTagPriority() {
        return new MaxTagPriorityPartialJudge();
    }

    @Override
    public PartialClassification judgeFile(final File file, final double bias) {
        if (file.getPriority() == null) {
            return PartialClassification.INCONCLUSIVE;
        }

        return file.getPriority().getPriority() == MAX_PRIORITY ?
                PartialClassification.NO_ACTION :
                PartialClassification.INCONCLUSIVE;
    }
}
