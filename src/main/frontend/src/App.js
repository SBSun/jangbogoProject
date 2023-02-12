import React from 'react';
import { Routes, Route } from 'react-router-dom';
import GlobalStyle from './GlobalStyle';
import SignUpPage from './pages/SignUpPage';
import HomePage from './pages/HomePage';
import CategoryPage from './pages/CategoryPage';
import SearchPage from './pages/SearchPage';
import MyPagePage from './pages/MyPagePage';
import LoginPage from './pages/LoginPage';

const App = () => {
  return (
    <>
      <GlobalStyle />
      <Routes>
        <Route path='/' element={<HomePage />} />
        <Route path='/category' element={<CategoryPage />} />
        <Route path='/search' element={<SearchPage />} />
        <Route path='/mypage' element={<MyPagePage />} />
        <Route path='/member/login' element={<LoginPage />} />
        <Route path='/member/signup' element={<SignUpPage />} />
      </Routes>
    </>
  );
};

export default App;
