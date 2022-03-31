package my.md.wikimd.controllers;

import my.md.wikimd.dtos.NoteDTO;
import my.md.wikimd.models.Note;
import my.md.wikimd.models.User;
import my.md.wikimd.services.NoteService;
import my.md.wikimd.services.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController
@CrossOrigin(origins="*", maxAge = 3600)
@RequestMapping("/note")
public class NoteController {

    @Autowired
    NoteService noteService;

    @Autowired
    UserService userService;

    @PostMapping
    public ResponseEntity<String> saveNote(@RequestBody @Valid NoteDTO noteDTO) {
        Note note = new Note();
        try {
            // Check if note UUID is sent in request
            if (noteDTO.getId() == null) {
                // Check if user exists by UUID if not throw exception
                User user = userService.getUserById(UUID.fromString(noteDTO.getCreatedBy()));
                if(user != null) {
                    System.out.println(user);
                }
                BeanUtils.copyProperties(noteDTO, note);
                note.setCreatedAt(LocalDateTime.now(ZoneId.of("UTC")));
                noteService.save(note);
                return new ResponseEntity<>("Saved Note", HttpStatus.OK);
            } else {
                noteService.updateNoteIfExists(noteDTO);
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
    public List<NoteDTO> getLatestFive() {
        List<Note> noteList = noteService.getLastestFive();
        List<NoteDTO> response = new ArrayList<>();
        for(Note n : noteList) {
            NoteDTO noteDTO = new NoteDTO();
            noteDTO.setId(n.getId().toString());
            noteDTO.setTitle(n.getTitle());
            noteDTO.setTagUUIDS(n.getTagUUIDS());
            noteDTO.setCreatedBy(userService.getUserById(UUID.fromString(n.getCreatedBy())).getUsername());
            response.add(noteDTO);
        }
        return response;
    }


}
