package main.file;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.Instant;

@RequiredArgsConstructor
@Getter
public class File extends Storage {
    private final String id;
    private final double size;
    private final Instant createdTime;
    private final Instant modifiedTime;
    private final String extension;

    @Override
    public double getSize() {
        return size;
    }
}
