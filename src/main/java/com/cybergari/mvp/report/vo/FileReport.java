package com.cybergari.mvp.report.vo;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.Instant;

@Getter
@RequiredArgsConstructor
public class FileReport {
    private final String id;
    private final String name;
    private final double size;
    private final Instant modifiedTime;
}
