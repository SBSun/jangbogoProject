import React, { useEffect, useState } from 'react';
import { useSelector } from 'react-redux';
import CommodityList from '../components/CommodityList';
import { getCommodityList } from '../lib/api/list';

const CommodityListContainer = () => {
  const [commoditys, setCommoditys] = useState([]);

  const { locationId } = useSelector(({ location }) => ({
    locationId: location.id,
  }));

  const promise = getCommodityList(locationId, 1, 1000);
  const fetchData = () => {
    promise.then(res => setCommoditys(res));
  };
  useEffect(() => {
    fetchData();
    // eslint-disable-next-line react-hooks/exhaustive-deps
  }, [locationId]);

  return <CommodityList commoditys={commoditys} />;
};

export default CommodityListContainer;
