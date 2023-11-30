package main.analyzer;

import main.file.File;

import java.util.Set;

public interface AnalyserService {
    ClassificationResult analyseFiles(Set<File> files, String userId);
}
