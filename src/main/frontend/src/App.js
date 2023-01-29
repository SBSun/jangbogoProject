import React, { useEffect, useState } from 'react';
import { Routes, Route } from 'react-router-dom';
import GlobalStyle from './GlobalStyle';
import Home from './routes/Home';
import Category from './routes/Category';
import Search from './routes/Search';
import MyPage from './routes/MyPage';
import LogIn from './routes/LogIn';
import SignUp from './routes/SignUp';

const App = () => {
  const [isLogin, setIslogin] = useState(false);
  const [isVisible, setIsVisible] = useState(false);
  useEffect(() => {
    setIslogin(false);
  }, []);

  const handleLocateVisible = () => {
    setIsVisible(!isVisible);
  };
  return (
    <>
      <GlobalStyle />
      <Routes>
        <Route
          path='/'
          element={
            <Home
              isLogin={isLogin}
              isVisible={isVisible}
              handleLocateVisible={handleLocateVisible}
            />
          }
        />
        <Route
          path='/category'
          element={
            <Category
              isLogin={isLogin}
              isVisible={isVisible}
              handleLocateVisible={handleLocateVisible}
            />
          }
        />
        <Route
          path='/search'
          element={
            <Search
              isLogin={isLogin}
              isVisible={isVisible}
              handleLocateVisible={handleLocateVisible}
            />
          }
        />
        <Route
          path='/mypage'
          element={
            <MyPage
              isLogin={isLogin}
              isVisible={isVisible}
              handleLocateVisible={handleLocateVisible}
            />
          }
        />
        <Route path='/member/login' element={<LogIn />} />
        <Route
          path='/member/signup'
          element={
            <SignUp
              isVisible={isVisible}
              handleLocateVisible={handleLocateVisible}
            />
          }
        />
      </Routes>
    </>
  );
};

export default App;
