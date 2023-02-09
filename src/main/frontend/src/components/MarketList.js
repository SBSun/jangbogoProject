import React, { useEffect, useState } from 'react';
import styled from 'styled-components';
import { getMarketList } from '../lib/api/list';

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

  useEffect(() => {
    const promise = getMarketList(110000);
    const fetchData = () => {
      promise.then(res => {
        setMarkets(res.marketList);
      });
    };
    fetchData();
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
      <MarketListBlock>{marketListItem}</MarketListBlock>
    </>
  );
};

export default MarketList;
