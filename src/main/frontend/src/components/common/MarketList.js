import React, { useEffect, useState } from 'react';
import { useSelector } from 'react-redux';
import styled from 'styled-components';
import { getMarketList } from '../../lib/api/list';

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

  const sessionLocationId = sessionStorage.getItem('location-id');
  const { storeLocationId } = useSelector(({ location }) => ({
    storeLocationId: location.id,
  }));

  const promise = getMarketList(sessionLocationId);
  const fetchData = () => {
    promise.then(data => {
      setMarkets(data);
    });
  };

  useEffect(() => {
    fetchData();
    // eslint-disable-next-line react-hooks/exhaustive-deps
  }, [storeLocationId]);

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
