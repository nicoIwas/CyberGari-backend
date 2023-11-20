package main.report;

import main.file.File;
import main.report.vo.FileReport;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface FileReportMapper {
    FileReport mapToReport(File file);
}
