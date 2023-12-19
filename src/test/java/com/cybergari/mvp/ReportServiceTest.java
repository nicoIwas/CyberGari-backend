package com.cybergari.mvp;

import com.cybergari.mvp.analyzer.judge.JudgeService;
import com.cybergari.mvp.file.File;
import com.cybergari.mvp.file.Folder;
import com.cybergari.mvp.filemanager.FileManager;
import com.cybergari.mvp.fixtures.FileFixture;
import com.cybergari.mvp.fixtures.ReportConfirmationFixture;
import com.cybergari.mvp.report.ReportRepository;
import com.cybergari.mvp.report.ReportService;
import com.cybergari.mvp.report.vo.Report;
import com.cybergari.mvp.storagesizelog.StorageSizeLogRepository;
import com.cybergari.mvp.storagesizelog.StorageSizeLogService;
import com.cybergari.mvp.storagesizelog.vo.StorageSizeLogMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import java.util.Objects;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
public class ReportServiceTest {

    @InjectMocks
    ReportService reportService;
    @Mock
    FileManager fileManager;
    @SpyBean
    JudgeService judgeService;
    @SpyBean
    StorageSizeLogService storageSizeLogService;

    @MockBean
    StorageSizeLogRepository storageSizeLogRepository;
    @MockBean
    StorageSizeLogMapper storageSizeLogMapper;
    @MockBean
    ReportRepository repository;

    @BeforeEach
    void init() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void givenAValidUserId_WhenGeneratingReport_ShouldReturnACompleteReport() {
        when(fileManager.getAllFiles()).thenReturn(givenValidFileList());
        when(fileManager.getFileStructure()).thenReturn(new Folder());
        final Report report = reportService.generateReportForUser("ID");
        Assertions.assertTrue(Objects.nonNull(report));
    }

    @Test
    public void givenAValidReportConfirmation_WhenExecutingReport_ShouldReturnAnEmptyList() {
        when(fileManager.compressFile(any())).thenReturn(true);
        when(fileManager.deleteFile(any())).thenReturn(true);
        when(fileManager.getFileStructure()).thenReturn(new Folder());
        final List<String> filesThatFailed = reportService.executeReport(ReportConfirmationFixture.load());
        Assertions.assertTrue(filesThatFailed.isEmpty());
    }

    private List<File> givenValidFileList() {
        final var fileOne = FileFixture.load();
        final var fileTwo = FileFixture.load();
        final var fileThree = FileFixture.load();
        return List.of(fileOne, fileTwo, fileThree);
    }
}
