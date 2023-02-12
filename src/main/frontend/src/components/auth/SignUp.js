import React from 'react';
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
    flex-direction: inherit;

    > div {
      display: inherit;
      justify-content: space-between;
    }
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
  padding: 0.5rem 0 0 0.5rem;

  ${({ isCheck }) =>
    isCheck
      ? css`
          color: var(--green);
        `
      : css`
          color: var(--red);
        `}
`;

const SignUp = ({
  id,
  password,
  passwordConfirm,
  name,
  address,
  handleInputs,
  emailReg,
  isEmailCheck,
  onCheckEmail,
  passReg,
  onSubmit,
}) => {
  return (
    <>
      <Header modify={'WHITE_BLOCK_LOCATION'} title={'회원가입'} />
      <SignUpForm>
        <section className='email-block'>
          <SignUpLabel>이메일</SignUpLabel>
          <div>
            <SignUpInput
              name='id'
              placeholder='name@example.com'
              value={id}
              onChange={handleInputs}
            />
            <SignUpButton
              type={'button'}
              modify={'WHITE_BLOCK'}
              onClick={onCheckEmail}
            >
              중복 확인
            </SignUpButton>
          </div>
          <ErrorMessage
            isCheck={
              id !== ''
                ? emailReg
                  ? isEmailCheck !== null
                    ? isEmailCheck
                      ? true
                      : false
                    : false
                  : false
                : false
            }
          >
            {id !== ''
              ? emailReg
                ? isEmailCheck === null
                  ? ''
                  : isEmailCheck
                  ? '사용 가능한 이메일입니다.'
                  : '중복된 이메일입니다.'
                : '이메일 형식을 맞춰주세요.'
              : '이메일을 입력해주세요.'}
          </ErrorMessage>
        </section>
        <section className='password-block'>
          <SignUpLabel>비밀번호</SignUpLabel>
          <SignUpInput
            type={'password'}
            name={'password'}
            placeholder='영문 포함 6 ~ 12자 형식으로 입력해주세요.'
            value={password}
            onChange={handleInputs}
          />
          <ErrorMessage
            isCheck={password !== '' ? (passReg ? true : false) : false}
          >
            {password !== ''
              ? passReg
                ? '사용 가능한 비밀번호입니다.'
                : '영문 포함 6 ~ 12자 형식으로 입력해주세요.'
              : '비밀번호를 입력해주세요.'}
          </ErrorMessage>
          <SignUpLabel>비밀번호 확인</SignUpLabel>
          <SignUpInput
            type={'password'}
            name={'passwordConfirm'}
            placeholder='비밀번호를 한 번 더 입력해주세요.'
            value={passwordConfirm}
            onChange={handleInputs}
          />
          <ErrorMessage></ErrorMessage>
        </section>
        <section className='user-block'>
          <SignUpLabel>이름</SignUpLabel>
          <SignUpInput
            name={'name'}
            placeholder='이름'
            value={name}
            onChange={handleInputs}
          />
          <SignUpLabel>주소</SignUpLabel>
          <div className='address-block'>
            <span>서울시</span>
            <SignUpInput
              name={'address'}
              placeholder='OO구'
              value={address}
              onChange={handleInputs}
            />
          </div>
        </section>
        <SignUpButton type={'submit'} onClick={onSubmit}>
          가입하기
        </SignUpButton>
      </SignUpForm>
      <Navigation />
    </>
  );
};

export default SignUp;
