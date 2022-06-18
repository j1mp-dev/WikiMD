package my.md.wikimd.controllers;

import my.md.wikimd.dtos.GetImagesByIdsRequest;
import my.md.wikimd.dtos.SaveImageRequest;
import my.md.wikimd.models.Image;
import my.md.wikimd.services.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController
@CrossOrigin(origins="*", maxAge = 3600)
@RequestMapping("/image")
public class ImageController {

    @Autowired
    ImageService imageService;

    @PostMapping("/saveImage")
    public ResponseEntity saveNoteImage(@RequestBody @Valid SaveImageRequest saveImageRequest) {
        try {
            Image image = new Image();
            image.setImage(saveImageRequest.getImage());
            image = imageService.saveNoteImage(image);
            return ResponseEntity.status(HttpStatus.OK).body(image);
        } catch(Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @PostMapping("/getImagesByIds")
    public ResponseEntity getImagesByIds(@RequestBody @Valid GetImagesByIdsRequest request) {
        System.out.println(request.getIds());
        List<UUID> ids = new ArrayList<>();
        for (String id: request.getIds()) {
            ids.add(UUID.fromString(id.replace("![](","").replace(")","")));
        }
        return ResponseEntity.status(HttpStatus.OK).body(imageService.getImagesByIds(ids));
    }

}
