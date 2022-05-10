package my.md.wikimd.controllers;

import my.md.wikimd.dtos.NoteImageDTO;
import my.md.wikimd.models.NoteImage;
import my.md.wikimd.services.NoteImageService;
import org.apache.tomcat.jni.Time;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@RestController
@CrossOrigin(origins="*", maxAge = 3600)
@RequestMapping("/noteImage")
public class NoteImageController {

    @Autowired
    NoteImageService noteImageService;

    @PostMapping("/saveImage")
    public ResponseEntity saveNoteImage(@RequestBody @Valid NoteImageDTO noteImageDTO) {
        try {
            NoteImage noteImage = new NoteImage();
            noteImage.setImage(noteImageDTO.getImage());
            noteImage.setNoteId(UUID.fromString(noteImageDTO.getNoteId()));
            noteImage = noteImageService.saveNoteImage(noteImage);
            TimeUnit.SECONDS.sleep(2);
            return ResponseEntity.status(HttpStatus.OK).body(noteImage);
        } catch(Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @GetMapping("getNoteImageById")
    public ResponseEntity getNoteImagesById(@RequestParam String id) {
        return ResponseEntity.status(HttpStatus.OK).body(noteImageService.getNoteImageById(UUID.fromString(id)));
    }

}
