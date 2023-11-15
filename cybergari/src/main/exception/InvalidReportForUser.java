package main.exception;

import static java.lang.String.format;

public class InvalidReportForUser extends RuntimeException {

    public InvalidReportForUser(final String reportId, final String userId) {
        super(format("Invalid report id %s for user %s", reportId, userId));
    }
}
