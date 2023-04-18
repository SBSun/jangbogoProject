import React from 'react';
import styled from 'styled-components';

import { SiNaver } from 'react-icons/si';
import { RiKakaoTalkFill } from 'react-icons/ri';

import Header from '../common/Header';
import Button from '../common/Button';

const Login = ({
  email,
  password,
  handleInputChange,
  handleSubmit,
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
        <div className='button-group'>
          <a href='/oauth2/authorization/kakao' className='kakao'>
            <RiKakaoTalkFill className='kakao-logo' />
            {/* <span>카카오톡으로 로그인</span> */}
          </a>
          <a href='/oauth2/authorization/naver' className='naver'>
            <SiNaver className='naver-logo' />
            {/* <span>네이버로 로그인</span> */}
          </a>
        </div>
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

    &:nth-child(1) {
      border-radius: 10px;
      margin-bottom: 0.5rem;
    }
    &:nth-child(2) {
      border-radius: 10px;
      margin-bottom: 2rem;
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

  .button-group {
    display: flex;
    justify-content: center;
    align-items: center;

    a {
      width: 10vw;
      text-decoration: none;
      color: white;
      margin: 1rem;
      padding: 1rem;
      display: inherit;
      justify-content: center;
      align-items: center;
      border-radius: 15px;
      cursor: pointer;

      svg {
        font-size: 2rem;
      }
    }

    .kakao {
      flex: 1;
      background: #fee500;
      color: black;
    }
    .naver {
      flex: 1;
      background: #03c75a;
    }
  }
`;

export default Login;
