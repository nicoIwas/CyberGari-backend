package main.file;

import main.controller.response.FileResponse;
import main.filemanager.FileManager;
import main.filemanager.local.LocalFileExecutor;
import main.filemanager.local.LocalFileMetaData;
import main.filemanager.local.LocalFileMetaDataManager;
import main.filemanager.local.LocalFileSettings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class FileService {

    @Autowired
    FileManager fileManager;
    private final LocalFileExecutor executor = new LocalFileExecutor(LocalFileSettings.FOLDER);
    final LocalFileMetaDataManager localFileMetaDataManager = new LocalFileMetaDataManager(LocalFileSettings.FOLDER);

    public List<FileResponse> getAllFiles() {
        final Collection<File> files = fileManager.getAllFiles();

        return files.stream().map(file -> {
            var filePath = executor.getFilePathFromId(file.getId());
            var fileInfo = new java.io.File(filePath);
            return new FileResponse(
                    file.getId(),
                    file.getName(),
                    filePath,
                    file.getModifiedTime(),
                    fileInfo.isFile() ? "File" : "Directory"
            );
        }).toList();
    }

    public List<FileResponse> getCompressedFiles() {
        final var metadata = localFileMetaDataManager.getFilesMetadata();

        if (metadata.isEmpty()) {
            return Collections.emptyList();
        }

        final List<String> filteredData = new ArrayList<>();
        final List<FileResponse> compressedFiles = new ArrayList<>();

        for (Map.Entry<String, LocalFileMetaData> entry : metadata.entrySet()) {
            if (entry.getValue().isCompressed()) {
                filteredData.add(entry.getKey());
            }
        }
        filteredData.forEach(
                fileId -> {
                    var filePath = executor.getFilePathFromId(fileId);
                    var file = new java.io.File(filePath);
                    compressedFiles.add(
                        new FileResponse(
                            fileId,
                            file.getName(),
                            filePath,
                            Instant.ofEpochMilli(file.lastModified()),
                            file.isFile() ? "File" : "Directory"
                        )
                    );
                }
        );
        return compressedFiles;
    }

    public List<String> uncompressFiles(List<String> filesToUncompress) {
        return filesToUncompress.stream().filter(file -> !fileManager.uncompressFile(file))
                   .collect(Collectors.toList());
    }
}