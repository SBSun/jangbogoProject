import client from './client';

// 품목 리스트 API
export const getCommodityList = async (id, curPage, recordSize) => {
  const res = await client.get(
    `/commodity/getCommodities?gu_id=${id}&curPage=${curPage}&recordSize=${recordSize}`
  );
  return res.data;
};

// 카테고리 별 품목 리스트 API
export const getCatagoryList = async (id, curPage, recordSize, keyword) => {
  const res = await client.get(
    `/commodity/findByCategory?gu_id=${id}&curPage=${curPage}&recordSize=${recordSize}&keyword=${keyword}`
  );
  return res.data;
};

// 검색 품목 리스트 API
export const getSearchList = async (id, curPage, recordSize, keyword) => {
  const res = await client.get(
    `/commodity/findByKeyword?gu_id=${id}&curPage=${curPage}&recordSize=${recordSize}&keyword=${keyword}`
  );
  return res.data;
};

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
