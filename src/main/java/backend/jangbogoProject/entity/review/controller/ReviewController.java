package backend.jangbogoProject.entity.review.controller;

import backend.jangbogoProject.entity.review.dto.ReviewRequestDTO;
import backend.jangbogoProject.entity.review.dto.ReviewResponseDTO;
import backend.jangbogoProject.entity.review.service.ReviewService;
import backend.jangbogoProject.exception.errorCode.UserErrorCode;
import backend.jangbogoProject.exception.exception.RestApiException;
import backend.jangbogoProject.utils.SecurityUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

@Validated
@RestController
@RequiredArgsConstructor
public class ReviewController {
    private final ReviewService reviewService;

    @PostMapping("/reviews")
    public void createReview(@RequestBody @Valid ReviewRequestDTO.Create createDTO){
        String loginEmail = SecurityUtil.getCurrentUserEmail().get();

        if(loginEmail.equals("anonymousUser"))
            throw new RestApiException(UserErrorCode.INACTIVE_USER);

        reviewService.createReview(createDTO, loginEmail);
    }

    @GetMapping("/reviews/{reviewId}")
    public ReviewResponseDTO.Info findById(@PathVariable @NotNull Long reviewId){
        return reviewService.findById(reviewId);
    }

    @GetMapping("/my/reviews")
    public List<ReviewResponseDTO.Info> findByUserEmail(){
        String loginEmail = SecurityUtil.getCurrentUserEmail().get();

        if(loginEmail.equals("anonymousUser"))
            throw new RestApiException(UserErrorCode.INACTIVE_USER);

        return reviewService.findByUserEmail(loginEmail);
    }

    @GetMapping("/markets/{marketId}/reviews")
    public List<ReviewResponseDTO.Info> findByMarketId(@PathVariable @NotNull Long marketId){
        return reviewService.findByMarketId(marketId);
    }

    @PatchMapping("/reviews/{reviewId}")
    public ReviewResponseDTO.Info editReview(@PathVariable @NotNull Long reviewId, @RequestBody @Valid String content){
        return reviewService.editReview(reviewId, content);
    }

    @DeleteMapping("/reviews/{reviewId}")
    public void deleteReview(@PathVariable @NotBlank Long reviewId){
        reviewService.deleteReview(reviewId);
    }
}
