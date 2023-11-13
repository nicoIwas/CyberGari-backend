package main.filemanager.local;

import main.compressor.Compressor;
import main.filemanager.FileManager;
import main.file.File;
import main.file.Folder;

import java.util.Collection;

public class LocalFileManager implements FileManager {
    private final LocalFileReader reader;
    private final Compressor compressor;

    public LocalFileManager(final Compressor compressor, final String rootPath) {
        this.reader = new LocalFileReader(rootPath);
        this.compressor = compressor;
    }

    @Override
    public Collection<File> getAllFiles() {
        return reader.getAllFilesMetadata();
    }

    @Override
    public Folder getFileStructure() {
        return reader.getFileStructure();
    }

    @Override
    public boolean compressFile(final String fileId) {
        final var filePath = reader.getFilePathFromId(fileId);

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
        final var filePath = reader.getFilePathFromId(fileId);

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
        // TODO: implement
        return true;
    }

    @Override
    public void persistMetadata(final Collection<File> toUpdate) {
        reader.persistMetadata(toUpdate);
    }
}
