import React from 'react';
import styled from 'styled-components';
import Header from '../components/Header';
import Navigation from '../components/Navigation';

const Container = styled.main`
  margin: 56px 0 0 0;
`;

const MyPage = () => {
  return (
    <>
      <Header modify={'DEFAULT_BLOCK'} title={'내 정보'} />
      <Container>
        <div>내 정보</div>
      </Container>
      <Navigation />
    </>
  );
};

export default MyPage;
