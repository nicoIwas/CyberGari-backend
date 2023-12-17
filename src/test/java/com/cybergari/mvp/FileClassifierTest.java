package com.cybergari.mvp;


import com.cybergari.mvp.analyzer.ScoredFile;
import com.cybergari.mvp.analyzer.classifier.ClassificationResult;
import com.cybergari.mvp.analyzer.classifier.FileClassifier;
import com.cybergari.mvp.file.File;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.Instant;
import java.util.List;

public class FileClassifierTest {

    @Test
    public void shouldDeleteFile() {
        final var should_delete = new ScoredFile(createFile(), FileClassifier.DELETE_THRESHOLD+1);
        ClassificationResult classificationResult = FileClassifier.classifyScoredFiles(List.of(should_delete));
        Assertions.assertEquals(classificationResult.getFilesToDelete().size(), 1);
        Assertions.assertEquals(classificationResult.getFilesToCompress().size(), 0);
        Assertions.assertTrue(classificationResult.getFilesToDelete().contains(should_delete.getFile()));
    }
    @Test
    public void shouldCompressFile() {
        final var should_compress = new ScoredFile(createFile(), FileClassifier.COMPRESS_THRESHOLD+1);
        ClassificationResult classificationResult = FileClassifier.classifyScoredFiles(List.of(should_compress));
        Assertions.assertEquals(classificationResult.getFilesToCompress().size(), 1);
        Assertions.assertEquals(classificationResult.getFilesToDelete().size(), 0);
        Assertions.assertTrue(classificationResult.getFilesToCompress().contains(should_compress.getFile()));
    }
    @Test
    public void shouldIgnoreFile() {
        final var should_ignore = new ScoredFile(createFile(), FileClassifier.COMPRESS_THRESHOLD);
        ClassificationResult classificationResult = FileClassifier.classifyScoredFiles(List.of(should_ignore));
        Assertions.assertEquals(classificationResult.getFilesToCompress().size(), 0);
        Assertions.assertEquals(classificationResult.getFilesToDelete().size(), 0);
    }
    private File createFile() {
        return new File("id", "aFile", 10, Instant.now(), Instant.now(), ".txt", null, false);
    }


}
