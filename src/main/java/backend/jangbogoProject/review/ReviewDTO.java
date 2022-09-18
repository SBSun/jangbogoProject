package backend.jangbogoProject.review;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ReviewDTO {
    private int review_id;
    private int member_id;
    private int marketSerialNum; // 일련번호
    private String contents;
    private boolean like_unlike;

    public Review toEntity(){
        Review build = Review.builder()
                .review_id(review_id)
                .member_id(member_id)
                .marketSerialNum(marketSerialNum)
                .contents(contents)
                .like_unlike(like_unlike)
                .build();

        return build;
    }

    @Builder
    public ReviewDTO(int review_id, int member_id, int marketSerialNum, String contents, boolean like_unlike) {
        this.review_id = review_id;
        this.member_id = member_id;
        this.marketSerialNum = marketSerialNum;
        this.contents = contents;
        this.like_unlike = like_unlike;
    }
}

