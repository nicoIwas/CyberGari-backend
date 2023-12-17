package com.cybergari.mvp.filemanager;



import com.cybergari.mvp.file.File;
import com.cybergari.mvp.file.Folder;

import java.util.Collection;


public interface FileManager {
    Collection<File> getAllFiles();
    Folder getFileStructure();
    boolean compressFile(String fileId);
    boolean uncompressFile(String fileId);
    boolean deleteFile(String fileId);
    void persistMetadata(Collection<File> toUpdate);
    void refresh();
}
