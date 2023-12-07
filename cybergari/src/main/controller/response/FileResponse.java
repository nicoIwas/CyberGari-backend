package main.controller.response;

import lombok.Data;

import java.time.Instant;

@Data
public class FileResponse {

    private final String id;
    private final String name;
    private final String filePath;
    private final Instant modifiedTime;
}
