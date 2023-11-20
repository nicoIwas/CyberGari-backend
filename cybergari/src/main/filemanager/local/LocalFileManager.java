package main.filemanager.local;

import main.compressor.Compressor;
import main.filemanager.FileManager;
import main.file.File;
import main.file.Folder;
import org.springframework.stereotype.Component;

import java.util.Collection;
@Component
public class LocalFileManager implements FileManager {
    private LocalFileExecutor executor;
    private final Compressor compressor;


    public LocalFileManager(final Compressor compressor) {
        this.executor = new LocalFileExecutor(LocalFileSettings.FOLDER);
        this.compressor = compressor;
    }

    @Override
    public Collection<File> getAllFiles() {
        return executor.getAllFilesMetadata();
    }

    @Override
    public Folder getFileStructure() {
        return executor.getFileStructure();
    }

    @Override
    public boolean compressFile(final String fileId) {
        final var filePath = executor.getFilePathFromId(fileId);

        try {
            compressor.compress(filePath);
        } catch (Exception e) {
            System.err.println("Error compressing file!");
            return false;
        }

        return true;
    }

    @Override
    public boolean uncompressFile(final String fileId) {
        final var filePath = executor.getFilePathFromId(fileId);

        try {
            compressor.uncompress(filePath);
        } catch (Exception e) {
            System.err.println("Error uncompressing file!");
            return false;
        }

        return true;
    }

    @Override
    public boolean deleteFile(final String fileId) {
        return executor.deleteFile(fileId);
    }

    @Override
    public void persistMetadata(final Collection<File> toUpdate) {
        executor.persistMetadata(toUpdate);
    }

    @Override
    public void refresh() {
        this.executor = new LocalFileExecutor(LocalFileSettings.FOLDER);
    }
}
