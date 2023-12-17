package com.cybergari.mvp.file;


import com.cybergari.mvp.controller.response.FileResponse;
import com.cybergari.mvp.filemanager.FileManager;
import com.cybergari.mvp.filemanager.local.LocalFileExecutor;
import com.cybergari.mvp.filemanager.local.LocalFileMetaData;
import com.cybergari.mvp.filemanager.local.LocalFileMetaDataManager;
import com.cybergari.mvp.filemanager.local.LocalFileSettings;
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
                    final var filePath = executor.getFilePathFromId(fileId);
                    final var file = new java.io.File(filePath);
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