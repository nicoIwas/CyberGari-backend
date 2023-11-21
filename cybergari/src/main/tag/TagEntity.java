package main.tag;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import java.time.Instant;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TagEntity{
    @EmbeddedId
    private TagEntityId tagEntityId;
    private String color;
    private int priority;

    public TagEntity(final String userId, final String name, final String color, final int priority){
        this.tagEntityId = new TagEntityId(userId, name);
        this.color = color;
        this.priority = priority;
    }
}
