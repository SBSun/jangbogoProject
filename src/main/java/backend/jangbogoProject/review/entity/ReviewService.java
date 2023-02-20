package backend.jangbogoProject.review.entity;

import backend.jangbogoProject.user.UserService;
import backend.jangbogoProject.utils.SecurityUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ReviewService {
    private final ReviewRepository reviewRepository;

    public ReviewResponseDTO.Info createReview(ReviewRequestDTO.Create createDTO){
        Review review = createDTO.toEntity();

        String loginUserEmail = SecurityUtil.getCurrentUserEmail()
                .orElseThrow(() ->
                        new RuntimeException("로그인 유저 정보가 없습니다."));

        if(!review.getUser_email().equals(loginUserEmail))
            throw new RuntimeException("로그인한 유저가 아닙니다.");

        return ReviewResponseDTO.Info.of(reviewRepository.save(review));
    }

    public ReviewResponseDTO.Info findById(Long id){
        Review review = reviewRepository.findById(id)
                .orElseThrow(() ->
                        new IllegalArgumentException("해당 리뷰가 존재하지 않습니다. " + id));

        return ReviewResponseDTO.Info.of(review);
    }

    @Transactional
    public ReviewResponseDTO.Info editReview(ReviewRequestDTO.Edit editDTO){
        Review review = reviewRepository.findById(editDTO.getReview_id()).get();

        String loginUserEmail = SecurityUtil.getCurrentUserEmail()
                .orElseThrow(() ->
                        new RuntimeException("로그인 유저 정보가 없습니다."));

        if(!review.getUser_email().equals(loginUserEmail))
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
