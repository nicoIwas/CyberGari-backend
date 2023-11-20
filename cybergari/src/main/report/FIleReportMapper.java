package main.report;

import main.file.File;
import main.report.vo.FileReport;
import main.user.UserData;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.springframework.stereotype.Component;

@Mapper(componentModel = "spring")
public interface FIleReportMapper {
    FileReport mapToReport(File file);
}
