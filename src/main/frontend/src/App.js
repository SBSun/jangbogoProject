import React from 'react';
import { Routes, Route } from 'react-router-dom';
import GlobalStyle from './GlobalStyle';
import SignUpPage from './pages/SignUpPage';
import HomePage from './pages/HomePage';
import CategoryPage from './pages/CategoryPage';
import CatagoryDetailPage from './pages/CatagoryDetailPage';
import SearchPage from './pages/SearchPage';
import SearchDetailPage from './pages/SearchDetailPage';
import MyPagePage from './pages/MyPagePage';
import LoginPage from './pages/LoginPage';
import MarketDetailPage from './pages/MarketDetailPage';

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
        <Route path='/mypage' element={<MyPagePage />} />
        <Route path='/member/login' element={<LoginPage />} />
        <Route path='/member/signup' element={<SignUpPage />} />
        <Route path='/market/:id' element={<MarketDetailPage />} />
      </Routes>
    </>
  );
};

export default App;
