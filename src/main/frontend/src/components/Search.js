import React, { useEffect, useState } from 'react';
import styled from 'styled-components';
import Header from './common/Header';
import Navigation from './common/Navigation';
import SelectLocationContainer from '../containers/SelectLoactionContainer';

const recommandKeyword = [
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
];

const SearchBlock = styled.main`
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
const RecommandBlock = styled.section`
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
  const [keywords, setKeywords] = useState([{}]);
  const recommandList = keywords.map((keyword, index) => (
    <li key={index}>
      <span>{keyword.name}</span>
    </li>
  ));

  useEffect(() => {
    setKeywords(recommandKeyword);
  }, []);

  const handleChange = e => {
    console.log(e.target.value);
  };
  const handleKeyDown = e => {
    if (e.key !== 'Enter') return;
    console.log(e.target.value);
  };

  return (
    <>
      <Header modify={'DEFAULT_BLOCK'} title={'검색'} />
      <SelectLocationContainer />
      <SearchBlock>
        <input
          type={'text'}
          placeholder={'검색어를 입력해주세요.'}
          onChange={handleChange}
          onKeyDown={handleKeyDown}
        />
        <RecommandBlock>
          <h2>추천 검색어</h2>
          <ul>{recommandList}</ul>
        </RecommandBlock>
      </SearchBlock>
      <Navigation />
    </>
  );
};

export default Search;
