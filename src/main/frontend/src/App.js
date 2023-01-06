import React from 'react';
import { BrowserRouter, Routes, Route } from 'react-router-dom';
import { useState } from 'react';
import GlobalStyle from './GlobalStyle';
import Loading from './components/Loading';
import Home from './routes/Home';
import SignIn from './routes/SignIn';
import SignUp from './routes/SignUp';
import Search from './routes/Search';
import Category from './routes/Category';
import Mypage from './routes/Mypage';
import Market from './routes/Market';
import Review from './routes/Review';

const App = () => {
  const [isLoading, setIsLoading] = useState(true);
  return (
    <BrowserRouter>
      <GlobalStyle />
      {isLoading ? (
        <Loading />
      ) : (
        <Routes>
          <Route path='/' element={<Home />} />
          <Route path='/signin' element={<SignIn />} />
          <Route path='/signup' element={<SignUp />} />
          <Route path='/mypage' element={<Mypage />} />
          <Route path='/search' element={<Search />} />
          <Route path='/category' element={<Category />} />
          <Route path='/market' element={<Market />} />
          <Route path='/review' element={<Review />} />
        </Routes>
      )}
    </BrowserRouter>
  );
};

export default App;
