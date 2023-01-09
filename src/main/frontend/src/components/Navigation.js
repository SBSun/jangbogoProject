import React from 'react';
import { Link } from 'react-router-dom';
import styled from 'styled-components';
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
`;
const Menu = styled.span`
  font-size: 2rem;
  color: var(--black);
`;

const Navigation = () => {
  return (
    <>
      <Container>
        <Link to={'/'}>
          <Menu>
            <i className='fa-solid fa-house'></i>
          </Menu>
        </Link>
        <Link to={'/category'}>
          <Menu>
            <i className='fa-solid fa-bars'></i>
          </Menu>
        </Link>
        <Link to={'/search'}>
          <Menu>
            <i className='fa-solid fa-search'></i>
          </Menu>
        </Link>
        <Link to={'/mypage'}>
          <Menu>
            <i className='fa-solid fa-user'></i>
          </Menu>
        </Link>
      </Container>
    </>
  );
};

export default Navigation;
