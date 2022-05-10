package my.md.wikimd.dtos;

import javax.validation.constraints.NotBlank;

public class NoteImageDTO {

    String id;

    @NotBlank
    String image;

    @NotBlank
    String noteId;

    public NoteImageDTO() {
    }

    public NoteImageDTO(String id, String image, String noteId) {
        this.id = id;
        this.image = image;
        this.noteId = noteId;
    }

    public String getId() {
        return id;
    }

    public String getImage() {
        return image;
    }

    public String getNoteId() {
        return noteId;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void setNoteId(String noteId) {
        this.noteId = noteId;
    }
}
