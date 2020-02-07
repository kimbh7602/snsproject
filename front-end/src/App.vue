<template>
  <div id="app" class="site-wrap">
    <!-- <Sidebar v-if="loginCheck" />
    <LoginSide v-if="!loginCheck" /> -->
    <Sideex />
    <main class="main-content">
      <!-- 공지 -->
      <div class="top-noti" style="background-color:black;width:101%">
        <div class="text-white offset-4 col-1" style="text-align:right; display:inline-block;"><span class="icon-notifications_active neon"></span></div>
        <div class="all-scroll col-3 mt-50" style="width:100%; display:inline-block;">
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
            <div style="display:inline-block;" class="col-4">
              <!-- <input style="margin-right:20px;" type="button" class="text-white btn btn-outline-info" value="WRITE" /> -->
              
              <!-- <input type="button" class="text-white btn btn-outline-warning" value="LOGOUT" /> -->
              <!-- <img style="height:100%;" src="/theme/images/edit.png" />
              <img style="width:100%;" src="/theme/images/logout.png" /> -->
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
            <button type="button" id="modalClose-btn" class="btn btn-danger text-white" data-dismiss="modal">닫기</button>
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
import http from "@/http-common.js"
// import io from 'socket.io-client';
// import $ from "jquery"
export default {
  name: 'app',
  data(){
    return{
      noti: [],
      noticeError: ""
    }
  },
  components: {
    // LoginSide,
    // Sidebar,
    Sideex,
    Footer,
  },
  methods: {
    getNotice() {
      http
        .get('/notice/noticeNow')
        .then((res) => {
          // console.log(res)
          if (res.data.resmsg === "조회성공") {
            this.noticeError = ""
            const noticeTitle = []      
            res.data.resValue.forEach(function(el) {
              noticeTitle.push(el.title)
            })
            this.noti = noticeTitle
          } else {
            this.noticeError = "공지사항이 없습니다."
          }
        })
    }
  },
  computed: {
    loginCheck: () => {
        return store.state.islogin;
    },
  },
  created() {
    // this.getNotice();
    // console.log(this.noti);

  },
  mounted(){
    this.getNotice();

  },
  updated() {
    //modal 뜰 때 body에 padding 붙는거 없애주는거
    document.body.style.paddingRight = 0;
    
      let recaptchaScripta = document.createElement('script')
      recaptchaScripta.setAttribute('type',"text/javascript")
      recaptchaScripta.setAttribute('src', "./theme/js/script.js")
      document.body.appendChild(recaptchaScripta)
      let recaptchaScript = document.createElement('script')
      recaptchaScript.setAttribute('type',"text/javascript")
      recaptchaScript.setAttribute('src', "./theme/js/swiper.js")
      document.body.appendChild(recaptchaScript)
  }
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
#myModal{
  z-index:99999;
}

@media (min-width: 992px) {
  #myModal{
    padding-right:0px;
    position:absolute;
    top:50px;
  }
}
@media (max-width: 991.98px) {
  #myModal{
    top:255px;
  }
}
</style>