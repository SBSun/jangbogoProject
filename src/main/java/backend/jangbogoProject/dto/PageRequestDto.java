package backend.jangbogoProject.dto;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

public class PageRequestDto {
    private int page;
    private int size;

    public void setPage(int page) {
        this.page = page <= 0 ? 1 : page;
    }

    public void setSize(int size) {
        int DEFAULT_SIZE = 10;
        int MAX_SIZE = 50;
        this.size = size > MAX_SIZE ? DEFAULT_SIZE : size;
    }

    public PageRequestDto(int page, int size) {
        setPage(this.page);
        setSize(this.size);
    }

    public PageRequest of() {
        return PageRequest.of(page -1, size);
    }
}
