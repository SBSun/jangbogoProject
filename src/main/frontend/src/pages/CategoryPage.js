import React, { useCallback, useState } from 'react';
import { useNavigate } from 'react-router-dom';
import styled from 'styled-components';

import { MdKeyboardArrowDown, MdKeyboardArrowUp } from 'react-icons/md';

import Header from '../components/common/Header';
import Navigation from '../components/common/Navigation';
import SelectLocationContainer from '../containers/SelectLoactionContainer';

const CategoryPage = () => {
  return <Category />;
};

// 상세 정보 리스트
const meatDetialItems = [
  { id: 2, name: '정육', text: '전체보기' },
  { id: 9, name: '달걀', text: '달걀' },
  { id: 10, name: '닭고기', text: '닭고기' },
  { id: 13, name: '돼지고기', text: '돼지고기' },
  { id: 21, name: '쇠고기', text: '쇠고기' },
];
const seafoodDetailItems = [
  { id: 3, name: '수산물', text: '전체보기' },
  { id: 6, name: '갈치', text: '갈치' },
  { id: 7, name: '건멸치', text: '건멸치' },
  { id: 8, name: '고등어', text: '고등어' },
  { id: 12, name: '동태', text: '동태' },
  { id: 14, name: '명태', text: '명태' },
  { id: 25, name: '오징어', text: '오징어' },
  { id: 26, name: '조기', text: '조기' },
];
const vegetaDetailItems = [
  { id: 4, name: '채소', text: '전체보기' },
  { id: 15, name: '무', text: '무' },
  { id: 18, name: '배추', text: '배추' },
  { id: 20, name: '상추', text: '상추' },
  { id: 22, name: '애호박', text: '애호박' },
  { id: 23, name: '양파', text: '양파' },
  { id: 24, name: '오이', text: '오이' },
];
const fruitDetailItems = [
  { id: 5, name: '과일', text: '전체보기' },
  { id: 11, name: '대추', text: '대추' },
  { id: 16, name: '밤', text: '밤' },
  { id: 17, name: '배', text: '배' },
  { id: 19, name: '사과', text: '사과' },
];

const Category = () => {
  const [checked, setChecked] = useState({
    meat: false,
    seafood: false,
    vegeta: false,
    fruit: false,
  });

  const navigate = useNavigate();

  const handleCheck = useCallback(e => {
    setChecked(prevChecked => ({
      ...prevChecked,
      [e.target.name]: e.target.checked,
    }));
  }, []);

  const renderItem = detailItems =>
    detailItems.map(item => (
      <li
        key={item.id}
        name={item.name}
        onClick={() => {
          navigate(`/category/${item.name}`, {
            state: [...detailItems],
          });
        }}
      >
        {item.text}
      </li>
    ));

  return (
    <>
      <Header modify='DEFAULT_BLOCK' title='카테고리' />
      <SelectLocationContainer />
      <CategoryBlock>
        <ul className='category-list'>
          {Object.entries(checked).map(([name, value]) => (
            <React.Fragment key={name}>
              <li className='category-item'>
                <input
                  type='checkbox'
                  id={`${name}-category`}
                  name={name}
                  checked={value}
                  onChange={handleCheck}
                />
                <label htmlFor={`${name}-category`}>
                  <div>
                    <span>
                      {name === 'meat'
                        ? '정육・계란'
                        : name === 'seafood'
                        ? '수산・해산물'
                        : name === 'vegeta'
                        ? '채소'
                        : '과일'}
                    </span>
                    {value ? <MdKeyboardArrowUp /> : <MdKeyboardArrowDown />}
                  </div>
                </label>
              </li>
              <DetailBlock isShow={value}>
                {name === 'meat'
                  ? renderItem(meatDetialItems)
                  : name === 'seafood'
                  ? renderItem(seafoodDetailItems)
                  : name === 'vegeta'
                  ? renderItem(vegetaDetailItems)
                  : renderItem(fruitDetailItems)}
              </DetailBlock>
            </React.Fragment>
          ))}
        </ul>
      </CategoryBlock>
      <Navigation />
    </>
  );
};

// CSS
const CategoryBlock = styled.main`
  margin: 56px 0;

  input {
    display: none;
  }

  .category-list > .category-item > label > div {
    display: flex;
    justify-content: space-between;
    font-size: 1.25rem;
    padding: 1.25rem;
    border-bottom: 1px solid var(--gray);
  }
`;

const DetailBlock = styled.ul`
  visibility: ${({ isShow }) => (isShow ? 'visible' : 'hidden')};
  background: var(--light-gray);
  color: var(--gray);
  height: ${({ isShow }) => (isShow ? 'auto' : '0')};

  li {
    margin-left: 1rem;
    padding: 1rem;
    cursor: pointer;
  }
`;

export default CategoryPage;
