import React, { useEffect, useState } from 'react';
import { useSelector } from 'react-redux';
import styled from 'styled-components';
import { getCommodityList } from '../../lib/api/list';

const CommodityListBlock = styled.ul`
  > li {
    padding: 1rem;
  }
  > li > img {
    width: 130px;
    height: 160px;
  }
  > li > .commodity_info > .market_name {
    color: var(--red);
    font-size: 14px;
  }
  > li > .commodity_info > .commodity_name {
    margin: 0.25rem 0 0 0;
    font-weight: 600;
    font-size: 16px;
  }
  > li > .commodity_info > .commodity_price {
    margin: 0.5rem 0 0 0;
    color: var(--yellow);
    font-size: 16px;
  }
`;

const CommodityList = ({
  modify,
  curPage,
  recordSize,
  keyword,
  searchType,
}) => {
  const [commoditys, setCommoditys] = useState([]);

  const sessionLocationId = sessionStorage.getItem('location-id');
  const { storeLocationId } = useSelector(({ location }) => ({
    storeLocationId: location.id,
  }));

  const promise = getCommodityList(sessionLocationId, curPage, recordSize);
  const fetchData = () => {
    promise.then(res => setCommoditys(res.infoList));
  };

  useEffect(() => {
    fetchData();
    // eslint-disable-next-line react-hooks/exhaustive-deps
  }, [storeLocationId, curPage, keyword, searchType]);

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
