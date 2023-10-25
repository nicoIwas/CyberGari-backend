package main.filemanager.local;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import main.filemanager.FileManager;
import main.files.FileStructureMetadata;

@RequiredArgsConstructor
public class LocalFileManager implements FileManager {
    private final String rootPath;
    @Getter
    private FileStructureMetadata metadata;

    @Override
    public FileStructureMetadata readFileStructureFromRoot() {
        final var root = LocalFileUtils.readFileStructureFromRoot(rootPath);
        this.metadata = new FileStructureMetadata(root);
        return metadata;
    }
}
