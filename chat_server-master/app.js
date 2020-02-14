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
let clients = [];
let room = [];

io.on('connection', function(socket){
  console.log('connect');
  socket.on('login', function(data) {
    let client = new Object();
    client.uid = data.user_id;
    client.id = socket.id;
    clients.push(client);
    console.log('login');
    console.log(clients);
  });

  // socket.on('initRoom', function(data) {
  //   room = data;
  //   console.log('data', data)
  //   console.log('room', room);
  // });

  // socket.on('createRoom', function(data) {
  //   room.push(data);
  // });

  // socket.on('joinRoom', function(data) {
  //   socket.join(data, () => {
  //     console.log('join');
  //   });
  // });

  // socket.on('leaveRoom', function(data) {
  //   socket.leave(data, () => {
  //     console.log('leave');
  //   });
  // });

  // socket.on('roomChat', function(data) {
  //   room.forEach(element => {
  //     if(element.dm_id == data.dm_id){
  //       io.to(element).emit('roomChat', data);
  //     }
  //   })
  // });





  // socketIds.push(socket.io);

  // 클라이언트로부터의 메시지가 수신되면
  socket.on('chat', function(data) {
    
    // 소켓아이디 저장
    // users[data.receive_id] = socket.id;

    console.log('Message from %s: %s', data.send_id, data.receive_id, data.message);

    io.to(socket.id).emit('chat', data);
    // console.log(socket.id, data.message);
    // console.log(data);
    clients.some(element => {
      if(element.uid == data.receive_id){
        io.to(element.id).emit('chat', data);
        // console.log(element.uid + " " + element.id);
        return (element.uid == data.receive_id);
      }
    });
    // 일대일 상대에게 보내기
    // io.to(users[data.receive_id]).emit('result', data);
    // 그룹에 보내기
    // io.to('room' + data.dm_id).emit('result', data);
    // io.sockets.emit('result', data);

    // socket.broadcast.emit('chat', data);
  });

  socket.on('read', function(data){
    console.log('read');
    console.log(data.send_id, data.receive_id);

    clients.some(element => {
      if(element.uid == data.send_id){
        io.to(element.id).emit('read', data);
        return(element.uid == data.send_id);
      }
    })
  });

  socket.on('notification', function(data) {
    
    // 소켓아이디 저장
    // users[data.receive_id] = socket.id;
    console.log(data);
    console.log('Message from %s: %s', data.user_id, data.target_user_id, data.category);
    socket.broadcast.emit('notification', data);

    clients.some(element => {
      if(element.uid == data.target_user_id){
        // io.to(element.id).emit('notification', data);
        console.log(element.uid + " " + element.id);
        return (element.uid == data.target_user_id);
      }
    });
  });

  socket.on('disconnectEvt', function() {
    clients.forEach(element => {
      if(element.id == socket.id){
        const idx = clients.indexOf(element);
        clients.splice(idx, 1);
      }
    });
    const id = socket.id;
    socket.disconnect(false);
    // socket.conn(id);
    console.log('dis');
    console.log(clients);
  });

  socket.on('disconnect', function() {
    // clients.forEach(element => {
    //   if(element.id == socket.id){
    //     const idx = clients.indexOf(element);
    //     clients.splice(idx, 1);
    //   }
    // });
    console.log('disc');
    console.log(clients);
  });

  socket.on('logout', function(data) {
    clients.forEach(element => {
      if(element.uid == data){
        const idx = clients.indexOf(element);
        clients.splice(idx, 1);
      }
    });
    console.log('logout');
    console.log(clients);
  });
});

server.listen(3000, () => {
  console.log('listening on *:3000');
})