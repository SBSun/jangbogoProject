import React from 'react';
import { BrowserRouter, Routes, Route } from 'react-router-dom';
import GlobalStyle from './GlobalStyle';
import { useState } from 'react';
import Loading from './components/Loading';
import Home from './routes/Home';
import Search from './routes/Search';
import SignIn from './routes/SignIn';
import SignUp from './routes/SignUp';
import Category from './routes/Category';

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
          <Route path='/search' element={<Search />} />
          <Route path='/signin' element={<SignIn />} />
          <Route path='/signup' element={<SignUp />} />
          <Route path='/category' element={<Category />} />
        </Routes>
      )}
    </BrowserRouter>
  );
};

export default App;
