import React from 'react';
import styled from 'styled-components';

// CSS
const LocationBlock = styled.div`
  display: ${({ isVisible }) => (isVisible ? `block` : `none`)};
  position: fixed;
  top: 0;
  width: 100%;
  height: 100vh;
  background-color: rgba(0, 0, 0, 0.3);
  z-index: 1000;

  @media (min-width: 415px) {
    margin: 0 auto;
    width: 412px;
  }
`;
const LocationWrap = styled.div`
  position: relative;
  width: 100%;
  height: 100%;
`;
const LocationPopUp = styled.div`
  position: absolute;
  bottom: 0;
  left: 0;
  right: 0;
  height: 60vh;
  background: white;
  text-align: center;
  overflow-y: scroll;
  border-radius: 15px 15px 0 0;
  z-index: 1000;

  > .location-title {
    border-radius: inherit;
    position: fixed;
    left: 0;
    right: 0;
    text-align: center;
    font-size: 1.25rem;
    font-weight: 800;
    padding: 1rem 0;
    color: white;
    background: var(--green);

    @media (min-width: 415px) {
      margin: 0 auto;
      width: 412px;
    }
  }
`;
const LocationList = styled.ul`
  margin: 53px 0 0 0;
  padding: 1rem;

  > li {
    padding: 1rem;
    width: 30%;
    display: inline-block;
    text-align: center;
    cursor: pointer;
  }
`;

const SelectLocation = ({ isVisible, locationList, onItemClick }) => {
  const locationListItem = locationList.map((item, index) => (
    <li key={index} title={item.name} id={item.gu_Id} onClick={onItemClick}>
      {item.name}
    </li>
  ));
  return (
    <LocationBlock isVisible={isVisible}>
      <LocationWrap>
        <LocationPopUp>
          <h2 className='location-title'>지역을 선택해주세요.</h2>
          <LocationList>{locationListItem}</LocationList>
        </LocationPopUp>
      </LocationWrap>
    </LocationBlock>
  );
};

export default SelectLocation;
