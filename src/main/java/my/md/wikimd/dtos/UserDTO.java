package my.md.wikimd.dtos;


import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class UserDTO {

    private String id;

    @NotBlank
    @Size(max = 20)
    private String username;

    @NotBlank
    @Size(max = 80)
    private String name;

    @NotBlank
    @Size(max = 255)
    private String password;

    @NotBlank
    private String image;

    @NotNull
    private int permLevel;

    public UserDTO() {
    }
    public UserDTO(String id, String username, String name, String password, String image, int permLevel) {
        this.id = id;
        this.username = username;
        this.name = name;
        this.password = password;
        this.image = image;
        this.permLevel = permLevel;
    }

    public String getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    public String getImage() {
        return image;
    }

    public int getPermLevel() {
        return permLevel;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void setPermLevel(int permLevel) {
        this.permLevel = permLevel;
    }
}
