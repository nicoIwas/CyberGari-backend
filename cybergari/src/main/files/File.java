package main.files;

import java.time.Instant;

public class File extends Storage {
    private final String id;
    private final double size;
    private final Instant createdTime;
    private final Instant modifiedTime;
    private final String extension;

    public File(final String id, final double size, final Instant createdTime, final Instant modifiedTime,
                final String extension) {
        this.id = id;
        this.size = size;
        this.createdTime = createdTime;
        this.modifiedTime = modifiedTime;
        this.extension = extension;
    }

    public String getId() {
        return id;
    }

    @Override
    public double getSize() {
        return size;
    }

    public Instant getCreatedTime() {
        return createdTime;
    }

    public Instant getModifiedTime() {
        return modifiedTime;
    }

    public String getExtension() {
        return extension;
    }
}
