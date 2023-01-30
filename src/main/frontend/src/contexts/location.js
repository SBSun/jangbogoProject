import { createContext, useState } from 'react';

const LocationContext = createContext({
  state: {
    isVisible: false,
    location: {
      id: null,
      name: null,
    },
  },
  actions: {
    setIsVisible: () => {},
    setLocation: () => {},
  },
});

const LocationProvider = ({ children }) => {
  const [isVisible, setIsVisible] = useState(false);
  const [location, setLocation] = useState({
    id: null,
    name: null,
  });

  const value = {
    state: { isVisible, location },
    actions: { setIsVisible, setLocation },
  };

  return (
    <LocationContext.Provider value={value}>
      {children}
    </LocationContext.Provider>
  );
};

const { Consumer: LocationConsumer } = LocationContext;

export { LocationProvider, LocationConsumer };

export default LocationContext;
