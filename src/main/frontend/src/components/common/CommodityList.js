import React, { useCallback, useEffect, useState } from 'react';
import { useSelector } from 'react-redux';
import styled from 'styled-components';
import {
  getCommodityList,
  getCatagoryList,
  getSearchList,
  getMarketItemList,
  getLowPriceItemList,
} from '../../lib/api/commodity';

// CSS
const CommodityXScrollBlock = styled.ul`
  display: flex;
  overflow-x: auto;
`;
const CommodityYScrollBlock = styled.ul`
  display: flex;
  flex-wrap: wrap;

  li {
    margin: 0 auto;
    width: 130px;
  }
`;
const CommodityItemStyled = styled.li`
  padding: 0.5rem 1rem;

  img {
    padding: 1rem 0;
    width: 7.5rem;
    height: 7.5rem;
  }
  .commodity_info > .market_name {
    color: var(--gray);
    font-size: 12px;
  }
  .commodity_info > .commodity_name {
    padding: 0.5rem 0 0 0;
    font-weight: 600;
    font-size: 16px;
  }
  .commodity_info > .commodity_remarks {
    padding: 0.5rem 0 0 0;
    color: var(--gray);
    font-size: 14px;
  }
  .commodity_info > .commodity_price {
    padding: 0.5rem 0 0 0;
    color: var(--black);
    font-weight: bold;
    font-size: 18px;
  }
`;
const CommoditySelectPage = styled.ul`
  padding: 0.5rem 1rem;
  display: flex;
  justify-content: center;
  align-items: center;

  li {
    padding: 1rem;
    cursor: pointer;
  }
  li:nth-child(${({ curPage }) => curPage}) {
    color: var(--green);
  }
`;
const EmptyBlock = styled.div`
  margin-top: 30vh;
  text-align: center;
`;
function handleCommodityThumbnail(id) {
  switch (id) {
    // 정육
    case 9:
      return 'egg';
    case 10:
      return 'chicken';
    case 13:
      return 'pork';
    case 21:
      return 'beef';
    // 수산물
    case 6:
      return 'galchi';
    case 7:
      return 'myeolchi';
    case 8:
      return 'mackerel';
    case 12:
      return 'dongtae';
    case 14:
      return 'myeongtae';
    case 25:
      return 'squid';
    case 26:
      return 'zogi';
    // 채소
    case 15:
      return 'radish';
    case 18:
      return 'cabbage';
    case 20:
      return 'lettuce';
    case 22:
      return 'zucchini';
    case 23:
      return 'onion';
    case 24:
      return 'cucumber';
    // 과일
    case 11:
      return 'jujube';
    case 16:
      return 'chestnut';
    case 17:
      return 'pear';
    case 19:
      return 'apple';
    default: {
      return 'none';
    }
  }
}

const CommodityList = ({ modify, recordSize, keyword }) => {
  // 품목 데이터 상태
  const [commoditys, setCommoditys] = useState([]);
  const [isEmpty, setIsEmpty] = useState(false);

  // 페이지 상태
  const [curPage, setCurPage] = useState(1);
  const [endPage, setEndPage] = useState(1);

  // 지역 ID 가져오기
  const sessionLocationId = sessionStorage.getItem('location-id');
  const { storeLocationId } = useSelector(({ location }) => ({
    storeLocationId: location.id,
  }));

  // modify에 따라 다르게 품목을 출력
  const selectAPI = useCallback(() => {
    switch (modify) {
      case 'CATEGORY': {
        return getCatagoryList(sessionLocationId, curPage, recordSize, keyword);
      }
      case 'SEARCH': {
        return getSearchList(sessionLocationId, curPage, recordSize, keyword);
      }
      case 'MARKET': {
        return getMarketItemList(curPage, recordSize, keyword);
      }
      case 'PRICE': {
        return getLowPriceItemList(sessionLocationId);
      }
      default: {
        return getCommodityList(sessionLocationId, curPage, recordSize);
      }
    }
    // eslint-disable-next-line react-hooks/exhaustive-deps
  }, [storeLocationId, curPage, keyword]);

  // API Fetch
  const promise = selectAPI();
  const fetchData = () => {
    promise.then(data => {
      data.data.infoList.length === 0 ? setIsEmpty(true) : setIsEmpty(false);

      setCommoditys(data.data.infoList);
      setEndPage(data.data.pageResponseDTO.endPage);
    });
  };

  useEffect(() => {
    if (endPage === 1) {
      setCurPage(1);
    }

    fetchData();
    // eslint-disable-next-line react-hooks/exhaustive-deps
  }, [storeLocationId, curPage, endPage, keyword]);

  // 받아온 품목 데이터 동적 생성
  const commodityListItem = commoditys.map((commodity, index) => {
    const thumbnail = handleCommodityThumbnail(commodity.category_Id);

    return (
      <CommodityItemStyled key={index}>
        <img src={`/assets/commodity/${thumbnail}.png`} alt='thumbnail' />
        <dl className='commodity_info'>
          <dd className='market_name'>{commodity.marketName}</dd>
          <dt className='commodity_name'>{commodity.categoryName}</dt>
          <dd className='commodity_remarks'>{commodity.remarks}</dd>
          <dd className='commodity_price'>
            {commodity.price
              .toString()
              .replace(/\B(?<!\.\d*)(?=(\d{3})+(?!\d))/g, ',')}
            원
          </dd>
        </dl>
      </CommodityItemStyled>
    );
  });

  // 품목 페이지 이동
  const onPageClick = useCallback(
    e => {
      setCurPage(e.target.value);
      window.scrollTo(0, 0);
    },
    // eslint-disable-next-line react-hooks/exhaustive-deps
    [curPage]
  );

  // 페이지 버튼 배열 생성
  const pageArray = Array(endPage)
    .fill()
    .map((value, index) => index + 1);
  // 데이터 양에 따른 페이지 이동 버튼 동적 생성
  const pageButtons = pageArray.map(element => (
    <li key={element} value={element} onClick={onPageClick}>
      {element}
    </li>
  ));

  // modify 값에 따라 다르게 품목 출력
  const HandleCommodityStyled = () => {
    switch (modify) {
      case 'CATEGORY': {
        return isEmpty ? (
          <EmptyBlock>해당 품목이 없습니다.</EmptyBlock>
        ) : (
          <>
            <CommodityYScrollBlock>{commodityListItem}</CommodityYScrollBlock>
            <CommoditySelectPage curPage={curPage}>
              {pageButtons}
            </CommoditySelectPage>
          </>
        );
      }
      case 'SEARCH': {
        return isEmpty ? (
          <EmptyBlock>해당 품목이 없습니다.</EmptyBlock>
        ) : (
          <>
            <CommodityYScrollBlock>{commodityListItem}</CommodityYScrollBlock>
            <CommoditySelectPage curPage={curPage}>
              {pageButtons}
            </CommoditySelectPage>
          </>
        );
      }
      case 'MARKET': {
        return isEmpty ? (
          <EmptyBlock>해당 품목이 없습니다.</EmptyBlock>
        ) : (
          <>
            <CommodityYScrollBlock>{commodityListItem}</CommodityYScrollBlock>
            <CommoditySelectPage curPage={curPage}>
              {pageButtons}
            </CommoditySelectPage>
          </>
        );
      }
      case 'PRICE': {
        return isEmpty ? (
          <EmptyBlock>해당 품목이 없습니다.</EmptyBlock>
        ) : (
          <CommodityXScrollBlock>{commodityListItem}</CommodityXScrollBlock>
        );
      }
      default: {
        return isEmpty ? (
          <EmptyBlock>해당 품목이 없습니다.</EmptyBlock>
        ) : (
          <CommodityXScrollBlock>{commodityListItem}</CommodityXScrollBlock>
        );
      }
    }
  };

  return (
    <>
      <HandleCommodityStyled />
    </>
  );
};

export default CommodityList;
