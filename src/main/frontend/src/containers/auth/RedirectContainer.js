import React, { useEffect } from 'react';
import { useDispatch } from 'react-redux';
import { useNavigate, useSearchParams } from 'react-router-dom';
import Redirect from '../../components/auth/Redirect';
import { getUserInfo, setAuthorizationToken } from '../../lib/api/auth';
import { postLogin } from '../../modules/auth';

const RedirectContainer = () => {
  // eslint-disable-next-line no-unused-vars
  const [searchParams, setSearchParams] = useSearchParams();

  const storeDispatch = useDispatch();
  const navigate = useNavigate();

  useEffect(() => {
    let token = searchParams.get('Authorization');
    setAuthorizationToken(token);

    const promise = getUserInfo();
    const fetchData = () => {
      promise
        .then(data => {
          storeDispatch(
            postLogin({
              accessToken: token,
              email: data.data.email,
              name: data.data.name,
              loginType: data.data.loginType,
            })
          );
          console.log(data.data);
        })
        .catch(e => console.log(e));
    };
    fetchData();

    navigate('/', { replace: true });
  });
  return <Redirect />;
};

export default RedirectContainer;
