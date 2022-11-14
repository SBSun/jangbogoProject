package backend.jangbogoProject;

import backend.jangbogoProject.domain.Review;
import backend.jangbogoProject.dto.ReviewDTO;
import backend.jangbogoProject.repository.ReviewRepository;
import backend.jangbogoProject.service.MemberService;
import backend.jangbogoProject.service.ReviewService;
import com.google.gson.Gson;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

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
    void 리뷰정보반환_멤버아이디(){
        List<Review> reviewList = reviewService.findAllById(4);

        Gson gson = new Gson();
        String listJson = gson.toJson(reviewService.switchToResponseDTO(reviewList), List.class).toString();
        System.out.println(listJson);
    }

    @Test
    void 리뷰정보반환_마켓번호(){
        List<Review> reviewList = reviewService.findAllByMarketSerialNum(26);

        Gson gson = new Gson();
        String listJson = gson.toJson(reviewService.switchToResponseDTO(reviewList), List.class).toString();
        System.out.println(listJson);
    }
}
