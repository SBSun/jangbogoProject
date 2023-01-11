import React, { useEffect, useState } from 'react';
import { BrowserRouter, Routes, Route } from 'react-router-dom';
import GlobalStyle from './GlobalStyle';
import Home from './routes/Home';
import Category from './routes/Category';
import Search from './routes/Search';
import Mypage from './routes/MyPage';
import LogIn from './routes/LogIn';
import SignUp from './routes/SignUp';
import Market from './routes/Market';
import Review from './routes/Review';
import Info from './routes/Info';
import Loading from './components/Loading';

const App = () => {
  const [isLoading, setIsLoading] = useState(true);
  useEffect(() => {
    setTimeout(() => {
      setIsLoading(false);
    }, 2000);
  }, []);
  return (
    <BrowserRouter>
      <GlobalStyle />
      {isLoading ? (
        <Loading />
      ) : (
        <Routes>
          <Route path='/' element={<Home />} />
          <Route path='/category' element={<Category />} />
          <Route path='/search' element={<Search />} />
          <Route path='/mypage' element={<Mypage />} />
          <Route path='/mypage/info' element={<Info />} />
          <Route path='/member/login' element={<LogIn />} />
          <Route path='/member/signup' element={<SignUp />} />
          <Route path='/market' element={<Market />} />
          <Route path='/market/review' element={<Review />} />
        </Routes>
      )}
    </BrowserRouter>
  );
};

export default App;
