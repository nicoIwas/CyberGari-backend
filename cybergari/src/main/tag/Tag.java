package main.tag;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class Tag {
    private final String name;
    private final int tagPriority;
}
