import React, { useEffect, useState } from 'react';

const App = () => {
  const [hello, setHello] = useState('');

  useEffect(() => {
    fetch('/api/hello')
      .then(response => setHello(response.data))
      .catch(error => console.log(error));
  }, []);

  return (
    <div>
      <h2>Hello from Frontend!</h2>
      <h2>{hello}</h2>
    </div>
  );
};

export default App;
