import React from 'react';
import styled from 'styled-components';
import logo from '../assets/Logo.svg';

const NavUlStyle = styled.ul`
  display: flex;
  align-items: center;
`;
const NavLiStyle = styled.li`
  padding: 0.5rem;
`;
const NavSearchStyle = styled.a`
  color: var(--black);
`;

const NavLoginStyle = styled.a`
  padding: 0.5rem;
  font-weight: bold;
  border-radius: 0.5rem;
  color: var(--black);
  background-color: var(--light-gray);
`;

const Nav = () => {
  return (
    <nav>
      <NavUlStyle>
        <NavLiStyle>
          <img src={logo} width={100} height={38}></img>
        </NavLiStyle>
        <NavLiStyle>
          <NavSearchStyle>
            <i className='fa-solid fa-magnifying-glass'></i>
          </NavSearchStyle>
        </NavLiStyle>
        <NavLiStyle>
          <NavLoginStyle>Log in</NavLoginStyle>
        </NavLiStyle>
      </NavUlStyle>
    </nav>
  );
};

export default Nav;
