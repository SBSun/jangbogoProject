import { useState, useEffect } from 'react';
import { connect } from 'react-redux';
import { getLocationList } from '../lib/api/etc';
import { setIsVisible, setLocation } from '../modules/location';

import SelectLocation from '../components/common/SelectLocation';

const SelectLoactionContainer = ({
  id,
  isVisible,
  setLocation,
  setIsVisible,
}) => {
  const [locationList, setLocationList] = useState([]);

  const promise = getLocationList();
  const fetchData = async () => {
    const data = await promise;
    setLocationList(data);
  };

  useEffect(() => {
    fetchData();

    if (!sessionStorage.getItem('location')) {
      setIsVisible(true);
    }
    // eslint-disable-next-line react-hooks/exhaustive-deps
  }, [id, isVisible]);

  const onItemClick = e => {
    setLocation({
      id: parseInt(e.target.id),
      name: e.target.title,
    });
    sessionStorage.setItem('location', e.target.id);
    setIsVisible(false);
  };

  return (
    <SelectLocation
      isVisible={isVisible}
      locationList={locationList}
      onItemClick={onItemClick}
    />
  );
};

const mapStateToProps = ({ location }) => ({
  id: location.id,
  isVisible: location.isVisible,
});

export default connect(mapStateToProps, { setLocation, setIsVisible })(
  SelectLoactionContainer
);
