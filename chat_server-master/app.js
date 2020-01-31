var app = require('express')();
var server = require('http').createServer(app);
var io = require('socket.io')(server,{
  pingTimeout: 1000,
});

app.all('/*', function(req, res, next) {
  res.header("Access-Control-Allow-Origin", "*");
  res.header("Access-Control-Allow-Headers", "X-Requested-With");
  next();
});

// localhost:3000서버에 접속하면 클라이언트로 메세지을 전송한다
app.get('/', function(req, res) {
  res.sendFile('Hellow Chating App Server');
});

var users = {};

io.on('connection', function(socket){

  socketIds.push(socket.io);

  // 클라이언트로부터의 메시지가 수신되면
  socket.on('chat', function(data) {
    
    // 소켓아이디 저장
    users[data.receive_id] = socket.id;

    console.log('Message from %s: %s', data.send_id, data.message);

    // 일대일 상대에게 보내기
    // io.to(users[data.receive_id]).emit('result', data);
    // 그룹에 보내기
    // io.to('room' + data.dm_id).emit('result', data);
    // io.sockets.emit('result', data);
  });
});

server.listen(3000, () => {
  console.log('listening on *:3000');
})