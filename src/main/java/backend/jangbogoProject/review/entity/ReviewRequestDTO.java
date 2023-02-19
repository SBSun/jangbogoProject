package backend.jangbogoProject.review.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class ReviewRequestDTO {
    @Getter
    @NoArgsConstructor
    public static class Create{
        private Long market_id;
        private String user_email;
        private String content;

        @Builder
        public Create(final Long market_id, final String user_email, final String content) {
            this.market_id = market_id;
            this.user_email = user_email;
            this.content = content;
        }

        public Review toEntity(){
            return Review.builder()
                    .market_id(market_id)
                    .user_email(user_email)
                    .content(content)
                    .build();
        }
    }

    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Edit{
        private Long review_id;
        private String content;
    }
}
