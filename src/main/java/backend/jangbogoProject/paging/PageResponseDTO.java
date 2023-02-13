package backend.jangbogoProject.paging;

import lombok.AllArgsConstructor;
import lombok.Builder;

@AllArgsConstructor
@Builder
public class PageResponseDTO {
    private int startPage;      // 화면의 시작 번호
    private int endPage;        // 화면의 끝 번호
    private boolean canPrev;       // 페이징 이전 버튼 활성화 여부
    private boolean canNext;       // 페이징 다음 버튼 활성화 여부
}
