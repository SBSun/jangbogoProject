import client from './client';

// 품목 리스트 API
export const getCommodityList = async (id, page, size) => {
  return await client
    .get(`/commodities?guId=${id}&page=${page}&size=${size}`)
    .then(res => {
      return res.data;
    });
};

// 카테고리 별 품목 리스트 API
export const getCatagoryList = async (id, page, size, keyword) => {
  return await client
    .get(
      `/commodities/categories/${keyword}?guId=${id}&page=${page}&size=${size}`
    )
    .then(res => {
      return res.data;
    });
};

// 검색 품목 리스트 API
export const getSearchList = async (id, page, size, keyword) => {
  return await client
    .get(`/commodities/search/${keyword}?guId=${id}&page=${page}&size=${size}`)
    .then(res => {
      return res.data;
    });
};

// 특정 매장 품목 리스트 API
export const getMarketItemList = async (page, size, marketId) => {
  return await client
    .get(`/commodities/markets/${marketId}?page=${page}&size=${size}`)
    .then(res => {
      return res.data;
    });
};

// 지역 내 최저가 품목 리스트 API
export const getLowPriceItemList = async id => {
  return await client.get(`/commodities/lowest?guId=${id}`).then(res => {
    return res.data;
  });
};
