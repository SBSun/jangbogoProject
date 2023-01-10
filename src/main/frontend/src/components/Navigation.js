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
  background-color: white;
`;
const Menu = styled.span`
  font-size: 2rem;
  > a {
    color: var(--black);
  }
`;

const Navigation = () => {
  return (
    <>
      <Container>
        <Menu>
          <Link to={'/'}>
            <i className='fa-solid fa-house'></i>
          </Link>
        </Menu>
        <Menu>
          <Link to={'/category'}>
            <i className='fa-solid fa-bars'></i>
          </Link>
        </Menu>
        <Menu>
          <Link to={'/search'}>
            <i className='fa-solid fa-search'></i>
          </Link>
        </Menu>
        <Menu>
          <Link to={'/mypage'}>
            <i className='fa-solid fa-user'></i>
          </Link>
        </Menu>
      </Container>
    </>
  );
};

export default Navigation;
