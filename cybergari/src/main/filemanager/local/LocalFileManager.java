package main.filemanager.local;

import main.filemanager.FileManager;
import main.files.File;
import main.files.Folder;

import java.util.Collection;

public class LocalFileManager implements FileManager {
    private final LocalFileReader reader;

    public LocalFileManager(final String rootPath) {
        this.reader = new LocalFileReader(rootPath);
    }

    @Override
    public Collection<File> getAllFiles() {
        return reader.getAllFilesMetadata();
    }

    @Override
    public Folder getFileStructure() {
        return reader.getFileStructure();
    }
}
