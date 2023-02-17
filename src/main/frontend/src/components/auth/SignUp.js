import React from 'react';
import styled from 'styled-components';
import Button from '../common/Button';
import Header from '../common/Header';
import Navigation from '../common/Navigation';
import SelectLocationContainer from '../../containers/SelectLoactionContainer';

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

const SignUp = ({
  email,
  password,
  passwordConfirm,
  name,
  address,
  handleInputs,
  validations,
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
        </section>
        <section className='password-block'>
          <SignUpLabel>비밀번호</SignUpLabel>
          <SignUpInput
            type={'password'}
            name={'PASSWORD'}
            placeholder='영문 포함 6 ~ 12자 형식으로 입력해주세요.'
            value={password}
            onChange={handleInputs}
          />

          <SignUpLabel>비밀번호 확인</SignUpLabel>
          <SignUpInput
            type={'password'}
            name={'PASSWORD_CONFIRM'}
            placeholder='비밀번호를 한 번 더 입력해주세요.'
            value={passwordConfirm}
            onChange={handleInputs}
          />
        </section>
        <section className='user-block'>
          <SignUpLabel>이름</SignUpLabel>
          <SignUpInput
            name={'NAME'}
            placeholder='이름'
            value={name}
            onChange={handleInputs}
          />
          <SignUpLabel>주소</SignUpLabel>
          <div className='address-block'>
            <span>서울시</span>
            <SignUpInput
              name={'ADDRESS'}
              placeholder='OO구'
              value={address}
              onChange={handleInputs}
            />
          </div>
        </section>
        <SignUpButton
          type={'submit'}
          onClick={onSubmit}
          disable={validations.duplicate.id}
        >
          가입하기
        </SignUpButton>
      </SignUpForm>
      <Navigation />
    </>
  );
};

export default SignUp;
