package my.md.wikimd.dtos;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

public class NoteResponse {

    private String id;

    private String tags;

    @NotBlank
    @Size(max = 100)
    private String title;

    @NotBlank
    @Size(max = 100000)
    private String content;

    @NotBlank
    private UserDTO createdBy;

    private String createdAt;

    public NoteResponse() {
    }

    public NoteResponse(String id, String tags, String title, String content, UserDTO createdBy, String createdAt) {
        this.id = id;
        this.tags = tags;
        this.title = title;
        this.content = content;
        this.createdBy = createdBy;
        this.createdAt = createdAt;
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

    public UserDTO getCreatedBy() {
        return createdBy;
    }

    public String getCreatedAt() {
        return createdAt;
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

    public void setCreatedBy(UserDTO createdBy) {
        this.createdBy = createdBy;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }
}
