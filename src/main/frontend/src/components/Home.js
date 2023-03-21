import React from 'react';
import styled from 'styled-components';
import Header from './common/Header';
import Navigation from './common/Navigation';
import SelectLocationContainer from '../containers/SelectLoactionContainer';
import CommodityList from './common/CommodityList';
import MarketList from './common/MarketList';
import Slider from 'react-slick';

const HomeContainer = styled.main`
  margin: 56px 0;
  overflow-x: hidden;

  > .list_title {
    padding: 2.75rem 1rem 0 1rem;
    font-size: 18px;
    font-weight: 500;
  }
`;
const StyledSlider = styled(Slider)`
  div > .banner {
    width: 100vw;
    height: 17.188rem;
    border-bottom: 1px solid var(--light-gray);

    @media (min-width: 415px) {
      width: 25.75rem;
      height: 17.188rem;
    }
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
const settings = {
  dots: true,
  infinite: true,
  speed: 500,
  slidesToShow: 1,
  slidesToScroll: 1,
  autoplay: true,
};

const Home = () => {
  return (
    <>
      <Header modify={'LOGO_BLOCK'} title={''} />
      <SelectLocationContainer />
      <HomeContainer>
        <StyledSlider {...settings}>
          <div>
            <img
              src={'/assets/banner/banner1.jpg'}
              alt='banner'
              className='banner'
            />
          </div>
          <div>
            <img
              src={'/assets/banner/banner2.jpg'}
              alt='banner'
              className='banner'
            />
          </div>
          <div>
            <img
              src={'/assets/banner/banner3.jpg'}
              alt='banner'
              className='banner'
            />
          </div>
        </StyledSlider>
        <h2 className='list_title'>품목 별로 최저가를 보여드려요.</h2>
        <CommodityList modify={'PRICE'} recordSize={1000} />
        <h2 className='list_title'>이 지역의 있는 매장들을 보여드려요.</h2>
        <MarketList />
        <Contact>
          <div>
            <p>장보고 - 서울시 식자재 조회</p>
            문의 : <b>hc9064@gmail.com</b>
            <br />
            <br />
            <b>현재 모바일 해상도만 지원하고 있습니다.</b>
          </div>
        </Contact>
      </HomeContainer>
      <Navigation />
    </>
  );
};

export default Home;
