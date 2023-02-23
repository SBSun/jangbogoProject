import React, { useState } from 'react';
import { useLocation, useParams } from 'react-router-dom';
import styled from 'styled-components';
import Header from './common/Header';
import Navigation from './common/Navigation';
import thumbnail from '../assets/thumbnail_market.svg';
import CommodityList from './common/CommodityList';

const MakertInfoBlock = styled.section`
  margin: 56px 0;
  display: flex;
  flex-direction: column;

  .market_thumbnail {
    padding: 1rem;
    width: 120px;
    height: 120px;
  }
  .market_info {
    display: inherit;
    flex-direction: row;
  }
  .market_text {
    display: inherit;
    flex-direction: column;

    h2 {
      margin: 1rem 0 0 0;
      font-size: 20px;
      font-weight: 600;
    }
    p {
      margin: 1.5rem 0 0 0;
    }
  }
`;
const MarketDetailBlock = styled.section`
  display: flex;
  align-items: center;

  input {
    display: none;
  }
  label {
    font-size: 20px;
    text-align: center;
    padding: 1rem;
    flex: 1;
  }

  input:checked {
    + label {
      color: var(--green);
      border-bottom: 2px solid var(--green);
    }
  }
`;

const MarketDetail = () => {
  const location = useLocation();
  const { name } = location.state;

  const [isChecked, setIsChecked] = useState({
    item: true,
    review: false,
  });

  const params = useParams();

  return (
    <>
      <Header modify={'WHITE_BLOCK'} title={'매장 정보'} />
      <MakertInfoBlock>
        <div className='market_info'>
          <img src={thumbnail} className='market_thumbnail' alt='thumbnail' />
          <div className='market_text'>
            <h2>{name}</h2>
            <p>상세 정보</p>
          </div>
        </div>
        <MarketDetailBlock>
          <input
            type={'checkbox'}
            id={'market_item'}
            checked={isChecked.item}
            onChange={() => console.log(isChecked)}
            onClick={() => setIsChecked({ item: true, review: false })}
          />
          <label htmlFor='market_item'>매장 품목</label>
          <input
            type={'checkbox'}
            id={'market_review'}
            checked={isChecked.review}
            onChange={() => console.log(isChecked)}
            onClick={() => setIsChecked({ item: false, review: true })}
          />
          <label htmlFor='market_review'>매장 리뷰</label>
        </MarketDetailBlock>
        {isChecked.item ? (
          <CommodityList
            modify={'MARKET'}
            recordSize={20}
            keyword={params.id}
          />
        ) : (
          <div>review</div>
        )}
      </MakertInfoBlock>
      <Navigation />
    </>
  );
};

export default MarketDetail;