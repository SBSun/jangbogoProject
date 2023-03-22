import React from 'react';
import styled from 'styled-components';

import Button from '../common/Button';
import Header from '../common/Header';
import Navigation from '../common/Navigation';
import SelectLocationContainer from '../../containers/SelectLoactionContainer';

const SignUp = ({
  email,
  password,
  passwordConfirm,
  name,
  handleInput,
  validate,
  onCheckEmail,
  onSubmit,
}) => {
  return (
    <>
      <Header modify={'WHITE_BLOCK_LOCATION'} title={'회원가입'} />
      <SelectLocationContainer />
      <SignUpForm>
        <section className='email-block'>
          <SignUpLabel>이메일</SignUpLabel>
          <div>
            <SignUpInput
              name='EMAIL'
              placeholder='name@example.com'
              value={email}
              onChange={handleInput}
            />
            <SignUpButton
              type={'button'}
              modify={'WHITE_BLOCK'}
              disabled={!validate.email}
              onClick={onCheckEmail}
            >
              중복 확인
            </SignUpButton>
          </div>
          <ErrorMessage validate={validate.email}>
            이메일 형식을 맞춰주세요.
          </ErrorMessage>
        </section>

        <section className='password-user-block'>
          <SignUpLabel>비밀번호</SignUpLabel>
          <SignUpInput
            type={'password'}
            name={'PASSWORD'}
            placeholder='8자 이상 입력해주세요.'
            value={password}
            onChange={handleInput}
            maxLength={12}
          />
          <ErrorMessage validate={validate.password}>
            비밀번호 형식을 맞춰주세요.
          </ErrorMessage>

          <SignUpLabel>비밀번호 확인</SignUpLabel>
          <SignUpInput
            type={'password'}
            name={'PASSWORD_CONFIRM'}
            placeholder='비밀번호를 다시 입력해주세요.'
            value={passwordConfirm}
            onChange={handleInput}
            maxLength={12}
          />
          <ErrorMessage validate={validate.passwordConfirm}>
            비밀번호가 서로 다릅니다.
          </ErrorMessage>
          <SignUpLabel>이름</SignUpLabel>
          <SignUpInput
            name={'NAME'}
            placeholder='이름'
            value={name}
            onChange={handleInput}
            maxLength={12}
          />
          <ErrorMessage validate={validate.name}>
            이름을 입력해주세요.
          </ErrorMessage>
        </section>

        <SignUpButton type={'submit'} onClick={onSubmit}>
          가입하기
        </SignUpButton>
      </SignUpForm>
      <Navigation />
    </>
  );
};

// CSS
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
      align-items: center;
    }
  }
  > .password-user-block {
    display: inherit;
    flex-direction: inherit;
    margin: 1rem 0 3rem 0;
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

const ErrorMessage = styled.span`
  color: var(--red);
  font-size: 0.75rem;
  margin-top: 0.5rem;
  padding-left: 0.5rem;
  visibility: ${({ validate }) => (validate ? 'hidden' : 'visible')};
`;

export default SignUp;
