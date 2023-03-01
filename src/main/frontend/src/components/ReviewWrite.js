import React, { useState } from 'react';
import { useLocation, useNavigate, useParams } from 'react-router-dom';
import styled from 'styled-components';
import Header from './common/Header';
import { postMarketReview } from '../lib/api/review';

const ReviewWriteForm = styled.form`
  margin: 56px 0 0 0;
  text-align: center;

  .review-content {
    outline: none;
    resize: none;
    border: 1px solid var(--light-gray);
    border-radius: 15px;
    margin: 10vh 0 0 0;
    padding: 1rem;
    width: 80vw;
    height: 50vh;

    @media (min-width: 415px) {
      width: 320px;
      margin: 0 auto;
      margin-top: 10vh;
    }
  }
  .review-content:focus {
    border: 2px solid var(--blue);
  }
`;
const WriteCompleteButton = styled.button`
  outline: none;
  border: none;
  position: fixed;
  bottom: 0;
  left: 0;
  right: 0;
  background: var(--blue);
  color: white;
  text-align: center;
  padding: 1rem 0;
  font-size: 20px;

  @media (min-width: 415px) {
    width: 412px;
    margin: 0 auto;
  }
`;

const ReviewWrite = () => {
  const params = useParams();
  const navigate = useNavigate();
  const location = useLocation();

  const { name, thumbnail } = location.state;

  const [content, setContent] = useState('');

  const user = JSON.parse(sessionStorage.getItem('user'));

  const onChange = e => {
    setContent(e.target.value);
    console.log(content);
  };
  const onSubmit = e => {
    e.preventDefault();

    if (content === '') {
      alert('내용을 입력해주세요.');
    }

    const promise = postMarketReview(params.id, user.email, content);
    const fetchData = () => {
      promise
        .then(res => {
          console.log(res);
          navigate(`/market/${params.id}`, {
            replace: true,
            state: { name: name, thumbnail: thumbnail },
          });
        })
        .catch(error => console.log(error));
    };

    fetchData();
  };

  return (
    <>
      <Header modify={'WHITE_BLOCK'} title={'리뷰 작성하기'} />
      <ReviewWriteForm onSubmit={onSubmit}>
        <textarea
          className='review-content'
          placeholder='리뷰를 작성해주세요.'
          onChange={onChange}
          value={content}
        />
        <WriteCompleteButton>리뷰 작성 완료</WriteCompleteButton>
      </ReviewWriteForm>
    </>
  );
};

export default ReviewWrite;
