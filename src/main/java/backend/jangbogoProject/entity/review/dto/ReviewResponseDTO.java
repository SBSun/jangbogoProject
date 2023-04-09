package backend.jangbogoProject.entity.review.dto;

import backend.jangbogoProject.entity.review.Review;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

public class ReviewResponseDTO {
    @Getter
    @Builder
    public static class Info{
        private Long reviewId;
        private Long marketId;
        private String userEmail;
        private String content;
        @JsonFormat(shape= JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss")
        private LocalDateTime createdDate;
        @JsonFormat(shape= JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss")
        private LocalDateTime lastModifiedDate;

        public static ReviewResponseDTO.Info of(Review review){
            return Info.builder()
                    .reviewId(review.getId())
                    .marketId(review.getMarketId())
                    .userEmail(review.getUserEmail())
                    .content(review.getContent())
                    .createdDate(review.getCreatedDate())
                    .lastModifiedDate(review.getLastModifiedDate())
                    .build();
        }
    }
}
