import React from 'react';
import { useLocation, useNavigate, useParams } from 'react-router-dom';
import styled from 'styled-components';
import SelectLoactionContainer from '../containers/SelectLoactionContainer';
import CommodityList from './common/CommodityList';
import Header from './common/Header';
import Navigation from './common/Navigation';

// CSS
const CategoryDetailBlock = styled.main`
  margin: 56px 0;
`;
const CategoryMenuBlock = styled.div`
  display: flex;
  overflow-x: auto;
  justify-content: space-around;
  padding: 0.5rem;
`;
const CategoryMenu = styled.span`
  padding: 1rem 0.5rem;
  text-decoration: none;
  color: var(--black);

  &.active {
    color: var(--green);
    border-bottom: 1px solid var(--green);
  }
`;

const CatogoryDetail = () => {
  const params = useParams();

  const navigate = useNavigate();
  const location = useLocation();

  // 카테고리 메뉴 정보 리스트 저장
  const list = location.state;

  // 카테고리 세부 메뉴 동적 생성
  const getList = list.map((menu, index) => (
    <CategoryMenu
      key={index}
      name={menu.name}
      className={params.name === menu.name ? 'active' : undefined}
      onClick={() => {
        navigate(`/category/${menu.name}`, { state: [...list] });
      }}
    >
      {menu.text}
    </CategoryMenu>
  ));

  return (
    <>
      <Header modify={'WHITE_BLOCK_LOCATION'} title={'카테고리'} />
      <SelectLoactionContainer />
      <CategoryDetailBlock>
        <CategoryMenuBlock>{getList}</CategoryMenuBlock>
        <CommodityList
          modify={'CATEGORY'}
          recordSize={20}
          keyword={params.name}
        />
      </CategoryDetailBlock>
      <Navigation />
    </>
  );
};

export default CatogoryDetail;
