package my.md.wikimd.dtos;

import javax.validation.constraints.NotBlank;

public class SaveImageRequest {

    String id;

    @NotBlank
    String image;

    public SaveImageRequest() {
    }
    public SaveImageRequest(String id, String image) {
        this.id = id;
        this.image = image;
    }

    public String getId() {
        return id;
    }

    public String getImage() {
        return image;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setImage(String image) {
        this.image = image;
    }
}

