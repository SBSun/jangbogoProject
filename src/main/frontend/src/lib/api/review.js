import client from './client';

// 매장 리뷰 리스트 API
export const getMarketReviewList = async id => {
  const res = await client.get(`/review/findAllByMarketId?marketId=${id}`);
  return res.data;
};
