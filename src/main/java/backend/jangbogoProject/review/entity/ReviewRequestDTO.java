package backend.jangbogoProject.review.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class ReviewRequestDTO {
    @Getter
    @NoArgsConstructor
    public static class Save{
        private Long market_id;
        private Long user_id;
        private String content;

        @Builder
        public Save(final Long market_id, final Long user_id, final String content) {
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
}
