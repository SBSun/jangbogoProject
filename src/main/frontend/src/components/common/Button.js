import React from 'react';
import styled from 'styled-components';

const DefaultBlock = styled.button`
  outline: none;
  border: none;
  background: var(--green);
  color: white;
  height: 2.5rem;
  font-size: 1.25rem;
  border-radius: 5px;
`;
const WhiteBlock = styled(DefaultBlock)`
  background: white;
  color: var(--green);
  border: 1px solid var(--green);
`;
const GrayBlock = styled(DefaultBlock)`
  background: var(--light-gray);
  color: var(--black);
`;
const Button = ({ type, modify, children, onClick }) => {
  const HandleModify = () => {
    switch (modify) {
      case 'WHITE_BLOCK': {
        return (
          <WhiteBlock type={type} onClick={onClick}>
            {children}
          </WhiteBlock>
        );
      }
      case 'GRAY_BLOCK': {
        return (
          <GrayBlock type={type} onClick={onClick}>
            {children}
          </GrayBlock>
        );
      }
      default: {
        return (
          <DefaultBlock type={type} onClick={onClick}>
            {children}
          </DefaultBlock>
        );
      }
    }
  };
  return <HandleModify />;
};

export default Button;
