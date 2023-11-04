package main.analyzer.judge.absolute;

import lombok.RequiredArgsConstructor;
import main.file.File;

import java.util.Set;

@RequiredArgsConstructor
public class ExtensionAbsoluteJudge implements AbsoluteJudge {
    private final Set<String> extensionsToIgnore;

    @Override
    public boolean judgeFile(final File toJudge) {
        return extensionsToIgnore.contains(toJudge.getExtension());
    }
}
