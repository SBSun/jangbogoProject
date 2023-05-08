import React from 'react';
import styled from 'styled-components';

import Header from '../common/Header';
import Button from '../common/Button';

const Login = ({
  email,
  password,
  handleInputChange,
  handleSubmit,
  onNaverLogin,
  onKakaoLogin,
  moveSignUp,
}) => {
  return (
    <>
      <Header modify={'WHITE_BLOCK'} title={'로그인'} />
      <LoginForm>
        <input
          type={'text'}
          name={'email'}
          placeholder={'이메일을 입력해주세요.'}
          value={email}
          onChange={handleInputChange}
        />
        <input
          type={'password'}
          name={'password'}
          placeholder={'비밀번호를 입력해주세요.'}
          value={password}
          onChange={handleInputChange}
        />
        <Button type={'submit'} onClick={handleSubmit}>
          로그인
        </Button>
        <Button type={'button'} modify={'WHITE_BLOCK'} onClick={moveSignUp}>
          회원가입
        </Button>
      </LoginForm>

      <SocialLoginForm>
        <p>소셜 로그인</p>

        <a href='/oauth2/authorization/naver'>
          <img src='/assets/svg/naver_icon.svg' alt='naver' />
          <span>네이버 로그인</span>
        </a>

        <a href='/oauth2/authorization/kakao'>
          <img src='/assets/svg/kakao_icon.svg' alt='kakao' />
          <span>카카오 로그인</span>
        </a>
      </SocialLoginForm>
    </>
  );
};

// CSS
const LoginForm = styled.form`
  margin: 56px 0 0 0;
  padding: 3rem;
  padding-bottom: 1rem;
  display: flex;
  flex-direction: column;

  input {
    height: 2.5rem;
    border: 1px solid var(--light-gray);
    padding: 0.5rem 1rem;
    border-radius: 12px;

    &:nth-child(1) {
      margin-bottom: 0.5rem;
    }
    &:nth-child(2) {
      margin-bottom: 2rem;
    }
  }

  button {
    cursor: pointer;
    border-radius: 12px;

    &:nth-child(4) {
      margin-top: 0.5rem;
    }
  }
`;

const SocialLoginForm = styled.div`
  margin-top: 1rem;
  display: flex;
  padding: 0 3rem 3rem 3rem;
  flex-direction: column;

  p {
    text-align: center;
    color: var(--gray);
    margin-bottom: 1rem;
  }

  a {
    text-decoration: none;
    padding: 0.5rem;
    border-radius: 12px;
    display: flex;
    justify-content: center;
    align-items: center;
    cursor: pointer;

    img {
      width: 2rem;
    }

    span {
      color: white;
      font-weight: 500;
    }

    &:nth-child(2) {
      background: #03c75a;
    }
    &:last-child {
      margin-top: 0.5rem;
      background: #fee500;

      span {
        color: #000;
        opacity: 0.8;
      }
    }
  }
`;

export default Login;
