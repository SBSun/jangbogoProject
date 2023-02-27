import React, { useEffect, useState } from 'react';
import { useSelector } from 'react-redux';
import styled from 'styled-components';
import { getMarketList } from '../../lib/api/etc';
import { useNavigate } from 'react-router-dom';

const MarketListBlock = styled.ul`
  display: flex;
  overflow-x: scroll;

  > li {
    padding: 0.5rem 1rem;
  }
  > li > img {
    width: 130px;
    height: 160px;
  }
  > li > .market_name {
    text-align: center;
    color: var(--black);
  }
`;

const MarketList = () => {
  const [markets, setMarkets] = useState([]);

  const navigate = useNavigate();

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
    <li
      key={market.marketId}
      onClick={() => {
        navigate(`/market/${market.marketId}`, {
          state: { name: market.name },
        });
      }}
    >
      <img src={'/assets/svg/thumbnail_market.svg'} alt='thumbnail' />
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
