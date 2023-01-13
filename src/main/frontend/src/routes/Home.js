import React, { useEffect, useState } from 'react';
import styled from 'styled-components';
import Header from '../components/Header';
import Navigation from '../components/Navigation';
import Banner from '../assets/banner.PNG';

const Container = styled.main`
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
const Location = styled.div`
  > .location_bar {
    border-bottom: 1px solid var(--light-gray);
    padding: 1rem;
    font-size: 1.25rem;
    text-align: center;
    color: var(--black);
  }
  > .location_bar > * {
    margin: 0 0 0 0.5rem;
  }
  > .location_bar > *:first-child {
    margin: 0;
  }
`;
const LocationDetail = styled.div`
  display: none;
`;
const ItemList = styled.ul`
  display: flex;
  overflow-x: scroll;

  > li {
    padding: 1rem;
  }
  > li > img {
    width: 130px;
    height: 160px;
  }
  > li > dl > .market_name {
    color: var(--red);
    font-size: 14px;
  }
  > li > dl > .item_name {
    margin: 0.25rem 0 0 0;
    font-weight: 600;
    font-size: 16px;
  }
  > li > dl > .item_price {
    margin: 0.5rem 0 0 0;
    color: var(--yellow);
    font-size: 16px;
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

const Home = () => {
  const [items, setItem] = useState([]);
  const [locationPosition, setLocation] = useState('종로');
  const getItemList = async () => {
    const json = await (
      await fetch('/commodity/getCommodityListFromGu?gu_id=110000')
    ).json();

    setItem(json.infoList);
    console.log(json);
  };
  useEffect(() => {
    getItemList();
  }, []);

  const itemList = items.map((item, index) => (
    <li key={index}>
      <img src={Banner} alt='itemImage' />
      <dl>
        <dd className='market_name'>{item.marketName}</dd>
        <dt className='item_name'>{item.itemName}</dt>
        <dd className='item_price'>{item.price}원</dd>
      </dl>
    </li>
  ));

  return (
    <>
      <Header />
      <Container>
        <img src={Banner} alt='banner' className='banner' />
        <Location>
          <div className='location_bar'>
            <i className='fa-solid fa-location-dot'></i>
            <span>서울시 {locationPosition}구</span>
            <i className='fa-solid fa-angle-right'></i>
          </div>
          <LocationDetail>
            <h2>지역 선택</h2>
            <ul></ul>
          </LocationDetail>
        </Location>
        <h2 className='list_title'>품목 별로 최저가를 보여드려요.</h2>
        <ItemList>{itemList}</ItemList>
        <h2 className='list_title'>이 지역의 있는 매장들을 보여드려요.</h2>
        <ItemList></ItemList>
        <Contact>
          <div>
            <p>장보고 - 서울시 식자재 조회</p>
            문의 : <b className='content'>hc9064@gmail.com</b>
          </div>
        </Contact>
      </Container>
      <Navigation />
    </>
  );
};

export default Home;
