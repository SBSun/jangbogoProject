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
  border-top: 1px solid var(--light-gray);
  text-align: center;
  z-index: 3;

  ${props =>
    props.isVisible &&
    css`
      display: block;
    `}
`;
const LocationList = styled.ul``;

const Location = () => {
  const { state } = useContext(LocationContext);
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
    <li key={item.gu_Id}>{item.name}</li>
  ));

  // if (state.location.id === null) {
  //   actions.setIsVisible(true);
  // }
  return (
    <LocationBlock isVisible={state.isVisible}>
      <LocationList>{locationListItem}</LocationList>
    </LocationBlock>
  );
};

export default Location;
