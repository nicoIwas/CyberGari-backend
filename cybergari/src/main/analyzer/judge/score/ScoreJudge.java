package main.analyzer.judge.score;

import main.file.Storage;

/**
 * Used to give a file a partial score that will be summed with others and used to
 * classify the file
 */
public interface ScoreJudge {
    int judgeFile(Storage toJudge);
}
