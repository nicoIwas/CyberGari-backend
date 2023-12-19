package com.cybergari.mvp.fixtures;

import com.cybergari.mvp.report.vo.ReportConfirmation;

import java.util.List;

public class ReportConfirmationFixture {

    public static ReportConfirmation load() {
        return new ReportConfirmation(
                List.of("FILE_1"),
                List.of("FILE_2"),
                "REPORT_ID",
                "USER_ID"
        );
    }
}
