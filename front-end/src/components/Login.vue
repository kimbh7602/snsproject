<template>

  <div class="offset-md-2 col-md-8" data-aos="fade-up">
    <div style="display:none" class="dummy" />
    <div class="offset-md-1 col-md-10">
    <div style="height:10px;"></div>
    <div class="logo-image" style="background-image: url('./theme/images/mainpage.jpg') ">
    </div>

    <label class="text-white" for="user_id">ID</label>
    <div class="d-flex bd-highlight">
        <div class="w-100 bd-highlight">
            <input type="text" required v-model="user_id" id="user_id" class="form-control">
        </div>
    </div>
    <label class="text-white" for="password">PW</label>
    <div class="d-flex bd-highlight">
        <div class="w-100 bd-highlight">
            <input type="password" required v-model="password" id="password" class="form-control">
        </div>
    </div>
    <br><br>
    <input type="button" value="Login" @click="login" class="btn btn-outline-light btn-block text-white">
    <br>
    <div style="margin-top:1%;margin-left:5%;margin-right:5%; height:50px;">
      <div style="text-align:center; font-style:">비밀번호가 기억나지 않으세요?</div>
      <div style="text-align:center">아직 회원이 아니신가요?</div>
    </div>

    <div style="margin-top:1%;margin-left:5%;margin-right:5%; height:50px;">
      <div style="display:inline-block; float :left">
        <input type="button" value="Login" @click="login" class="btn btn-primary btn-md text-white">
      </div>
      <div style="display:inline-block; float:right">
        <input type="button" value="FindPw" @click="findpw" class="btn btn-success btn-md text-white">
      </div>
    </div>
    </div>
  </div>
</template>

<script>
import http from "../http-common";
import $ from "jquery"
  export default {
    data (){
        return{
            user_id:"",
            password:"",
            logintrue:false,
        }
    },
    methods: {
      login () {
          http
            .post("/user/login",{
                user_id : this.user_id,
                password:this.password,
            })
            .then(response => {
                if(response.data['resmsg'] == "로그인"){
                    this.$store.commit("login", response.data['resValue']);
                    // this.$socket.emit('login', {
                    //   user_id : this.user_id
                    // });
                    document.getElementById('modalBtn').click();
                    this.$router.push("/");
                }else{
                    this.$store.commit('setModalText', "아이디 또는 비밀번호를 확인해주세요.");
                    document.getElementById('modalBtn').click();
                }
            })
      },
      findpw(){
          this.$router.push("/password");
      },
      // sendNotification(){
      //       window.console.log("notification");
      //       // this.$Eventbus.$emit('notification', {
      //       //   body: '알림을 확인해보세요!',
      //       //   title: 'Like!',
      //       //   icon: 'favicon.ico'
      //       // });
      //       this.$snotify.simple('알림을 확인해보세요!', 'Like!', {
      //         icon : '/favicon.ico',
      //         // html : '<div>Like!</div><div>알림을 확인해보세요!</div> <input type="button" @click="sendNotification" value="Login" class="btn btn-sm">'
      //       });
      //   },
      // requestPermission(){
      //   Notification.requestPermission(function(result) {
      //     if(result === 'denied'){
      //       return;
      //     }
      //   });
      // }
    },
    mounted(){
      $('html').scrollTop(0);
      this.$nextTick(() => {
        // 모든 화면이 렌더링된 후 호출됩니다.
        if(document.querySelector(".site-nav-wrap")==null){
          $('.js-clone-nav').each(function() {
            var $this = $(this);
            $this.clone().attr('class', 'site-nav-wrap').appendTo('.site-mobile-menu-body');
          });
        }
      });
    //   if(document.querySelector(".site-nav-wrap")==null){
    //   $('.js-clone-nav').each(function() {
    //     var $this = $(this);
    //     $this.clone().attr('class', 'site-nav-wrap').appendTo('.site-mobile-menu-body');
    //   });
    // }

    //   // let uls = document.querySelector("ul");
    //     // console.log(document.querySelectorAll("ul")); 
    //   setTimeout (() => {
    //     if(document.querySelectorAll("ul").length>4){
    //     document.querySelector("ul").remove();
    //     document.querySelector("ul").remove();
    //     }
    //   }, 1000);
    }
  }
</script>

<style scoped>
.logo-image{
    height:500px;
    background-size: cover;
}
</style>