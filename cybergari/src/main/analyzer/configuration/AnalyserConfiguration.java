package main.analyzer.configuration;

public record AnalyserConfiguration(
        boolean fileExtension,
        boolean fileSize,
        boolean tags,
        boolean numVisualizations,
        boolean lastSeen
) {
}
