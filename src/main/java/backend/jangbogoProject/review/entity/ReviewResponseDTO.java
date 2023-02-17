package backend.jangbogoProject.review.entity;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

public class ReviewResponseDTO {
    @Getter
    @Builder
    public static class Info{
        private Long review_id;
        private Long market_id;
        private Long user_id;
        private String content;
        private LocalDateTime createdDate;
        private LocalDateTime lastModifiedDate;

        public static ReviewResponseDTO.Info of(Review review){
            return Info.builder()
                    .review_id(review.getId())
                    .market_id(review.getMarket_id())
                    .user_id(review.getUser_id())
                    .content(review.getContent())
                    .createdDate(review.getCreatedDate())
                    .lastModifiedDate(review.getLastModifiedDate())
                    .build();
        }
    }
}
