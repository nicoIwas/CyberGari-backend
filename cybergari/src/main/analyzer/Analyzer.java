package main.analyzer;

import main.analyzer.judge.JudgePackage;
import main.analyzer.judge.absolute.AbsoluteJudge;
import main.analyzer.judge.multiplier.MultiplierJudge;
import main.analyzer.judge.score.ScoreJudge;
import main.file.File;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class Analyzer {
    private final List<AbsoluteJudge> absoluteJudges;
    private final List<ScoreJudge> scoreJudges;
    private final List<MultiplierJudge> multiplierJudges;

    public Analyzer(final JudgePackage judgePackage) {
        this.absoluteJudges = judgePackage.getAbsoluteJudges();
        this.scoreJudges = judgePackage.getScoreJudges();
        this.multiplierJudges = judgePackage.getMultiplierJudges();
    }

    public List<ScoredFile> analyze(final Collection<File> files) {
        final List<ScoredFile> result = new LinkedList<>();
        final var toAnalyse = getAnalysableFiles(files);

        for(final var file : toAnalyse) {
            final var score = (int)(getFileScore(file) * getFileMultiplier(file));
            result.add(new ScoredFile(file, score));
        }

        return result;
    }

    private Collection<File> getAnalysableFiles(final Collection<File> files) {
        return files.stream().filter(file -> !shouldIgnoreFile(file)).collect(Collectors.toSet());
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

    private float getFileMultiplier(final File file){
        float result = 1;

        for(final var judge : multiplierJudges)
            result *= judge.judgeFile(file);

        return result;
    }
}
