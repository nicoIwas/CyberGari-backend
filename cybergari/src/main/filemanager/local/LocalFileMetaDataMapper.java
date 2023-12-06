package main.filemanager.local;

import main.file.File;

import java.util.ArrayList;

public class LocalFileMetaDataMapper {
    public LocalFileMetaData toMetadata(final File f) {
        return new LocalFileMetaData(
                f.getId(),
                new ArrayList<>(f.getTags().values()),
                f.isCompressed()
        );
    }
}
