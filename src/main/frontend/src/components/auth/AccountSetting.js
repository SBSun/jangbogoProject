import React, { useEffect, useState } from 'react';
import styled from 'styled-components';
import Button from '../common/Button';
import Header from '../common/Header';
import Navigation from '../common/Navigation';

// CSS
const AccountForm = styled.form`
  margin: 56px 0;
  padding: 0 1.25rem;
  display: flex;
  flex-direction: column;

  label {
    padding: 1.25rem 0 0.75rem 0;
  }
  input {
    height: 2.5rem;
    border: 1px solid var(--light-gray);
    padding: 0 1rem;
    border-radius: 10px;
  }
  p {
    padding: 2rem;
  }
  span {
    margin-left: auto;
    padding: 1rem 0;
    color: var(--green);
    text-decoration: underline;
    cursor: pointer;
  }
`;
const AccountButton = styled(Button)`
  height: 2.5rem;
`;

const AccountSetting = () => {
  const user = JSON.parse(sessionStorage.getItem('user'));

  // 인풋 상태 관리
  const [form, setForm] = useState({
    password: '',
    passwordConfirm: '',
    name: '',
  });

  // 사용자 이름 받아오기
  useEffect(() => {
    setForm({ ...form, name: user.name });
    // eslint-disable-next-line react-hooks/exhaustive-deps
  }, [user.name]);

  const onChange = e => {
    console.log(e.target.value);
    switch (e.target.name) {
      case 'PASSWORD': {
        return setForm({ ...form, password: e.target.value });
      }
      case 'PASSWORD_CONFIRM': {
        return setForm({ ...form, passwordConfirm: e.target.value });
      }
      case 'NAME': {
        return setForm({ ...form, name: e.target.value });
      }
      default: {
        return form;
      }
    }
  };
  const onSubmit = e => {
    e.preventDefault();
    if (form.password === '' || form.passwordConfirm === '') {
      return alert('비밀번호를 입력해주세요.');
    }
    if (form.password !== form.passwordConfirm) {
      return alert('비밀번호가 서로 다릅니다.');
    }
    console.log(form);
  };

  return (
    <>
      <Header modify={'WHITE_BLOCK'} title={'계정 설정'} />
      <AccountForm onSubmit={onSubmit}>
        <label>이메일</label>
        <input type={'text'} value={user.email} onChange={onChange} />
        <label>새 비밀번호</label>
        <input
          type={'password'}
          name={'PASSWORD'}
          placeholder={'새 비밀번호를 입력해주세요'}
          value={form.password}
          onChange={onChange}
        />
        <label>새 비밀번호 확인</label>
        <input
          type={'password'}
          name={'PASSWORD_CONFIRM'}
          placeholder={'새 비밀번호를 다시 입력해주세요'}
          value={form.passwordConfirm}
          onChange={onChange}
        />
        <label>이름</label>
        <input
          type={'text'}
          name={'NAME'}
          value={form.name}
          onChange={onChange}
        />
        <p> </p>
        <span>탈퇴하기</span>
        <AccountButton>수정하기</AccountButton>
      </AccountForm>
      <Navigation />
    </>
  );
};

export default AccountSetting;
