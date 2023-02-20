import client from './client';

// 매장 리스트 API
export const getMarketList = async id => {
  const res = await client.get(`/market/findMarketsInGu?gu_id=${id}`);
  return res.data;
};

// 지역 리스트 API
export const getLocationList = async () => {
  const res = await client.get(`/gu/findAllGuInfo`);
  return res.data;
};
