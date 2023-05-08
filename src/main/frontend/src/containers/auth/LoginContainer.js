import React, { useReducer } from 'react';
import { useNavigate } from 'react-router-dom';
import { useDispatch } from 'react-redux';
import { login, setAuthorizationToken } from '../../lib/api/auth';
import { postLogin } from '../../modules/auth';

import Login from '../../components/auth/LogIn';

const initialState = {
  email: '',
  password: '',
};

function loginReducer(state, action) {
  return {
    ...state,
    [action.name]: action.value,
  };
}

function LoginContainer() {
  const [state, dispatch] = useReducer(loginReducer, initialState);
  const { email, password } = state;

  const storeDispatch = useDispatch();
  const navigate = useNavigate();

  const moveSignUp = () => navigate('/member/signup');

  function handleInputChange(event) {
    const { name, value } = event.target;
    dispatch({ name, value });
  }

  async function handleSubmit(event) {
    event.preventDefault();
    try {
      const data = await login(email, password);
      console.log(data);

      storeDispatch(
        postLogin({
          accessToken: data.tokenInfo.accessToken,
          email,
          name: data.name,
          loginType: data.loginType,
        })
      );

      setAuthorizationToken(data.tokenInfo.accessToken);

      alert('로그인되었습니다.');
      navigate('/', { replace: true });
    } catch (error) {
      console.log(error);
      alert('다시 로그인해주세요.');
    }
  }

  function onNaverLogin() {
    navigate('/oauth2/authorization/naver');
  }

  function onKakaoLogin() {
    navigate('/oauth2/authorization/kakao');
  }

  return (
    <Login
      email={email}
      password={password}
      handleInputChange={handleInputChange}
      handleSubmit={handleSubmit}
      onNaverLogin={onNaverLogin}
      onKakaoLogin={onKakaoLogin}
      moveSignUp={moveSignUp}
    />
  );
}

export default LoginContainer;
