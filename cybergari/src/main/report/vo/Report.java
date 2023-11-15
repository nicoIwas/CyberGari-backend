package main.report.vo;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Getter
@RequiredArgsConstructor
public class Report {
    private final String id;
    private final List<FileReport> filesToCompress;
    private final List<FileReport> filesToDelete;
    private final int fileCount;
}
