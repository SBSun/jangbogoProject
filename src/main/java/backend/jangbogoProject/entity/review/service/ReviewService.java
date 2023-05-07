package backend.jangbogoProject.entity.review.service;

import backend.jangbogoProject.entity.review.dto.ReviewRequestDTO;
import backend.jangbogoProject.entity.review.dto.ReviewResponseDTO;
import backend.jangbogoProject.entity.review.Review;
import backend.jangbogoProject.exception.errorCode.CommonErrorCode;
import backend.jangbogoProject.exception.errorCode.UserErrorCode;
import backend.jangbogoProject.exception.exception.RestApiException;
import backend.jangbogoProject.entity.review.repository.ReviewRepository;
import backend.jangbogoProject.utils.SecurityUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ReviewService {
    private final ReviewRepository reviewRepository;

    @Transactional
    public ReviewResponseDTO.Info createReview(ReviewRequestDTO.Create createDTO, String loginEmail){
        Review review = createDTO.toEntity();
        review.setUserEmail(loginEmail);

        return ReviewResponseDTO.Info.of(reviewRepository.save(review));
    }

    public ReviewResponseDTO.Info findById(Long id){
        Review review = reviewRepository.findById(id)
                .orElseThrow(() ->
                     new RestApiException(CommonErrorCode.RESOURCE_NOT_FOUND));

        return ReviewResponseDTO.Info.of(review);
    }

    public List<ReviewResponseDTO.Info> findByUserEmail(String loginEmail){

        List<Review> reviewList = reviewRepository.findAllByUserEmail(loginEmail);

        List<ReviewResponseDTO.Info> responseList = reviewList.stream()
                .map(review -> ReviewResponseDTO.Info.of(review))
                .collect(Collectors.toList());

        return responseList;
    }

    public List<ReviewResponseDTO.Info> findByMarketId(Long marketId){
        List<Review> reviewList = reviewRepository.findAllByMarketId(marketId);

        List<ReviewResponseDTO.Info> responseList = reviewList.stream()
                .map(review -> ReviewResponseDTO.Info.of(review))
                .collect(Collectors.toList());

        return responseList;
    }

    @Transactional
    public ReviewResponseDTO.Info editReview(Long reviewId, String content){
        Review review = reviewRepository.findById(reviewId).get();
        if(review == null)
            throw new RestApiException(CommonErrorCode.RESOURCE_NOT_FOUND);

        String loginEmail = SecurityUtil.getCurrentUserEmail().get();
        if(loginEmail.equals("anonymousUser") || !review.getUserEmail().equals(loginEmail))
            throw new RestApiException(UserErrorCode.INACTIVE_USER);

        review.update(content);

        return ReviewResponseDTO.Info.of(review);
    }

    @Transactional
    public void deleteReview(Long reviewId){
        Review review = reviewRepository.findById(reviewId).get();
        if(review == null)
            throw new RestApiException(CommonErrorCode.RESOURCE_NOT_FOUND);

        String loginEmail = SecurityUtil.getCurrentUserEmail().get();
        if(loginEmail.equals("anonymousUser") || !review.getUserEmail().equals(loginEmail))
            throw new RestApiException(UserErrorCode.INACTIVE_USER);

        reviewRepository.deleteById(reviewId);
    }
}
