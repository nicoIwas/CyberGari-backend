package main.compressor;

import lombok.extern.slf4j.Slf4j;
import main.filemanager.local.LocalFileMetaData;
import main.filemanager.local.LocalFileMetaDataManager;
import main.filemanager.local.LocalFileSettings;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;
@Component
@Slf4j
public class SimpleCompressor implements Compressor {

    final LocalFileMetaDataManager localFileMetaDataManager = new LocalFileMetaDataManager(LocalFileSettings.FOLDER);
    @Override
    public void compress(final String filePath, final String fileId) throws IOException {
        // compressing
        var tempFilePath = filePath + "temp";

        final var fileToZip = new File(filePath);
        final var fis = new FileInputStream(fileToZip);

        final var fileName = extractExtension(fileToZip.getName());

        final var metadata = localFileMetaDataManager.getMetaData(fileId).orElse(new LocalFileMetaData(fileId, null));
        tempFilePath = tempFilePath.replace("temp", "").replace(fileName.get(1), "") + "zip";

        final var fos = new FileOutputStream(tempFilePath);
        final var zipOut = new ZipOutputStream(fos);

        final var zipEntry = new ZipEntry(fileName.get(0) + ".zip");
        
        zipOut.putNextEntry(zipEntry);

        final var bytes = new byte[1024];
        int length;
        while((length = fis.read(bytes)) >= 0) {
            zipOut.write(bytes, 0, length);
        }

        zipOut.close();
        fis.close();
        fos.close();

        metadata.setExtension(fileName.get(1));
        metadata.setCompressed(true);
        localFileMetaDataManager.putMetaData(metadata);
        localFileMetaDataManager.persistMetaData();


        // fixing temp files
        substituteTempFile(filePath);
    }

    @Override
    public void uncompress(final String filePath, final String fileId) throws IOException {
        // uncompressing
        final var metadata = localFileMetaDataManager.getMetaData(fileId).orElseThrow();
        final var actualPath = filePath.replace("zip", metadata.getExtension());

        final var buffer = new byte[1024];
        final var zis = new ZipInputStream(new FileInputStream(filePath));
        zis.getNextEntry();

        // write file content
        final var fos = new FileOutputStream(actualPath);
        int len;
        while ((len = zis.read(buffer)) > 0) {
            fos.write(buffer, 0, len);
        }
        fos.close();
        zis.closeEntry();
        zis.close();

        metadata.setCompressed(false);
        localFileMetaDataManager.putMetaData(metadata);
        localFileMetaDataManager.persistMetaData();

        // fixing temp files
        substituteTempFile(filePath);
    }

    private void substituteTempFile(final String actualPath) {
        final var actual = new File(actualPath);

        actual.delete();
    }

    private List<String> extractExtension(final String fileName) {
        int lastDotIndex = fileName.lastIndexOf(".");
        if (lastDotIndex != -1 && lastDotIndex < fileName.length() - 1) {
            String nameWithoutExtension = fileName.substring(0, lastDotIndex);
            String extension = fileName.substring(lastDotIndex + 1);

            return List.of(nameWithoutExtension, extension);
        } else {
            // No extension found
            return null;
        }
    }
}
