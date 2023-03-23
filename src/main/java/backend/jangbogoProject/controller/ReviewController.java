package backend.jangbogoProject.controller;

import backend.jangbogoProject.dto.ReviewRequestDTO;
import backend.jangbogoProject.dto.ReviewResponseDTO;
import backend.jangbogoProject.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("/review")
public class ReviewController {
    private final ReviewService reviewService;

    @PostMapping("/create")
    public ReviewResponseDTO.Info createReview(@RequestBody @Valid ReviewRequestDTO.Create createDTO){
        return reviewService.createReview(createDTO);
    }

    @GetMapping("/findById")
    public ReviewResponseDTO.Info findById(@RequestParam @Min(1) Long reviewId){
        return reviewService.findById(reviewId);
    }

    @GetMapping("/findAllByUserEmail")
    public ResponseEntity<List<ReviewResponseDTO.Info>> findAllByUserEmail(){
        List<ReviewResponseDTO.Info> infoList = reviewService.findAllByUserEmail();

        if(infoList.isEmpty()){
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }else{
            return new ResponseEntity<>(infoList, HttpStatus.OK);
        }
    }

    @GetMapping("/findAllByMarketId")
    public ResponseEntity<List<ReviewResponseDTO.Info>> findAllByMarketId(@RequestParam @NotBlank Long marketId){
        List<ReviewResponseDTO.Info> infoList = reviewService.findAllByMarketId(marketId);

        if(infoList.isEmpty()){
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }else{
            return new ResponseEntity<>(infoList, HttpStatus.OK);
        }
    }

    @PatchMapping("/edit")
    public ReviewResponseDTO.Info editReview(@RequestBody @Valid ReviewRequestDTO.Edit editDTO){
        return reviewService.editReview(editDTO);
    }

    @DeleteMapping("/delete")
    public void deleteReview(@RequestParam @NotBlank Long reviewId){
        reviewService.deleteReview(reviewId);
    }
}
