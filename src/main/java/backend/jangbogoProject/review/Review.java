package backend.jangbogoProject.review;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int review_id;
    @Column(nullable = false, name = "id")
    private int member_id;
    @Column(name="M_SEQ")
    private int marketSerialNum; // 일련번호
    private String contents;
    private boolean like_unlike;

    @Builder
    public Review(int review_id, int member_id, int marketSerialNum, String contents, boolean like_unlike) {
        this.review_id = review_id;
        this.member_id = member_id;
        this.marketSerialNum = marketSerialNum;
        this.contents = contents;
        this.like_unlike = like_unlike;
    }
}
