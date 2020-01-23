import Vue from 'vue';
import io from 'socket.io-client';

const socket = io('http://192.168.100.41:4000');
// const socket = io('http://localhost:4000');

const SocketPlugin = {
  install(vue) {
    vue.mixin({
    });

    vue.prototype.$sendMessage = ($payload) => {
      socket.emit('chat', {
        dm_id: $payload.dm_id,
        send_id: $payload.user_id,
        receive_id: $payload.other_id,
        message: $payload.message,
        timestamp: $payload.timestamp,
        read_check: $payload.read_check
      });
    };

    // 인스턴스 메소드 추가
    vue.prototype.$socket = socket;
  },
};

Vue.use(SocketPlugin);