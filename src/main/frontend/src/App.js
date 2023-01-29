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
  const [location, setLocation] = useState({ id: 110000, name: '종로' });
  useEffect(() => {
    setIslogin(false);
    setLocation({ id: 110000, name: '종로' });
  }, []);
  return (
    <>
      <GlobalStyle />
      <Routes>
        <Route
          path='/'
          element={<Home location={location} isLogin={isLogin} />}
        />
        <Route
          path='/category'
          element={<Category location={location} isLogin={isLogin} />}
        />
        <Route
          path='/search'
          element={<Search location={location} isLogin={isLogin} />}
        />
        <Route
          path='/mypage'
          element={<MyPage location={location} isLogin={isLogin} />}
        />
        <Route path='/member/login' element={<LogIn />} />
        <Route path='/member/signup' element={<SignUp />} />
      </Routes>
    </>
  );
};

export default App;
