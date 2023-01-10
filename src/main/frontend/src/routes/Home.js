import React, { useEffect, useState } from 'react';
import styled from 'styled-components';
import Header from '../components/Header';
import Navigation from '../components/Navigation';
import Banner from '../assets/banner.PNG';
import Loading from './Loading';

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
  justify-content: space-around;
  margin: 1rem;
  overflow-x: scroll;

  > li {
    padding: 1rem;
    border: 1px solid var(--light-gray);
  }
  > li > img {
    width: 3rem;
    height: 5rem;
  }
  > li > dl > dt {
    font-weight: 600;
  }
  > li > dl > dd {
    color: var(--yellow);
  }
`;
const MarketList = styled(ItemList)``;
const Detail = styled.div`
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
  const [isLoading, setIsLoading] = useState(false);
  useEffect(() => {
    setTimeout(() => {
      console.log('Fetch is Complete!');
      setIsLoading(false);
    }, 1000);
  }, []);

  const items = [
    {
      id: 1,
      name: 'item1',
      price: 1000,
    },
    {
      id: 2,
      name: 'item2',
      price: 1000,
    },
    {
      id: 3,
      name: 'item3',
      price: 1000,
    },
    {
      id: 4,
      name: 'item4',
      price: 1000,
    },
    {
      id: 5,
      name: 'item5',
      price: 1000,
    },
    {
      id: 6,
      name: 'item6',
      price: 1000,
    },
    {
      id: 7,
      name: 'item7',
      price: 1000,
    },
    {
      id: 8,
      name: 'item8',
      price: 1000,
    },
    {
      id: 9,
      name: 'item9',
      price: 1000,
    },
    {
      id: 10,
      name: 'item10',
      price: 1000,
    },
  ];
  const itemList = items.map(item => (
    <li key={item.id}>
      <img src={Banner} alt='itemImage' />
      <dl>
        <dt>{item.name}</dt>
        <dd>{item.price}</dd>
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
            <MarketList>{itemList}</MarketList>
            <Detail>
              <div>
                <p>사이드 프로젝트 장보고 식자재 조회</p>
                버그 문의 : <b className='content'>hc9064@gmail.com</b>
              </div>
            </Detail>
          </Container>
          <Navigation />
        </>
      )}
    </>
  );
};

export default Home;
