package main.tag;

import java.util.Set;

public class TagRepository {
    public Set<Tag> findByUserId(final String userId) {
        return Set.of(
                new Tag("LOW_PRI", 10, "low"),
                new Tag("MED_PRI", 5, "med"),
                new Tag("HIGH_PRI", 0, "high")
        );
    }
}
