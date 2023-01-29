import { createContext, useState } from 'react';

const LocationContext = createContext({
  state: {
    isVisible: false,
    location: {
      id: 110000,
      name: '종로',
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
    id: 110000,
    name: '종로',
  });
};
