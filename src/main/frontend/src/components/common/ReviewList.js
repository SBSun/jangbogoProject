import React, { useCallback, useEffect, useState } from 'react';
import styled from 'styled-components';
import { MdModeEdit, MdDelete } from 'react-icons/md';
import { getMarketReviewList, deleteMarketReview } from '../../lib/api/review';
import { useNavigate } from 'react-router-dom';

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
        font-weight: 600;
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

const ReviewList = ({ marketId, marketName, thumbnail }) => {
  const [reviews, setReviews] = useState([]);

  const user = JSON.parse(sessionStorage.getItem('user'));

  const navigate = useNavigate();

  useEffect(() => {
    // API에서 받아온 리뷰 데이터 저장
    const promise = getMarketReviewList(marketId);
    const fetchData = () => {
      promise
        .then(data => {
          setReviews(data);
        })
        .catch(error => console.log(error));
    };

    fetchData();
  }, [marketId]);

  // 리뷰 삭제 이벤트
  const onDeleteClick = useCallback(e => {
    const promise = deleteMarketReview(e.target.id);
    const fetchData = () => {
      promise
        .then(res => {
          console.log(res);
          alert('리뷰가 삭제되었습니다.');
          window.location.reload();
        })
        .catch(error => console.log(error));
    };

    fetchData();
  }, []);

  // 리뷰 동적 생성
  const reviewList = reviews.map((review, index) => (
    <li key={index}>
      <div className='review-info'>
        <span>{review.userEmail}</span>
        {user ? (
          review.userEmail === user.email ? (
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
          ) : undefined
        ) : undefined}
      </div>
      <p className='review-content'>{review.content}</p>
      <span className='review-date'>{review.createdDate}</span>
    </li>
  ));

  return (
    <>
      <ReviewListBlock>{reviewList}</ReviewListBlock>
    </>
  );
};

export default ReviewList;
