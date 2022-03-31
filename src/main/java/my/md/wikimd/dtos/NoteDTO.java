package my.md.wikimd.dtos;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

public class NoteDTO {

    private String id;

    private String tagUUIDS;

    @NotBlank
    @Size(max = 100)
    private String title;

    @NotBlank
    @Size(max = 100000)
    private String content;

    @NotBlank
    private String createdBy;

    public NoteDTO() {
    }

    public NoteDTO(String id, String tagUUIDS, String title, String content, String createdBy) {
        this.id = id;
        this.tagUUIDS = tagUUIDS;
        this.title = title;
        this.content = content;
        this.createdBy = createdBy;
    }

    public String getId() {
        return id;
    }

    public String getTagUUIDS() {
        return tagUUIDS;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setTagUUIDS(String tagUUIDS) {
        this.tagUUIDS = tagUUIDS;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }
}
