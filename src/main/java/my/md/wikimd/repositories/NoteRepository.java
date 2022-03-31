package my.md.wikimd.repositories;

import my.md.wikimd.models.Note;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface NoteRepository extends JpaRepository<Note, UUID> {

    @Query(value = "SELECT * FROM tb_note ORDER BY created_at DESC LIMIT 5", nativeQuery = true)
    List<Note> getLatestFive();

}
