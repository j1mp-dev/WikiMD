package my.md.wikimd.models;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name="TB_NOTE_IMAGE")
public class NoteImage {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    UUID id;

    @Column(nullable = false, columnDefinition="TEXT")
    String image;

    @Column(nullable = false)
    UUID noteId;

    public NoteImage() {
    }

    public NoteImage(UUID id, String image, UUID noteId) {
        this.id = id;
        this.image = image;
        this.noteId = noteId;
    }

    public UUID getId() {
        return id;
    }

    public String getImage() {
        return image;
    }

    public UUID getNoteId() {
        return noteId;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void setNoteId(UUID noteId) {
        this.noteId = noteId;
    }
}
