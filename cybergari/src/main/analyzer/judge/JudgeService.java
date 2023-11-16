package main.analyzer.judge;

import main.analyzer.judge.multiplier.PriorityJudge;
import main.analyzer.judge.score.SizeScoreJudge;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class JudgeService {
    public JudgePackage findJudgesForUser(final String userId) {

        return new JudgePackage(
                List.of(),
                List.of(new SizeScoreJudge(0, (float)0.2)),
                List.of(new PriorityJudge())
        );
    }
}
