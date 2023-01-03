import React, { useState } from 'react';
import styled from 'styled-components';
import { Link } from 'react-router-dom';
import Nav from '../components/Nav';

const Home = () => {
  const BannerStyle = styled.section`
    padding: 80px 0 0 0;
    width: 100vw;
    height: 30vh;
    background-color: var(--green);
  `;
  const LocationConainer = styled.section`
    width: 100vw;
    height: 10vh;
    background-color: var(--yellow);
  `;
  const CategoryContainer = styled.section`
    display: flex;
    justify-content: space-between;
    align-items: center;
    padding: 0.5rem;
  `;
  const CategoryItem = styled.div`
    padding: 0.5rem;
    font-size: 1rem;
  `;
  const DetailContainer = styled.section`
    width: 100vw;
    height: 60vh;
    background-color: var(--blue);
  `;

  const [location, setLocation] = useState(false);

  return (
    <>
      <Nav />
      <BannerStyle />
      <LocationConainer />
      <CategoryContainer>
        <CategoryItem>
          <Link to={'/category'}>축산물</Link>
        </CategoryItem>
        <CategoryItem>
          <Link to={'/category'}>해산물</Link>
        </CategoryItem>
        <CategoryItem>
          <Link to={'/category'}>과일</Link>
        </CategoryItem>
        <CategoryItem>
          <Link to={'/category'}>채소</Link>
        </CategoryItem>
      </CategoryContainer>
      <DetailContainer></DetailContainer>
    </>
  );
};

export default Home;
