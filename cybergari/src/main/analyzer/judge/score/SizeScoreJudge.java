package main.analyzer.judge.score;

import main.files.Storage;

public class SizeScoreJudge implements ScoreJudge {
    private final float minSize;
    private final float coefficient;

    public SizeScoreJudge(float minSize, float coefficient) {
        this.minSize = minSize;
        this.coefficient = coefficient;
    }


    @Override
    public int judgeFile(final Storage toJudge) {
        if(toJudge.getSize() < minSize)
            return 0;

        return (int) Math.round((toJudge.getSize() - minSize) * coefficient);
    }
}