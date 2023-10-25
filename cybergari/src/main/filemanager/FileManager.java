package main.filemanager;

import main.files.FileStructureMetadata;

public interface FileManager {
    FileStructureMetadata readFileStructureFromRoot();
}
