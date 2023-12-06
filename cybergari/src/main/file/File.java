package main.file;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import main.tag.vos.Priority;
import main.tag.vos.Tag;

import java.time.Instant;
import java.util.List;
import java.util.Map;

@AllArgsConstructor
@Getter
public class File extends Storage {
    private final String id;
    private final String name;
    private final double size;
    private final Instant createdTime;
    private final Instant lastSeen;
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

    public Integer getPriority() {
        if(priority == null || priority.getPriority() == null) {
            return null;
        }

        return  priority.getPriority();
    }

    public Map<String, Tag> getTags() {
        if(priority == null) {
            return Map.of();
        }

        return priority.getTags();
    }
}
