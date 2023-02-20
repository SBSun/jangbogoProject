import SelectLocation from '../components/common/SelectLocation';
import { useState, useEffect } from 'react';
import { useSelector, useDispatch } from 'react-redux';
import { getLocationList } from '../lib/api/etc';
import { setIsVisible, setLocation } from '../modules/location';

const SelectLoactionContainer = () => {
  const [locationList, setLocationList] = useState([]);

  const { id, isVisible } = useSelector(({ location }) => ({
    id: location.id,
    isVisible: location.isVisible,
  }));
  const storeDispatch = useDispatch();

  const promise = getLocationList();
  const fetchData = async () => {
    await promise.then(data => {
      setLocationList(data);
    });
  };

  useEffect(() => {
    fetchData();

    if (!sessionStorage.getItem('location-id')) {
      storeDispatch(setIsVisible(true));
    }
    // eslint-disable-next-line react-hooks/exhaustive-deps
  }, [id, isVisible]);

  const onItemClick = e => {
    storeDispatch(
      setLocation({
        id: parseInt(e.target.id),
        name: e.target.title,
      })
    );
    storeDispatch(setIsVisible(false));
    sessionStorage.setItem('location-id', e.target.id);
  };

  return (
    <SelectLocation
      isVisible={isVisible}
      locationList={locationList}
      onItemClick={onItemClick}
    />
  );
};

export default SelectLoactionContainer;
