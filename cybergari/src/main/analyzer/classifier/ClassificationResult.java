package main.analyzer.classifier;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import main.file.File;

import java.util.List;

@Getter
@RequiredArgsConstructor
public class ClassificationResult {
    private final List<File> filesToCompress;
    private final List<File> filesToDelete;
}
