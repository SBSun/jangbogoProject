package backend.jangbogoProject.service;

import backend.jangbogoProject.dto.PageRequestDto;
import backend.jangbogoProject.entity.category.Category;
import backend.jangbogoProject.entity.category.repository.CategoryRepository;
import backend.jangbogoProject.entity.commodity.Commodity;
import backend.jangbogoProject.entity.commodity.dto.CommodityResponseDto;
import backend.jangbogoProject.entity.commodity.repository.CommodityRepository;
import backend.jangbogoProject.entity.commodity.service.CommodityService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import java.awt.print.Pageable;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CommodityServiceTest {

    @InjectMocks
    private CommodityService commodityService;

    @Mock
    private CommodityRepository commodityRepository;
    @Mock
    private CategoryRepository categoryRepository;

    private final Long guId = 170000l;
    private final PageRequestDto pageRequest = new PageRequestDto(1, 10);

    @Test
    public void 품목목록조회()
    {
        // given
        List<CommodityResponseDto.Info> infoList = Arrays.asList(
                new CommodityResponseDto.Info(),
                new CommodityResponseDto.Info(),
                new CommodityResponseDto.Info()
        );

        doReturn(new PageImpl<>(infoList, pageRequest.of(), 3)).when(commodityRepository).getCommodities(guId, pageRequest.of());

        // when
        final Page<CommodityResponseDto.Info> result = commodityService.getCommodities(guId ,pageRequest.of());

        // then
        assertThat(result.getContent().size()).isEqualTo(3);
    }

    @Test
    public void 카테고리별_최저가품목목록조회()
    {
        // given
        doReturn(Arrays.asList(
                new CommodityResponseDto.Info(),
                new CommodityResponseDto.Info(),
                new CommodityResponseDto.Info()
        )).when(commodityRepository).getLowestPriceCommodities(guId);

        // when
        final List<CommodityResponseDto.Info> result = commodityService.getLowestPriceCommodities(guId);

        // then
        assertThat(result.size()).isEqualTo(3);
    }


    @Test
    public void 검색품목목록조회()
    {
        // given
        List<CommodityResponseDto.Info> infoList = Arrays.asList(
                new CommodityResponseDto.Info(),
                new CommodityResponseDto.Info(),
                new CommodityResponseDto.Info()
        );
        final String keyword = "배";
        
        doReturn(new PageImpl<>(infoList, pageRequest.of(), 3)).when(commodityRepository).findByKeyword(guId, keyword, pageRequest.of());

        // when
        final Page<CommodityResponseDto.Info> result = commodityService.findByKeyword(guId, keyword, pageRequest.of());

        // then
        assertThat(result.getContent().size()).isEqualTo(3);
    }


    @Test
    public void 특정매장의품목목록조회()
    {
        // given
        List<CommodityResponseDto.Info> infoList = Arrays.asList(
                new CommodityResponseDto.Info(),
                new CommodityResponseDto.Info(),
                new CommodityResponseDto.Info()
        );
        final Long marketId = 11l;
        
        doReturn(new PageImpl<>(infoList, pageRequest.of(), 3)).when(commodityRepository).findByMarket(marketId, pageRequest.of());

        // when
        final Page<CommodityResponseDto.Info> result = commodityService.findByMarket(marketId ,pageRequest.of());

        // then
        assertThat(result.getContent().size()).isEqualTo(3);
    }

    @Test
    public void 카테고리별품목조회()
    {
        // given
        List<CommodityResponseDto.Info> infoList = Arrays.asList(
                new CommodityResponseDto.Info(),
                new CommodityResponseDto.Info(),
                new CommodityResponseDto.Info()
        );
        final String categoryName = "고등어";
        Category category = new Category(7l, "고등어", 2l, 2);
        doReturn(category).when(categoryRepository).findByName(categoryName);
        doReturn(new PageImpl<>(infoList, pageRequest.of(), 3)).when(commodityRepository).findByChildCategory(guId, category.getId(), pageRequest.of());

        // when
        final Page<CommodityResponseDto.Info> result = commodityService.findByCategory(guId , categoryName, pageRequest.of());

        // then
        assertThat(result.getContent().size()).isEqualTo(3);
    }

}
