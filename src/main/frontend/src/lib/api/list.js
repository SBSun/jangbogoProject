import client from './client';

export const getCommodityList = async id => {
  const res = await client.get(`/commodity/findCommoditiesInGu?gu_id=${id}`);
  return res.data;
};

export const getMarketList = async id => {
  const res = await client.get(`/market/findMarketsInGu?gu_id=${id}`);
  return res.data;
};

export const getLocationList = () => client.get(`/gu/findAllGuInfo`);
