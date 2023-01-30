import React from 'react';
import { Routes, Route } from 'react-router-dom';
import GlobalStyle from './GlobalStyle';
import Home from './routes/Home';
import Category from './routes/Category';
import Search from './routes/Search';
import MyPage from './routes/MyPage';
import LogIn from './routes/LogIn';
import SignUp from './routes/SignUp';
import { LocationProvider } from './contexts/location';
import { UserProvider } from './contexts/user';

const App = () => {
  return (
    <>
      <GlobalStyle />
      <Routes>
        <Route
          path='/'
          element={
            <LocationProvider>
              <UserProvider>
                <Home />
              </UserProvider>
            </LocationProvider>
          }
        />
        <Route
          path='/category'
          element={
            <LocationProvider>
              <UserProvider>
                <Category />
              </UserProvider>
            </LocationProvider>
          }
        />
        <Route
          path='/search'
          element={
            <LocationProvider>
              <Search />
            </LocationProvider>
          }
        />
        <Route
          path='/mypage'
          element={
            <LocationProvider>
              <MyPage />
            </LocationProvider>
          }
        />
        <Route
          path='/member/login'
          element={
            <LocationProvider>
              <LogIn />
            </LocationProvider>
          }
        />
        <Route
          path='/member/signup'
          element={
            <LocationProvider>
              <SignUp />
            </LocationProvider>
          }
        />
      </Routes>
    </>
  );
};

export default App;
