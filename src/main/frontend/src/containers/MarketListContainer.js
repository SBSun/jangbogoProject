import React, { useEffect, useState } from 'react';
import { useSelector } from 'react-redux';
import MarketList from '../components/MarketList';
import { getMarketList } from '../lib/api/list';

const MarketListContainer = () => {
  const [markets, setMarkets] = useState([]);

  const { locationId } = useSelector(({ location }) => ({
    locationId: location.id,
  }));

  const promise = getMarketList(locationId);
  const fetchData = () => {
    promise.then(res => {
      setMarkets(res.marketList);
    });
  };
  useEffect(() => {
    fetchData();
    // eslint-disable-next-line react-hooks/exhaustive-deps
  }, [locationId]);

  return <MarketList markets={markets} />;
};

export default MarketListContainer;
