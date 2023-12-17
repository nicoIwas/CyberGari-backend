package com.cybergari.mvp.analyzer.judge.absolute;


import com.cybergari.mvp.file.File;

/**
 * Used to immediately ignore a file compression
 */
public interface AbsoluteJudge {
    boolean judgeFile(File toJudge);
}
