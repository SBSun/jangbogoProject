package backend.jangbogoProject.review.entity;

import backend.jangbogoProject.BaseTimeEntity;
import lombok.*;

import javax.persistence.*;

@NoArgsConstructor
@Getter
@Entity
public class Review extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "review_id")
    private Long id;

    @Column(nullable = false, name = "market_id")
    private Long market_id;

    @Column(nullable = false, name = "user_id")
    private Long user_id;

    @Column
    private String content;

    @Builder
    public Review(Long id, Long market_id, Long user_id, String content) {
        this.id = id;
        this.market_id = market_id;
        this.user_id = user_id;
        this.content = content;
    }
}
