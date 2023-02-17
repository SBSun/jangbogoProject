package backend.jangbogoProject.review;

import backend.jangbogoProject.review.entity.Review;
import backend.jangbogoProject.review.entity.ReviewRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class ReviewRepositoryTest {
    @Autowired
    private ReviewRepository reviewRepository;

    @Test
    void createReview(){
        // given
        Review review = review();

        // when
        Review createdReview = reviewRepository.save(review);

        // then
        assertThat(createdReview.getMarket_id()).isEqualTo(review.getMarket_id());
        assertThat(createdReview.getUser_id()).isEqualTo(review.getUser_id());
        assertThat(createdReview.getContent()).isEqualTo(review.getContent());
    }

    private Review review(){
        return Review.builder()
                .market_id(1L)
                .user_id(1L)
                .content("test content")
                .build();
    }
}