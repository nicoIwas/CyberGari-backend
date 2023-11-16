package main.analyzer.judge;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import main.analyzer.judge.absolute.AbsoluteJudge;
import main.analyzer.judge.multiplier.MultiplierJudge;
import main.analyzer.judge.score.ScoreJudge;

import java.util.List;

@Getter
@RequiredArgsConstructor
public class JudgePackage {
    private final List<AbsoluteJudge> absoluteJudges;
    private final List<ScoreJudge> scoreJudges;
    private final List<MultiplierJudge> multiplierJudges;
}
