package my.md.wikimd.repositories;

import my.md.wikimd.models.Note;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface NoteRepository extends JpaRepository<Note, UUID> {

    @Query(value = "SELECT * FROM tb_note WHERE title <> '' AND content <> '' ORDER BY created_at DESC LIMIT 4", nativeQuery = true)
    List<Note> getLatestFive();

    @Query(value="SELECT n FROM Note n WHERE n.createdBy = :createdById AND n.title <> '' AND n.content <> '' ORDER BY created_at DESC")
    List<Note> getNotesCreatedBy(@Param("createdById") UUID createdById);

    @Query(value="SELECT * FROM tb_note WHERE title <> '' AND content <> '' AND UPPER(title) LIKE UPPER(:filter) ORDER BY id OFFSET :page ROWS FETCH NEXT :itemsPerPage ROWS ONLY", nativeQuery = true)
    List<Note> getNotesByPage(@Param("page") int page, @Param("itemsPerPage") int itemsPerPage, @Param("filter") String filter);

}
