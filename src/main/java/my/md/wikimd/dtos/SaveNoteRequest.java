package my.md.wikimd.dtos;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class SaveNoteRequest {

    private String id;

    private String tags;

    @Size(max = 100)
    private String title;

    @Size(max = 100000)
    private String content;

    @NotBlank
    private String createdBy;

    public SaveNoteRequest() {
    }

    public SaveNoteRequest(String id, String tags, String title, String content, String createdBy) {
        this.id = id;
        this.tags = tags;
        this.title = title;
        this.content = content;
        this.createdBy = createdBy;
    }

    public String getId() {
        return id;
    }

    public String getTags() {
        return tags;
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

    public void setTags(String tags) {
        this.tags = tags;
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
