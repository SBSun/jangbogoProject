import React, { useCallback, useEffect, useMemo, useState } from 'react';
import { useSelector } from 'react-redux';
import styled, { keyframes } from 'styled-components';
import Slider from 'react-slick';

import {
  getCommodityList,
  getCatagoryList,
  getSearchList,
  getMarketItemList,
  getLowPriceItemList,
} from '../../lib/api/commodity';

function handleCommodityThumbnail(id) {
  switch (id) {
    // 정육
    case 8:
      return 'egg';
    case 9:
      return 'chicken';
    case 12:
      return 'pork';
    case 20:
      return 'beef';
    // 수산물
    case 5:
      return 'galchi';
    case 6:
      return 'myeolchi';
    case 7:
      return 'mackerel';
    case 11:
      return 'dongtae';
    case 13:
      return 'myeongtae';
    case 24:
      return 'squid';
    case 25:
      return 'zogi';
    // 채소
    case 14:
      return 'radish';
    case 17:
      return 'cabbage';
    case 19:
      return 'lettuce';
    case 21:
      return 'zucchini';
    case 22:
      return 'onion';
    case 23:
      return 'cucumber';
    // 과일
    case 10:
      return 'jujube';
    case 15:
      return 'chestnut';
    case 16:
      return 'pear';
    case 18:
      return 'apple';
    default: {
      return 'none';
    }
  }
}

// Carousel 설정
const settings = {
  infinite: false,
  speed: 500,
  slidesToShow: 3,
  slidesToScroll: 3,
  autoplay: false,
};

const CommodityList = ({ modify, recordSize, keyword }) => {
  // 품목 데이터 상태
  const [commoditys, setCommoditys] = useState([]);
  const [isEmpty, setIsEmpty] = useState(false);

  // 페이지 상태
  const [curPage, setCurPage] = useState(1);
  const [endPage, setEndPage] = useState(1);

  // 지역 ID 가져오기
  const location = sessionStorage.getItem('location');
  const storeLocation = useSelector(state => state.location);

  // modify에 따라 다르게 품목을 출력
  const selectAPI = useMemo(() => {
    switch (modify) {
      case 'CATEGORY': {
        return () => getCatagoryList(location, curPage, recordSize, keyword);
      }
      case 'SEARCH': {
        return () => getSearchList(location, curPage, recordSize, keyword);
      }
      case 'MARKET': {
        return () => getMarketItemList(curPage, recordSize, keyword);
      }
      case 'PRICE': {
        return () => getLowPriceItemList(location);
      }
      default: {
        return () => getCommodityList(location, curPage, recordSize);
      }
    }
    // eslint-disable-next-line react-hooks/exhaustive-deps
  }, [modify, storeLocation.id, curPage, recordSize, keyword]);

  useEffect(() => {
    if (endPage === 1) {
      setCurPage(1);
    }
    const fetchData = () => {
      selectAPI()
        .then(data => {
          setIsEmpty(data?.content.length === 0);
          setCommoditys(data?.content);
          setEndPage(data?.totalPages);
        })
        .catch(error => {
          if (error.response.status === 404) setIsEmpty(true);
        });
    };

    fetchData();
  }, [selectAPI, endPage]);

  // 받아온 품목 데이터 동적 생성
  const commodityListItems = commoditys.map((commodity, index) => {
    const thumbnail = handleCommodityThumbnail(commodity.categoryId);

    return (
      <CommodityItemStyled key={index}>
        <img src={`/assets/commodity/${thumbnail}.png`} alt='thumbnail' />
        <dl className='commodity_info'>
          <dd className='market_name'>{commodity.marketName}</dd>
          <dt className='commodity_name'>{commodity.categoryName}</dt>
          <dd className='commodity_remarks'>{commodity.remarks}</dd>
          <dd className='commodity_price'>
            {commodity.price.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ',')}원
          </dd>
        </dl>
      </CommodityItemStyled>
    );
  });

  // 품목 페이지 이동
  const onPageClick = useCallback(e => {
    setCurPage(parseInt(e.target.value));
    window.scrollTo(0, 0);
  }, []);

  // 페이지 버튼 배열 생성
  const pageArray = Array.from({ length: endPage }, (_, index) => index + 1);
  // 데이터 양에 따른 페이지 이동 버튼 동적 생성
  const pageButtons = pageArray.map(element => (
    <li key={element} value={element} onClick={onPageClick}>
      {element}
    </li>
  ));

  const commodityBlock = isEmpty ? (
    <EmptyBlock>품목이 없습니다.</EmptyBlock>
  ) : modify === 'PRICE' ? (
    <CommodityXScrollBlock {...settings}>
      {commodityListItems}
    </CommodityXScrollBlock>
  ) : (
    <>
      <CommodityYScrollBlock>{commodityListItems}</CommodityYScrollBlock>
      <CommoditySelectPage curPage={curPage}>{pageButtons}</CommoditySelectPage>
    </>
  );

  const HandleCommodityStyled = () => {
    switch (modify) {
      case 'CATEGORY':
      case 'SEARCH':
      case 'MARKET':
        return commodityBlock;
      case 'PRICE':
        return commodityBlock;
      default:
        return commodityBlock;
    }
  };

  return (
    <>
      <HandleCommodityStyled />
    </>
  );
};

// CSS
const fadeIn = keyframes`
  from {
    opacity: 0;
  }
  to {
    opacity: 1;
  }
`;

const CommodityXScrollBlock = styled(Slider)`
  animation: ${fadeIn} 0.5s ease-in forwards;
  display: flex;
`;

const CommodityYScrollBlock = styled.ul`
  display: flex;
  flex-wrap: wrap;
  flex: 2;
  animation: ${fadeIn} 0.5s ease-in forwards;

  li {
    flex: 1;
    width: 8.125rem;
  }
  li:nth-child(odd) {
    margin-left: 1rem;
  }
`;

const CommodityItemStyled = styled.li`
  padding: 0.5rem 1rem;
  -webkit-user-drag: none;

  img {
    user-select: none;
    padding: 1rem 0;
    width: 7.5rem;
    height: 7.5rem;
  }
  .commodity_info > .market_name {
    color: var(--gray);
    font-size: 0.75rem;
  }
  .commodity_info > .commodity_name {
    padding: 0.5rem 0 0 0;
  }
  .commodity_info > .commodity_remarks {
    padding: 0.5rem 0 0 0;
    color: var(--gray);
    font-size: 0.85rem;
  }
  .commodity_info > .commodity_price {
    padding: 0.5rem 0 0 0;
    color: var(--black);
    font-weight: 600;
  }
`;

const CommoditySelectPage = styled.ul`
  padding: 0.5rem 1rem;
  display: flex;
  justify-content: center;
  align-items: center;

  li {
    padding: 1rem;
    cursor: pointer;
  }
  li:nth-child(${({ curPage }) => curPage}) {
    color: var(--green);
  }
`;

const EmptyBlock = styled.div`
  height: 185px;
  padding: 1rem;
  display: flex;
  justify-content: center;
  align-items: center;
`;

export default React.memo(CommodityList);
