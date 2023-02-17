/* eslint-disable */
const WebSocket = require('ws');
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
  }, 100);

  ws.on('message', (msg) => {
    console.log(msg);
  })

  ws.on('close', () => {
    console.log('Client disconnected');
  })
});