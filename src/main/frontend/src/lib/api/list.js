import client from './client';

// 품목 리스트 API
export const getCommodityList = async (
  id,
  curPage,
  recordSize,
  keyword,
  searchType
) => {
  const res = await client.get(
    `/commodity/getCommodities?gu_id=${id}&curPage=${curPage}&recordSize=${recordSize}&keyword=${keyword}&searchType=${searchType}`
  );
  return res.data.infoList;
};

// 매장 리스트 API
export const getMarketList = async id => {
  const res = await client.get(`/market/findMarketsInGu?gu_id=${id}`);
  return res.data;
};

// 지역 리스트 API
export const getLocationList = async () => {
  const res = await client.get(`/gu/findAllGuInfo`);
  return res.data.guInfoList;
};
