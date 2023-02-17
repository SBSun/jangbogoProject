package backend.jangbogoProject.review.entity;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ReviewService {
    private final ReviewRepository reviewRepository;

    public ReviewResponseDTO.Info createReview(ReviewRequestDTO.Create createDTO){
        Review review = createDTO.toEntity();

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
        Review review = reviewRepository.findById(editDTO.getReview_id())
                .orElseThrow(() ->
                        new IllegalArgumentException("해당 리뷰가 존재하지 않습니다. " + editDTO.getReview_id()));

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
