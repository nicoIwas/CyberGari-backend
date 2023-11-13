package main.report.vo;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Getter
@RequiredArgsConstructor
public class ReportConfirmation {
    private final List<String> filesToCompress;
    private final List<String> filesToDelete;
    private final String reportId;
    private final String userId;
}
