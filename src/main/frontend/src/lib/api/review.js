import client from './client';

// 매장 리뷰 리스트 API
export const getMarketReviewList = async id => {
  const res = await client.get(`/review/findAllByMarketId?marketId=${id}`);
  return res.data;
};

// 리뷰 작성 API
export const postMarketReview = async (id, email, content) => {
  return await client.post(`/review/create`, {
    marketId: id,
    userEmail: email,
    content,
  });
};

// 리뷰 수정 API
export const editMarketReview = async (id, content) => {
  return await client.patch(`/review/edit`, {
    marketId: id,
    content,
  });
};

// 리뷰 삭제 API
export const deleteMarketReview = async id => {
  return await client.delete(`/review/delete?reviewId=${id}`);
};
