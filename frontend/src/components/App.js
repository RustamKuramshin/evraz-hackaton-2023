import './App.css';
import React, { useEffect, useRef, useState } from 'react';
import Api from '../utils/Api';

function App() {
  const [data, setData] = useState({});
  //const socket = useRef(new WebSocket('ws://localhost:8080/'));
  const socket = useRef(new WebSocket('ws://51.250.23.216:38080/info'));

  useEffect(() => {
    socket.current.onopen = () => {
      console.log('Opened Connection!');
      Api.getInfo();
    };

    socket.current.onmessage = (event) => {
      setData(JSON.parse(event.data));
    }

    return () => socket.current.onclose = () => {
      console.log('Closed Connection!');
    };
  }, [])

  return (
    <div>
      {JSON.stringify(data)}
    </div>
  );
}

export default App;
