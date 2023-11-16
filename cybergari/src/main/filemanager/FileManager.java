package main.filemanager;

import main.file.File;
import main.file.Folder;

import java.util.Collection;


public interface FileManager {
    Collection<File> getAllFiles();
    Folder getFileStructure();
    boolean compressFile(String fileId);
    boolean uncompressFile(String fileId);
    boolean deleteFile(String fileId);
    void persistMetadata(Collection<File> toUpdate);
}
