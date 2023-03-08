import React, { useEffect } from 'react';
import { useDispatch, useSelector } from 'react-redux';
import Home from '../components/Home';
import {
  reissueAuthorizationToken,
  setAuthorizationToken,
} from '../lib/api/auth';
import { postLogin } from '../modules/auth';

const HomeContainer = () => {
  const user = JSON.parse(sessionStorage.getItem('user'));
  const { accessToken } = useSelector(({ auth }) => ({
    accessToken: auth.login.accessToken,
  }));
  const storeDispatch = useDispatch();

  useEffect(() => {
    if (!user) return;

    const refreshToken = user.refreshToken;

    const promise = reissueAuthorizationToken(accessToken, refreshToken);
    const fetchData = () => {
      promise
        .then(res => {
          console.log(res);
          storeDispatch(
            postLogin({
              email: user.email,
              accessToken: res.data.accessToken,
            })
          );
          sessionStorage.setItem(
            'user',
            JSON.stringify({
              ...user,
              refreshToken: res.data.refreshToken,
            })
          );
          setAuthorizationToken(res.data.accessToken);
        })
        .catch(e => console.log(e));
    };
    fetchData();
    // eslint-disable-next-line react-hooks/exhaustive-deps
  }, []);

  return <Home />;
};

export default HomeContainer;
