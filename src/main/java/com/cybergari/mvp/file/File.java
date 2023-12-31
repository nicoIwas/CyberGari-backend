package com.cybergari.mvp.file;

import com.cybergari.mvp.tag.vos.Priority;
import com.cybergari.mvp.tag.vos.Tag;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
;
import java.time.Instant;
import java.util.List;

@AllArgsConstructor
@Getter
public class File extends Storage {
    private final String id;
    private final String name;
    private final double size;
    private final Instant createdTime;
    private final Instant modifiedTime;
    private final String extension;
    private Priority priority;
    @Setter
    private boolean compressed;

    @Override
    public double getSize() {
        return size;
    }

    @Override
    public int getFileCount() {
        return 1;
    }

    public void setTags(final List<Tag> tags) {
        this.priority = new Priority(tags);
    }
}
