import React, { useEffect, useState } from 'react';
import styled from 'styled-components';
import Header from './common/Header';
import Navigation from './common/Navigation';
import SelectLocationContainer from '../containers/SelectLoactionContainer';
import { useNavigate } from 'react-router-dom';

// CSS
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
    font-size: 18px;
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

const Search = () => {
  // 검색 입력 값, 추천 검색어 상태
  const [input, setInput] = useState('');
  const [keywords, setKeywords] = useState([{}]);

  const navigate = useNavigate();

  useEffect(() => {
    setKeywords(recommandKeyword);
  }, []);

  // 추천 검색어 동적 생성
  const recommandList = keywords.map((keyword, index) => (
    <li
      key={index}
      onClick={() => {
        navigate(`/search/${keyword.name}`);
      }}
    >
      <span>{keyword.name}</span>
    </li>
  ));

  // 검색 입력 값 관리
  const onChange = e => {
    setInput(e.target.value);
  };
  // 엔터 누르면 키워드 저장 및 페이지 이동
  const onKeyDown = e => {
    if (e.key !== 'Enter') return;
    navigate(`/search/${input}`);
  };

  return (
    <>
      <Header modify={'DEFAULT_BLOCK'} title={'검색'} />
      <SelectLocationContainer />
      <SearchBlock>
        <input
          type={'text'}
          placeholder={'검색어를 입력해주세요.'}
          value={input}
          onChange={onChange}
          onKeyDown={onKeyDown}
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
