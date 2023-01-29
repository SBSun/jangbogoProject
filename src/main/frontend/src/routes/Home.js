import React from 'react';
import styled from 'styled-components';
import Header from '../components/Header';
import Navigation from '../components/Navigation';
import Banner from '../assets/banner.PNG';
import CommodityList from '../components/CommodityList';
import MarketList from '../components/MarketList';
import SelectLocation from '../components/SelectLocation';

const HomeContainer = styled.main`
  margin: 56px 0;

  > .banner {
    width: 100vw;
    height: 40vh;
    border: 1px solid var(--light-gray);
  }
  > .list_title {
    margin: 1rem 0 0 0;
    padding: 1rem;
    font-size: 1.25rem;
    font-weight: 500;
  }
`;
const Contact = styled.div`
  background-color: var(--light-gray);
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

const Home = ({ isLogin, isVisible, handleLocateVisible }) => {
  return (
    <>
      <Header
        modify={'LOGO_BLOCK'}
        title={''}
        handleLocateVisible={handleLocateVisible}
      />
      <SelectLocation isVisible={isVisible} />
      <HomeContainer>
        <img src={Banner} alt='banner' className='banner' />
        <h2 className='list_title'>품목 별로 최저가를 보여드려요.</h2>
        <CommodityList />
        <h2 className='list_title'>이 지역의 있는 매장들을 보여드려요.</h2>
        <MarketList />
        <Contact>
          <div>
            <p>장보고 - 서울시 식자재 조회</p>
            문의 : <b className='content'>hc9064@gmail.com</b>
          </div>
        </Contact>
      </HomeContainer>
      <Navigation isLogin={isLogin} />
    </>
  );
};

export default Home;
