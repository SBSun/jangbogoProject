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
    private Long market_id;

    @Column(nullable = false, name = "user_email")
    private String user_email;

    @Column(nullable = false)
    private String content;

    @Builder
    public Review(Long id, Long market_id, String user_email, String content) {
        this.id = id;
        this.market_id = market_id;
        this.user_email = user_email;
        this.content = content;
    }

    public void update(String content){
        this.content = content;
    }
}
