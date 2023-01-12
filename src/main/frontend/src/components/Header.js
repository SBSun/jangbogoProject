import React from 'react';
import { Link } from 'react-router-dom';
import styled from 'styled-components';
import logo from '../assets/Logo_eng.svg';

const Container = styled.header`
  position: fixed;
  top: 0;
  left: 0;
  right: 0;
  display: flex;
  justify-content: space-between;
  align-items: center;
  height: 2.5rem;
  padding: 0.5rem 1rem;
  background-color: var(--green);
`;
const LogoStyle = styled.img`
  width: 6rem;
  height: 3rem;
`;
const Location = styled.span`
  > * {
    color: white;
    font-size: 2rem;
  }
`;

const Header = () => {
  return (
    <>
      <Container>
        <LogoStyle src={logo} />
        <Location>
          <Link to={'/gps'}>
            <i className='fa-solid fa-location-dot'></i>
          </Link>
        </Location>
      </Container>
    </>
  );
};

export default Header;
