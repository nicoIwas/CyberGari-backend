package main.tag;

import lombok.RequiredArgsConstructor;
import main.file.File;
import main.filemanager.FileManager;
import main.tag.vos.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class TagService {
    @Autowired
    TagEntityMapper tagEntityMapper;
    @Autowired
    TagRepository tagRepository;
    @Autowired
    FileManager fileManager;

    public void updateFileTags(final List<FileTagUpdateVO> request, final String userId) {
        final var validFiles = fileManager.getAllFiles().stream()
                .collect(Collectors.toMap(File::getId, Function.identity()));
        final var validTags = findAll(userId);

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

    public void save(final Tag tag, final String userId){
        final var tagToSave = new TagEntity(userId, tag.getName(), tag.getTagColor(), tag.getTagPriority());
        tagRepository.save(tagToSave);
    }

    public void delete(final String tagName, final String userId){
        final var tagToDelete = new TagEntityId(userId, tagName);
        tagRepository.deleteById(tagToDelete);
    }

    public Set<Tag> findAll(final String userId){
        return tagRepository.findByUserId(userId).stream()
                .map(tag -> tagEntityMapper.toDomain(tag)).collect(Collectors.toSet());
    }

}
