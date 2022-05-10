package my.md.wikimd.repositories;

import my.md.wikimd.models.Tag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface TagRepository extends JpaRepository<Tag, UUID> {

    @Query(value="SELECT t FROM Tag t WHERE t.name = :name")
    Tag getTagByName(@Param("name") String name);

}
