import React, { useCallback, useEffect, useState } from 'react';
import { useNavigate } from 'react-router-dom';
import { useSelector } from 'react-redux';
import { getMarketReviewList, deleteMarketReview } from '../../lib/api/review';
import styled from 'styled-components';

import { MdModeEdit, MdDelete } from 'react-icons/md';

const ReviewList = ({ marketId, marketName, thumbnail }) => {
  // 리뷰 데이터
  const [reviews, setReviews] = useState([]);
  const [isEmpty, setIsEmpty] = useState(false);

  const { isLogin, email } = useSelector(state => state.auth);

  const navigate = useNavigate();

  // API에서 받아온 리뷰 데이터 저장
  const fetchData = useCallback(async () => {
    try {
      const data = await getMarketReviewList(marketId);
      setReviews(data);
      setIsEmpty(false);
    } catch (error) {
      console.log(error);
      setIsEmpty(true);
    }
  }, [marketId]);

  useEffect(() => {
    fetchData();
  }, [fetchData]);

  // 리뷰 삭제 이벤트
  const onDeleteClick = useCallback(e => {
    const reviewId = e.target.id;
    deleteMarketReview(reviewId)
      .then(() => {
        setReviews(prevReviews =>
          prevReviews.filter(review => review.reviewId !== reviewId)
        );
        alert('리뷰가 삭제되었습니다.');
        window.location.reload();
      })
      .catch(error => console.log(error));
  }, []);

  // 리뷰 동적 생성
  const reviewList = reviews.map((review, index) => (
    <li key={review.reviewId}>
      <div className='review-info'>
        <span>{review.userEmail}</span>
        {isLogin ? (
          review.userEmail === email ? (
            <>
              <MdModeEdit
                id={review.reviewId}
                onClick={e => {
                  console.log(e.target.id);
                  navigate(`/market/${marketId}/${e.target.id}`, {
                    state: { name: marketName, thumbnail: thumbnail },
                  });
                }}
              />
              <MdDelete id={review.reviewId} onClick={onDeleteClick} />
            </>
          ) : null
        ) : null}
      </div>
      <p className='review-content'>{review.content}</p>
      <span className='review-date'>{review.createdDate}</span>
    </li>
  ));

  return isEmpty ? (
    <EmptyBlock>
      <span>리뷰가 없습니다.</span>
    </EmptyBlock>
  ) : (
    <>
      <ReviewListBlock>{reviewList}</ReviewListBlock>
    </>
  );
};

// CSS
const ReviewListBlock = styled.ul`
  padding: 1rem;
  margin-bottom: 52px;

  li {
    display: flex;
    flex-direction: column;
    padding: 1rem 0 0.5rem 0;
    border-bottom: 1px solid var(--light-gray);

    .review-info {
      display: inherit;
      padding: 0 0 0.75rem 0;

      span {
        margin-right: auto;
      }
      svg {
        flex: none;
        padding: 0 0.5rem;
        cursor: pointer;
        color: var(--gray);
      }
    }
    .review-content {
      padding: 0.5rem 0 1rem 0;
      font-size: 14px;
    }
    .review-date {
      color: var(--gray);
      font-size: 12px;
    }
  }
`;

const EmptyBlock = styled.div`
  min-height: 45vh;
  display: flex;
  justify-content: center;
  align-items: center;

  @media screen and (max-height: 568px) {
    min-height: 30vh;
  }
`;

export default React.memo(ReviewList);
