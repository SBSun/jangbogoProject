import client from './client';

// 품목 리스트 API
export const getCommodityList = async (id, curPage, recordSize) => {
  return await client
    .get(
      `/commodity/getCommodities?gu_id=${id}&curPage=${curPage}&recordSize=${recordSize}`
    )
    .then(res => {
      return res.data;
    });
};

// 카테고리 별 품목 리스트 API
export const getCatagoryList = async (id, curPage, recordSize, keyword) => {
  return await client
    .get(
      `/commodity/findByCategory?gu_id=${id}&curPage=${curPage}&recordSize=${recordSize}&keyword=${keyword}`
    )
    .then(res => {
      return res.data;
    });
};

// 검색 품목 리스트 API
export const getSearchList = async (id, curPage, recordSize, keyword) => {
  return await client
    .get(
      `/commodity/findByKeyword?gu_id=${id}&curPage=${curPage}&recordSize=${recordSize}&keyword=${keyword}`
    )
    .then(res => {
      return res.data;
    });
};

// 특정 매장 품목 리스트 API
export const getMarketItemList = async (curPage, recordSize, keyword) => {
  return await client
    .get(
      `/commodity/findByMarket?curPage=${curPage}&recordSize=${recordSize}&keyword=${keyword}`
    )
    .then(res => {
      return res.data;
    });
};

// 지역 내 최저가 품목 리스트 API
export const getLowPriceItemList = async id => {
  return await client
    .get(`/commodity/getLowestPriceCommodities?gu_id=${id}`)
    .then(res => {
      return res.data;
    });
};
