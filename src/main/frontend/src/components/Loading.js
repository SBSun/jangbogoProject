import React from 'react';
import styled from 'styled-components';
import logo from '../assets/Logo.svg';

const LoadingStyle = styled.div`
  height: 100vh;
  display: flex;
  justify-content: center;
  align-items: center;
`;

const Loading = () => {
  return (
    <LoadingStyle>
      <img src={logo} width={200} height={200} />
    </LoadingStyle>
  );
};

export default Loading;
