package backend.jangbogoProject.review;

import backend.jangbogoProject.entity.Review;
import backend.jangbogoProject.repository.ReviewRepository;
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
        assertThat(createdReview.getMarketId()).isEqualTo(review.getMarketId());
        assertThat(createdReview.getUserEmail()).isEqualTo(review.getUserEmail());
        assertThat(createdReview.getContent()).isEqualTo(review.getContent());
    }
/*
    @Test
    void findById(){
        // given
        Long id = 1L;

        // when
        Review review = reviewRepository.findById(id).get();

        // then
        assertThat(review).isNotNull();
    }
*/
    private Review review(){
        return Review.builder()
                .marketId(1L)
                .userEmail("sbs")
                .content("test content")
                .build();
    }
}