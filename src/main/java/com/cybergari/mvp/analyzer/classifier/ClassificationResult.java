package com.cybergari.mvp.analyzer.classifier;

import com.cybergari.mvp.file.File;
import lombok.Getter;
import lombok.RequiredArgsConstructor;


import java.util.List;

@Getter
@RequiredArgsConstructor
public class ClassificationResult {
    private final List<File> filesToCompress;
    private final List<File> filesToDelete;
}
