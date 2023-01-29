import React from 'react';
import styled, { css } from 'styled-components';

const ButtonBlock = styled.button`
  outline: none;
  border: none;
  background: var(--green);
  color: white;
  height: 2.5rem;
  font-size: 1.25rem;
  font-weight: 600;
  margin: 1rem 0;
  border-radius: 5px;

  ${props =>
    props.inverted &&
    css`
      background: white;
      color: var(--green);
      border: 1px solid var(--green);
    `}
`;

const Button = ({ type, inverted, onClick, children }) => {
  return (
    <ButtonBlock type={type} inverted={inverted} onClick={onClick}>
      {children}
    </ButtonBlock>
  );
};

export default Button;
