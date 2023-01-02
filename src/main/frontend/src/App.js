import { useState } from 'react';
import GlobalStyle from './GlobalStyle';
import Loading from './components/Loading';
import Home from './routes/Home';

const App = () => {
  const [isLoading, setIsLoading] = useState(false);
  return (
    <>
      <GlobalStyle />
      {isLoading ? <Loading /> : <Home />}
    </>
  );
};

export default App;
