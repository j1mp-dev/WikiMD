package my.md.wikimd.dtos;

import javax.validation.constraints.NotNull;

public class DeleteNoteRequest {

    @NotNull
    String id;

    @NotNull
    String deletedBy;

    @NotNull
    String createdBy;

    public DeleteNoteRequest(String id, String deletedBy, String createdBy) {
        this.id = id;
        this.deletedBy = deletedBy;
        this.createdBy = createdBy;
    }

    public String getId() {
        return id;
    }

    public String getDeletedBy() {
        return deletedBy;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setDeletedBy(String deletedBy) {
        this.deletedBy = deletedBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    @Override
    public String toString() {
        return "DeleteNoteRequest{" +
                "id='" + id + '\'' +
                ", deletedBy='" + deletedBy + '\'' +
                ", createdBy='" + createdBy + '\'' +
                '}';
    }
}
