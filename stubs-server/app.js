/* eslint-disable */
//const ws = require('ws');
const express = require('express');
const { connection } = require('mongoose');
const app = express();
const WSServer = require('express-ws')(app)
const aWss = WSServer.getWss();
const router = require('express').Router();

const sendingData = { message: 'Data for GET request' };

app.ws('/', (ws, req) => {
  console.log('SUCCESSFULY CONNECTED');
  ws.send('Ты подключён');
  ws.on('message', (msg) => {
    msg = JSON.parse(msg);
    switch (msg.method) {
      case 'connection':
        connectionHandler(ws, msg);
        break;
    }
  })
})

app.listen(8080, () => console.log('Server started on PORT 8080'));

const getInfo = (req, res) => {
  const msg = { text: 'aboba' };
  res.status(200).send(msg);
};

router.get('/api/v1/info', getInfo);
app.use(router);
const connectionHandler = (ws, msg) => {
  ws.id = msg.id; //id для каждой сессии
  broadcastConnection(ws, msg);
}

const broadcastConnection = (ws, msg) => {
  aWss.clients.forEach(client => {
    if(client.id === msg.id) {
      client.send(`Пользователь ${msg.username}`);
    }
  });
}
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