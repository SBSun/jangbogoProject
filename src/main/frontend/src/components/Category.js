import React from 'react';
import styled from 'styled-components';
import { MdOutlineNavigateNext } from 'react-icons/md';
import Header from './common/Header';
import Navigation from './common/Navigation';
import SelectLocationContainer from '../containers/SelectLoactionContainer';

const categories = [
  {
    name: 'meat',
    text: '정육 ・ 계란',
  },
  {
    name: 'seafood',
    text: '수산 ・ 해산물',
  },
  {
    name: 'vegetable',
    text: '채소',
  },
  {
    name: 'fruit',
    text: '과일',
  },
];
const CategoryBlock = styled.main`
  margin: 56px 0 0 0;

  > ul > li {
    display: flex;
    justify-content: space-between;
    font-size: 1.25rem;
    padding: 1.25rem;
    border-bottom: 1px solid var(--gray);
  }
`;

const Category = () => {
  const onCategoryClick = e => {
    console.log(e.target);
  };
  return (
    <>
      <Header modify={'DEFAULT_BLOCK'} title={'카테고리'} />
      <SelectLocationContainer />
      <CategoryBlock>
        <ul>
          {categories.map(c => (
            <li key={c.name} onClick={onCategoryClick}>
              <span>{c.text}</span>
              <MdOutlineNavigateNext />
            </li>
          ))}
        </ul>
      </CategoryBlock>
      <Navigation />
    </>
  );
};

export default Category;