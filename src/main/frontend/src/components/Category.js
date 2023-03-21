import React, { useCallback, useReducer } from 'react';
import styled from 'styled-components';
import { MdKeyboardArrowDown, MdKeyboardArrowUp } from 'react-icons/md';
import Header from './common/Header';
import Navigation from './common/Navigation';
import SelectLocationContainer from '../containers/SelectLoactionContainer';
import { useNavigate } from 'react-router-dom';

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

// 체크박스 상태 관리 리듀서
function reducer(state, action) {
  return {
    ...state,
    [action.name]: action.checked,
  };
}

const Category = () => {
  const [state, dispatch] = useReducer(reducer, {
    meat: false,
    seafood: false,
    vegeta: false,
    fruit: false,
  });

  const navigate = useNavigate();

  // 체크박스 값을 리듀서에 최신화
  const handleCheck = useCallback(e => {
    dispatch(e.target);
  }, []);

  // 상세 보기 메뉴를 동적 출력
  const meatItemList = meatDetialItems.map(item => (
    <li
      key={item.id}
      name={item.name}
      onClick={() => {
        navigate(`/category/${item.name}`, {
          state: [...meatDetialItems],
        });
      }}
    >
      {item.text}
    </li>
  ));
  const seafoodItemList = seafoodDetailItems.map(item => (
    <li
      key={item.id}
      name={item.name}
      onClick={() => {
        navigate(`/category/${item.name}`, {
          state: [...seafoodDetailItems],
        });
      }}
    >
      {item.text}
    </li>
  ));
  const vegetaItemList = vegetaDetailItems.map(item => (
    <li
      key={item.id}
      name={item.name}
      onClick={() => {
        navigate(`/category/${item.name}`, {
          state: [...vegetaDetailItems],
        });
      }}
    >
      {item.text}
    </li>
  ));
  const fruitItemList = fruitDetailItems.map(item => (
    <li
      key={item.id}
      name={item.name}
      onClick={() => {
        navigate(`/category/${item.name}`, {
          state: [...fruitDetailItems],
        });
      }}
    >
      {item.text}
    </li>
  ));

  return (
    <>
      <Header modify={'DEFAULT_BLOCK'} title={'카테고리'} />
      <SelectLocationContainer />
      <CategoryBlock>
        <ul className='category-list'>
          <li className='category-item'>
            <input
              type={'checkbox'}
              id={'meat-category'}
              name={'meat'}
              onClick={handleCheck}
            />
            <label htmlFor='meat-category'>
              <div>
                <span>정육・계란</span>
                {state.meat ? <MdKeyboardArrowUp /> : <MdKeyboardArrowDown />}
              </div>
            </label>
          </li>
          <DetailBlock isShow={state.meat}>{meatItemList}</DetailBlock>

          <li className='category-item'>
            <input
              type={'checkbox'}
              id={'seafood-category'}
              name={'seafood'}
              onClick={handleCheck}
            />
            <label htmlFor='seafood-category'>
              <div>
                <span>수산・해산물</span>
                {state.seafood ? (
                  <MdKeyboardArrowUp />
                ) : (
                  <MdKeyboardArrowDown />
                )}
              </div>
            </label>
          </li>
          <DetailBlock isShow={state.seafood}>{seafoodItemList}</DetailBlock>

          <li className='category-item'>
            <input
              type={'checkbox'}
              id={'vegeta-category'}
              name={'vegeta'}
              onClick={handleCheck}
            />
            <label htmlFor='vegeta-category'>
              <div>
                <span>채소</span>
                {state.vegeta ? <MdKeyboardArrowUp /> : <MdKeyboardArrowDown />}
              </div>
            </label>
          </li>
          <DetailBlock isShow={state.vegeta}>{vegetaItemList}</DetailBlock>

          <li className='category-item'>
            <input
              type={'checkbox'}
              id={'fruit-category'}
              name={'fruit'}
              onClick={handleCheck}
            />
            <label htmlFor='fruit-category'>
              <div>
                <span>과일</span>
                {state.fruit ? <MdKeyboardArrowUp /> : <MdKeyboardArrowDown />}
              </div>
            </label>
          </li>
          <DetailBlock isShow={state.fruit}>{fruitItemList}</DetailBlock>
        </ul>
      </CategoryBlock>
      <Navigation />
    </>
  );
};

export default Category;
