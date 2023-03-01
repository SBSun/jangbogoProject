import React, { useReducer } from 'react';
import { useNavigate } from 'react-router-dom';
import Login from '../../components/auth/LogIn';
import { useDispatch } from 'react-redux';
import { login, setAuthorizationToken } from '../../lib/api/auth';
import { postLogin } from '../../modules/auth';

function reducer(state, action) {
  return {
    ...state,
    [action.name]: action.value,
  };
}

const LoginContainer = () => {
  const [state, dispatch] = useReducer(reducer, {
    email: '',
    password: '',
  });
  const { email, password } = state;

  const storeDispatch = useDispatch();
  const navigate = useNavigate();

  const moveSignUp = () => navigate('/member/signup');

  const handleInputs = e => {
    dispatch(e.target);
  };
  const onSubmit = e => {
    e.preventDefault();
    const promise = login(email, password);
    const fetchData = () => {
      promise
        .then(data => {
          // Store에 로그인 정보 저장
          storeDispatch(
            postLogin({
              email: email,
              accessToken: data.tokenInfo.accessToken,
            })
          );
          // 세션에 로그인 정보 저장
          sessionStorage.setItem(
            'user',
            JSON.stringify({
              email: email,
              name: data.name,
              address: data.address,
              refreshToken: data.tokenInfo.refreshToken,
            })
          );

          // 헤더에 엑세스 토큰 설정
          setAuthorizationToken(data.tokenInfo.accessToken);

          alert('로그인되었습니다.');
          navigate('/', { replace: true });
        })
        .catch(error => {
          console.log(error);
          alert('다시 로그인해주세요.');
        });
    };
    fetchData();
  };

  return (
    <Login
      email={email}
      password={password}
      handleInputs={handleInputs}
      onSubmit={onSubmit}
      moveSignUp={moveSignUp}
    />
  );
};

export default LoginContainer;
