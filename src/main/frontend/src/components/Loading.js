import React from 'react';
import styled from 'styled-components';
import logo from '../assets/Logo.svg';

const LoadingStyled = styled.div`
  height: 100vh;
  display: flex;
  justify-content: center;
  align-items: center;
`;

const Loading = () => {
  return (
    <LoadingStyled>
      <img src={logo} width={160} height={160} />
    </LoadingStyled>
  );
};

export default Loading;
