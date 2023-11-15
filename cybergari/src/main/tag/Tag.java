package main.tag;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.io.Serializable;

@RequiredArgsConstructor
@Getter
public class Tag implements Serializable {
    private final String name;
    private final int tagPriority;
    private final String tagColor;
}
