import client from './client';

// 매장 리뷰 리스트 API
export const getMarketReviewList = async id => {
  return await client.get(`/markets/${id}/reviews`).then(res => {
    return res.data;
  });
};

// 리뷰 작성 API
export const postMarketReview = async (marketId, userEmail, content) => {
  return await client.post(`/reviews`, {
    marketId,
    userEmail,
    content,
  });
};

// 리뷰 수정 API
export const editMarketReview = async (reviewId, content) => {
  return await client.patch(`/reviews/${reviewId}`, {
    content,
  });
};

// 리뷰 삭제 API
export const deleteMarketReview = async id => {
  return await client.delete(`/reviews/${id}`);
};
