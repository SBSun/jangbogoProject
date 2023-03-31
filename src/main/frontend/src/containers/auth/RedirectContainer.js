import React, { useEffect } from 'react';
import { useNavigate, useSearchParams } from 'react-router-dom';
import { useDispatch } from 'react-redux';
import { getUserInfo, setAuthorizationToken } from '../../lib/api/auth';
import { postLogin } from '../../modules/auth';

const Redirect = () => {
  return <div>Redirecting...</div>;
};

const RedirectContainer = () => {
  const [searchParams] = useSearchParams();
  const storeDispatch = useDispatch();
  const navigate = useNavigate();

  useEffect(() => {
    const token = searchParams.get('Authorization');
    setAuthorizationToken(token);

    const fetchData = async () => {
      try {
        const { data } = await getUserInfo();
        storeDispatch(
          postLogin({
            accessToken: token,
            email: data?.email,
            name: data?.name,
            loginType: data?.loginType,
          })
        );
      } catch (error) {
        console.error(error);
      }
    };

    fetchData();
    navigate('/', { replace: true });
  }, [navigate, searchParams, storeDispatch]);

  return <Redirect />;
};

export default RedirectContainer;
