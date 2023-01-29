import React, { useCallback, useState } from 'react';
import { useNavigate } from 'react-router-dom';
import styled from 'styled-components';
import Header from '../components/Header';
import Button from '../components/Button';

const LoginContainer = styled.main`
  margin: 56px 0 0 0;
`;
const LoginForm = styled.form`
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
    margin-top: 0;
  }
`;

const Login = () => {
  const navigate = useNavigate();
  const [email, setEmail] = useState('');
  const [password, setPassword] = useState('');

  const onEmailChange = e => {
    setEmail(e.target.value);
  };
  const onPassChange = e => {
    setPassword(e.target.value);
  };
  const onSubmit = useCallback(
    e => {
      e.preventDefault();
      const getLogin = async () => {
        const json = await (
          await fetch(`/user/login`, {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify({
              id: email,
              password: password,
            }),
          })
        ).json();
        console.log(json);
      };

      getLogin();
      setEmail('');
      setPassword('');
    },
    [email, password]
  );

  return (
    <>
      <Header modify={2} title={'로그인'} />
      <LoginContainer>
        <LoginForm onSubmit={onSubmit}>
          <input
            type={'text'}
            placeholder={'이메일을 입력해주세요.'}
            value={email}
            onChange={onEmailChange}
          />
          <input
            type={'password'}
            placeholder={'비밀번호를 입력해주세요.'}
            value={password}
            onChange={onPassChange}
          />
          <Button type={'submit'}>로그인</Button>
          <Button
            type={'button'}
            inverted={true}
            onClick={() => navigate('/member/signup')}
          >
            회원가입
          </Button>
        </LoginForm>
      </LoginContainer>
    </>
  );
};

export default Login;
