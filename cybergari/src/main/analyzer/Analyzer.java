package main.analyzer;

import main.analyzer.judge.absolute.AbsoluteJudge;
import main.analyzer.judge.score.ScoreJudge;
import main.files.File;
import main.files.Folder;
import main.files.Storage;

import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

public class Analyzer {
    private final List<AbsoluteJudge> absoluteJudges;
    private final List<ScoreJudge> scoreJudges;

    public Analyzer(final List<AbsoluteJudge> absoluteJudges, final List<ScoreJudge> scoreJudges) {
        this.absoluteJudges = absoluteJudges;
        this.scoreJudges = scoreJudges;
    }

    public List<ScoredFile> analyze(final Folder root) {
        final List<ScoredFile> result = new LinkedList<>();
        final var toAnalyse = getAnalysableFiles(root);

        for(final var file : toAnalyse) {
            final var score = getFileScore(file);
            result.add(new ScoredFile(file, score));
        }

        return result;
    }

    private List<File> getAnalysableFiles(final Folder root) {
        final List<File> result = new LinkedList<>();
        final Stack<Folder> toExplore = new Stack<>();
        toExplore.push(root);

        while(!toExplore.empty()) {
            for(final Storage storage : toExplore.pop().getStorages()) {
                if(storage instanceof Folder)
                    toExplore.add((Folder) storage);

                if(storage instanceof File && !shouldIgnoreFile((File) storage))
                    result.add((File) storage);
            }
        }

        return result;
    }

    private boolean shouldIgnoreFile(final File file) {
        for(final var judge : absoluteJudges)
            if(judge.judgeFile(file)) return true;

        return false;
    }

    private int getFileScore(final File file) {
        int result = 0;

        for(final var judge : scoreJudges)
            result += judge.judgeFile(file);

        return result;
    }
}
