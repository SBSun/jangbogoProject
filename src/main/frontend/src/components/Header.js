import React, { useEffect, useState } from 'react';
import styled from 'styled-components';
import { MdClear } from 'react-icons/md';
import logo from '../assets/Logo_eng.svg';
import { useNavigate } from 'react-router-dom';

const Container = styled.header`
  position: fixed;
  top: 0;
  left: 0;
  right: 0;
  display: flex;
  align-items: center;
  justify-content: center;
  height: 2.5rem;
  padding: 0.5rem 1rem;
  font-size: 1.25rem;
  font-weight: 600;
  background-color: var(--green);
  color: white;
`;
const WhiteContainer = styled(Container)`
  justify-content: space-between;
  background-color: white;
  color: var(--black);
`;
const LogoStyle = styled.img`
  width: 6rem;
  height: 3rem;
`;

const Header = prop => {
  const [modify, setModify] = useState(0);
  const navigate = useNavigate();

  useEffect(() => {
    setModify(prop.modify);
  }, [prop]);

  const HandleModify = () => {
    switch (modify) {
      case 1:
        return (
          <Container>
            <h2>{prop.title}</h2>
          </Container>
        );

      case 2:
        return (
          <WhiteContainer>
            <MdClear onClick={() => navigate(-1)} />
            <span>{prop.title}</span>
            <span></span>
          </WhiteContainer>
        );

      default:
        return (
          <Container>
            <LogoStyle src={logo} />
          </Container>
        );
    }
  };

  return (
    <>
      <HandleModify />
    </>
  );
};

export default Header;
