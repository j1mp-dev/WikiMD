package my.md.wikimd.models;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name="TB_IMAGE")
public class Image {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    UUID id;

    @Column(nullable = false, columnDefinition="TEXT")
    String image;

    public Image() {
    }

    public Image(UUID id, String image) {
        this.id = id;
        this.image = image;
    }

    public UUID getId() {
        return id;
    }

    public String getImage() {
        return image;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
