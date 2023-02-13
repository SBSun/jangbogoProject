package backend.jangbogoProject.paging;

import backend.jangbogoProject.commodity.search.SearchRequestDTO;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Page {
    private int totalDataCount; // 전체 데이터 개수
    private int pageSize;       // 화면 하단에 출력할 페이지 사이즈

    private int curPage;
    private int startPage;      // 화면의 시작 번호
    private int endPage;        // 화면의 끝 번호
    private boolean canPrev;       // 페이징 이전 버튼 활성화 여부
    private boolean canNext;       // 페이징 다음 버튼 활성화 여부

    private SearchRequestDTO searchRequestDTO;

    public Page(SearchRequestDTO searchRequestDTO, int totalDataCount){
        pageSize = 10;

        this.searchRequestDTO = searchRequestDTO;

        this.curPage = searchRequestDTO.getCurPage();
        this.totalDataCount = totalDataCount;

        SetPageData();
    }
    
    private void SetPageData(){
        endPage = (int)(Math.ceil(curPage / (double)pageSize) * pageSize);
        // (현재 페이지 번호 / 화면에 보여질 페이지 번호의 개수) * 화면에 보여질 페이지 번호의 개수
        startPage = (endPage - pageSize) + 1;
        // (마지막 페이지 - 화면에 보여질 페이지 번호의 개수) + 1

        int tempEndPage = (int)(Math.ceil(totalDataCount / (double)searchRequestDTO.getRecordSize()));
        // (전체 데이터 개수 / 한 페이지당 보여줄 데이터의 개수) -> 마지막 페이지 번호를 알 수 있다.

        if(endPage > tempEndPage){
            // 마지막 페이지 번호 = 총 데이터 수 / 한 페이지당 보여줄 데이터의 개수
            endPage = tempEndPage;
        }

        canPrev = startPage == 1 ? false : true;

        canNext = endPage * searchRequestDTO.getRecordSize() >= totalDataCount ? false : true;
    }
}
