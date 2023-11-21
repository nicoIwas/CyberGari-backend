package main.tag;

import main.tag.vos.TagEntity;
import main.tag.vos.TagEntityId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface TagRepository extends JpaRepository<TagEntity, TagEntityId> {

    @Query("""
        FROM TagEntity te
        WHERE te.tagEntityId.userId = :userId
    """)
    Set<TagEntity> findByUserId(final String userId);

}
