package backend.jangbogoProject.service;

import backend.jangbogoProject.dto.ReviewRequestDTO;
import backend.jangbogoProject.dto.ReviewResponseDTO;
import backend.jangbogoProject.entity.Review;
import backend.jangbogoProject.exception.errorCode.CommonErrorCode;
import backend.jangbogoProject.exception.exception.RestApiException;
import backend.jangbogoProject.repository.ReviewRepository;
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
    public ReviewResponseDTO.Info createReview(ReviewRequestDTO.Create createDTO){
        Review review = createDTO.toEntity();

        String loginUserEmail = SecurityUtil.getCurrentUserEmail().get();

        if(loginUserEmail.equals("anonymousUser"))
            throw new RestApiException(CommonErrorCode.RESOURCE_NOT_FOUND);

        if(!review.getUserEmail().equals(loginUserEmail))
            throw new RestApiException(CommonErrorCode.RESOURCE_NOT_FOUND);

        return ReviewResponseDTO.Info.of(reviewRepository.save(review));
    }

    public ReviewResponseDTO.Info findById(Long id){
        Review review = reviewRepository.findById(id)
                .orElseThrow(() ->
                     new RestApiException(CommonErrorCode.RESOURCE_NOT_FOUND));

        return ReviewResponseDTO.Info.of(review);
    }

    public List<ReviewResponseDTO.Info> findAllByUserEmail(){
        String loginUserEmail = SecurityUtil.getCurrentUserEmail().get();

        if(loginUserEmail.equals("anonymousUser"))
            throw new RestApiException(CommonErrorCode.RESOURCE_NOT_FOUND);

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
            throw new RestApiException(CommonErrorCode.RESOURCE_NOT_FOUND);

        if(!review.getUserEmail().equals(loginUserEmail))
            throw new RestApiException(CommonErrorCode.RESOURCE_NOT_FOUND);

        review.update(editDTO.getContent());

        return ReviewResponseDTO.Info.of(review);
    }

    @Transactional
    public void deleteReview(Long review_id){
        if(!reviewRepository.existsById(review_id))
            throw new RestApiException(CommonErrorCode.RESOURCE_NOT_FOUND);

        reviewRepository.deleteById(review_id);
    }
}
