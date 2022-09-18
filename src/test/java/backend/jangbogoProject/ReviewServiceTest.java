package backend.jangbogoProject;

import backend.jangbogoProject.member.service.MemberService;
import backend.jangbogoProject.review.Review;
import backend.jangbogoProject.review.ReviewDTO;
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
    @Autowired
    MemberService memberService;

    @Test
    void 리뷰저장(){
        ReviewDTO reviewDTO = new ReviewDTO();
        reviewDTO.setMember_id(memberService.findEmail("byung0216@naver.com").get().getId().intValue());
        reviewDTO.setMarketSerialNum(26);
        reviewDTO.setContents("상품들 상태도 좋고 매장도 청결해서 좋았습니다.");
        reviewDTO.setLike_unlike(true);

        reviewRepository.save(reviewDTO.toEntity());
    }

    @Test
    void 리뷰정보반환(){
        Review review = reviewService.findById(4).get();
    }
}
