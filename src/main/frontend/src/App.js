import React from 'react';
import { BrowserRouter, Routes, Route } from 'react-router-dom';
import { useState } from 'react';
import styled from 'styled-components';
import GlobalStyle from './GlobalStyle';
import Loading from './routes/Loading';
import Home from './routes/Home';
import Category from './routes/Category';
import Search from './routes/Search';
import Mypage from './routes/MyPage';
import SignIn from './routes/SignIn';
import SignUp from './routes/SignUp';
import Market from './routes/Market';
import Review from './routes/Review';

const App = () => {
  const ScreenSize = styled.div`
    display: none;
    width: 100vw;
    font-size: 40px;
    vertical-align: middle;
    text-align: center;
    background-color: var(--black);
    color: white;
    @media screen and (min-width: 600px) {
      display: block;
    }
  `;
  const [isLoading, setIsLoading] = useState(true);
  return (
    <BrowserRouter>
      <GlobalStyle />
      <ScreenSize>모바일 해상도만 지원 중입니다.</ScreenSize>
      {isLoading ? (
        <Loading />
      ) : (
        <Routes>
          <Route path='/' element={<Home />} />
          <Route path='/category' element={<Category />} />
          <Route path='/search' element={<Search />} />
          <Route path='/mypage' element={<Mypage />} />
          <Route path='/signin' element={<SignIn />} />
          <Route path='/signup' element={<SignUp />} />
          <Route path='/market' element={<Market />} />
          <Route path='/review' element={<Review />} />
        </Routes>
      )}
    </BrowserRouter>
  );
};

export default App;
