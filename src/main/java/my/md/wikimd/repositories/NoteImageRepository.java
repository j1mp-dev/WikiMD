package my.md.wikimd.repositories;

import my.md.wikimd.models.Note;
import my.md.wikimd.models.NoteImage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface NoteImageRepository extends JpaRepository<NoteImage, UUID> {

}
