package com.cybergari.mvp;

import com.cybergari.mvp.fixtures.TagFixture;
import com.cybergari.mvp.tag.TagRepository;
import com.cybergari.mvp.tag.TagService;
import com.cybergari.mvp.tag.vos.TagEntity;
import com.cybergari.mvp.tag.vos.TagEntityMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


import java.util.Set;

@SpringBootTest
public class TagServiceTest {

    @Autowired
    TagService tagService;
    @Autowired
    TagRepository tagRepository;

    @Autowired
    TagEntityMapper tagEntityMapper;

    private static final String USER_ID = "FAKEID123";

//    @AfterEach
//    void init() {
//        tagRepository.deleteAll();
//    }

    @Test
    public void givenAValidTagEntityAndUserId_WhenSavingNewTag_ShouldPersistOnDatabase() {
        tagService.save(TagFixture.load(), USER_ID);
        final Set<TagEntity> expectedTag = tagRepository.findByUserId(USER_ID);
        Assertions.assertEquals(1, expectedTag.size());
    }

    @Test
    public void givenAValidTagNameAndUserId_WhenDeletingExistingTag_ShouldRemoveFromDatabase() {

    }


}
