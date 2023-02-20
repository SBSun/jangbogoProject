package backend.jangbogoProject.review.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class ReviewRequestDTO {
    @Getter
    @NoArgsConstructor
    public static class Create{
        private Long marketId;
        private String userEmail;
        private String content;

        @Builder
        public Create(final Long marketId, final String userEmail, final String content) {
            this.marketId = marketId;
            this.userEmail = userEmail;
            this.content = content;
        }

        public Review toEntity(){
            return Review.builder()
                    .marketId(marketId)
                    .userEmail(userEmail)
                    .content(content)
                    .build();
        }
    }

    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Edit{
        private Long reviewId;
        private String content;
    }
}
