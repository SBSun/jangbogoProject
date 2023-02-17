import React from 'react';
import styled from 'styled-components';
import Header from '../common/Header';
import Button from '../common/Button';

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
    </>
  );
};

export default Login;
