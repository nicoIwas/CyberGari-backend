package main.file;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import main.tag.Priority;

import java.time.Instant;

@AllArgsConstructor
@Getter
public class File extends Storage {
    private final String id;
    private final double size;
    private final Instant createdTime;
    private final Instant modifiedTime;
    private final String extension;
    private final Priority priority;
    @Setter
    private boolean compressed;

    @Override
    public double getSize() {
        return size;
    }
}
