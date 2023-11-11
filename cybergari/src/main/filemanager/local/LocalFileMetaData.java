package main.filemanager.local;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import main.tag.Tag;

import java.util.List;

@Getter
@RequiredArgsConstructor
public class LocalFileMetaData {
    private final String fileId;
    private final List<Tag> tags;
    private final boolean compressed;
}
