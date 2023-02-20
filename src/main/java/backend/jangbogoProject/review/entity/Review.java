package backend.jangbogoProject.review.entity;

import backend.jangbogoProject.BaseTimeEntity;
import lombok.*;

import javax.persistence.*;

@Getter
@Entity
@NoArgsConstructor
public class Review extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "review_id")
    private Long id;

    @Column(nullable = false, name = "market_id")
    private Long marketId;

    @Column(nullable = false, name = "user_email")
    private String userEmail;

    @Column(nullable = false)
    private String content;

    @Builder
    public Review(Long id, Long marketId, String userEmail, String content) {
        this.id = id;
        this.marketId = marketId;
        this.userEmail = userEmail;
        this.content = content;
    }

    public void update(String content){
        this.content = content;
    }
}
