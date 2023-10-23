package main.analyzer;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import main.files.File;

@Getter
@RequiredArgsConstructor
public class ScoredFile {
    private final File file;
    private final int score;
    // We could add the compression level / method to be used here
}
