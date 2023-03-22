import React from 'react';
import styled from 'styled-components';

const Button = ({ type, modify, children, onClick, disabled }) => {
  const renderButton = () => {
    const commonProps = {
      type,
      onClick,
      disabled,
    };

    switch (modify) {
      case 'WHITE_BLOCK': {
        return <WhiteBlock {...commonProps}>{children}</WhiteBlock>;
      }
      case 'GRAY_BLOCK': {
        return <GrayBlock {...commonProps}>{children}</GrayBlock>;
      }
      default: {
        return <DefaultBlock {...commonProps}>{children}</DefaultBlock>;
      }
    }
  };
  return <>{renderButton()}</>;
};

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

export default Button;
