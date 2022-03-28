package my.md.wikimd.dtos;


import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class UserDTO {

    @NotBlank
    @Size(max = 20)
    private String username;

    @NotBlank
    @Size(max = 255)
    private String password;

    @NotNull
    private int permLevel;

    public UserDTO() {
    }

    public UserDTO(String username, String password, int permLevel) {
        this.username = username;
        this.password = password;
        this.permLevel = permLevel;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public int getPermLevel() {
        return permLevel;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setPermLevel(int permLevel) {
        this.permLevel = permLevel;
    }

}
