/* eslint-disable */
const WebSocket = require('ws');
<<<<<<< HEAD
// const fs = require('fs');
const { info } = require('./scratch_7');

const wss = new WebSocket.Server({ port: 8080 });

wss.on('connection', (ws) => {
  console.log('Client connected');

  // fs.readFile('scratch_7.json', function (err, data) {
  //   if (err) console.log(err);
  //   else {
  //     jsonData = JSON.parse(data);
  //   }
  // })

  let i = 0;
  setInterval(() => {
    if(i <= 3) {
      ws.send(JSON.stringify(info[i]));
      i++;
    }
  }, 500);

  ws.on('message', (msg) => {
    console.log(msg);
  })

  ws.on('close', () => {
    console.log('Client disconnected');
  })
});
=======
const express = require('express');
const app = express();

const wss = new WebSocket.Server({ port: 8080 });

wss.on('connection', (ws) => {
  console.log('New client connected!');

  ws.on('message', (data) => {
    console.log(`Client send ${data}`);
  });

  ws.on('close', () => {
    console.log('Client has disconnected!');
  });
});

// const WSServer = require('express-ws')(app)
// const aWss = WSServer.getWss();
// const router = require('express').Router();
//const { connection } = require('mongoose');

// app.ws('/', (ws, req) => {
//   console.log('SUCCESSFULY CONNECTED');
//   ws.send('Ты подключён');
//   ws.on('message', (msg) => {
//     msg = JSON.parse(msg);
//     switch (msg.method) {
//       case 'connection':
//         connectionHandler(ws, msg);
//         break;
//     }
//   })
// })

// app.listen(8080, () => console.log('Server started on PORT 8080'));

// const getInfo = (req, res) => {
//   const msg = { text: 'aboba' };
//   res.status(200).send(msg);
// };

// router.get('/api/v1/info', getInfo);
// app.use(router);
// const connectionHandler = (ws, msg) => {
//   ws.id = msg.id; //id для каждой сессии
//   broadcastConnection(ws, msg);
// }

// const broadcastConnection = (ws, msg) => {
//   aWss.clients.forEach(client => {
//     if(client.id === msg.id) {
//       client.send(`Пользователь ${msg.username}`);
//     }
//   });
// }
// const wss = new ws.Server({
//   port: 8080,
// }, () => console.log('Server started on PORT 8080'));

// wss.on('connection', function connection(ws) {
//   ws.on('message', function(msg) {
//     msg = JSON.parse(msg);
//     switch (msg.event) {
//       case 'message':
//         broadcastMessage(msg);
//         break;
//       case 'connection':
//         broadcastMessage(msg);
//         break;
//     }
//   })
// });

// function broadcastMessage(message) {
//   wss.clients.forEach(client => {
//     client.send(JSON.stringify(message));
//   })
// }

>>>>>>> main
