package main.analyzer.judge;

import java.util.List;

public class JudgeService {
    public JudgePackage findJudgesForUser(final String userId) {
        return new JudgePackage(List.of(), List.of());
    }
}
