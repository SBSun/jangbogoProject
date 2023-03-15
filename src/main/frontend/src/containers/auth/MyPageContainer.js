import React from 'react';
import { useSelector } from 'react-redux';
import { useNavigate } from 'react-router-dom';
import MyPage from '../../components/auth/MyPage';
import { logout } from '../../lib/api/auth';

const MyPageContainer = () => {
  const user = JSON.parse(sessionStorage.getItem('user'));

  const navigate = useNavigate();

  // 엑세스, 리프레쉬 토큰 받아오기
  const { accessToken, loginType } = useSelector(({ auth }) => ({
    accessToken: auth.login.accessToken,
    loginType: auth.login.loginType,
  }));
  const refreshToken = user.refreshToken;

  // 로그아웃 함수
  const onLogoutClick = () => {
    logout(accessToken, refreshToken);
    sessionStorage.removeItem('user');
    alert('로그아웃되었습니다.');
    navigate('/', { replace: true });
  };
  // 소셜 로그안 상태인 경우, 계정 설정 불가능
  const onAccountClick = () => {
    if (loginType === 'Social')
      return alert('소셜 로그인 상태에선 설정할 수 없습니다.');
    console.log(loginType);
    navigate('/mypage/account', { replace: true });
  };
  return (
    <MyPage
      user={user}
      onAccountClick={onAccountClick}
      onLogoutClick={onLogoutClick}
    />
  );
};

export default MyPageContainer;
