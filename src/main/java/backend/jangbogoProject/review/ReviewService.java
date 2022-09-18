package backend.jangbogoProject.review;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Transactional
@Service
@RequiredArgsConstructor
public class ReviewService {
    private final ReviewRepository reviewRepository;

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
}
