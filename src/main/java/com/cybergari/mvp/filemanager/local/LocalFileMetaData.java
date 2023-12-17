package com.cybergari.mvp.filemanager.local;

import com.cybergari.mvp.tag.vos.Tag;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;


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
