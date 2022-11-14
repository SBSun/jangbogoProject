package backend.jangbogoProject.dto;

import lombok.*;

import javax.persistence.Entity;

@Getter
public class ReviewResponseDTO {
    private int review_id;
    private String name;
    private String contents;
    private boolean like_unlike;
    
    public ReviewResponseDTO(int review_id, String name, String contents, boolean like_unlike) {
        this.review_id = review_id;
        this.name = name;
        this.contents = contents;
        this.like_unlike = like_unlike;
    }
}
