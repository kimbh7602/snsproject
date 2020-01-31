import Vue from 'vue';
import io from 'socket.io-client';

let socket = io('http://192.168.100.41:3000');

const SocketPlugin = {
  install(vue) {
    vue.mixin({
    });

    vue.prototype.$sendMessage = ($payload) => {
      socket.emit('chat', $payload);
    };

    vue.prototype.$login = ($payload) => {
      socket.emit('login', $payload);
    };

    vue.prototype.$logout = ($payload) => {
      socket.emit('logout', $payload.user_id);
    };

    // vue.prototype.$initRoom = ($payload) => {
    //   socket.emit('initRoom', $payload);
    // };

    // vue.prototype.$joinRoom = ($payload) => {
    //   socket.emit('joinRoom', $payload);
    // };

    // vue.prototype.$leaveRoom = ($payload) => {
    //   socket.emit('leaveRoom', $payload);
    // };

    // vue.prototype.$roomChat = ($payload) => {
    //   socket.emit('roomChat', $payload);
    // };

    // 인스턴스 메소드 추가
    vue.prototype.$socket = socket;
  },
};

Vue.use(SocketPlugin);