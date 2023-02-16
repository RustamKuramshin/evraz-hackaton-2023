/* eslint-disable */
const WebSocket = require('ws');

const wss = new WebSocket.Server({ port: 8080 });

wss.on('connection', ws => {
  console.log('Client connected');

  ws.on('message', data => {
    console.log(`client send: ${data}`);
  })

  ws.on('close', () => {
    console.log('Client disconnected');
  })
});