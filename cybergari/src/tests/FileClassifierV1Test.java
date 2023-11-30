package tests;

import main.analyzer.v1.ScoredFile;
import main.analyzer.ClassificationResult;
import main.analyzer.v1.FileClassifierV1;
import main.file.File;
import org.junit.Assert;
import org.junit.Test;
import java.util.List;

import java.time.Instant;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class FileClassifierV1Test {

    @Test
    public void shouldDeleteFile() {
        final var should_delete = new ScoredFile(createFile(), FileClassifierV1.DELETE_THRESHOLD+1);
        ClassificationResult classificationResult = FileClassifierV1.classifyScoredFiles(List.of(should_delete));
        assertEquals(classificationResult.filesToDelete().size(), 1);
        assertEquals(classificationResult.filesToCompress().size(), 0);
        Assert.assertTrue(classificationResult.filesToDelete().contains(should_delete.getFile()));
    }
    @Test
    public void shouldCompressFile() {
        final var should_compress = new ScoredFile(createFile(), FileClassifierV1.COMPRESS_THRESHOLD+1);
        ClassificationResult classificationResult = FileClassifierV1.classifyScoredFiles(List.of(should_compress));
        assertEquals(classificationResult.filesToCompress().size(), 1);
        assertEquals(classificationResult.filesToDelete().size(), 0);
        Assert.assertTrue(classificationResult.filesToCompress().contains(should_compress.getFile()));
    }
    @Test
    public void shouldIgnoreFile() {
        final var should_ignore = new ScoredFile(createFile(), FileClassifierV1.COMPRESS_THRESHOLD);
        ClassificationResult classificationResult = FileClassifierV1.classifyScoredFiles(List.of(should_ignore));
        assertEquals(classificationResult.filesToCompress().size(), 0);
        assertEquals(classificationResult.filesToDelete().size(), 0);
    }
    private File createFile() {
        return new File("id", "aFile", 10, Instant.now(), Instant.now(), ".txt", null, false);
    }


}
