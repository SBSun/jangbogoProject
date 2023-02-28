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
    padding: 1rem 0;
    width: 7.5rem;
    height: 7.5rem;
  }
  > li > .market_name {
    text-align: center;
    color: var(--black);
  }
`;

function handleMarketThumbnail(name) {
  switch (true) {
    case name.includes('이마트'): {
      return 'emart';
    }
    case name.includes('롯데백화점'): {
      return 'lotte_depart';
    }
    case name.includes('롯데마트'): {
      return 'lotte_mart';
    }
    case name.includes('신세계'): {
      return 'shinsegae';
    }
    case name.includes('농협'): {
      return 'nh';
    }
    case name.includes('홈플러스'): {
      return 'homeplus';
    }
    case name.includes('시장'): {
      return 'tradition';
    }
    default: {
      return 'none';
    }
  }
}

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

  const marketListItem = markets.map(market => {
    const thumbnail = handleMarketThumbnail(market.name);
    console.log(thumbnail);
    return (
      <li
        key={market.marketId}
        onClick={() => {
          navigate(`/market/${market.marketId}`, {
            state: { name: market.name, thumbnail: thumbnail },
          });
        }}
      >
        <img src={`/assets/market/${thumbnail}.png`} alt='thumbnail' />
        <div className='market_name'>{market.name}</div>
      </li>
    );
  });

  return (
    <>
      <MarketListBlock>{marketListItem}</MarketListBlock>
    </>
  );
};

export default MarketList;
