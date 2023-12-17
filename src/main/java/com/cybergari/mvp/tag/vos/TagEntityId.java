package com.cybergari.mvp.tag.vos;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Embeddable
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TagEntityId implements Serializable {
    private String userId;
    private String name;
//    private String color;
}
