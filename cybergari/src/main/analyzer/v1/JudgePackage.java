package main.analyzer.v1;

import main.analyzer.judge.absolute.AbsoluteJudge;
import main.analyzer.judge.multiplier.MultiplierJudge;
import main.analyzer.judge.score.ScoreJudge;

import java.util.List;

public record JudgePackage(List<AbsoluteJudge> absoluteJudges, List<ScoreJudge> scoreJudges,
                           List<MultiplierJudge> multiplierJudges) {
}
