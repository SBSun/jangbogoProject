import React from 'react';
import styled from 'styled-components';
import logo from '../assets/Logo_eng.svg';

const Container = styled.header`
  display: flex;
  justify-content: space-between;
  align-items: center;
  width: 100vw;
  height: 2.5rem;
  padding: 0.5rem 1rem;
  background-color: var(--green);
`;
const LogoStyle = styled.img`
  width: 6rem;
  height: 3rem;
`;
const Location = styled.span`
  color: white;
  font-size: 2rem;
`;

const Header = () => {
  return (
    <>
      <Container>
        <LogoStyle src={logo} />
        <Location>
          <i className='fa-solid fa-location-dot'></i>
        </Location>
      </Container>
    </>
  );
};

export default Header;
