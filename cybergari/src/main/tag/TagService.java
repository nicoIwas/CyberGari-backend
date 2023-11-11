package main.tag;

import lombok.RequiredArgsConstructor;
import main.file.File;
import main.filemanager.FileManager;
import main.tag.vos.FileTagUpdateVO;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class TagService {
    @Autowired
    private final TagRepository tagRepository;
    @Autowired
    private final FileManager fileManager;

    public void updateFileTags(final List<FileTagUpdateVO> request, final String userId) {
        final var validFiles = fileManager.getAllFiles().stream()
                .collect(Collectors.toMap(File::getId, Function.identity()));
        final var validTags = tagRepository.findByUserId(userId);

        final var filesToUpdate = request.stream()
                .filter(f -> validFiles.containsKey(f.getFileId()))
                .map(toUpdate -> {
                    final var finalTags = validTags.stream()
                            .filter(tag -> toUpdate.getTagNames().contains(tag.getName()))
                            .collect(Collectors.toList());

                    final var currentFile = validFiles.get(toUpdate.getFileId());
                    currentFile.setTags(finalTags);

                    return currentFile;
                })
                .collect(Collectors.toSet());

        fileManager.persistMetadata(filesToUpdate);
    }
}
