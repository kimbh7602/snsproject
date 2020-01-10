import Vue from 'vue'
import App from './App.vue'
import { router } from './router'
import './plugins/element.js'
// import vuetify from './plugins/vuetify';

// Vue.use(Directives);
Vue.config.productionTip = false
Vue.prototype.$EventBus = new Vue();

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
  // vuetify,
  render: h => h(App)
}).$mount('#app')
