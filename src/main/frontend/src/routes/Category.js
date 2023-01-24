import React from 'react';
import styled from 'styled-components';
import { MdOutlineNavigateNext } from 'react-icons/md';
import Header from '../components/Header';
import Navigation from '../components/Navigation';

const Container = styled.main`
  margin: 56px 0 0 0;
`;
const CategoryItem = styled.li`
  display: flex;
  justify-content: space-between;
  font-size: 1.25rem;
  padding: 1.25rem;
  border-bottom: 1px solid var(--gray);
`;

const Category = () => {
  const handleClick = e => {
    console.log(e);
  };
  return (
    <>
      <Header modify={1} title={'카테고리'} />
      <Container>
        <ul>
          <CategoryItem onClick={handleClick}>
            <span>정육 ・ 계란</span>
            <MdOutlineNavigateNext />
          </CategoryItem>
          <CategoryItem onClick={handleClick}>
            <span>수산 ・ 해산물</span>
            <MdOutlineNavigateNext />
          </CategoryItem>
          <CategoryItem onClick={handleClick}>
            <span>채소</span>
            <MdOutlineNavigateNext />
          </CategoryItem>
          <CategoryItem onClick={handleClick}>
            <span>과일</span>
            <MdOutlineNavigateNext />
          </CategoryItem>
        </ul>
      </Container>
      <Navigation />
    </>
  );
};

export default Category;
