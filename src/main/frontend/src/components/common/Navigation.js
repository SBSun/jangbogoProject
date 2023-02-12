import React from 'react';
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
  border-top: solid 1px var(--light-gray);
  background-color: white;
`;
const Menu = styled(NavLink)`
  font-size: 1.75rem;
  color: var(--black);

  &.active {
    color: var(--green);
  }
`;

const Navigation = () => {
  const user = localStorage.getItem('user');
  const isLogin = JSON.parse(user);

  return (
    <>
      <Container>
        <Menu
          to={'/'}
          className={({ isActive }) => (isActive ? 'active' : undefined)}
        >
          <MdHome />
        </Menu>
        <Menu
          to={'/category'}
          className={({ isActive }) => (isActive ? 'active' : undefined)}
        >
          <MdMenu />
        </Menu>
        <Menu
          to={'/search'}
          className={({ isActive }) => (isActive ? 'active' : undefined)}
        >
          <MdSearch />
        </Menu>
        <Menu
          to={isLogin ? '/mypage' : '/member/login'}
          className={({ isActive }) => (isActive ? 'active' : undefined)}
        >
          <MdPerson />
        </Menu>
      </Container>
    </>
  );
};

export default Navigation;
