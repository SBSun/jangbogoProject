import React, { useContext, useEffect, useState } from 'react';
import styled, { css } from 'styled-components';
import LocationContext from '../contexts/location';

const LocationBlock = styled.div`
  display: none;
  position: fixed;
  bottom: 0;
  left: 0;
  right: 0;
  height: 60vh;
  background: white;
  border-top: 1px solid var(--gray);
  text-align: center;
  overflow-y: scroll;
  z-index: 3;

  > .location-title {
    position: fixed;
    left: 0;
    right: 0;
    text-align: center;
    font-size: 1.25rem;
    font-weight: 800;
    padding: 1rem;
    border-bottom: 1px solid var(--gray);
    color: white;
    background: var(--green);
  }

  ${props =>
    props.isVisible &&
    css`
      display: block;

      body {
        filter: blur(15px);
      }
    `}
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

const Location = () => {
  const { state, actions } = useContext(LocationContext);
  const [locationList, setLocationList] = useState([]);

  useEffect(() => {
    const fetchData = () => getLocationList();
    fetchData();
  }, []);

  const getLocationList = async () => {
    const response = await (await fetch(`/gu/findAllGuInfo`)).json();
    setLocationList(response.guInfoList);
    console.log(response.guInfoList);
  };
  const locationListItem = locationList.map(item => (
    <li
      key={item.gu_Id}
      onClick={() => {
        actions.setLocation({
          id: item.gu_Id,
          name: item.name,
        });
        console.log(
          `Set Location : [id : ${state.location.id}] [name : ${state.location.name}]`
        );
        actions.setIsVisible(false);
      }}
    >
      {item.name}
    </li>
  ));

  if (state.location.id === null) {
    actions.setIsVisible(true);
  }
  return (
    <LocationBlock isVisible={state.isVisible}>
      <h2 className='location-title'>지역을 선택해주세요.</h2>
      <LocationList>{locationListItem}</LocationList>
    </LocationBlock>
  );
};

export default Location;
