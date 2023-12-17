package com.cybergari.mvp.fixtures;


import com.cybergari.mvp.tag.vos.Tag;

public class TagFixture {

    public static Tag load() {
        return new Tag(
                "test_tag",
                5,
                "#FFFFFFF"
        );
    }
}
