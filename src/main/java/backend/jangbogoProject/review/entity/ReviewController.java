package backend.jangbogoProject.review.entity;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    private ReviewResponseDTO.Info findById(Long reviewId){
        return reviewService.findById(reviewId);
    }

    @GetMapping("/findAllByUserEmail")
    private ResponseEntity<List<ReviewResponseDTO.Info>> findAllByUserEmail(){
        List<ReviewResponseDTO.Info> infoList = reviewService.findAllByUserEmail();

        if(infoList.isEmpty()){
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }else{
            return new ResponseEntity<>(infoList, HttpStatus.OK);
        }
    }

    @PatchMapping("/edit")
    private ReviewResponseDTO.Info editReview(@RequestBody ReviewRequestDTO.Edit editDTO){
        return reviewService.editReview(editDTO);
    }

    @DeleteMapping("/delete")
    private void deleteReview(Long reviewId){
        reviewService.deleteReview(reviewId);
    }
}
