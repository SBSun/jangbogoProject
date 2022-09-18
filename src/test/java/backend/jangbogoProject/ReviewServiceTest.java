package backend.jangbogoProject;

import backend.jangbogoProject.item.domain.Item;
import backend.jangbogoProject.member.service.MemberService;
import backend.jangbogoProject.review.*;
import com.google.gson.Gson;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
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
    void 리뷰정보반환(){
        List<Review> reviewList = reviewService.findAllById(4);
        List<ReviewResponseDTO> responseDTOList = new ArrayList<>();

        for(Review review : reviewList){
            ReviewResponseDTO reviewResponseDTO = new ReviewResponseDTO(
                    review.getReview_id(),
                    memberService.findById(Long.valueOf(review.getMember_id())).get().getName(),
                    review.getContents(),
                    review.isLike_unlike()
            );

            responseDTOList.add(reviewResponseDTO);
        }
        Gson gson = new Gson();
        String listJson = gson.toJson(responseDTOList, List.class).toString();
        System.out.println(listJson);
    }
}
