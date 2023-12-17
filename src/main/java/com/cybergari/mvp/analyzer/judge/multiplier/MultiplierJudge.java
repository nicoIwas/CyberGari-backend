package com.cybergari.mvp.analyzer.judge.multiplier;


import com.cybergari.mvp.file.File;

/**
 * Used to give a file a partial score that will be summed with others and used to
 * classify the file
 */
public interface MultiplierJudge {
    float judgeFile(File toJudge);
}
