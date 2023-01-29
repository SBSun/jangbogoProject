import React from 'react';
import styled, { css } from 'styled-components';

const LocationBlock = styled.div`
  display: none;
  position: fixed;
  bottom: 0;
  left: 0;
  right: 0;
  height: 60vh;
  background: white;
  border-top: 1px solid var(--light-gray);
  text-align: center;
  z-index: 3;

  ${props =>
    props.isVisible &&
    css`
      display: block;
    `}
`;

const Location = ({ isVisible }) => {
  return <LocationBlock isVisible={isVisible}>test</LocationBlock>;
};

export default Location;
