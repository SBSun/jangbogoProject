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

const Location = () => {
  const { state, actions } = useContext(LocationContext);
  const [locationList, setLocationList] = useState([]);
  useEffect(() => {
    const fetchData = () => getLocationList();
    fetchData();
  }, []);
  const getLocationList = async () => {
    const response = await (await fetch(`/gu/findAllGuInfo`)).json();
    setLocationList(response);
    console.log(response);
  };
  const locationListItem = locationList.map((item, index) => (
    <li key={index}>{item}</li>
  ));

  if (state.location.id === null) {
    actions.setIsVisible(true);
  }
  return (
    <LocationBlock isVisible={state.isVisible}>
      {locationListItem}
    </LocationBlock>
  );
};

export default Location;
