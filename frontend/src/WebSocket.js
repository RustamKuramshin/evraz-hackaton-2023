import React, {useEffect, useRef, useState} from "react";

const WebSock = () => {
  const socket = new WebSocket('ws://51.250.23.216:38080/info');


  socket.onopen = () => {
    // socket.send(JSON.stringify({
    //   message: 'ПРИВЕТ ХАКАТОН-СЕРВЕР(websocket)',
    //   method: 'connection',
    //   id: 21,
    //   username: "denis",
    // }))
    getInfo();
  };

  socket.onmessage = (event) => {
    console.log(`The Data is: ${JSON.stringify(event.data)}`);
  }

  const btnAction = async () => {
    socket.send(JSON.stringify({
      message: "ХАКАТОН Отправка Data",
      id: 21,
      method: 'message',
      username: "denis",
    }));
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
      console.log(`REST RESPONSE ${JSON.stringify(body)}`);
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