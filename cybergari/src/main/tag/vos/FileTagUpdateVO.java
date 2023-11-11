package main.tag.vos;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Getter
@RequiredArgsConstructor
public class FileTagUpdateVO {
    private final String fileId;
    private final List<String> tagNames;
}
