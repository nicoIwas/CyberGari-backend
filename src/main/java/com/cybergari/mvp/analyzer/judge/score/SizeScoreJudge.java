package com.cybergari.mvp.analyzer.judge.score;

import com.cybergari.mvp.file.File;
import lombok.RequiredArgsConstructor;


@RequiredArgsConstructor
public class SizeScoreJudge implements ScoreJudge {
    private final float minSize;
    private final float coefficient;

    @Override
    public int judgeFile(final File toJudge) {
        if(toJudge.getSize() < minSize)
            return 0;

        return (int) Math.round((toJudge.getSize() - minSize) * coefficient);
    }
}
