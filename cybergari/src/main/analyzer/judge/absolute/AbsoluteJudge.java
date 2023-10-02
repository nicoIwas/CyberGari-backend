package main.analyzer.judge.absolute;

import main.files.File;

/**
 * Used to immediately ignore a file compression
 */
public interface AbsoluteJudge {
    boolean judgeFile(File toJudge);
}
