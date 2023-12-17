package com.cybergari.mvp.analyzer.judge.multiplier;


import com.cybergari.mvp.file.File;

public class PriorityJudge implements MultiplierJudge{

    @Override
    public float judgeFile(final File toJudge) {
        if (toJudge.getPriority() == null || toJudge.getPriority().getPriority() == null)
            return 1;

        return 10000;
    }
}
