import React, { useEffect } from 'react';
import { useDispatch, useSelector } from 'react-redux';
import { useSearchParams } from 'react-router-dom';
import Home from '../components/Home';
import { reissueAuthorizationToken } from '../lib/api/auth';
import { postLogin } from '../modules/auth';

const HomeContainer = () => {
  const user = JSON.parse(sessionStorage.getItem('user'));

  const [searchParams, setSearchParams] = useSearchParams();

  const { accessToken } = useSelector(({ auth }) => ({
    accessToken: auth.login.accessToken,
  }));
  const storeDispatch = useDispatch();

  useEffect(() => {
    if (!user) {
      console.log(searchParams.get('Authorization'));
      return;
    }

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
        })
        .catch(e => console.log(e));
    };
    fetchData();
    // eslint-disable-next-line react-hooks/exhaustive-deps
  }, []);

  return <Home />;
};

export default HomeContainer;
