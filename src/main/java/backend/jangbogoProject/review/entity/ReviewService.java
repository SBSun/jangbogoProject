package backend.jangbogoProject.review.entity;

import backend.jangbogoProject.user.UserService;
import backend.jangbogoProject.utils.SecurityUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ReviewService {
    private final ReviewRepository reviewRepository;

    public ReviewResponseDTO.Info createReview(ReviewRequestDTO.Create createDTO){
        Review review = createDTO.toEntity();

        String loginUserEmail = SecurityUtil.getCurrentUserEmail().get();

        if(loginUserEmail.equals("anonymousUser"))
            throw new RuntimeException("로그인한 유저가 아닙니다.");

        if(!review.getUserEmail().equals(loginUserEmail))
            throw new RuntimeException("로그인한 유저가 아닙니다.");

        return ReviewResponseDTO.Info.of(reviewRepository.save(review));
    }

    public ReviewResponseDTO.Info findById(Long id){
        Review review = reviewRepository.findById(id)
                .orElseThrow(() ->
                        new IllegalArgumentException("해당 리뷰가 존재하지 않습니다. " + id));

        return ReviewResponseDTO.Info.of(review);
    }

    public List<ReviewResponseDTO.Info> findAllByUserEmail(){
        String loginUserEmail = SecurityUtil.getCurrentUserEmail().get();

        if(loginUserEmail.equals("anonymousUser"))
            throw new RuntimeException("로그인한 유저가 아닙니다.");

        List<Review> reviewList = reviewRepository.findAllByUserEmail(loginUserEmail);

        List<ReviewResponseDTO.Info> responseList = reviewList.stream()
                .map(review -> ReviewResponseDTO.Info.of(review))
                .collect(Collectors.toList());

        return responseList;
    }

    public List<ReviewResponseDTO.Info> findAllByMarketId(Long marketId){
        List<Review> reviewList = reviewRepository.findAllByMarketId(marketId);

        List<ReviewResponseDTO.Info> responseList = reviewList.stream()
                .map(review -> ReviewResponseDTO.Info.of(review))
                .collect(Collectors.toList());

        return responseList;
    }

    @Transactional
    public ReviewResponseDTO.Info editReview(ReviewRequestDTO.Edit editDTO){
        Review review = reviewRepository.findById(editDTO.getReviewId()).get();

        String loginUserEmail = SecurityUtil.getCurrentUserEmail().get();

        if(loginUserEmail.equals("anonymousUser"))
            throw new RuntimeException("로그인한 유저가 아닙니다.");

        if(!review.getUserEmail().equals(loginUserEmail))
            throw new RuntimeException("작성자가 아닙니다.");

        review.update(editDTO.getContent());

        return ReviewResponseDTO.Info.of(review);
    }

    @Transactional
    public void deleteReview(Long review_id){
        if(!reviewRepository.existsById(review_id))
            throw new IllegalArgumentException("해당 리뷰가 존재하지 않습니다. " + review_id);

        reviewRepository.deleteById(review_id);
    }
}
