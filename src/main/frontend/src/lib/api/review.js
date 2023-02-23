import client from './client';

// 매장 리뷰 리스트 API
export const getMarketReviewList = async id => {
  const res = await client.get(`/review/findAllByMarketId?marketId=${id}`);
  return res.data;
};

// 리뷰 작성 API
export const postMarketReview = async (id, email, content) => {
  const res = await client.post(`/review/create`, {
    marketId: id,
    userEmail: email,
    content,
  });
  return res;
};
