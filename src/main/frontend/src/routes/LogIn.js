import React from 'react';
import styled from 'styled-components';

const LoginForm = styled.form`
  padding: 1rem;
  display: flex;
  flex-direction: column;
  text-align: center;
`;

const Login = () => {
  return (
    <>
      <LoginForm>
        <input type={'text'} placeholder={'이메일을 입력해주세요.'} />
        <input type={'text'} placeholder={'비밀번호를 입력해주세요.'} />
        <button>로그인</button>
        <button>회원가입</button>
      </LoginForm>
    </>
  );
};

export default Login;
