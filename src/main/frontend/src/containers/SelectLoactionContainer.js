import SelectLocation from '../components/SelectLocation';
import { useState, useEffect } from 'react';
import { useSelector, useDispatch } from 'react-redux';
import { getLocationList } from '../lib/api/list';
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
    await promise.then(res => {
      setLocationList(res);
    });
  };

  useEffect(() => {
    fetchData();

    if (id === '') {
      storeDispatch(setIsVisible(true));
    }
    // eslint-disable-next-line react-hooks/exhaustive-deps
  }, []);

  const onItemClick = e => {
    storeDispatch(
      setLocation({
        id: parseInt(e.target.id),
        name: e.target.title,
      })
    );
    storeDispatch(setIsVisible(false));
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
