import React, {useEffect, useRef, useState} from "react";

const WebSock = () => {
  const socket = new WebSocket('ws://localhost:8080/info');

  socket.onopen = () => {
    socket.send(JSON.stringify({
      message: 'ПРИВЕТ ХАКАТОН-СЕРВЕР',
      method: 'connection',
      id: 21,
      username: "denis",
    }))
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

  // const [messages, setMessages] = useState([]);
  // const [value, setValue] = useState('');
  // const socket = useRef()
  // const [connected, setConnected] = useState(false);
  // const [data, setData] = useState('')

  // function connect() {
  //     socket.current = new WebSocket('ws://localhost:8080/')

  //     socket.current.onopen = () => {
  //         setConnected(true);
  //         const message = {
  //             event: 'connection',
  //             data,
  //             id: Date.now()
  //         };
  //         socket.current.send(JSON.stringify(message));
  //     }
  //     socket.current.onmessage = (event) => {
  //         const message = JSON.parse(event.data)
  //         setMessages(prev => [message, ...prev])
  //     }
  //     socket.current.onclose= () => {
  //         console.log('Socket закрыт')
  //     }
  //     socket.current.onerror = () => {
  //         console.log('Socket произошла ошибка')
  //     }
  // }

  // const sendMessage = async () => {
  //     const message = {
  //         data,
  //         message: value,
  //         id: Date.now(),
  //         event: 'message'
  //     }
  //     socket.current.send(JSON.stringify(message));
  //     setValue('')
  // }


  // if (!connected) {
  //     return (
  //         <div className="center">
  //             <div className="form">
  //                 <input
  //                     value={data}
  //                     onChange={e => setData(e.target.value)}
  //                     type="text"
  //                     placeholder="Введите ваше имя"/>
  //                 <button onClick={connect}>Войти</button>
  //             </div>
  //         </div>
  //     )
  // }


  // return (
  //     <div className="center">
  //         <div>
  //             <div className="form">
  //                 <input value={value} onChange={e => setValue(e.target.value)} type="text"/>
  //                 <button onClick={sendMessage}>Отправить</button>
  //             </div>
  //             <div className="messages">
  //                 {messages.map(mess =>
  //                     <div key={mess.id}>
  //                         {mess.event === 'connection'
  //                             ? <div className="connection_message">
  //                                 Пользователь {mess.data} подключился
  //                             </div>
  //                             : <div className="message">
  //                                 {mess.username}. {mess.message}
  //                             </div>
  //                         }
  //                     </div>
  //                 )}
  //             </div>
  //         </div>
  //     </div>
  // );

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