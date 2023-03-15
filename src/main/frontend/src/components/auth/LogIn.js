import React from 'react';
import styled from 'styled-components';
import Header from '../common/Header';
import Button from '../common/Button';
import { SiNaver } from 'react-icons/si';
import { RiKakaoTalkFill } from 'react-icons/ri';

const LoginForm = styled.form`
  margin: 56px 0 0 0;
  padding: 1rem;
  display: flex;
  flex-direction: column;

  > input {
    height: 2.5rem;
    border: 1px solid var(--light-gray);
    padding: 0.5rem 1rem;
    &:nth-child(1) {
      border-radius: 10px 10px 0 0;
    }
    &:nth-child(2) {
      border-radius: 0 0 10px 10px;
      border-top: none;
      margin: 0 0 1rem 0;
    }
  }
  > button:nth-child(4) {
    margin-top: 10px;
  }
`;
const SocialLoginForm = styled.div`
  margin-top: 1rem;
  display: flex;
  flex-direction: column;
  align-items: center;

  p {
    color: var(--gray);
    margin-bottom: 1rem;
  }
  a {
    width: 80%;
    text-decoration: none;
    color: white;
    margin: 1rem 0;
    padding: 1rem;
    display: inherit;
    align-items: center;
    border-radius: 15px;
    cursor: pointer;

    svg {
      padding-right: 1rem;
      font-size: 1.5rem;
    }
    span {
      padding-left: 1rem;
    }
  }
  .kakao {
    background: #fee500;
    color: black;
  }
  .naver {
    background: #03c75a;
  }
`;

const Login = ({ email, password, handleInputs, onSubmit, moveSignUp }) => {
  return (
    <>
      <Header modify={'WHITE_BLOCK'} title={'로그인'} />
      <LoginForm>
        <input
          type={'text'}
          name={'email'}
          placeholder={'이메일을 입력해주세요.'}
          value={email}
          onChange={handleInputs}
        />
        <input
          type={'password'}
          name={'password'}
          placeholder={'비밀번호를 입력해주세요.'}
          value={password}
          onChange={handleInputs}
        />
        <Button type={'submit'} onClick={onSubmit}>
          로그인
        </Button>
        <Button type={'button'} modify={'WHITE_BLOCK'} onClick={moveSignUp}>
          회원가입
        </Button>
      </LoginForm>

      <SocialLoginForm>
        <p>소셜 로그인</p>
        <a href='/oauth2/authorization/kakao' className='kakao'>
          <RiKakaoTalkFill className='kakao-logo' />
          <span>Login With Kakao</span>
        </a>
        <a href='/oauth2/authorization/naver' className='naver'>
          <SiNaver className='naver-logo' />
          <span>Login With Naver</span>
        </a>
      </SocialLoginForm>
    </>
  );
};

export default Login;
