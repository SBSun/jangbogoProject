package backend.jangbogoProject.commodity.search;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class SearchController {
    private final SearchService searchService;

    @GetMapping("/search")
    private SearchResponseDto.SearchDataList search(int gu_id, String find){
        SearchResponseDto.SearchDataList searchDataList = searchService.search(gu_id, find);

        return searchDataList;
    }
}
