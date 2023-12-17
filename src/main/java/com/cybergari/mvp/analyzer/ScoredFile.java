package com.cybergari.mvp.analyzer;

import com.cybergari.mvp.file.File;
import lombok.Getter;
import lombok.RequiredArgsConstructor;


@Getter
@RequiredArgsConstructor
public class ScoredFile {
    private final File file;
    private final int score;
    // We could add the compression level / method to be used here
}
