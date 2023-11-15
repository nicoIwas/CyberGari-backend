package main.compressor;

import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;
@Component
public class SimpleCompressor implements Compressor {
    @Override
    public void compress(final String filePath) throws IOException {
        // compressing
        final var tempFilePath = filePath + "temp";

        final var fos = new FileOutputStream(tempFilePath);
        final var zipOut = new ZipOutputStream(fos);

        final var fileToZip = new File(filePath);
        final var fis = new FileInputStream(fileToZip);
        final var zipEntry = new ZipEntry(fileToZip.getName());
        
        zipOut.putNextEntry(zipEntry);

        final var bytes = new byte[1024];
        int length;
        while((length = fis.read(bytes)) >= 0) {
            zipOut.write(bytes, 0, length);
        }

        zipOut.close();
        fis.close();
        fos.close();

        // fixing temp files
        substituteTempFile(tempFilePath, filePath);
    }

    @Override
    public void uncompress(final String filePath) throws IOException {
        // uncompressing
        final var tempFilePath = filePath + "temp";

        final var buffer = new byte[1024];
        final var zis = new ZipInputStream(new FileInputStream(filePath));
        zis.getNextEntry();

        // write file content
        final var fos = new FileOutputStream(tempFilePath);
        int len;
        while ((len = zis.read(buffer)) > 0) {
            fos.write(buffer, 0, len);
        }
        fos.close();
        zis.closeEntry();
        zis.close();

        // fixing temp files
        substituteTempFile(tempFilePath, filePath);
    }

    private void substituteTempFile(final String tempPath, final String actualPath) {
        final var temp = new File(tempPath);
        final var actual = new File(actualPath);

        actual.delete();
        temp.renameTo(actual);
    }
}
