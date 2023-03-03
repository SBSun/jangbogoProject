import React from 'react';
import styled from 'styled-components';
import Header from './common/Header';
import Navigation from './common/Navigation';
import SelectLocationContainer from '../containers/SelectLoactionContainer';
import CommodityList from './common/CommodityList';
import MarketList from './common/MarketList';

const HomeContainer = styled.main`
  margin: 56px 0;

  > .banner {
    width: 100vw;
    height: 40vh;
    border-bottom: 1px solid var(--light-gray);

    @media (min-width: 415px) {
      width: 412px;
      height: 275px;
    }
  }
  > .list_title {
    padding: 1.75rem 1rem 0 1rem;
    font-size: 18px;
  }
`;
const Contact = styled.div`
  background-color: var(--light-gray);
  margin-top: 0.5rem;
  padding: 1rem;

  > div {
    color: var(--gray);
    font-size: 12px;
    padding: 0.5rem;
  }
  > div > p {
    padding: 0 0 0.5rem 0;
  }
  > div > b {
    color: var(--green);
  }
`;

const Home = () => {
  return (
    <>
      <Header modify={'LOGO_BLOCK'} title={''} />
      <SelectLocationContainer />
      <HomeContainer>
        <img
          src={'/assets/banner/banner1.png'}
          alt='banner'
          className='banner'
        />
        <h2 className='list_title'>품목 별로 최저가를 보여드려요.</h2>
        <CommodityList modify={'PRICE'} recordSize={1000} />
        <h2 className='list_title'>이 지역의 있는 매장들을 보여드려요.</h2>
        <MarketList />
        <Contact>
          <div>
            <p>장보고 - 서울시 식자재 조회</p>
            문의 : <b className='content'>hc9064@gmail.com</b>
          </div>
        </Contact>
      </HomeContainer>
      <Navigation />
    </>
  );
};

export default Home;
