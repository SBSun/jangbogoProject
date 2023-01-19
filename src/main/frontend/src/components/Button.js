import React from 'react';
import styled from 'styled-components';

const GreenButton = styled.button`
  outline: none;
  border: none;
  background: var(--green);
  color: white;
  height: 2.5rem;
  font-size: 1.25rem;
  font-weight: 600;
  margin: 1rem 0;
  border-radius: 5px;
`;

const Button = ({ children }) => {
  return <GreenButton>{children}</GreenButton>;
};

export default Button;
