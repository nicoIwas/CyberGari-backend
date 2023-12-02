package tests;

import main.file.File;
import main.file.Folder;
import org.junit.Test;

import java.time.Instant;

import static org.junit.Assert.assertEquals;

public class FolderTest {
    @Test
    public void shouldCalculateFolderSize() {
        // given
        final var file1 = createFileWithSize(10.3);
        final var file2 = createFileWithSize(5.4);
        final var file3 = createFileWithSize(3.7);

        final var innerFolder = new Folder();
        final var outerFolder = new Folder();

        innerFolder.addStorage(file1, file2);
        outerFolder.addStorage(file3, innerFolder);

        // when
        final var total = outerFolder.getSize();

        // then
        final var expectedTotal = file1.getSize() + file2.getSize() + file3.getSize();
        assertEquals(expectedTotal, total, 0.01);
    }

    private File createFileWithSize(final double size) {
        return new File("id", "aFile", size, Instant.now(), Instant.now(), Instant.now(), ".txt", null, false);
    }
}
