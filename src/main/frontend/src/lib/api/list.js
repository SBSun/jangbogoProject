import client from './client';

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
  console.log(res.data);
  return res.data.infoList;
};

export const getMarketList = async id => {
  const res = await client.get(`/market/findMarketsInGu?gu_id=${id}`);
  return res.data;
};

export const getLocationList = async () => {
  const res = await client.get(`/gu/findAllGuInfo`);
  return res.data.guInfoList;
};
