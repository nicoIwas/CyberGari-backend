package main.filemanager.local;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import main.tag.vos.Tag;

import java.io.Serializable;
import java.util.List;

@Getter
@RequiredArgsConstructor
public class LocalFileMetaData implements Serializable {
    private final String fileId;
    private final List<Tag> tags;
    private final boolean compressed;
}
