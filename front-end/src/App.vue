<template>
  <div id="app" class="site-wrap">
    <!-- <Sidebar v-if="loginCheck" />
    <LoginSide v-if="!loginCheck" /> -->
    <Sideex />
    <main class="main-content">
      <!-- 공지 -->
      <div class="top-noti" style="background-color:black; width:100%">
        <div class="text-white offset-4 col-1" style="text-align:right; display:inline-block;"><span class="icon-notifications_active neon"></span></div>
        <div class="all-scroll col-7 mt-50" style="width:100%; display:inline-block;">
          <div class="swiper-scrollbar"></div>
          <div class="swiper-container oflow-visible" style="padding-top:10px; background-color:black; height:50px;" data-slide-effect="flip" data-autoheight="false" 
                                  data-swiper-speed="5000" data-swiper-margin="25" data-swiper-slides-per-view="1"  data-swiper-wheel-control="true"
                                  data-swiper-breakpoints="true" data-scrollbar="true" data-swiper-loop="true" data-swiper-direction="vertical" 
                                  data-swiper-autoplay="true" data-swpr-responsive="[1, 2, 1, 2]">
              <div class="swiper-wrapper" style="height:10px;">
                <div class="elip text-white swiper-slide" style="background-color:black;" v-for="no in noti" :key="no.index">
                  {{no}}
                </div>
              </div>
            </div>
        </div>
      </div>

      <!-- 공지 2 -->
      <!-- <MARQUEE onmouseover=this.stop() style="WIDTH: 100%; HEIGHT: 10px" onmouseout=this.start() scrollAmount=1 scrollmount=10 direction=up>
        <div style="PADDING-RIGHT: 0px; OVERFLOW-Y: auto; PADDING-LEFT: 0px; OVERFLOW-X: hidden; PADDING-BOTTOM: 3px; WIDTH: 100%; PADDING-TOP: 3px">
          <div class="neon" style="display:inline-block; background-color:black;" v-for="no in noti" :key="no.index">
                {{no}}
          </div>
        </div>
      </MARQUEE> -->
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
      noti:["!!!!!","bbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbb","cccccccccccccccc","dddddddddddddddd","eeeeeeeeeeeee","ffffffffffffff","ggggggggggggg","hhhhhhhhhhhhhhhh","iiiiiiiiiiiiiiiii","aaaaaaaaa"],
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
<style>
.snotifyToast__title {
  font-size: 17px;
}
.snotifyToast__body {
  font-size: 12px;
}
.elip{
  text-overflow:ellipsis;
  white-space:nowrap;
  word-wrap:normal;
  width:180px;
  overflow:hidden;
}
</style>