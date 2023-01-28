import React, { useEffect, useState } from 'react';
import { NavLink } from 'react-router-dom';
import styled from 'styled-components';
import { MdHome, MdSearch, MdMenu, MdPerson } from 'react-icons/md';

const Container = styled.footer`
  position: fixed;
  bottom: 0;
  left: 0;
  right: 0;
  display: flex;
  justify-content: space-around;
  align-items: center;
  height: 2.5rem;
  padding: 0.5rem 1rem;
  border-top: solid 1px var(--gray);
  background-color: white;
`;
const Menu = styled(NavLink)`
  font-size: 1.5rem;
  color: var(--black);
`;

const Navigation = prop => {
  const [isLogin, setIsLogin] = useState(false);
  useEffect(() => {
    setIsLogin(prop.isLogin);
  }, [prop]);
  return (
    <>
      <Container>
        <Menu to={'/'}>
          <MdHome />
        </Menu>
        <Menu to={'/category'}>
          <MdMenu />
        </Menu>
        <Menu to={'/search'}>
          <MdSearch />
        </Menu>
        <Menu to={isLogin ? '/mypage' : '/member/login'}>
          {isLogin ? <MdPerson /> : <MdPerson />}
        </Menu>
      </Container>
    </>
  );
};

export default Navigation;
