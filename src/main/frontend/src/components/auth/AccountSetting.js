import React, { useEffect, useState } from 'react';
import { useNavigate } from 'react-router-dom';
import styled from 'styled-components';
import { editUserInfo, deleteAccount } from '../../lib/api/auth';
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
    padding: 1rem 0 2rem 0;
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
  const navigate = useNavigate();

  // 인풋 상태 관리
  const [form, setForm] = useState({
    password: '',
    passwordConfirm: '',
    name: '',
  });

  const { password, passwordConfirm, name } = form;

  // 사용자 이름 받아오기
  useEffect(() => {
    setForm({ ...form, name: user.name });
    // eslint-disable-next-line react-hooks/exhaustive-deps
  }, [user.name]);

  // 인풋 값 관리
  const onChange = e => {
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
  // 회원탈퇴 기능
  const onClick = () => {
    const isDelete = window.confirm('정말 삭제하시겠습니까?');

    if (isDelete) {
      const promise = deleteAccount();
      const fetchData = () => {
        promise.then(res => {
          console.log(res);
          // 세션에서 유저 정보 삭제
          sessionStorage.removeItem('user');
          alert('계정이 삭제되었습니다.');
          navigate('/', { replace: true });
        });
      };

      fetchData();
    }
  };
  // 회원 정보 수정
  const onSubmit = e => {
    e.preventDefault();
    if (password === '' || passwordConfirm === '') {
      return alert('비밀번호를 입력해주세요.');
    }
    if (password !== passwordConfirm) {
      return alert('비밀번호가 서로 다릅니다.');
    }

    const promise = editUserInfo(passwordConfirm, name);
    console.log(passwordConfirm, name);
    const fetchData = () => {
      promise
        .then(res => {
          console.log(res);
          sessionStorage.setItem(
            'user',
            JSON.stringify({ ...user, name: name })
          );
          alert('사용자 정보가 변경되었습니다.');
          navigate('/mypage', { replace: true });
        })
        .catch(error => console.log(error));
    };
    fetchData();
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
          value={password}
          onChange={onChange}
        />
        <label>새 비밀번호 확인</label>
        <input
          type={'password'}
          name={'PASSWORD_CONFIRM'}
          placeholder={'새 비밀번호를 다시 입력해주세요'}
          value={passwordConfirm}
          onChange={onChange}
        />
        <label>이름</label>
        <input type={'text'} name={'NAME'} value={name} onChange={onChange} />
        <p></p>
        <span onClick={onClick}>계정 삭제</span>
        <AccountButton>수정하기</AccountButton>
      </AccountForm>
      <Navigation />
    </>
  );
};

export default AccountSetting;
