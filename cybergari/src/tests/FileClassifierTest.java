package tests;

import main.analyzer.ScoredFile;
import main.analyzer.classifier.ClassificationResult;
import main.analyzer.classifier.FileClassifier;
import main.file.File;
import main.file.Folder;
import org.junit.Assert;
import org.junit.Test;
import java.util.List;

import java.time.Instant;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class FileClassifierTest {

    @Test
    public void shouldDeleteFile() {
        final var should_delete = new ScoredFile(createFile(), FileClassifier.DELETE_THRESHOLD+1);
        ClassificationResult classificationResult = FileClassifier.classifyScoredFiles(List.of(should_delete));
        assertEquals(classificationResult.getFilesToDelete().size(), 1);
        assertEquals(classificationResult.getFilesToCompress().size(), 0);
        Assert.assertTrue(classificationResult.getFilesToDelete().contains(should_delete.getFile()));
    }
    @Test
    public void shouldCompressFile() {
        final var should_compress = new ScoredFile(createFile(), FileClassifier.COMPRESS_THRESHOLD+1);
        ClassificationResult classificationResult = FileClassifier.classifyScoredFiles(List.of(should_compress));
        assertEquals(classificationResult.getFilesToCompress().size(), 1);
        assertEquals(classificationResult.getFilesToDelete().size(), 0);
        Assert.assertTrue(classificationResult.getFilesToCompress().contains(should_compress.getFile()));
    }
    @Test
    public void shouldIgnoreFile() {
        final var should_ignore = new ScoredFile(createFile(), FileClassifier.COMPRESS_THRESHOLD);
        ClassificationResult classificationResult = FileClassifier.classifyScoredFiles(List.of(should_ignore));
        assertEquals(classificationResult.getFilesToCompress().size(), 0);
        assertEquals(classificationResult.getFilesToDelete().size(), 0);
    }
    private File createFile() {
        return new File("id", "aFile", 10, Instant.now(), Instant.now(), ".txt", null, false);
    }


}
