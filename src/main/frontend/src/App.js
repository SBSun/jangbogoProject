import { useState } from 'react';
import { BrowserRouter, Routes, Route } from 'react-router-dom';
import GlobalStyle from './GlobalStyle';
import Loading from './components/Loading';
import Home from './routes/Home';
import Search from './routes/Search';
import Login from './routes/Login';

const App = () => {
  const [isLoading, setIsLoading] = useState(false);
  return (
    <BrowserRouter>
      <GlobalStyle />
      {isLoading ? (
        <Loading />
      ) : (
        <Routes>
          <Route path='/' element={<Home />} />
          <Route path='/search' element={<Search />} />
          <Route path='/login' element={<Login />} />
        </Routes>
      )}
    </BrowserRouter>
  );
};

export default App;
