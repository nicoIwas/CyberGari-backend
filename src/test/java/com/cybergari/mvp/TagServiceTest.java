package com.cybergari.mvp;

import com.cybergari.mvp.fixtures.TagEntityFixture;
import com.cybergari.mvp.fixtures.TagFixture;
import com.cybergari.mvp.tag.TagRepository;
import com.cybergari.mvp.tag.TagService;
import com.cybergari.mvp.tag.vos.Tag;
import com.cybergari.mvp.tag.vos.TagEntity;
import com.cybergari.mvp.tag.vos.TagEntityMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
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

    @BeforeEach
    void init() {
        tagRepository.deleteAll();
    }

    @Test
    public void givenAValidTagEntityAndUserId_WhenSavingNewTag_ShouldPersistOnDatabase() {
        tagService.save(TagFixture.load(), USER_ID);
        final Set<TagEntity> expectedTag = tagRepository.findByUserId(USER_ID);
        Assertions.assertEquals(1, expectedTag.size());
    }

    @Test
    public void givenAValidTagNameAndUserId_WhenDeletingExistingTag_ShouldRemoveFromDatabase() {
        tagRepository.save(TagEntityFixture.load());
        tagService.delete("name", "ID");
        final List<TagEntity> tags = tagRepository.findAll();
        Assertions.assertEquals(0, tags.size());
    }

    @Test
    public void givenAValidUserId_WhenFindingAll_ShouldReturnTheCorrectTags() {
        givenDifferentTagsFromTheSameUser();
        final Set<Tag> tags = tagService.findAll("ID");
        Assertions.assertEquals(2, tags.size());
    }

    private void givenDifferentTagsFromTheSameUser() {
        final var tagOne = TagEntityFixture.load();
        final var tagTwo = TagEntityFixture.load();
        tagOne.getTagEntityId().setName("Tag One");
        tagTwo.getTagEntityId().setName("Tag Two");

        tagRepository.saveAll(List.of(tagOne, tagTwo));
    }

}
