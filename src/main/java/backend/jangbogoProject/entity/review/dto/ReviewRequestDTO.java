package backend.jangbogoProject.entity.review.dto;

import backend.jangbogoProject.entity.review.Review;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class ReviewRequestDTO {
    @Getter
    @NoArgsConstructor
    public static class Create{
        @NotNull(message = "매장 아이디는 필수 입력 값입니다.")
        private Long marketId;
        @NotBlank(message = "작성자 이메일은 필수 입력 값입니다.")
        private String userEmail;
        @NotBlank(message = "리뷰는 필수 입력 값입니다.")
        private String content;

        @Builder
        public Create(final Long marketId, final String content) {
            this.marketId = marketId;
            this.content = content;
        }

        public Review toEntity(){
            return Review.builder()
                    .marketId(marketId)
                    .content(content)
                    .build();
        }
    }
}
