// import Vue from 'vue';
// import io from 'socket.io-client';

// let notification = io('http://192.168.100.41:3001');

// const NotificationPlugin = {
//   install(vue) {
//     vue.mixin({
//     });

//     vue.prototype.$sendMessage = ($payload) => {
//       notification.emit('notification', $payload);
//     };

//     vue.prototype.$login = ($payload) => {
//       notification.emit('login', $payload);
//     };

//     vue.prototype.$logout = ($payload) => {
//       notification.emit('logout', $payload.user_id);
//     };

//     // vue.prototype.$initRoom = ($payload) => {
//     //   socket.emit('initRoom', $payload);
//     // };

//     // vue.prototype.$joinRoom = ($payload) => {
//     //   socket.emit('joinRoom', $payload);
//     // };

//     // vue.prototype.$leaveRoom = ($payload) => {
//     //   socket.emit('leaveRoom', $payload);
//     // };

//     // vue.prototype.$roomChat = ($payload) => {
//     //   socket.emit('roomChat', $payload);
//     // };

//     // 인스턴스 메소드 추가
//     vue.prototype.$notification = notification;
//   },
// };

// Vue.use(NotificationPlugin);