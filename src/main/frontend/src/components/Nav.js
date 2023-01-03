import React from 'react';
import styled from 'styled-components';
import { Link } from 'react-router-dom';
import logo from '../assets/Logo_eng.svg';

const NavContainer = styled.nav`
  position: fixed;
  display: flex;
  align-items: center;
  width: 100vw;
  background-color: white;
`;
const NavLogo = styled.div`
  flex: none;
  padding: 0.5rem;
`;
const NavItems = styled.div`
  flex: none;
  margin-left: auto;
  margin-right: 0.5rem;
  padding: 0.5rem;
`;
const NavSearch = styled.span`
  font-size: 1.25rem;
  padding: 0.5rem;
  color: var(--black);
`;
const NavSignIn = styled.span`
  margin-left: 0.5rem;
  font-size: 1.25rem;
  padding: 0.5rem;
  font-weight: bold;
  border-radius: 0.5rem;
  color: var(--black);
  background-color: var(--light-gray);
`;

const Nav = () => {
  return (
    <NavContainer>
      <NavLogo>
        <Link to={'/'}>
          <img src={logo} width={100} height={60}></img>
        </Link>
      </NavLogo>
      <NavItems>
        <NavSearch>
          <Link to={'/search'}>
            <i className='fa-solid fa-magnifying-glass'></i>
          </Link>
        </NavSearch>
        <NavSignIn>
          <Link to={'/signin'}>Sign in</Link>
        </NavSignIn>
      </NavItems>
    </NavContainer>
  );
};

export default Nav;
