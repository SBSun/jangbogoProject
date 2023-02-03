import React from 'react';
import { Routes, Route } from 'react-router-dom';
import GlobalStyle from './GlobalStyle';
import Home from './pages/Home';
import Category from './pages/Category';
import Search from './pages/Search';
import MyPage from './pages/MyPage';
import LogIn from './pages/LogIn';
import SignUp from './pages/SignUp';

const App = () => {
  return (
    <>
      <GlobalStyle />
      <Routes>
        <Route path='/' element={<Home />} />
        <Route path='/category' element={<Category />} />
        <Route path='/search' element={<Search />} />
        <Route path='/mypage:userId' element={<MyPage />} />
        <Route path='/member/login' element={<LogIn />} />
        <Route path='/member/signup' element={<SignUp />} />
      </Routes>
    </>
  );
};

export default App;
