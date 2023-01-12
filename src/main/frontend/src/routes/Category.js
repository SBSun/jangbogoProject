import React, { useState } from 'react';
import styled from 'styled-components';
import Header from '../components/Header';
import Navigation from '../components/Navigation';

const Container = styled.main`
  margin: 56px 0 0 0;

  > ul > .category_menu {
    display: flex;
    justify-content: space-between;
    font-size: 1.25rem;
    padding: 1.25rem;
    border-bottom: 1px solid var(--gray);
  }
  > ul > .category_menu > i:nth-child(2) {
    display: none;
  }
`;
const Detail = styled.section`
  background-color: var(--light-gray);
  color: var(--black);
  padding: 1rem 1rem 1rem 4rem;
  border-bottom: 1px solid var(--gray);

  > ul > li {
    margin: 0 0 1rem 0;
  }
  > ul > li:last-child {
    margin: 0;
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
        <ul>
          <li className='category_menu' onClick={handleClick}>
            <span>정육 ・ 계란</span>
            <i className='fa-solid fa-angle-down'></i>
            <i className='fa-solid fa-angle-up'></i>
          </li>
          <Detail>
            <ul>
              <li>전체보기</li>
              <li>전체보기</li>
              <li>전체보기</li>
              <li>전체보기</li>
            </ul>
          </Detail>
          <li className='category_menu' onClick={handleClick}>
            <span>수산 ・ 해산물</span>
            <i className='fa-solid fa-angle-down'></i>
            <i className='fa-solid fa-angle-up'></i>
          </li>
          <Detail>
            <ul>
              <li>전체보기</li>
              <li>전체보기</li>
              <li>전체보기</li>
              <li>전체보기</li>
            </ul>
          </Detail>
          <li className='category_menu' onClick={handleClick}>
            <span>과일</span>
            <i className='fa-solid fa-angle-down'></i>
            <i className='fa-solid fa-angle-up'></i>
          </li>
          <Detail>
            <ul>
              <li>전체보기</li>
              <li>전체보기</li>
              <li>전체보기</li>
              <li>전체보기</li>
            </ul>
          </Detail>
          <li className='category_menu' onClick={handleClick}>
            <span>채소</span>
            <i className='fa-solid fa-angle-down'></i>
            <i className='fa-solid fa-angle-up'></i>
          </li>
          <Detail>
            <ul>
              <li>전체보기</li>
              <li>전체보기</li>
              <li>전체보기</li>
              <li>전체보기</li>
            </ul>
          </Detail>
        </ul>
      </Container>
      <Navigation />
    </>
  );
};

export default Category;
