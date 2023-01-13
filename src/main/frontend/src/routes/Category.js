import React from 'react';
import styled from 'styled-components';
import Header from '../components/Header';
import Navigation from '../components/Navigation';

const Container = styled.main`
  margin: 56px 0 0 0;
`;
const MenuList = styled.ul`
  > .category_menu {
    display: flex;
    justify-content: space-between;
    font-size: 1.25rem;
    padding: 1.25rem;
    border-bottom: 1px solid var(--gray);
  }
`;

const Category = () => {
  const handleClick = e => {
    console.log(e.target);
  };
  return (
    <>
      <Header />
      <Container>
        <MenuList>
          <li className='category_menu' onClick={handleClick}>
            <span>정육 ・ 계란</span>
            <i className='fa-solid fa-angle-right'></i>
          </li>
          <li className='category_menu' onClick={handleClick}>
            <span>수산 ・ 해산물</span>
            <i className='fa-solid fa-angle-right'></i>
          </li>
          <li className='category_menu' onClick={handleClick}>
            <span>채소</span>
            <i className='fa-solid fa-angle-right'></i>
          </li>
          <li className='category_menu' onClick={handleClick}>
            <span>과일</span>
            <i className='fa-solid fa-angle-right'></i>
          </li>
        </MenuList>
      </Container>
      <Navigation />
    </>
  );
};

export default Category;
