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
                List.of(new SizeScoreJudge(30000, (float) 0.02)),
                List.of()
        );
    }
}

// 30 - 50 compactar
// 50+ deletar
