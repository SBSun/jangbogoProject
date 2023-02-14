import React from 'react';
import styled from 'styled-components';
import {
  MdClear,
  MdPlace,
  MdKeyboardArrowLeft,
  MdOutlinePlace,
} from 'react-icons/md';
import logo from '../../assets/Logo_eng.svg';
import { useNavigate } from 'react-router-dom';
import { useDispatch, useSelector } from 'react-redux';
import { setIsVisible } from '../../modules/location';

const DefaultBlock = styled.header`
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

  > span {
    flex: 1;
  }
  > h2 {
    flex: 8;
    text-align: center;
  }
  > svg {
    flex: 1;
    font-size: 2rem;
    cursor: pointer;
  }
`;
const WhiteBlock = styled(DefaultBlock)`
  justify-content: left;
  background-color: white;
  color: var(--black);

  > svg {
    flex: 1;
  }
  > h2 {
    text-align: center;
    flex: 8;
  }
  > span {
    flex: 1;
  }
`;
const LogoBlock = styled(DefaultBlock)`
  justify-content: space-between;

  > img {
    width: 6rem;
    height: 3rem;
  }
  > svg {
    flex: none;
    font-size: 2rem;
    cursor: pointer;
  }
`;

const Header = ({ modify, title }) => {
  const navigate = useNavigate();

  const { isVisible } = useSelector(({ location }) => ({
    isVisible: location.isVisible,
  }));
  const storeDispatch = useDispatch();

  const onClick = () => {
    storeDispatch(setIsVisible(!isVisible));
  };

  const HandleModify = () => {
    switch (modify) {
      case 'LOGO_BLOCK':
        return (
          <LogoBlock>
            <img src={logo} alt='logo' />
            <MdPlace onClick={onClick} />
          </LogoBlock>
        );

      case 'WHITE_BLOCK':
        return (
          <WhiteBlock>
            <MdClear onClick={() => navigate(-1, true)} />
            <h2>{title}</h2>
            <span></span>
          </WhiteBlock>
        );

      case 'WHITE_BLOCK_LOCATION':
        return (
          <WhiteBlock>
            <MdKeyboardArrowLeft onClick={() => navigate(-1, true)} />
            <h2>{title}</h2>
            <MdOutlinePlace onClick={onClick} />
          </WhiteBlock>
        );

      default:
        return (
          <DefaultBlock>
            <span></span>
            <h2>{title}</h2>
            <MdPlace onClick={onClick} />
          </DefaultBlock>
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