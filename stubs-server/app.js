/* eslint-disable */
const WebSocket = require('ws');
const fs = require('fs');

const wss = new WebSocket.Server({ port: 8080 });
let jsonData;
function uuidv4() {
  return 'xxxxxxxx-xxxx-4xxx-yxxx-xxxxxxxxxxxx'.replace(/[xy]/g, function(c) {
      var r = Math.random() * 16 | 0, v = c == 'x' ? r : (r & 0x3 | 0x8);
      return v.toString(16);
  });
}


const sleep = (ms) => {
  return new Promise(resolve => setTimeout(resolve, ms));
}

wss.on('connection', async (ws) => {
  console.log('Client connected');

  fs.readFile('scratch_7.json', function (err, data) {
    if (err) console.log(err);
    else {
      jsonData = JSON.parse(data);
    }
  })

  // setInterval(() => {
  //   ws.send(JSON.stringify(jsonData));
  //   setTimeout(() => {
  //     jsonData = { info: uuidv4() };
  //   }, 200)
  // }, 2000);

  setInterval(() => {
    ws.send(JSON.stringify(jsonData));
    setTimeout(() => {
      jsonData = { info: uuidv4() };
    }, 200)
  }, 2000);

  ws.on('message', async (data) => {
    console.log(`client send: ${data}`);
  })

  ws.on('close', () => {
    console.log('Client disconnected');
  })
});