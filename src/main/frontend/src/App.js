import React from 'react';
import { Routes, Route } from 'react-router-dom';
import GlobalStyle from './GlobalStyle';
import Home from './pages/Home';
import Category from './pages/Category';
import Search from './pages/Search';
import MyPage from './pages/MyPage';
import LogIn from './pages/LogIn';
import SignUp from './pages/SignUp';
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
              <UserProvider>
                <Search />
              </UserProvider>
            </LocationProvider>
          }
        />
        <Route
          path='/mypage'
          element={
            <LocationProvider>
              <UserProvider>
                <MyPage />
              </UserProvider>
            </LocationProvider>
          }
        />
        <Route
          path='/member/login'
          element={
            <LocationProvider>
              <UserProvider>
                <LogIn />
              </UserProvider>
            </LocationProvider>
          }
        />
        <Route
          path='/member/signup'
          element={
            <LocationProvider>
              <UserProvider>
                <SignUp />
              </UserProvider>
            </LocationProvider>
          }
        />
      </Routes>
    </>
  );
};

export default App;
