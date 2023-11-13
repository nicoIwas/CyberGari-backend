package main.report;

import main.report.vo.Report;

import java.util.Optional;
import java.util.UUID;

public class ReportRepository {
    public void save(final Report report) {

    }

    public Optional<String> getLatestReportForUser(final String userId) {
        return Optional.of(UUID.randomUUID().toString());
    }
}
