import React, { useEffect, useState } from 'react';
import { useNavigate, useParams } from 'react-router-dom';
import styled from 'styled-components';
import SelectLoactionContainer from '../containers/SelectLoactionContainer';
import CommodityList from './common/CommodityList';
import Header from './common/Header';
import Navigation from './common/Navigation';

// CSS
const SearchBlock = styled.main`
  margin: 56px 0;
  text-align: center;

  input {
    border: none;
    border-radius: 10px;
    background: var(--light-gray);
    padding: 0.5rem 1rem;
    font-size: 18px;
    margin: 1rem 0;
  }
`;

const SearchDetail = () => {
  const params = useParams();

  const [input, setInput] = useState('');

  const navigate = useNavigate();

  useEffect(() => {
    setInput(params.keyword);
  }, [params.keyword]);

  const onChange = e => {
    setInput(e.target.value);
  };
  const onKeyDown = e => {
    if (e.key !== 'Enter') return;
    navigate(`/search/${input}`);
  };

  return (
    <>
      <Header modify={'WHITE_BLOCK_LOCATION'} title={'검색'} />
      <SelectLoactionContainer />
      <SearchBlock>
        <input
          value={input}
          onChange={onChange}
          onKeyDown={onKeyDown}
          onFocus={() => setInput('')}
          placeholder={'검색어를 입력해주세요.'}
        />
        <CommodityList
          modify={'SEARCH'}
          recordSize={20}
          keyword={params.keyword}
        />
      </SearchBlock>
      <Navigation />
    </>
  );
};

export default SearchDetail;
