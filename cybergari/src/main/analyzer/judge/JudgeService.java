package main.analyzer.judge;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class JudgeService {
    public JudgePackage findJudgesForUser(final String userId) {
        return new JudgePackage(List.of(), List.of());
    }
}
