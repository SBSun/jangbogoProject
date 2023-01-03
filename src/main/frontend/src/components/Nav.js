import React from 'react';
import { Link } from 'react-router-dom';
import styled from 'styled-components';
import logo from '../assets/Logo_eng.svg';

const NavContainerStyle = styled.nav`
  display: flex;
  align-items: center;
`;
const NavLogoStyle = styled.div`
  flex: none;
  padding: 0.5rem;
`;
const NavItemsStyle = styled.div`
  flex: none;
  margin-left: auto;
  margin-right: 0.5rem;
  padding: 0.5rem;
`;
const NavSearchStyle = styled.span`
  font-size: 1.25rem;
  padding: 0.5rem;
  color: var(--black);
`;
const NavLoginStyle = styled.span`
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
    <NavContainerStyle>
      <NavLogoStyle>
        <Link to={'/'}>
          <img src={logo} width={100} height={60}></img>
        </Link>
      </NavLogoStyle>
      <NavItemsStyle>
        <NavSearchStyle>
          <Link to={'/search'}>
            <i className='fa-solid fa-magnifying-glass'></i>
          </Link>
        </NavSearchStyle>
        <NavLoginStyle>
          <Link to={'/login'}>Log in</Link>
        </NavLoginStyle>
      </NavItemsStyle>
    </NavContainerStyle>
  );
};

export default Nav;
