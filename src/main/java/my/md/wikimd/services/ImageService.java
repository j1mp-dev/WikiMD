package my.md.wikimd.services;

import my.md.wikimd.models.Image;
import my.md.wikimd.repositories.ImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ImageService {

    @Autowired
    ImageRepository imageRepository;

    public Image saveNoteImage(Image image) {
        return imageRepository.save(image);
    }

    public Image getNoteImageById(UUID id) {
        return imageRepository.findById(id).get();
    }

    public List<Image> getImagesByIds(List<UUID> ids) {
        return imageRepository.getImagesByIds(ids);
    }


}
