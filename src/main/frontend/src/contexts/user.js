import { createContext, useState } from 'react';

const UserContext = createContext({
  state: {
    isLogin: false,
    token: {
      accessToken: null,
      refreshToken: null,
    },
  },
  actions: {
    setIsLogin: () => {},
    setToken: () => {},
  },
});

const UserProvider = ({ children }) => {
  const [isLogin, setIsLogin] = useState(false);
  const [token, setToken] = useState({
    accessToken: null,
    refreshToken: null,
  });

  const value = {
    state: {
      isLogin,
      token,
    },
    actions: {
      setIsLogin,
      setToken,
    },
  };

  return <UserContext.Provider value={value}>{children}</UserContext.Provider>;
};

const { Consumer: UserConsumer } = UserContext;

export { UserProvider, UserConsumer };

export default UserContext;
