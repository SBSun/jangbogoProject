import React, { useEffect, useState } from 'react';
import styled from 'styled-components';

const MarketListBlock = styled.ul`
  display: flex;
  overflow-x: scroll;

  > li {
    padding: 1rem;
  }
  > li > img {
    width: 130px;
    height: 160px;
  }
  > li > .market_name {
    margin: 0.25rem 0 0 0;
    font-weight: 600;
    font-size: 16px;
    color: var(--black);
  }
`;

const MarketList = () => {
  const [markets, setMarkets] = useState([]);
  const [isLoading, setIsLoading] = useState(false);

  const getMarketList = async () => {
    const response = await fetch(`/market/findMarketsInGu?gu_id=110000`);

    if (!response.ok) {
      const message = `데이터를 불러오지 못했습니다. : ${response.status}`;
      throw new Error(message);
    }

    const data = await response.json();
    console.log(data);
    setMarkets(data.marketList);
  };

  useEffect(() => {
    const fetchData = () => {
      setIsLoading(true);
      getMarketList();
    };
    fetchData();
    setIsLoading(false);
    // eslint-disable-next-line react-hooks/exhaustive-deps
  }, []);

  const marketListItem = markets.map(market => (
    <li key={market.marketId}>
      <img src={''} alt='thumbnail' />
      <div className='market_name'>{market.name}</div>
    </li>
  ));
  return (
    <>
      {isLoading ? (
        <div>Loading...</div>
      ) : (
        <MarketListBlock>{marketListItem}</MarketListBlock>
      )}
    </>
  );
};

export default MarketList;
