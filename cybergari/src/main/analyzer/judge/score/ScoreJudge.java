package main.analyzer.judge.score;

import main.files.File;
import main.files.Storage;

/**
 * Used to give a file a partial score that will be summed with others and used to
 * classify the file
 */
public interface ScoreJudge {
    int judgeFile(Storage toJudge);
}
