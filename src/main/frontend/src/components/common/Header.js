import React, { useEffect } from 'react';
import styled from 'styled-components';
import {
  MdClear,
  MdPlace,
  MdKeyboardArrowLeft,
  MdOutlinePlace,
} from 'react-icons/md';
import { useNavigate } from 'react-router-dom';
import { useDispatch, useSelector } from 'react-redux';
import { setIsVisible } from '../../modules/location';
import { setAuthorizationToken } from '../../lib/api/auth';

// CSS
const DefaultBlock = styled.header`
  position: fixed;
  top: 0;
  left: 0;
  right: 0;
  display: flex;
  align-items: center;
  justify-content: center;
  height: 2.5rem;
  padding: 0.5rem 0;
  font-size: 1.25rem;
  background-color: var(--green);
  color: white;
  z-index: 1;

  span {
    padding: 0 1rem;
    flex: 1;
  }
  h2 {
    padding: 0 1rem;
    flex: 8;
    text-align: center;
  }
  svg {
    padding: 0 1rem;
    flex: 1;
    font-size: 2rem;
    cursor: pointer;
  }
`;
const WhiteBlock = styled(DefaultBlock)`
  justify-content: left;
  background-color: white;
  color: var(--black);

  svg {
    padding: 0 1rem;
    flex: 1;
  }
  h2 {
    padding: 0 1rem;
    text-align: center;
    flex: 8;
  }
  span {
    padding: 0 1rem;
    flex: 1;
  }
`;
const LogoBlock = styled(DefaultBlock)`
  justify-content: space-between;

  img {
    padding: 0 1rem;
    width: 6rem;
    height: 3rem;
  }
  svg {
    padding: 0 1rem;
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
  const { accessToken } = useSelector(({ auth }) => ({
    accessToken: auth.login.accessToken,
  }));
  const storeDispatch = useDispatch();

  const user = JSON.parse(sessionStorage.getItem('user'));

  useEffect(() => {
    if (user) {
      setAuthorizationToken(accessToken);
    }
    // eslint-disable-next-line react-hooks/exhaustive-deps
  }, [accessToken]);

  const onClick = () => {
    storeDispatch(setIsVisible(!isVisible));
  };

  const HandleHeaderStyled = () => {
    switch (modify) {
      case 'LOGO_BLOCK':
        return (
          <LogoBlock>
            <img src={'/assets/svg/Logo_eng.svg'} alt='logo' />
            <MdPlace onClick={onClick} />
          </LogoBlock>
        );

      case 'WHITE_BLOCK':
        return (
          <WhiteBlock>
            <MdClear onClick={() => navigate(-1, { replace: true })} />
            <h2>{title}</h2>
            <span></span>
          </WhiteBlock>
        );

      case 'WHITE_BLOCK_LOCATION':
        return (
          <WhiteBlock>
            <MdKeyboardArrowLeft
              onClick={() => navigate(-1, { replace: true })}
            />
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
      <HandleHeaderStyled />
    </>
  );
};

export default Header;
