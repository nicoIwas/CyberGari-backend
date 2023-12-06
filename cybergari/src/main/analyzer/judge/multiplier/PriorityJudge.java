package main.analyzer.judge.multiplier;

import main.file.File;

public class PriorityJudge implements MultiplierJudge{

    @Override
    public float judgeFile(final File toJudge) {
        if (toJudge.getPriority() == null)
            return 1;

        return 10000;
    }
}
