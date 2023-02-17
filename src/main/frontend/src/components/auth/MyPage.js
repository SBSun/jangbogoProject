import React from 'react';
import styled from 'styled-components';
import Header from '../common/Header';
import Navigation from '../common/Navigation';
import SelectLocationContainer from '../../containers/SelectLoactionContainer';

const Container = styled.main`
  margin: 56px 0 0 0;
`;

const MyPage = () => {
  const user = JSON.parse(sessionStorage.getItem('user'));

  return (
    <>
      <Header modify={'DEFAULT_BLOCK'} title={'내 정보'} />
      <SelectLocationContainer />
      <Container>
        <div>내 정보</div>
        <span>이메일 : {user.email}</span>
      </Container>
      <Navigation />
    </>
  );
};

export default MyPage;
