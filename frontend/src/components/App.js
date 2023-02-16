import './App.css';
import React, { useEffect, useState } from 'react';
import Api from '../utils/Api';

function App() {
  const [data, setData] = useState({});
  const socket = new WebSocket('ws://51.250.23.216:38080/info');

  useEffect(() => {
    socket.onopen = () => {
      console.log('Opened Connection!');
      Api.getInfo();
    };

    socket.onmessage = (event) => {
      setData(JSON.stringify(event.data));
    }

    return () => socket.close();
  })

  return (
    <div>
      {data}
    </div>
  );
}

export default App;
