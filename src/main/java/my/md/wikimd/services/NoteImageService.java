package my.md.wikimd.services;

import my.md.wikimd.models.NoteImage;
import my.md.wikimd.repositories.NoteImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class NoteImageService {

    @Autowired
    NoteImageRepository noteImageRepository;

    public NoteImage saveNoteImage(NoteImage noteImage) {
        return noteImageRepository.save(noteImage);
    }

    public NoteImage getNoteImageById(UUID id) {
        return noteImageRepository.findById(id).get();
    }

}
