package my.md.wikimd.dtos;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class GetNoteByPageRequest {

    String orderBy;

    @NotNull
    int page;

    @NotNull
    int itemsPerPage;

    public GetNoteByPageRequest() {
    }

    public GetNoteByPageRequest(String orderBy, int page, int itemsPerPage) {
        this.orderBy = orderBy;
        this.page = page;
        this.itemsPerPage = itemsPerPage;
    }

    public String getOrderBy() {
        return orderBy;
    }

    public int getPage() {
        return page;
    }

    public int getItemsPerPage() {
        return itemsPerPage;
    }

    public void setOrderBy(String orderBy) {
        this.orderBy = orderBy;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public void setItemsPerPage(int itemsPerPage) {
        this.itemsPerPage = itemsPerPage;
    }
}
