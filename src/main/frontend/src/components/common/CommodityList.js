import React, { useEffect, useState } from 'react';
import { useSelector } from 'react-redux';
import styled from 'styled-components';
import {
  getCommodityList,
  getCatagoryList,
  getSearchList,
} from '../../lib/api/list';

const CommodityListBlock = styled.ul`
  display: flex;
  overflow-x: auto;

  li {
    padding: 1rem;
  }
  li > img {
    width: 130px;
    height: 160px;
  }
  li > .commodity_info > .market_name {
    color: var(--red);
    font-size: 14px;
  }
  li > .commodity_info > .commodity_name {
    margin: 0.25rem 0 0 0;
    font-weight: 600;
    font-size: 16px;
  }
  li > .commodity_info > .commodity_price {
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
    promise.then(res => setCommoditys(res.infoList));
  };

  useEffect(() => {
    fetchData();
    // eslint-disable-next-line react-hooks/exhaustive-deps
  }, [storeLocationId, curPage, keyword]);

  // 받아온 품목 데이터 동적 생성
  const commodityListItem = commoditys.map((commodity, index) => (
    <li key={index}>
      <img src={''} alt='thumbnail' />
      <dl className='commodity_info'>
        <dd className='market_name'>{commodity.marketName}</dd>
        <dt className='commodity_name'>{commodity.categoryName}</dt>
        <dd className='commodity_price'>{commodity.remarks}</dd>
        <dd className='commodity_price'>{commodity.price}원</dd>
      </dl>
    </li>
  ));

  return (
    <>
      <CommodityListBlock>{commodityListItem}</CommodityListBlock>
    </>
  );
};

export default CommodityList;
