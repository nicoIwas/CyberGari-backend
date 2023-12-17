package com.cybergari.mvp.report.vo;

import java.util.List;

public record ReportConfirmation(List<String> filesToCompress, List<String> filesToDelete, String reportId,
                                 String userId) {
}
