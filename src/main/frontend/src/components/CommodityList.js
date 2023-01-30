import React, { useEffect, useState } from 'react';
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

const CommodityList = () => {
  const [commoditys, setCommoditys] = useState([]);
  const [isLoading, setIsLoading] = useState(false);

  const getCommodityList = async () => {
    const response = await fetch(
      `/commodity/findCommodityListInGu?gu_id=110000`
    );

    if (!response.ok) {
      const message = `데이터를 불러오지 못했습니다. : ${response.status}`;
      throw new Error(message);
    }

    const data = await response.json();
    console.log(data);
    setCommoditys(data);
  };

  useEffect(() => {
    const fetchData = () => {
      setIsLoading(true);
      getCommodityList();
    };
    fetchData();
    setIsLoading(false);
    // eslint-disable-next-line react-hooks/exhaustive-deps
  }, []);

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
      {isLoading ? (
        <div>Loading...</div>
      ) : (
        <CommodityListBlock>{commodityListItem}</CommodityListBlock>
      )}
    </>
  );
};

export default CommodityList;
