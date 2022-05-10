package my.md.wikimd.models;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name="TB_ACTION_LOG")
public class ActionLog {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(nullable = false)
    private UUID userId;

    @Column(nullable = false)
    private UUID noteId;

    @Column(nullable = false)
    private String action;

    @Column(nullable = false)
    private LocalDateTime actionDate;

    public ActionLog() {
    }

    public ActionLog(UUID userId, UUID noteId, String action, LocalDateTime actionDate) {
        this.userId = userId;
        this.noteId = noteId;
        this.action = action;
        this.actionDate = actionDate;
    }

    public ActionLog(UUID id, UUID userId, UUID noteId, String action, LocalDateTime actionDate) {
        this.id = id;
        this.userId = userId;
        this.noteId = noteId;
        this.action = action;
        this.actionDate = actionDate;
    }

    public UUID getId() {
        return id;
    }

    public UUID getUserId() {
        return userId;
    }

    public UUID getNoteId() {
        return noteId;
    }

    public String getAction() {
        return action;
    }

    public LocalDateTime getActionDate() {
        return actionDate;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public void setUserId(UUID userId) {
        this.userId = userId;
    }

    public void setNoteId(UUID noteId) {
        this.noteId = noteId;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public void setActionDate(LocalDateTime actionDate) {
        this.actionDate = actionDate;
    }
}
