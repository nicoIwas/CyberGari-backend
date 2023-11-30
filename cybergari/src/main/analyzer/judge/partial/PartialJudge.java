package main.analyzer.judge.partial;

import main.analyzer.v2.PartialClassification;
import main.file.File;

public interface PartialJudge {
    PartialClassification judgeFile(File file);
}
