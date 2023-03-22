import React, { useState } from 'react';
import { useNavigate, useParams } from 'react-router-dom';
import styled from 'styled-components';

import SelectLoactionContainer from '../containers/SelectLoactionContainer';
import CommodityList from './common/CommodityList';
import Header from './common/Header';
import Navigation from './common/Navigation';

const SearchDetail = () => {
  const params = useParams();
  const navigate = useNavigate();

  const [input, setInput] = useState(params.keyword);

  const onChange = e => {
    setInput(e.target.value);
  };

  const onKeyDown = e => {
    if (e.key === 'Enter') {
      navigate(`/search/${input}`);
    }
  };

  return (
    <>
      <Header modify={'WHITE_BLOCK_LOCATION'} title={'검색'} />
      <SelectLoactionContainer />
      <SearchBlock>
        <div className='search-bar'>
          <input
            value={input}
            onChange={onChange}
            onKeyDown={onKeyDown}
            onFocus={() => setInput('')}
            placeholder={'검색어를 입력해주세요.'}
          />
        </div>
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

// CSS
const SearchBlock = styled.main`
  margin: 56px 0;

  .search-bar {
    text-align: center;

    input {
      border: none;
      border-radius: 10px;
      background: var(--light-gray);
      padding: 0.5rem 1rem;
      font-size: 1rem;
      margin: 1rem 0;
    }
  }
`;

export default SearchDetail;
