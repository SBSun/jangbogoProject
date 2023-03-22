import React, { useCallback, useEffect, useMemo, useState } from 'react';
import { useSelector } from 'react-redux';
import {
  getCommodityList,
  getCatagoryList,
  getSearchList,
  getMarketItemList,
  getLowPriceItemList,
} from '../../lib/api/commodity';
import styled, { keyframes } from 'styled-components';

function handleCommodityThumbnail(id) {
  switch (id) {
    // 정육
    case 9:
      return 'egg';
    case 10:
      return 'chicken';
    case 13:
      return 'pork';
    case 21:
      return 'beef';
    // 수산물
    case 6:
      return 'galchi';
    case 7:
      return 'myeolchi';
    case 8:
      return 'mackerel';
    case 12:
      return 'dongtae';
    case 14:
      return 'myeongtae';
    case 25:
      return 'squid';
    case 26:
      return 'zogi';
    // 채소
    case 15:
      return 'radish';
    case 18:
      return 'cabbage';
    case 20:
      return 'lettuce';
    case 22:
      return 'zucchini';
    case 23:
      return 'onion';
    case 24:
      return 'cucumber';
    // 과일
    case 11:
      return 'jujube';
    case 16:
      return 'chestnut';
    case 17:
      return 'pear';
    case 19:
      return 'apple';
    default: {
      return 'none';
    }
  }
}

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
          setIsEmpty(data?.data?.infoList.length === 0);
          setCommoditys(data?.data?.infoList);
          setEndPage(data?.data?.pageResponseDTO.endPage);
        })
        .catch(error => {
          if (error.response.status === 404) setIsEmpty(true);
        });
    };

    fetchData();
  }, [selectAPI, endPage]);

  // 받아온 품목 데이터 동적 생성
  const commodityListItem = commoditys.map((commodity, index) => {
    const thumbnail = handleCommodityThumbnail(commodity.category_Id);

    return (
      <CommodityItemStyled key={index}>
        <img src={`/assets/commodity/${thumbnail}.png`} alt='thumbnail' />
        <dl className='commodity_info'>
          <dd className='market_name'>{commodity.marketName}</dd>
          <dt className='commodity_name'>{commodity.categoryName}</dt>
          <dd className='commodity_remarks'>{commodity.remarks}</dd>
          <dd className='commodity_price'>
            {commodity.price
              .toString()
              .replace(/\B(?<!\.\d*)(?=(\d{3})+(?!\d))/g, ',')}
            원
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
    <Container>
      <CommodityXScrollBlock>{commodityListItem}</CommodityXScrollBlock>
    </Container>
  ) : (
    <>
      <CommodityYScrollBlock>{commodityListItem}</CommodityYScrollBlock>
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

const Container = styled.div`
  animation: ${fadeIn} 0.5s ease-in forwards;
`;

const CommodityXScrollBlock = styled.ul`
  display: flex;
  overflow-x: auto;
  scrollbar-width: none;

  ::-webkit-scrollbar {
    display: none;
  }
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

  img {
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
  margin-top: 30vh;
  text-align: center;
`;

export default React.memo(CommodityList);
