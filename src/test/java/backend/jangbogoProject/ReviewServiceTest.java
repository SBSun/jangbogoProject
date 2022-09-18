package backend.jangbogoProject;

import backend.jangbogoProject.review.Review;
import backend.jangbogoProject.review.ReviewRepository;
import backend.jangbogoProject.review.ReviewService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ReviewServiceTest {
    @Autowired
    ReviewService reviewService;
    @Autowired
    ReviewRepository reviewRepository;

    @Test
    void 리뷰저장(){
        Review review = new Review(1 ,4, 26, "추천합니다", true);

        reviewRepository.save(review);
    }

    @Test
    void 리뷰정보반환(){
        Review review = reviewService.findById(4).get();
    }
}
