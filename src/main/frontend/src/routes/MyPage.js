import React from 'react';
import styled from 'styled-components';
import Header from '../components/Header';
import Location from '../components/Location';
import Navigation from '../components/Navigation';

const Container = styled.main`
  margin: 56px 0 0 0;
`;

const MyPage = ({ location, isLogin, isVisible, handleLocateVisible }) => {
  return (
    <>
      <Header
        modify={'DEFAULT_BLOCK'}
        title={'내 정보'}
        handleLocateVisible={handleLocateVisible}
      />
      <Location isVisible={isVisible} />
      <Container>
        <div>내 정보</div>
      </Container>
      <Navigation isLogin={isLogin} />
    </>
  );
};

export default MyPage;
