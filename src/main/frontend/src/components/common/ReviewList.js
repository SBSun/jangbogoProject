import React, { useEffect, useState } from 'react';
import styled from 'styled-components';
import { MdModeEdit, MdDelete } from 'react-icons/md';
import { getMarketReviewList } from '../../lib/api/review';

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

const ReviewList = ({ marketId }) => {
  const [reviews, setReviews] = useState([{}]);

  const user = JSON.parse(sessionStorage.getItem('user'));

  const promise = getMarketReviewList(marketId);
  const fetchData = () => {
    promise
      .then(data => {
        setReviews(data);
      })
      .catch(error => console.log(error));
  };
  useEffect(() => {
    fetchData();
    // eslint-disable-next-line react-hooks/exhaustive-deps
  }, [marketId]);

  //   if (reviews === '') return;

  const reviewList = reviews.map((review, index) => (
    <li key={index} id={review.reviewId}>
      <div className='review-info'>
        <span>{review.userEmail}</span>
        {user ? (
          review.userEmail === user.email ? (
            <>
              <MdModeEdit onClick={() => console.log('Edit Click')} />
              <MdDelete onClick={() => console.log('Delete Click')} />
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
