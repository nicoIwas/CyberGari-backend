package com.cybergari.mvp.tag.vos;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


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
