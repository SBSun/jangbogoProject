import React, { useEffect, useState } from 'react';
import styled from 'styled-components';
import Header from '../components/Header';
import Navigation from '../components/Navigation';
import Login from './LogIn';

const Container = styled.main`
  margin: 56px 0 0 0;
`;

const MyPage = () => {
  const [login, setLogin] = useState('');
  useEffect(() => {
    setLogin(false);
  }, [login]);

  return (
    <>
      {login ? (
        <>
          <Header />
          <Container>
            <div>내 정보</div>
          </Container>
          <Navigation />
        </>
      ) : (
        <>
          <Header modify={2} title={'로그인'} />
          <Container>
            <Login />
          </Container>
          <Navigation />
        </>
      )}
    </>
  );
};

export default MyPage;
