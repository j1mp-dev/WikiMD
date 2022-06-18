package my.md.wikimd.controllers;

import my.md.wikimd.dtos.*;
import my.md.wikimd.models.ActionLog;
import my.md.wikimd.models.Note;
import my.md.wikimd.models.User;
import my.md.wikimd.services.ActionLogService;
import my.md.wikimd.services.NoteService;
import my.md.wikimd.services.TagService;
import my.md.wikimd.services.UserService;
import my.md.wikimd.utils.Utils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.UUID;

@RestController
@CrossOrigin(origins="*", maxAge = 3600)
@RequestMapping("/note")
public class NoteController {

    @Autowired
    NoteService noteService;

    @Autowired
    UserService userService;

    @Autowired
    ActionLogService actionLogService;

    @Autowired
    TagService tagService;

    @PostMapping
    public ResponseEntity saveNote(@RequestBody @Valid SaveNoteRequest saveNoteRequest) {
        Note note = new Note();
        try {
            // Check if note UUID is sent in request
            if (saveNoteRequest.getId() == null) {
                // Check if user exists by UUID if not throw exception
                User user = userService.getUserById(UUID.fromString(saveNoteRequest.getCreatedBy()));
                if(user != null) {
                    System.out.println(user);
                }
                String tags = "";
                if (saveNoteRequest.getTags() != null && !saveNoteRequest.getTags().isBlank()) {
                    for(String tag: saveNoteRequest.getTags().split(",")) {
                        String s = tag.substring(0, 1).toUpperCase(Locale.ROOT) + tag.substring(1);
                        System.out.println(s);
                        tags += tagService.saveTagIfNotExists(s).getName() + ",";
                    }
                }
                saveNoteRequest.setTags(tags);
                BeanUtils.copyProperties(saveNoteRequest, note);
                note.setCreatedBy(UUID.fromString(saveNoteRequest.getCreatedBy()));
                note.setCreatedAt(LocalDateTime.now(ZoneId.of("UTC")));
                note = noteService.save(note);
                actionLogService.save(new ActionLog(
                        user.getId(),
                        note.getId(),
                        "CREATED",
                        LocalDateTime.now(ZoneId.of("UTC"))));
                return ResponseEntity.status(HttpStatus.OK).body(note.getId());
            } else {
                note = noteService.updateNoteIfExists(saveNoteRequest);
                actionLogService.save(new ActionLog(
                        note.getCreatedBy(),
                        note.getId(),
                        "EDITED",
                        LocalDateTime.now(ZoneId.of("UTC"))));

                return new ResponseEntity<>("Updated Note", HttpStatus.OK);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }

    }

    @GetMapping
    public List<Note> getAll() {
        return noteService.getAll();
    }

    @GetMapping(value="getLatestFive")
    public List<NoteResponse> getLatestFive() {
        List<Note> noteList = noteService.getLastestFive();
        List<NoteResponse> response = new ArrayList<>();
        for(Note n : noteList) {
            User user = userService.getUserById(n.getCreatedBy());
            NoteResponse note = new NoteResponse();
            note.setId(n.getId().toString());
            note.setTitle(n.getTitle());
            note.setTags(n.getTags());
            note.setCreatedBy(new UserDTO(user.getId().toString(), user.getUsername(), user.getName(), "", user.getImage(), user.getPermLevel()));
            note.setCreatedAt(Utils.calculateDurationToString(Duration.between(n.getCreatedAt(), LocalDateTime.now(ZoneId.of("UTC")))));
            response.add(note);
        }
        return response;
    }

    @GetMapping(value="getNotesCreatedBy")
    public List<NoteDTO> getNotesCreatedBy(@RequestParam String id) {
        System.out.print(id);
        List<Note> noteList = noteService.getNotesCreatedBy(UUID.fromString(id));
        List<NoteDTO> response = new ArrayList<>();
        for(Note n : noteList) {
            NoteDTO noteDTO = new NoteDTO();
            noteDTO.setId(n.getId().toString());
            noteDTO.setTitle(n.getTitle());
            noteDTO.setTags(n.getTags());
            noteDTO.setCreatedAt(Utils.calculateDurationToString(Duration.between(n.getCreatedAt(), LocalDateTime.now(ZoneId.of("UTC")))));
            response.add(noteDTO);
        }
        return response;
    }

    @GetMapping(value="getNoteById")
    public NoteResponse getNotesById(@RequestParam String id) {
        Note note = noteService.getNoteById(UUID.fromString(id));
        User user = userService.getUserById(note.getCreatedBy());
        NoteResponse response = new NoteResponse(
                note.getId().toString(),
                note.getTags(),
                note.getTitle(),
                note.getContent(),
                new UserDTO(user.getId().toString(), user.getUsername(), user.getName(), "", user.getImage(), user.getPermLevel()),
                note.getCreatedAt().toString());
        return response;
    }

    @GetMapping(value="getNotesByPage")
    public List<NoteResponse> getNotesByPage(@Valid @NotNull @RequestParam int page,
                                        @Valid @NotNull @RequestParam int  itemsPerPage,
                                        @RequestParam(required = false) String orderBy,
                                             @RequestParam String filter) {
        filter = filter == "" ? "%" : "%" + filter + "%";
        List<Note> noteList = noteService.getNotesByPage(page, itemsPerPage, filter);
        List<NoteResponse> response = new ArrayList<>();

        for(Note n: noteList) {
            User user = userService.getUserById(n.getCreatedBy());
            NoteResponse note = new NoteResponse();
            note.setId(n.getId().toString());
            note.setTitle(n.getTitle());
            note.setCreatedBy(new UserDTO(user.getId().toString(), user.getUsername(), user.getName(), "", user.getImage(), user.getPermLevel()));
            note.setTags(n.getTags());
            note.setCreatedAt(Utils.calculateDurationToString(Duration.between(n.getCreatedAt(), LocalDateTime.now(ZoneId.of("UTC")))));
            response.add(note);
        }
        return response;
    }

    @PostMapping(value="deleteNote")
    public ResponseEntity deleteNoteById(@RequestBody @Valid DeleteNoteRequest deleteNoteRequest) {
        System.out.println(deleteNoteRequest);
        try {
            User user = userService.getUserById(UUID.fromString(deleteNoteRequest.getDeletedBy()));
            System.out.println(user.toString());
            if(user.getPermLevel() >= 1) {
                Note note = noteService.getNoteById(UUID.fromString(deleteNoteRequest.getId()));
                System.out.println(note.getCreatedBy() + " " + deleteNoteRequest.getCreatedBy());
                if(note != null && note.getCreatedBy().toString().equals(deleteNoteRequest.getCreatedBy())) {
                    noteService.deleteNote(UUID.fromString(deleteNoteRequest.getId()));
                    return ResponseEntity.status(HttpStatus.OK).body("Deleted");
                }
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Nice try");
    }


}
