package main.filemanager;

import main.file.File;
import main.file.Folder;

import java.util.Collection;

public interface FileManager {
    Collection<File> getAllFiles();
    Folder getFileStructure();
    void compressFile(String fileId);
    void uncompressFile(String fileId);
    void persistMetadata(Collection<File> toUpdate);
}
