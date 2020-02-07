import Vue from 'vue'
import App from './App.vue'
import { router } from './router'
// import { store } from './store/index.js';
import './plugins/element.js'
import './plugins/socketPlugin';
// import './plugins/notificationPlugin';
import store from './store';
import * as VueGoogleMaps from "vue2-google-maps";
import Snotify from 'vue-snotify';
import 'vue-snotify/styles/material.css';
import vuetify from '@/plugins/vuetify'


Vue.config.productionTip = false
Vue.prototype.$EventBus = new Vue();

Vue.use(Snotify, {
  toast: {
    timeout: 2000,
    closeOnClick: true,
    titleMaxLength: 30,
  }
});

Vue.use(VueGoogleMaps, {
  load: {
    key: "AIzaSyAcnkt6IBUt-bGIMw4u-VEIYpesgw4-2Lk",
    libraries: "places" // necessary for places input
  }
});

new Vue({
  el: '#app',
  // data: {
  //   swiper: null,
  // },
  // mounted() {
  //   this.swiper = new window.Swiper('.swiper-container',{
  //   })
  // },
  // update() {
  //   this.swiper = new window.Swiper('.swiper-container',{
  //   })
  // },
  vuetify,
  router,
  store,
  // chatStore,
  // vuetify,
  render: h => h(App)
}).$mount('#app')