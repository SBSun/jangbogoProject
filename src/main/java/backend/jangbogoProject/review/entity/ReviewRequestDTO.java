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
        private Long user_id;
        private String content;

        @Builder
        public Create(final Long market_id, final Long user_id, final String content) {
            this.market_id = market_id;
            this.user_id = user_id;
            this.content = content;
        }

        public Review toEntity(){
            return Review.builder()
                    .market_id(market_id)
                    .user_id(user_id)
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
