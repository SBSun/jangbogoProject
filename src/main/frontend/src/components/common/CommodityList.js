import React, { useEffect, useState } from 'react';
import { useSelector } from 'react-redux';
import styled from 'styled-components';
import {
  getCommodityList,
  getCatagoryList,
  getSearchList,
} from '../../lib/api/list';

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
  padding: 1rem;

  img {
    width: 130px;
    height: 160px;
  }
  .commodity_info > .market_name {
    color: var(--red);
    font-size: 14px;
  }
  .commodity_info > .commodity_name {
    margin: 0.25rem 0 0 0;
    font-weight: 600;
    font-size: 16px;
  }
  .commodity_info > .commodity_price {
    margin: 0.5rem 0 0 0;
    color: var(--yellow);
    font-size: 16px;
  }
`;

const CommodityList = ({ modify, curPage, recordSize, keyword }) => {
  // 품목 데이터 상태
  const [commoditys, setCommoditys] = useState([]);

  // 지역 ID 가져오기
  const sessionLocationId = sessionStorage.getItem('location-id');
  const { storeLocationId } = useSelector(({ location }) => ({
    storeLocationId: location.id,
  }));

  // modify에 따라 다르게 품목을 출력
  const selectAPI = () => {
    switch (modify) {
      case 'CATEGORY': {
        return getCatagoryList(sessionLocationId, curPage, recordSize, keyword);
      }
      case 'SEARCH': {
        return getSearchList(sessionLocationId, curPage, recordSize, keyword);
      }
      default: {
        return getCommodityList(sessionLocationId, curPage, recordSize);
      }
    }
  };

  // API Fetch
  const promise = selectAPI();
  const fetchData = () => {
    promise.then(data => setCommoditys(data.infoList));
  };

  useEffect(() => {
    fetchData();
    // eslint-disable-next-line react-hooks/exhaustive-deps
  }, [storeLocationId, curPage, keyword]);

  // 받아온 품목 데이터 동적 생성
  const commodityListItem = commoditys.map((commodity, index) => (
    <CommodityItemStyled key={index}>
      <img src={''} alt='thumbnail' />
      <dl className='commodity_info'>
        <dd className='market_name'>{commodity.marketName}</dd>
        <dt className='commodity_name'>{commodity.categoryName}</dt>
        <dd className='commodity_price'>{commodity.remarks}</dd>
        <dd className='commodity_price'>{commodity.price}원</dd>
      </dl>
    </CommodityItemStyled>
  ));

  // modify 값에 따라 다르게 품목 출력
  const HandleCommodityStyled = () => {
    switch (modify) {
      case 'CATEGORY': {
        return (
          <CommodityYScrollBlock>{commodityListItem}</CommodityYScrollBlock>
        );
      }
      case 'SEARCH': {
        return (
          <CommodityYScrollBlock>{commodityListItem}</CommodityYScrollBlock>
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
