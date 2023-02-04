import React, { useState } from 'react';
import styled, { css } from 'styled-components';
import Button from '../common/Button';
import Header from '../common/Header';
import Navigation from '../common/Navigation';

const SignUpForm = styled.form`
  margin: 56px 0;
  padding: 1rem;
  display: flex;
  flex-direction: column;

  > .email-block {
    display: inherit;
    justify-content: space-between;
    align-items: center;
  }
  > .password-block,
  .user-block {
    display: inherit;
    flex-direction: inherit;
    margin: 1rem 0;
  }
  > .user-block > .address-block {
    margin: 0 0 1rem 0;

    > span {
      color: var(--black);
      font-size: 1.25rem;
      font-weight: 600;
      margin: 0 1rem 0 0.5rem;
    }
  }
`;
const SignUpLabel = styled.label`
  color: var(--black);
  padding: 1rem 0 0.5rem 0.5rem;
`;
const SignUpInput = styled.input`
  height: 2.5rem;
  border: 1px solid var(--light-gray);
  padding: 0.5rem 1rem;
  border-radius: 10px;
`;
const SignUpButton = styled(Button)`
  height: 2.5rem;
`;
const ErrorMessage = styled.p`
  font-size: 8px;
  color: var(--green);
  padding: 0.5rem 0 0 0.5rem;

  ${({ isError }) =>
    isError &&
    css`
      color: var(--red);
    `};
`;

const SignUp = ({
  email,
  password,
  passwordConfirm,
  name,
  address,
  onSubmit,
}) => {
  const [isError, setIsError] = useState({
    email: false,
    password: false,
  });

  return (
    <>
      <Header modify={'WHITE_BLOCK_LOCATION'} title={'회원가입'} />
      <SignUpForm>
        <SignUpLabel>이메일</SignUpLabel>
        <div className='email-block'>
          <SignUpInput
            placeholder='이메일을 입력해주세요.'
            className='email'
            value={email}
          />
          <SignUpButton
            type={'button'}
            modify={'WHITE_BLOCK'}
            onClick={() =>
              setIsError({
                ...isError,
                email: !isError.email,
              })
            }
          >
            중복 확인
          </SignUpButton>
        </div>
        <ErrorMessage isError={isError.email}>
          {isError.email ? '중복된 이메일입니다.' : '사용 가능한 이메일입니다.'}
        </ErrorMessage>
        <div className='password-block'>
          <SignUpLabel>비밀번호</SignUpLabel>
          <SignUpInput
            type={'password'}
            placeholder='비밀번호를 입력해주세요.'
            value={password}
          />
          <SignUpLabel>비밀번호 확인</SignUpLabel>
          <SignUpInput
            type={'password'}
            placeholder='비밀번호를 한 번 더 입력해주세요.'
            value={passwordConfirm}
          />
          <ErrorMessage isError={isError.password}>
            {isError.password
              ? '비밀번호가 다릅니다.'
              : '사용 가능한 비밀번호입니다.'}
          </ErrorMessage>
        </div>
        <div className='user-block'>
          <SignUpLabel>이름</SignUpLabel>
          <SignUpInput placeholder='이름' value={name} />
          <SignUpLabel>주소</SignUpLabel>
          <div className='address-block'>
            <span>서울시</span>
            <SignUpInput placeholder='OO구' value={address} />
          </div>
        </div>
        <SignUpButton type={'submit'} onClick={onSubmit}>
          가입하기
        </SignUpButton>
      </SignUpForm>
      <Navigation />
    </>
  );
};

export default SignUp;
