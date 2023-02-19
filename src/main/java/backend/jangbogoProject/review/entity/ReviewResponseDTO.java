package backend.jangbogoProject.review.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

public class ReviewResponseDTO {
    @Getter
    @Builder
    public static class Info{
        private Long review_id;
        private Long market_id;
        private String user_email;
        private String content;
        @JsonFormat(shape= JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss")
        private LocalDateTime createdDate;
        @JsonFormat(shape= JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss")
        private LocalDateTime lastModifiedDate;

        public static ReviewResponseDTO.Info of(Review review){
            return Info.builder()
                    .review_id(review.getId())
                    .market_id(review.getMarket_id())
                    .user_email(review.getUser_email())
                    .content(review.getContent())
                    .createdDate(review.getCreatedDate())
                    .lastModifiedDate(review.getLastModifiedDate())
                    .build();
        }
    }
}
