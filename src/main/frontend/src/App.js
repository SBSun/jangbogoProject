import React from 'react';
import { Routes, Route } from 'react-router-dom';

import GlobalStyle from './GlobalStyle';

import HomePage from './pages/HomePage';
import CategoryPage from './pages/CategoryPage';
import CatagoryDetailPage from './pages/CatagoryDetailPage';
import SearchPage from './pages/SearchPage';
import SearchDetailPage from './pages/SearchDetailPage';
import MyPagePage from './pages/MyPagePage';
import AccountSettingPage from './pages/AccountSettingPage';
import LoginPage from './pages/LoginPage';
import SignUpPage from './pages/SignUpPage';
import RedirectPage from './pages/RedirectPage';
import MarketDetailPage from './pages/MarketDetailPage';
import ReviewWritePage from './pages/ReviewWritePage';
import ReviewEditPage from './pages/ReviewEditPage';

import 'slick-carousel/slick/slick.css';
import 'slick-carousel/slick/slick-theme.css';

const App = () => {
  return (
    <>
      <GlobalStyle />
      <Routes>
        <Route path='/' element={<HomePage />} />

        <Route path='/category' element={<CategoryPage />} />
        <Route path='/category/:name' element={<CatagoryDetailPage />} />

        <Route path='/search' element={<SearchPage />} />
        <Route path='/search/:keyword' element={<SearchDetailPage />} />

        <Route path='/member/login' element={<LoginPage />} />
        <Route path='/member/signup' element={<SignUpPage />} />
        <Route path='/member/redirect' element={<RedirectPage />} />

        <Route path='/mypage' element={<MyPagePage />} />
        <Route path='/mypage/account' element={<AccountSettingPage />} />

        <Route path='/market/:id' element={<MarketDetailPage />} />
        <Route path='/market/:id/write' element={<ReviewWritePage />} />
        <Route path='/market/:id/:reviewId' element={<ReviewEditPage />} />
      </Routes>
    </>
  );
};

export default App;
