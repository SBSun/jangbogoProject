import React, { useCallback, useEffect, useState } from 'react';
import { useSelector } from 'react-redux';
import styled from 'styled-components';
import {
  getCommodityList,
  getCatagoryList,
  getSearchList,
  getMarketItemList,
} from '../../lib/api/commodity';
import thumbnail from '../../assets/thumbnail_commo.svg';

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
    width: 130px;
    height: 160px;
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

const CommodityList = ({ modify, recordSize, keyword }) => {
  // 품목 데이터 상태
  const [commoditys, setCommoditys] = useState([]);

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
      default: {
        return getCommodityList(sessionLocationId, curPage, recordSize);
      }
    }
    // eslint-disable-next-line react-hooks/exhaustive-deps
  }, [storeLocationId, keyword]);

  // API Fetch
  const promise = selectAPI();
  const fetchData = () => {
    promise.then(data => {
      setCommoditys(data.infoList);
      setEndPage(data.pageResponseDTO.endPage);
    });
  };

  useEffect(() => {
    fetchData();
    // eslint-disable-next-line react-hooks/exhaustive-deps
  }, [storeLocationId, curPage, keyword]);

  // 받아온 품목 데이터 동적 생성
  const commodityListItem = commoditys.map((commodity, index) => (
    <CommodityItemStyled key={index}>
      <img src={thumbnail} alt='thumbnail' />
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
  ));

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
        return (
          <>
            <CommodityYScrollBlock>{commodityListItem}</CommodityYScrollBlock>
            <CommoditySelectPage curPage={curPage}>
              {pageButtons}
            </CommoditySelectPage>
          </>
        );
      }
      case 'SEARCH': {
        return (
          <>
            <CommodityYScrollBlock>{commodityListItem}</CommodityYScrollBlock>
            <CommoditySelectPage curPage={curPage}>
              {pageButtons}
            </CommoditySelectPage>
          </>
        );
      }
      case 'MARKET': {
        return (
          <>
            <CommodityYScrollBlock>{commodityListItem}</CommodityYScrollBlock>
            <CommoditySelectPage curPage={curPage}>
              {pageButtons}
            </CommoditySelectPage>
          </>
        );
      }
      default: {
        return (
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
