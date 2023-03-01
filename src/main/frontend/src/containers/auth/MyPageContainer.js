import React from 'react';
import { useSelector } from 'react-redux';
import { useNavigate } from 'react-router-dom';
import MyPage from '../../components/auth/MyPage';
import { logout } from '../../lib/api/auth';

const MyPageContainer = () => {
  const user = JSON.parse(sessionStorage.getItem('user'));

  const navigate = useNavigate();

  // 엑세스, 리프레쉬 토큰 받아오기
  const { accessToken } = useSelector(({ auth }) => ({
    accessToken: auth.login.accessToken,
  }));
  const refreshToken = user.refreshToken;

  // 로그아웃 함수
  const onLogoutClick = () => {
    logout(accessToken, refreshToken);
    sessionStorage.removeItem('user');
    alert('로그아웃되었습니다.');
    navigate('/', true);
  };
  const onAccountClick = () => {
    navigate('/mypage/account');
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
