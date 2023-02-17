import './App.css';
import React, { useEffect, useRef, useState } from 'react';
import Api from '../utils/Api';

function App() {
  const [data, setData] = useState({});
  const socket = useRef(new WebSocket('ws://localhost:8080/'));
  //const socket = useRef(new WebSocket('ws://51.250.23.216:38080/info'));

  //console.log([input, data]);

  useEffect(() => {
    socket.current.onopen = () => {
      console.log('Opened Connection!');
      Api.getInfo();
    };

    socket.current.onmessage = (event) => {
      let temp = JSON.parse(event.data);
      setData(temp);
      console.log(data["SM_Exgauster\\[8:1]"]);

      // setInput(temp["SM_Exgauster\\[8:1]"]);
      // console.log(input);
    }

    socket.current.send("READY");
    return () => socket.current.onclose = () => {
      console.log('Closed Connection!');
    };
  }, [data]);
  // }, [input, data]);

  return (
    <div
      style={{alignSelf: "center", justifySelf: "center"}}
    >
      {data["SM_Exgauster\\[8:1]"]}
    </div>
);
}

export default App;
