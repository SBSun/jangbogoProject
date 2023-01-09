import React, { useEffect, useState } from 'react';
import styled from 'styled-components';
import Header from '../components/Header';
import Navigation from '../components/Navigation';
import Banner from '../assets/banner.PNG';

const BannerStyle = styled.img`
  margin: 56px 0 0 0;
  width: 100vw;
  height: 40vh;
  border: 1px solid var(--light-gray);
`;

const Home = () => {
  return (
    <>
      <Header />
      <BannerStyle src={Banner} />
      <Navigation />
    </>
  );
};

export default Home;
