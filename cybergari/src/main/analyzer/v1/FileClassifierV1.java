package main.analyzer.v1;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import main.analyzer.ClassificationResult;
import main.file.File;

import java.util.LinkedList;
import java.util.List;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class FileClassifierV1 {
    public static final int COMPRESS_THRESHOLD = 500;
    public static final int DELETE_THRESHOLD = 1500;

    public static ClassificationResult classifyScoredFiles(final List<ScoredFile> scoredFiles) {
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
