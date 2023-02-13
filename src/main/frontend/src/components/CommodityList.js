import React from 'react';
import styled from 'styled-components';

const CommodityListBlock = styled.ul`
  display: flex;
  overflow-x: scroll;

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

const CommodityList = ({ commoditys }) => {
  const commodityListItem = commoditys.map((commodity, index) => (
    <li key={index}>
      <img src={''} alt='thumbnail' />
      <dl className='commodity_info'>
        <dd className='market_name'>{commodity.marketName}</dd>
        <dt className='commodity_name'>{commodity.itemName}</dt>
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
