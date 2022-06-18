package my.md.wikimd.repositories;

import my.md.wikimd.models.Image;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ImageRepository extends JpaRepository<Image, UUID> {

    @Query(value="SELECT * FROM tb_image WHERE id IN (:ids)", nativeQuery = true)
    List<Image> getImagesByIds(@Param("ids") List<UUID> ids);

}
