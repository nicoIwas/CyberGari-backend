package main.analyzer.judge;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.time.temporal.ChronoUnit;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class JudgeUtils {
    public static long daysSince(final Instant date) {
        return Math.abs(ChronoUnit.DAYS.between(date, Instant.now()));
    }

    public static double bytesToMB(final double bytes) {
        return Math.floor(bytes / 1048576);
    }
}
