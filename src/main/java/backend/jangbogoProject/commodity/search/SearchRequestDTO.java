package backend.jangbogoProject.commodity.search;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
public class SearchRequestDTO {
    private int curPage;        // 현재 페이지 번호
    private int recordSize;     // 페이지당 출력할 데이터 개수
    private String keyword;     // 검색 키워드
    private String searchType;  // 검색 유형

    public int getOffset() {    // MySQL에서 LIMIT 구문의 시자 부분에 사용되는 메서드
        return (curPage - 1) * recordSize;
    }
}
