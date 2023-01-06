import React from 'react';
import styled from 'styled-components';
import logo from '../assets/Logo.svg';

const LoadingStyle = styled.div`
  height: 100vh;
  display: flex;
  justify-content: center;
  align-items: center;
`;

const LogoStyle = styled.img`
  width: 200px;
  height: 200px;
`;

const Loading = () => {
  return (
    <LoadingStyle>
      <LogoStyle src={logo} />
    </LoadingStyle>
  );
};

export default Loading;
