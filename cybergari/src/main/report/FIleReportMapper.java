package main.report;

import main.file.File;
import main.report.vo.FileReport;

public class FIleReportMapper {
    public FileReport mapToReport(final File file) {
        return new FileReport(
                file.getId(),
                file.getName(),
                file.getSize(),
                file.getModifiedTime()
        );
    }
}
