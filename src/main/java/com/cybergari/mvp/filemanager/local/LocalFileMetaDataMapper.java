package com.cybergari.mvp.filemanager.local;



import com.cybergari.mvp.file.File;

import java.util.ArrayList;

public class LocalFileMetaDataMapper {
    public LocalFileMetaData toMetadata(final File f) {
        return new LocalFileMetaData(
                f.getId(),
                new ArrayList<>(f.getPriority().getTags().values()),
                f.isCompressed(),
                f.getExtension()
        );
    }
}
