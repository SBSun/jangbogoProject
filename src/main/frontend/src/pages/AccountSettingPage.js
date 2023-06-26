import React, { useEffect, useState } from 'react';
import { useDispatch, useSelector } from 'react-redux';
import { useNavigate } from 'react-router-dom';
import { editUserInfo, deleteAccount } from '../lib/api/auth';
import { postLogin, postLogout } from '../modules/auth';
import styled from 'styled-components';

import Button from '../components/common/Button';
import Header from '../components/common/Header';
import Navigation from '../components/common/Navigation';

const AccountSettingPage = () => {
  return <AccountSetting />;
};

const AccountSetting = () => {
  const auth = useSelector(state => state.auth);
  const storeDispatch = useDispatch();
  const navigate = useNavigate();

  // 인풋 상태 관리
  const [form, setForm] = useState({
    password: '',
    passwordConfirm: '',
    name: auth.name,
  });
  // 유효성 검사
  const [validate, setValidate] = useState({
    password: false,
    passwordConfirm: false,
    name: true,
  });

  const { password, passwordConfirm, name } = form;

  // 사용자 이름 받아오기
  useEffect(() => {
    // eslint-disable-next-line no-useless-escape
    const PASSWORD_REGEX = /^[\da-zA-Z0-9!@#]{8,}$/;

    password.match(PASSWORD_REGEX)
      ? setValidate(validate => ({ ...validate, password: true }))
      : setValidate(validate => ({ ...validate, password: false }));

    if (password !== '') {
      setValidate(validate => ({
        ...validate,
        passwordConfirm: password === passwordConfirm,
      }));
    }

    setValidate(validate => ({ ...validate, name: name !== '' }));

    // setForm({ ...form, name: auth.name });
    // eslint-disable-next-line react-hooks/exhaustive-deps
  }, [password, passwordConfirm, name]);

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
      const fetchData = () => {
        deleteAccount().then(res => {
          storeDispatch(postLogout());
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
    if (!validate.password) {
      return alert('비밀번호를 형식에 맞게 입력해주세요.');
    }
    if (!validate.passwordConfirm) {
      return alert('비밀번호가 서로 다릅니다.');
    }
    if (!validate.name) {
      return alert('이름을 꼭 입력해주세요.');
    }

    const fetchData = () => {
      editUserInfo(passwordConfirm, name)
        .then(res => {
          storeDispatch(postLogin({ ...auth, name: name }));
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
        <input type={'text'} value={auth.email} onChange={onChange} />
        <label>새 비밀번호</label>
        <input
          type={'password'}
          name={'PASSWORD'}
          placeholder={'새 비밀번호를 입력해주세요'}
          value={password}
          onChange={onChange}
        />
        <ErrorMessage validate={validate.password}>
          비밀번호를 8자 이상 입력해주세요.
        </ErrorMessage>
        <label>새 비밀번호 확인</label>
        <input
          type={'password'}
          name={'PASSWORD_CONFIRM'}
          placeholder={'새 비밀번호를 다시 입력해주세요'}
          value={passwordConfirm}
          onChange={onChange}
        />
        <ErrorMessage validate={validate.passwordConfirm}>
          비밀번호가 서로 다릅니다.
        </ErrorMessage>
        <label>이름</label>
        <input type={'text'} name={'NAME'} value={name} onChange={onChange} />
        <ErrorMessage validate={validate.name}>
          이름을 입력해주세요.
        </ErrorMessage>
        <p></p>
        <span className='delete-account' onClick={onClick}>
          계정 삭제
        </span>
        <AccountButton>수정하기</AccountButton>
      </AccountForm>
      <Navigation />
    </>
  );
};

// CSS
const AccountForm = styled.form`
  margin: 56px 0;
  padding: 0 1.25rem 1.25rem 1.25rem;
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
  .delete-account {
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

const ErrorMessage = styled.span`
  color: var(--red);
  font-size: 0.75rem;
  margin-top: 0.5rem;
  padding-left: 0.5rem;
  visibility: ${({ validate }) => (validate ? 'hidden' : 'visible')};
`;

export default AccountSettingPage;
