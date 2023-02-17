package backend.jangbogoProject.review.entity;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ReviewService {
    private final ReviewRepository reviewRepository;

    public Long createReview(ReviewRequestDTO.Save saveDTO){
        Review review = saveDTO.toEntity();

        return reviewRepository.save(review).getId();
    }
}
