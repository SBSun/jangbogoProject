import React from 'react';
import styled from 'styled-components';
import Header from '../common/Header';
import Navigation from '../common/Navigation';
import Button from '../common/Button';
import SelectLocationContainer from '../../containers/SelectLoactionContainer';

// CSS
const Container = styled.main`
  margin: 56px 0 0 0;
  background: var(--light-gray);
`;
const UserInfoBlock = styled.section`
  display: flex;
  flex-direction: column;
  background: white;

  h2 {
    padding: 1rem;
    font-size: 20px;
  }
  span {
    padding: 0 1rem 0.5rem 1rem;
    color: var(--green);

    strong {
      color: var(--black);
    }
  }
`;
const ButtonBlock = styled.div`
  margin: 1.5rem 0 0 0;
  display: flex;

  button {
    margin: 0 0.5rem 0.5rem 0.5rem;
    flex: 1;
    cursor: pointer;
  }
`;

const MyPage = ({ auth, onAccountClick, onLogoutClick }) => {
  return (
    <>
      <Header modify={'DEFAULT_BLOCK'} title={'내 정보'} />
      <SelectLocationContainer />
      <Container>
        <UserInfoBlock>
          <h2>{auth.name}님, 환영합니다.</h2>
          <span>
            이메일 - <strong>{auth.email}</strong>
          </span>
          <ButtonBlock>
            <Button
              type={'button'}
              modify={'GRAY_BLOCK'}
              onClick={onAccountClick}
            >
              계정 설정
            </Button>
            <Button
              type={'button'}
              modify={'GRAY_BLOCK'}
              onClick={onLogoutClick}
            >
              로그아웃
            </Button>
          </ButtonBlock>
        </UserInfoBlock>
      </Container>
      <Navigation />
    </>
  );
};

export default MyPage;
