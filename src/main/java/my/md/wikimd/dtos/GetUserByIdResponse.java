package my.md.wikimd.dtos;

public class GetUserByIdResponse {

    UserDTO user;
    int permLevel;
    String created_at;
    int count_cards;

    public GetUserByIdResponse() {
    }

    public GetUserByIdResponse(UserDTO user, int permLevel, String created_at, int count_cards) {
        this.user = user;
        this.permLevel = permLevel;
        this.created_at = created_at;
        this.count_cards = count_cards;
    }

    public UserDTO getUser() {
        return user;
    }

    public int getPermLevel() {
        return permLevel;
    }

    public String getCreated_at() {
        return created_at;
    }

    public int getCount_cards() {
        return count_cards;
    }

    public void setUser(UserDTO user) {
        this.user = user;
    }

    public void setPermLevel(int permLevel) {
        this.permLevel = permLevel;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public void setCount_cards(int count_cards) {
        this.count_cards = count_cards;
    }
}
