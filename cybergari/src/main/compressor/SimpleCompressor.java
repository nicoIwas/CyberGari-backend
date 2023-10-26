package main.compressor;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

public class SimpleCompressor implements Compressor {
    @Override
    public void compress(final String filePath) throws IOException {
        // compressing
        final var tempFilePath = filePath + "temp";

        FileOutputStream fos = new FileOutputStream(tempFilePath);
        ZipOutputStream zipOut = new ZipOutputStream(fos);

        File fileToZip = new File(filePath);
        FileInputStream fis = new FileInputStream(fileToZip);
        ZipEntry zipEntry = new ZipEntry(fileToZip.getName());
        zipOut.putNextEntry(zipEntry);

        byte[] bytes = new byte[1024];
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

        byte[] buffer = new byte[1024];
        ZipInputStream zis = new ZipInputStream(new FileInputStream(filePath));
        zis.getNextEntry();

        // write file content
        FileOutputStream fos = new FileOutputStream(tempFilePath);
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
