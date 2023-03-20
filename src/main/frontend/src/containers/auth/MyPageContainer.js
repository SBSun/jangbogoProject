import React from 'react';
import { useDispatch, useSelector } from 'react-redux';
import { useNavigate } from 'react-router-dom';
import MyPage from '../../components/auth/MyPage';
import { logout } from '../../lib/api/auth';
import { postLogout } from '../../modules/auth';

const MyPageContainer = () => {
  const navigate = useNavigate();

  const auth = useSelector(state => state.auth);
  const storeDispatch = useDispatch();

  // 로그아웃 함수
  const onLogoutClick = () => {
    logout(auth.accessToken);
    storeDispatch(postLogout());
    alert('로그아웃되었습니다.');
    navigate('/', { replace: true });
  };
  // 소셜 로그안 상태인 경우, 계정 설정 불가능
  const onAccountClick = () => {
    if (auth.loginType === 'Social')
      return alert('소셜 로그인 상태에선 설정할 수 없습니다.');
    console.log(auth.loginType);
    navigate('/mypage/account');
  };

  return (
    <MyPage
      auth={auth}
      onAccountClick={onAccountClick}
      onLogoutClick={onLogoutClick}
    />
  );
};

export default MyPageContainer;
