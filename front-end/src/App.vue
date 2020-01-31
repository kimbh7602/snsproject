<template>
  <div id="app" class="site-wrap">
    <!-- <Sidebar v-if="loginCheck" />
    <LoginSide v-if="!loginCheck" /> -->
    <Sideex />
    <main class="main-content">
      <!-- 공지 -->
      <div class="all-scroll pos-relative mt-50 col-12" style="height:50px">
        <div class="swiper-scrollbar"></div>
        <div class="swiper-container oflow-visible" style=" text-align:center;" data-slide-effect="flip" data-autoheight="false" 
                                data-swiper-speed="5000" data-swiper-margin="25" data-swiper-slides-per-view="1"  data-swiper-wheel-control="true"
                                data-swiper-breakpoints="true" data-scrollbar="true" data-swiper-loop="true" data-swiper-direction="vertical" 
                                data-swiper-autoplay="true" data-swpr-responsive="[1, 2, 1, 2]">
            <div class="swiper-wrapper" style="height:50px">
                    <div class="flux swiper-slide" v-for="no in noti" :key="no.index">
                      {{no}}
                    </div>
            </div>
        </div>
    </div>
      <router-view :key="$route.fullPath"></router-view>
      <Footer />
    </main>
    <!-- modal -->
    <p id="modalBtn" style="display:none;" data-toggle="modal" data-target="#myModal"></p>
    <div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
      <div class="modal-dialog" role="document">
        <div class="modal-content">
          <div class="modal-body" style="text-align:center;">
            {{$store.state.modalText}}
          </div>
          <div class="modal-footer">
            <button type="button" class="btn btn-danger text-white" data-dismiss="modal">닫기</button>
          </div>
        </div>
      </div>
    </div>
    <vue-snotify></vue-snotify>
  </div>
</template>

<script>
// import Sidebar from "@/components/Sidebar.vue"
// import LoginSide from "@/components/LoginSide.vue"
import Sideex from "@/components/Sideex.vue"
import Footer from "@/components/Footer.vue"
import store from "@/store.js"
// import io from 'socket.io-client';
// import $ from "jquery"
export default {
  name: 'app',
  data(){
    return{
      noti:["aaaaaaaaa","bbbbbbbbbbbbbbb","cccccccccccccccc","dddddddddddddddd","eeeeeeeeeeeee","ffffffffffffff","ggggggggggggg","hhhhhhhhhhhhhhhh","iiiiiiiiiiiiiiiii","aaaaaaaaa"],
    }
  },
  components: {
    // LoginSide,
    // Sidebar,
    Sideex,
    Footer,
  },
  computed: {
    loginCheck: () => {
        return store.state.islogin;
    },
  },
}
</script>