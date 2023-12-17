package com.cybergari.mvp.analyzer.judge;

import com.cybergari.mvp.analyzer.judge.absolute.AbsoluteJudge;
import com.cybergari.mvp.analyzer.judge.multiplier.MultiplierJudge;
import com.cybergari.mvp.analyzer.judge.score.ScoreJudge;
import lombok.Getter;
import lombok.RequiredArgsConstructor;


import java.util.List;

@Getter
@RequiredArgsConstructor
public class JudgePackage {
    private final List<AbsoluteJudge> absoluteJudges;
    private final List<ScoreJudge> scoreJudges;
    private final List<MultiplierJudge> multiplierJudges;
}
