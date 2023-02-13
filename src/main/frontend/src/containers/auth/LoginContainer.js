import React, { useReducer } from 'react';
import { useNavigate } from 'react-router-dom';
import Login from '../../components/auth/LogIn';
import { useDispatch } from 'react-redux';
import { login } from '../../lib/api/auth';
import { postLogin } from '../../modules/auth';

function reducer(state, action) {
  return {
    ...state,
    [action.name]: action.value,
  };
}

const LoginContainer = () => {
  const [state, dispatch] = useReducer(reducer, {
    id: '',
    password: '',
  });
  const { id, password } = state;

  const storeDispatch = useDispatch();
  const navigate = useNavigate();

  const moveSignUp = () => navigate('/member/signup');
  const handleInputs = e => {
    dispatch(e.target);
  };
  const onSubmit = e => {
    e.preventDefault();
    const promise = login({ id, password });
    const fetchData = () => {
      promise
        .then(res => {
          console.log(res);
          storeDispatch(
            postLogin({
              id: id,
              accessToken: res.data.accessToken,
              isLogin: true,
            })
          );
          localStorage.setItem(
            'user',
            JSON.stringify({
              id: id,
              refreshToken: res.data.refreshToken,
            })
          );
          alert('로그인되었습니다.');
          navigate('/', true);
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
      id={id}
      password={password}
      handleInputs={handleInputs}
      onSubmit={onSubmit}
      moveSignUp={moveSignUp}
    />
  );
};

export default LoginContainer;
