import React, {useEffect, useRef, useState} from "react";

const WebSock = () => {
  const socket = new WebSocket('ws://51.250.23.216:38080/metrics');


  socket.onopen = () => {
    getInfo();
  };

  socket.onmessage = (event) => {
    console.log(`The Data is: ${JSON.stringify(event.data)}`);
  }

  const btnAction = () => {

  }

  const getInfo = () => {
    return fetch('http://51.250.23.216:38080/api/v1/info', {
      method: 'GET',
      headers: {
        'Content-Type': 'application/json'
      }
    })
    .then(checkErrors);
  }

  const checkErrors = async (res) => {
    if (res.ok) {
      const body = await res.json()
      return JSON.stringify(body);
    }

    return Promise.reject(`Ошибка: ${res.status}`);
  }

  return (
    <div className="center">
      <div>
        <div className="form">
          <button onClick={btnAction}>Отправить</button>
        </div>
      </div>
    </div>
  );
};

export default WebSock;