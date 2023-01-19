import React, { useState } from 'react';
import { Link } from 'react-router-dom';
import styled from 'styled-components';
import Header from '../components/Header';
import Button from '../components/Button';

const Container = styled.main`
  margin: 56px 0 0 0;
`;
const LoginForm = styled.form`
  padding: 1rem;
  display: flex;
  flex-direction: column;

  > input {
    height: 2.5rem;
    border: 1px solid var(--light-gray);
    padding: 0.5rem;
    &:nth-child(1) {
      border-radius: 10px 10px 0 0;
    }
    &:nth-child(2) {
      border-radius: 0 0 10px 10px;
      margin: 0 0 1rem 0;
    }
  }
  > a {
    height: 2.5rem;
    text-align: center;
    text-decoration: none;
    color: var(--green);
    font-size: 1.25rem;
    font-weight: 600;
    border: 1px solid var(--green);
    border-radius: 5px;
  }
`;

const Login = () => {
  const [input, setInput] = useState({ email: '', password: '' });
  const onSubmit = e => {
    e.preventDefault();
  };
  return (
    <>
      <Header modify={2} title={'로그인'} />
      <Container onSubmit={onSubmit}>
        <LoginForm>
          <input type={'text'} placeholder={'이메일을 입력해주세요.'} />
          <input type={'text'} placeholder={'비밀번호를 입력해주세요.'} />
          <Button>로그인</Button>
          <Link to={'/member/signup'}>회원가입</Link>
        </LoginForm>
      </Container>
    </>
  );
};

export default Login;
