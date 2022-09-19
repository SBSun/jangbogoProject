package backend.jangbogoProject.review;

import backend.jangbogoProject.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Transactional
@Service
@RequiredArgsConstructor
public class ReviewService {
    private final ReviewRepository reviewRepository;
    private final MemberService memberService;

    public Review save(Review review){
        if(reviewRepository.existsById(review.getReview_id())){
            return null;
        }

        reviewRepository.save(review);
        return review;
    }

    public Optional<Review> findById(int id){
        return reviewRepository.findById(id);
    }

    public List<Review> findAllById(int id){
        return reviewRepository.findAllByMemberId(id);
    }

    public List<Review> findAllByMarketSerialNum(int marketSerialNum){
        return reviewRepository.findAllByMarketSerialNum(marketSerialNum);
    }

    public List<ReviewResponseDTO> switchToResponseDTO(List<Review> reviewList){
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

        return responseDTOList;
    }
}
