package my.md.wikimd.services;

import my.md.wikimd.dtos.NoteDTO;
import my.md.wikimd.models.Note;
import my.md.wikimd.repositories.NoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class NoteService {

    @Autowired
    NoteRepository noteRepository;

    @Transactional
    public Note save(Note note) {
        return noteRepository.save(note);
    }

    @Transactional
    public List<Note> getAll() {
        return noteRepository.findAll();
    }

    @Transactional
    public Note getNoteById(UUID uuid) {
        return noteRepository.getById(uuid);
    }

    @Transactional
    public boolean updateNoteIfExists(NoteDTO noteDTO) {
        Note note = getNoteById(UUID.fromString(noteDTO.getId()));
        if(note == null) {
            return false;
        }
        note.setTitle(noteDTO.getTitle());
        note.setContent(noteDTO.getContent());
        noteRepository.save(note);
        return true;
    }

    @Transactional
    public List<Note> getLastestFive() {
        return noteRepository.getLatestFive();
    }


}
