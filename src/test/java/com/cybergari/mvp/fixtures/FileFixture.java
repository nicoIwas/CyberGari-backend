package com.cybergari.mvp.fixtures;

import com.cybergari.mvp.file.File;
import com.cybergari.mvp.tag.vos.Priority;

import java.time.Instant;

public class FileFixture {

    public static File load() {
        return new File(
                "ID",
                "name",
                2.0,
                Instant.now(),
                Instant.now(),
                "pdf",
                new Priority(),
                false
        );
    }
}
