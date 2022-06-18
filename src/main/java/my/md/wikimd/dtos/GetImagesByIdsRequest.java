package my.md.wikimd.dtos;

import javax.validation.constraints.NotNull;
import java.util.List;

public class GetImagesByIdsRequest {

    @NotNull
    List<String> ids;

    public GetImagesByIdsRequest() {
    }

    public GetImagesByIdsRequest(List<String> ids) {
        this.ids = ids;
    }

    public List<String> getIds() {
        return ids;
    }

    public void setIds(List<String> ids) {
        this.ids = ids;
    }
}
