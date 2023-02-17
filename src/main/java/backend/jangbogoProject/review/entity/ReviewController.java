package backend.jangbogoProject.review.entity;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/review")
public class ReviewController {
    private final ReviewService reviewService;

    @PostMapping("/create")
    private ReviewResponseDTO.Info createReview(@RequestBody ReviewRequestDTO.Create createDTO){
        return reviewService.createReview(createDTO);
    }

    @GetMapping("/findById")
    private ReviewResponseDTO.Info findById(Long review_id){
        return reviewService.findById(review_id);
    }

    @PatchMapping("/edit")
    private ReviewResponseDTO.Info editReview(@RequestBody ReviewRequestDTO.Edit editDTO){
        return reviewService.editReview(editDTO);
    }

    @DeleteMapping("/delete")
    private void deleteReview(Long review_id){
        reviewService.deleteReview(review_id);
    }
}
