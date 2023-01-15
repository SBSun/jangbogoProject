import React, { useState } from 'react';
import styled from 'styled-components';
import Header from '../components/Header';
import Navigation from '../components/Navigation';

const Container = styled.main`
  margin: 56px 0 0 0;
  padding: 1rem;
  text-align: center;

  > input {
    width: 90%;
    padding: 1rem;
    border: none;
    border-radius: 15px;
    background-color: var(--light-gray);
  }
`;
const Recommand = styled.section`
  > h2 {
    padding: 2rem 1rem 1rem 1rem;
    text-align: left;
    font-size: 1.25rem;
    font-weight: 700;
  }
  > ul {
    display: flex;
    align-items: center;
  }
  > ul > li {
    margin: 0 0 0 0.5rem;
    padding: 0.5rem;
    color: var(--green);
    background-color: rgba(85, 239, 196, 0.5);
    border-radius: 15px;
  }
`;

const Search = () => {
  const handleChange = e => {
    console.log(e.target.value);
  };
  const handleKeyDown = e => {
    if (e.key !== 'Enter') return;
    console.log(e.target, e.target.value);
  };
  const [keywords, setKeywords] = useState([
    {
      name: '돼지고기',
    },
    {
      name: '사과',
    },
    {
      name: '고등어',
    },
    {
      name: '이마트',
    },
  ]);
  const recommandList = keywords.map((keyword, index) => (
    <li key={index}>
      <span>{keyword.name}</span>
    </li>
  ));

  return (
    <>
      <Header />
      <Container>
        <input
          type={'text'}
          placeholder={'검색어를 입력해주세요.'}
          onChange={handleChange}
          onKeyDown={handleKeyDown}
        />
        <Recommand>
          <h2>추천 검색어</h2>
          <ul>{recommandList}</ul>
        </Recommand>
      </Container>
      <Navigation />
    </>
  );
};

export default Search;
