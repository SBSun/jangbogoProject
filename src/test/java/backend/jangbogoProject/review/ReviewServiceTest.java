package backend.jangbogoProject.review;

import backend.jangbogoProject.review.entity.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ReviewServiceTest {
    @InjectMocks
    private ReviewService reviewService;

    @Mock
    private ReviewRepository reviewRepository;

    @Test
    void createReview(){
        // given
        ReviewRequestDTO.Create request = createRequest();

        Review requestReview = request.toEntity();

        doReturn(requestReview).when(reviewRepository)
                .save(any(Review.class));

        // when
        ReviewResponseDTO.Info createdReview = reviewService.createReview(request);

        // then
        assertThat(requestReview.getContent()).isEqualTo(createdReview.getContent());

        verify(reviewRepository, times(1)).save(any(Review.class));
    }

    private ReviewRequestDTO.Create createRequest(){
        return ReviewRequestDTO.Create.builder()
                .market_id(1L)
                .user_id(1L)
                .content("test content")
                .build();
    }
}
