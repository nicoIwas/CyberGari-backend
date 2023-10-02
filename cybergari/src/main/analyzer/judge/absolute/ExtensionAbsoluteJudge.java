package main.analyzer.judge.absolute;

import main.files.File;

import java.util.Set;

public class ExtensionAbsoluteJudge implements AbsoluteJudge {
    private final Set<String> extensionsToIgnore;

    public ExtensionAbsoluteJudge(Set<String> extensionsToIgnore) {
        this.extensionsToIgnore = extensionsToIgnore;
    }

    @Override
    public boolean judgeFile(final File toJudge) {
        return !extensionsToIgnore.contains(toJudge.getExtension());
    }
}
