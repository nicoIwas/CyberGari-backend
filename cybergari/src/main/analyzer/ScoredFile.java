package main.analyzer;

import main.files.File;

public class ScoredFile {
    private final File file;
    private final int score;
    // We could add the compression level / method to be used here

    public ScoredFile(final File file, final int score) {
        this.score = score;
        this.file = file;
    }

    public int getScore() {
        return score;
    }

    public File getFile() {
        return file;
    }
}
