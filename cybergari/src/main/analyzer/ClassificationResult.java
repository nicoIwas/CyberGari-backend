package main.analyzer;

import main.file.File;

import java.util.List;

public record ClassificationResult(List<File> filesToCompress, List<File> filesToDelete) {
}
