import client from './client';

// 매장 리스트 API
export const getMarketList = async id => {
  return await client.get(`/markets?guId=${id}`).then(res => {
    return res.data;
  });
};

// 지역 리스트 API
export const getLocationList = async () => {
  return await client.get(`/gus`).then(res => {
    return res.data;
  });
};
