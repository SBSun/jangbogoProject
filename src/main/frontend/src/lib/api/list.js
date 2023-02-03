import client from './client';

export const getCommodityList = id =>
  client.get(`/commodity/findCommodityListInGu?gu_id=${id}`);

export const getMarketList = id =>
  client.get(`/market/findMarketsInGu?gu_id=${id}`);

export const getLocationList = () => client.get(`/gu/findAllGuInfo`);
