import React, { useEffect, useState } from 'react';
import styled from 'styled-components';
import Loading from '../components/Loading';
import Header from '../components/Header';
import Navigation from '../components/Navigation';
import Banner from '../assets/banner.PNG';

const Container = styled.div`
  margin: 56px 0;

  > .banner {
    width: 100vw;
    height: 40vh;
    border: 1px solid var(--light-gray);
  }
  > .list_title {
    padding: 1rem;
    font-size: 1.25rem;
    font-weight: 600;
  }
`;
const ItemList = styled.ul`
  display: flex;
  margin: 1rem;
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
    font-weight: 600;
    font-size: 16px;
  }
  > li > dl > .item_price {
    color: var(--yellow);
    font-size: 16px;
  }
`;
const MarketList = styled(ItemList)``;
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
  const [isLoading, setIsLoading] = useState(true);
  const [items, setItem] = useState([]);
  const getItemList = async () => {
    const json = await (
      await fetch('/commodity/getCommodityListFromGu?gu_id=110000')
    ).json();

    setItem(json.infoList);
    setIsLoading(false);
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
      {isLoading ? (
        <Loading />
      ) : (
        <>
          <Header />
          <Container>
            <img src={Banner} alt='banner' className='banner' />
            <h2 className='list_title'>품목 별 최저가</h2>
            <ItemList>{itemList}</ItemList>
            <h2 className='list_title'>이 지역의 매장</h2>
            <MarketList></MarketList>
            <Contact>
              <div>
                <p>사이드 프로젝트 장보고 식자재 조회</p>
                버그 문의 : <b className='content'>hc9064@gmail.com</b>
              </div>
            </Contact>
          </Container>
          <Navigation />
        </>
      )}
    </>
  );
};

export default Home;
