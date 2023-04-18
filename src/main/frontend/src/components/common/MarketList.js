import React, { useEffect, useMemo, useRef, useState } from 'react';
import { useNavigate } from 'react-router-dom';
import { useSelector } from 'react-redux';
import styled, { keyframes } from 'styled-components';

import { getMarketList } from '../../lib/api/etc';

function handleMarketThumbnail(name) {
  switch (true) {
    case name.includes('이마트'): {
      return 'emart';
    }
    case name.includes('롯데백화점'): {
      return 'lotte_depart';
    }
    case name.includes('롯데마트'): {
      return 'lotte_mart';
    }
    case name.includes('신세계'): {
      return 'shinsegae';
    }
    case name.includes('농협'): {
      return 'nh';
    }
    case name.includes('홈플러스'): {
      return 'homeplus';
    }
    case name.includes('시장'): {
      return 'tradition';
    }
    default: {
      return 'none';
    }
  }
}

const MarketList = () => {
  // 매장 데이터 상태
  const [markets, setMarkets] = useState([]);
  const [isEmpty, setIsEmpty] = useState(false);

  const navigate = useNavigate();

  // 지역 설정
  const location = sessionStorage.getItem('location');
  const storeLocation = useSelector(state => state.location);

  // X축 마우스 스크롤
  const scrollRef = useRef(null);
  const [isDrag, setIsDrag] = useState(false);
  const [startX, setStartX] = useState();

  const onDragStart = e => {
    e.preventDefault();
    console.log(e);
    setIsDrag(true);
    setStartX(e.pageX + scrollRef.current.scrollLeft);
  };

  const onDragEnd = () => {
    setIsDrag(false);
  };

  const throttle = (func, ms) => {
    let throttled = false;
    return (...args) => {
      if (!throttled) {
        throttled = true;
        setTimeout(() => {
          func(...args);
          throttled = false;
        }, ms);
      }
    };
  };

  const onDragMove = e => {
    if (isDrag) {
      const { scrollWidth, clientWidth, scrollLeft } = scrollRef.current;

      scrollRef.current.scrollLeft = startX - e.pageX;

      if (scrollLeft === 0) {
        setStartX(e.pageX);
      } else if (scrollWidth <= clientWidth + scrollLeft) {
        setStartX(e.pageX + scrollLeft);
      }
    }
  };

  const delay = 25;
  const onThrottleDragMove = throttle(onDragMove, delay);

  useEffect(() => {
    const fetchData = () => {
      getMarketList(location)
        .then(data => {
          setIsEmpty(data.length === 0);
          setMarkets(data);
        })
        .catch(error => {
          if (error.response.status === 404) setIsEmpty(true);
        });
    };

    fetchData();
    // eslint-disable-next-line react-hooks/exhaustive-deps
  }, [storeLocation.id]);

  // 매장 데이터 동적 생성
  const marketListItems = useMemo(
    () =>
      markets.map(market => {
        const thumbnail = handleMarketThumbnail(market.name);

        return (
          <MarketItemStyled
            key={market.marketId}
            onClick={() => {
              navigate(`/market/${market.marketId}`, {
                state: { name: market.name, thumbnail: thumbnail },
              });
            }}
          >
            <img src={`/assets/market/${thumbnail}.png`} alt='thumbnail' />
            <div className='market_name'>{market.name}</div>
          </MarketItemStyled>
        );
      }),
    [markets, navigate]
  );

  return isEmpty ? (
    <EmptyBlock>매장 데이터가 없습니다.</EmptyBlock>
  ) : (
    <MarketListBlock
      onMouseDown={onDragStart}
      onMouseMove={isDrag ? onThrottleDragMove : null}
      onMouseUp={onDragEnd}
      onMouseLeave={onDragEnd}
      ref={scrollRef}
    >
      {marketListItems}
    </MarketListBlock>
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

const MarketListBlock = styled.ul`
  animation: ${fadeIn} 0.5s ease-in forwards;
  display: flex;
  overflow-x: scroll;
  scrollbar-width: none;

  ::-webkit-scrollbar {
    display: none;
  }
`;

const MarketItemStyled = styled.li`
  user-select: none;
  padding: 0.5rem 1rem;

  img {
    padding: 1rem 0;
    width: 7.5rem;
    height: 7.5rem;
    -webkit-user-drag: none;
  }
  .market_name {
    text-align: center;
    color: var(--black);
  }
`;

const EmptyBlock = styled.div`
  height: 185px;
  padding: 1rem;
  display: flex;
  justify-content: center;
  align-items: center;
`;

export default React.memo(MarketList);
