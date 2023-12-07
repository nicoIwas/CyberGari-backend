package main.filemanager.local;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import main.tag.vos.Tag;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
public class LocalFileMetaData implements Serializable {
    private final String fileId;
    private final List<Tag> tags;
    private boolean compressed;
    private String extension;

}
