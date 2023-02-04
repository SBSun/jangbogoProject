import React, { useCallback, useState } from 'react';
import { useNavigate } from 'react-router-dom';
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
        const response = await fetch(`/user/login`, {
          method: 'POST',
          headers: { 'Content-Type': 'application/json' },
          body: JSON.stringify({
            id: email,
            password: password,
          }),
        });

        if (!response.ok) {
          const message = `다시 로그인해주세요.`;
          alert(message);
          throw new Error(message);
        }

        const post = await response.json();
        console.log(post);
        alert('로그인되었습니다.');
        // navigate('/', true);
      };

      getLogin();
      setEmail('');
      setPassword('');
    },
    // eslint-disable-next-line react-hooks/exhaustive-deps
    [email, password]
  );

  return (
    <>
      <Header modify={'WHITE_BLOCK'} title={'로그인'} />
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
          modify={'WHITE_BLOCK'}
          onClick={() => navigate('/member/signup')}
        >
          회원가입
        </Button>
      </LoginForm>
    </>
  );
};

export default Login;
