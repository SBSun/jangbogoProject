import React from 'react';
import { Routes, Route } from 'react-router-dom';
import GlobalStyle from './GlobalStyle';
import Home from './routes/Home';
import Category from './routes/Category';
import Search from './routes/Search';
import MyPage from './routes/MyPage';
import LogIn from './routes/LogIn';
import SignUp from './routes/SignUp';

const App = () => {
  return (
    <>
      <GlobalStyle />
      <Routes>
        <Route path='/' element={<Home />} />
        <Route path='/category' element={<Category />} />
        <Route path='/search' element={<Search />} />
        <Route path='/mypage' element={<MyPage />} />
        <Route path='/member/login' element={<LogIn />} />
        <Route path='/member/signup' element={<SignUp />} />
      </Routes>
    </>
  );
};

export default App;
