import axios from 'axios';

export const getCommodityList = id =>
  axios.get(`/commodity/findCommodityListInGu?gu_id=${id}`);

export const getMarketList = id =>
  axios.get(`/market/findMarketsInGu?gu_id=${id}`);

export const getLocationList = () => axios.get(`/gu/findAllGuInfo`);
