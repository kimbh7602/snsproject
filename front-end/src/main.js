import Vue from 'vue'
import App from './App.vue'
import { router } from './router'
import './plugins/element.js'
import store from './store';
import * as VueGoogleMaps from "vue2-google-maps";
// import vuetify from './plugins/vuetify';

// Vue.use(Directives);
Vue.config.productionTip = false
Vue.prototype.$EventBus = new Vue();

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

  router,
  store,
  // vuetify,
  render: h => h(App)
}).$mount('#app')
