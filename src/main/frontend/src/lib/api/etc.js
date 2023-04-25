import client from './client';

// 매장 리스트 API
export const getMarketList = async id => {
  return await client.get(`/market/findMarketsInGu?guId=${id}`).then(res => {
    return res.data;
  });
};

// 지역 리스트 API
export const getLocationList = async () => {
  return await client.get(`/gu/findAllGuInfo`).then(res => {
    return res.data;
  });
};
