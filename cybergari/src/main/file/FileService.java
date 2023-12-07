package main.file;

import main.controller.response.FileResponse;
import main.filemanager.FileManager;
import main.filemanager.local.LocalFileExecutor;
import main.filemanager.local.LocalFileSettings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

@Service
public class FileService {

    @Autowired
    FileManager fileManager;
    private final LocalFileExecutor executor = new LocalFileExecutor(LocalFileSettings.FOLDER);

    public List<FileResponse> getAllFiles() {
        final Collection<File> files = fileManager.getAllFiles();

        return files.stream().map(file -> {
            return new FileResponse(
                    file.getId(),
                    file.getName(),
                    executor.getFilePathFromId(file.getId()),
                    file.getModifiedTime()
            );
        }).toList();
    }
}
