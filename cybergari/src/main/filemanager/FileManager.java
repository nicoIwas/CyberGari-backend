package main.filemanager;

import main.files.File;
import main.files.Folder;

import java.util.Collection;

public interface FileManager {
    Collection<File> getAllFiles();
    Folder getFileStructure();
    void compressFile(String fileId);
    void uncompressFile(String fileId);
}
