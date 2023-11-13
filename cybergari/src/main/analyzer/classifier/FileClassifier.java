package main.analyzer.classifier;

import main.analyzer.ScoredFile;
import main.file.File;

import java.util.LinkedList;
import java.util.List;

public class FileClassifier {
    private static final int COMPRESS_THRESHOLD = 500;
    private static final int DELETE_THRESHOLD = 1500;

    public ClassificationResult classifyScoredFiles(final List<ScoredFile> scoredFiles) {
        final var toCompress = new LinkedList<File>();
        final var toDelete = new LinkedList<File>();

        scoredFiles.forEach(scoredFile -> {
            if (scoredFile.getScore() > DELETE_THRESHOLD) {
                toDelete.add(scoredFile.getFile());
            } else if (scoredFile.getScore() > COMPRESS_THRESHOLD) {
                toCompress.add(scoredFile.getFile());
            }
        });

        return new ClassificationResult(toCompress, toDelete);
    }
}
