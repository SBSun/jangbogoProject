package backend.jangbogoProject.review.entity;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ReviewService {
    private final ReviewRepository reviewRepository;

    public ReviewResponseDTO.Info createReview(ReviewRequestDTO.Create createDTO){
        Review review = createDTO.toEntity();

        return ReviewResponseDTO.Info.of(reviewRepository.save(review));
    }
}
