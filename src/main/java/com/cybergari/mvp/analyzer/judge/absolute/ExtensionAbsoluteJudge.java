package com.cybergari.mvp.analyzer.judge.absolute;

import com.cybergari.mvp.file.File;
import lombok.RequiredArgsConstructor;


import java.util.Set;

@RequiredArgsConstructor
public class ExtensionAbsoluteJudge implements AbsoluteJudge {
    private final Set<String> extensionsToIgnore;

    @Override
    public boolean judgeFile(final File toJudge) {
        return extensionsToIgnore.contains(toJudge.getExtension());
    }
}
