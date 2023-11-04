package main.filemanager.local;

import main.file.File;
import main.file.tag.Tag;

import java.util.stream.Collectors;

public class LocalFileMetaDataMapper {
    public LocalFileMetaData toMetadata(final File f) {
        return new LocalFileMetaData(
                f.getId(),
                f.getPriority().getTags().entrySet().stream()
                        .map(entry -> new Tag(entry.getKey(), entry.getValue())).collect(Collectors.toList()),
                f.isCompressed()
        );
    }
}
