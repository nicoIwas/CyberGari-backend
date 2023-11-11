package main.tag;

import lombok.Getter;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Getter
public class Priority {
    private final Map<String, Integer> tags;
    private Integer priority;

    public Priority() {
        this.tags = new HashMap<>();
        this.priority = null;
    }

    public Priority(final List<Tag> tags) {
        this.tags = tags.stream().collect(Collectors.toMap(Tag::getName, Tag::getTagPriority));
        calculatePriority();
    }

    private void calculatePriority() {
        this.priority = tags.values().stream().min(Integer::compareTo).orElse(null);
    }
}
