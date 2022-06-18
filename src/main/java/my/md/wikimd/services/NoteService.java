package my.md.wikimd.services;

import my.md.wikimd.dtos.NoteDTO;
import my.md.wikimd.dtos.SaveNoteRequest;
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
    public Note updateNoteIfExists(SaveNoteRequest saveNoteRequest) {
        Note note = getNoteById(UUID.fromString(saveNoteRequest.getId()));
        if(note == null) {
            return null;
        }
        note.setTitle(saveNoteRequest.getTitle());
        note.setTags(saveNoteRequest.getTags());
        note.setContent(saveNoteRequest.getContent());
        noteRepository.save(note);
        return note;
    }

    @Transactional
    public List<Note> getLastestFive() {
        return noteRepository.getLatestFive();
    }

    @Transactional
    public List<Note> getNotesCreatedBy(UUID id) {
        return noteRepository.getNotesCreatedBy(id);
    }

    @Transactional
    public List<Note> getNotesByPage(int page, int itemsPerPage, String filter) {
        page = page*itemsPerPage;
        return noteRepository.getNotesByPage(page, itemsPerPage, filter);
    }

    @Transactional
    public void deleteNote(UUID id) {
        noteRepository.deleteById(id);
    }

}
