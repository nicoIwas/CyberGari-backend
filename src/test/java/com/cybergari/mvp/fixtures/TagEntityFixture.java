package com.cybergari.mvp.fixtures;

import com.cybergari.mvp.tag.vos.TagEntity;
import com.cybergari.mvp.tag.vos.TagEntityId;

public class TagEntityFixture {

    public static TagEntity load() {
        return new TagEntity(
          new TagEntityId("ID", "name"),
          "#FFFFFF",
          5
        );
    }
}
