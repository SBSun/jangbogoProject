import React from 'react';
import { useParams } from 'react-router-dom';
import styled from 'styled-components';
import SelectLoactionContainer from '../containers/SelectLoactionContainer';
import CommodityList from './common/CommodityList';
import Header from './common/Header';
import Navigation from './common/Navigation';

// CSS
const CategoryDetailBlock = styled.main`
  margin: 56px 0;
`;

const CatogoryDetail = () => {
  const params = useParams();

  return (
    <>
      <Header modify={'WHITE_BLOCK_LOCATION'} title={'카테고리'} />
      <SelectLoactionContainer />
      <CategoryDetailBlock>
        <CommodityList
          modify={'CATEGORY'}
          curPage={1}
          recordSize={20}
          keyword={params.name}
        />
      </CategoryDetailBlock>
      <Navigation />
    </>
  );
};

export default CatogoryDetail;
