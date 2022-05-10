package my.md.wikimd.services;

import my.md.wikimd.models.Tag;
import my.md.wikimd.repositories.TagRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class TagService {

    @Autowired
    TagRepository tagRepository;

    @Transactional
    public List<Tag> getAllTags() {
        return tagRepository.findAll();
    }

    @Transactional
    public Tag getTagByName(String name) {
        return tagRepository.getTagByName(name);
    }

    @Transactional
    public Tag saveTagIfNotExists(String name) {
        Tag tag = getTagByName(name);
        // If tag doesn't exist create it
        if(tag == null) {
            tag = tagRepository.save(new Tag(name));
        }
        return tag;
    }

}
