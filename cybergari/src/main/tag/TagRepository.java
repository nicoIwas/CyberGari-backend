package main.tag;

import java.util.Set;

public class TagRepository {
    public Set<Tag> findByUserId(final String userId) {
        return Set.of(
                new Tag("LOW_PRI", 10),
                new Tag("MED_PRI", 5),
                new Tag("HIGH_PRI", 0)
        );
    }
}
