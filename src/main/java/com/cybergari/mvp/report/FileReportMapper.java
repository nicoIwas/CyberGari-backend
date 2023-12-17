package com.cybergari.mvp.report;


import com.cybergari.mvp.file.File;
import com.cybergari.mvp.report.vo.FileReport;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface FileReportMapper {
    FileReport mapToReport(File file);
}
