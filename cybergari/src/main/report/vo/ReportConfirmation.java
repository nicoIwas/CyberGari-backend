package main.report.vo;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Getter
@RequiredArgsConstructor
public record ReportConfirmation(List<String> filesToCompress, List<String> filesToDelete, String reportId,
                                 String userId) {
}
