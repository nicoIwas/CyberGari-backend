package main.analyzer.judge.multiplier;

import main.file.File;
import main.file.Storage;

/**
 * Used to give a file a partial score that will be summed with others and used to
 * classify the file
 */
public interface MultiplierJudge {
    float judgeFile(File toJudge);
}
