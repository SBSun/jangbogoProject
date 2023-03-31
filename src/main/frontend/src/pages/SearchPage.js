import React, { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import styled from 'styled-components';

import Header from '../components/common/Header';
import Navigation from '../components/common/Navigation';
import SelectLocationContainer from '../containers/SelectLoactionContainer';

const SearchPage = () => {
  return <Search />;
};

// 추천 검색어
const RECOMMENDED_KEYWORDS = ['돼지고기', '사과', '고등어', '쇠고기', '양파'];

const Search = () => {
  const [input, setInput] = useState('');
  const navigate = useNavigate();

  const onChange = e => setInput(e.target.value);

  const onKeyDown = e => {
    if (e.key === 'Enter') {
      navigate(`/search/${input}`);
    }
  };

  const handleKeywordClick = keyword => {
    navigate(`/search/${keyword}`);
  };

  const recommendedKeywords = RECOMMENDED_KEYWORDS.map(keyword => (
    <li key={keyword} onClick={() => handleKeywordClick(keyword)}>
      <span>{keyword}</span>
    </li>
  ));

  return (
    <>
      <Header modify='DEFAULT_BLOCK' title='검색' />
      <SelectLocationContainer />
      <SearchBlock>
        <input
          type='text'
          placeholder='검색어를 입력해주세요.'
          value={input}
          onChange={onChange}
          onKeyDown={onKeyDown}
        />
        <RecommandBlock>
          <h2>추천 검색어</h2>
          <ul>{recommendedKeywords}</ul>
        </RecommandBlock>
      </SearchBlock>
      <Navigation />
    </>
  );
};

// CSS
const SearchBlock = styled.main`
  margin: 56px 0 0 0;
  padding: 1rem;
  text-align: center;

  input {
    width: 90%;
    padding: 1rem;
    border: none;
    border-radius: 15px;
    background-color: var(--light-gray);
    font-size: 1rem;
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
    cursor: pointer;
  }
`;

export default SearchPage;
