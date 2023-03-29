import React, { useState } from 'react';
import { useLocation, useNavigate, useParams } from 'react-router-dom';
import { useSelector } from 'react-redux';
import styled, { css } from 'styled-components';

import Header from '../components/common/Header';
import Navigation from '../components/common/Navigation';
import CommodityList from '../components/common/CommodityList';
import ReviewList from '../components/common/ReviewList';

const MarketDetailPage = () => {
  return <MarketDetail />;
};

const MarketDetail = () => {
  const location = useLocation();
  const { name, thumbnail } = location.state;
  const { isLogin } = useSelector(state => state.auth);

  const navigate = useNavigate();
  const params = useParams();

  const [activeTab, setActiveTab] = useState('item');

  const handleTabChange = tabName => {
    setActiveTab(tabName);
  };

  const renderTabContent = () => {
    if (activeTab === 'item') {
      return (
        <CommodityList modify='MARKET' recordSize={20} keyword={params.id} />
      );
    }

    if (activeTab === 'review') {
      return (
        <>
          <ReviewList
            marketId={params.id}
            marketName={name}
            thumbnail={thumbnail}
          />
          <MarketReviewBlock
            onClick={() => {
              isLogin
                ? navigate(`/market/${params.id}/write`, {
                    state: { name, thumbnail },
                  })
                : navigate('/member/login');
            }}
          >
            리뷰 작성
          </MarketReviewBlock>
        </>
      );
    }

    return null;
  };

  return (
    <>
      <Header modify='WHITE_BLOCK' title='매장 정보' />
      <MakertInfoBlock>
        <div className='market_info'>
          <img
            src={`/assets/market/${thumbnail}.png`}
            className='market_thumbnail'
            alt='thumbnail'
          />
          <div className='market_text'>
            <h2>{name}</h2>
          </div>
        </div>
        <MarketDetailBlock>
          <TabButton
            active={activeTab === 'item'}
            onClick={() => handleTabChange('item')}
          >
            매장 품목
          </TabButton>
          <TabButton
            active={activeTab === 'review'}
            onClick={() => handleTabChange('review')}
          >
            매장 리뷰
          </TabButton>
        </MarketDetailBlock>
        {renderTabContent()}
      </MakertInfoBlock>
      <Navigation />
    </>
  );
};

// CSS
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
    }
    p {
      margin: 1.5rem 0 0 0;
    }
  }
`;

const MarketDetailBlock = styled.section`
  display: flex;
  align-items: center;
  flex: 2;
`;

const TabButton = styled.span`
  flex: 1;
  font-size: 20px;
  text-align: center;
  padding: 1rem;

  ${({ active }) =>
    active &&
    css`
      color: var(--green);
      border-bottom: 2px solid var(--green);
    `}
`;

const MarketReviewBlock = styled.div`
  position: fixed;
  left: 0;
  right: 0;
  bottom: 57px;
  padding: 1rem 0;
  display: flex;
  justify-content: center;
  align-items: center;
  background: var(--blue);
  color: white;
  font-size: 1.25rem;
  cursor: pointer;

  @media (min-width: 415px) {
    width: 412px;
    margin: 0 auto;
  }
`;

export default MarketDetailPage;
