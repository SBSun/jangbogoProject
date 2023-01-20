import React, { useEffect, useState } from 'react';
import { Link } from 'react-router-dom';
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
const Menu = styled.span`
  font-size: 1.5rem;
  > a {
    color: var(--black);
  }
`;

const Navigation = prop => {
  const [isLogin, setIsLogin] = useState(false);
  useEffect(() => {
    setIsLogin(prop.isLogin);
  }, [prop]);
  return (
    <>
      <Container>
        <Menu>
          <Link to={'/'}>
            <MdHome />
          </Link>
        </Menu>
        <Menu>
          <Link to={'/category'}>
            <MdMenu />
          </Link>
        </Menu>
        <Menu>
          <Link to={'/search'}>
            <MdSearch />
          </Link>
        </Menu>
        <Menu>
          {isLogin ? (
            <Link to={'/mypage'}>
              <MdPerson />
            </Link>
          ) : (
            <Link to={'/member/login'}>
              <MdPerson />
            </Link>
          )}
        </Menu>
      </Container>
    </>
  );
};

export default Navigation;
