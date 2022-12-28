import { useState } from 'react';
import Home from './routes/Home';
import Loading from './components/Loading';

const App = () => {
  const [isLoading, setIsLoading] = useState(true);
  return isLoading ? <Loading /> : <Home />;
};

export default App;
