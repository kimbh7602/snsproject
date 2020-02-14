<template>

  <div class="offset-md-2 col-md-8" data-aos="fade-up">
    <div style="display:none" class="dummy" />
    <div class="offset-md-1 col-md-10">
    <!-- <div class="logo-image" style="background-image: url('./theme/images/mainpage.jpg') "> -->
    <div style="text-align:center; padding-bottom:10%;">
      <!-- <img src='../../public/theme/images/mail_512px.png' style="height:25%;width:25%;"/> -->
      <!-- <div style="position:absolute; left:140px; top:2px;"> -->
        <h1 class="logo_name text-white" data-aos="fade-up" data-aos-duration=90000 data-aos-delay=700>떠</h1>
        <h1 class="logo_name text-white" data-aos="fade-up" data-aos-duration=90000 data-aos-delay=1400>올</h1>
        <h1 class="logo_name text-white" data-aos="fade-up" data-aos-duration=90000 data-aos-delay=2100>리</h1>
        <h1 class="logo_name text-white" data-aos="fade-up" data-aos-duration=90000 data-aos-delay=2800>다</h1>
      <!-- </div> -->
    </div>

    <label class="text-white" for="user_id">ID</label>
    <div class="d-flex bd-highlight">
        <div class="w-100 bd-highlight">
            <input type="text" required v-model="user_id" id="user_id" @keydown.enter="login" class="form-control">
        </div>
    </div>
    <label class="text-white" for="password">PW</label>
    <div class="d-flex bd-highlight">
        <div class="w-100 bd-highlight">
            <input type="password" required v-model="password" id="password" @keydown.enter="login" class="form-control">
        </div>
    </div>
    <br><br>
    <input type="button" value="Login" @click="login" class="btn btn-outline-light btn-block text-white">
    <br>
    <div style="margin-top:1%;margin-left:5%;margin-right:5%; height:50px;">
      <div style="margin-bottom:3px; text-align:center"><span @click="register" style="cursor:pointer; border-bottom:1px solid;">아직 회원이 아니신가요?</span></div>
      <div style="text-align:center;"><span @click="findpw" style="cursor:pointer; border-bottom:1px solid;">비밀번호가 기억나지 않으세요?</span></div>
    </div>

    <!-- <div style="margin-top:1%;margin-left:5%;margin-right:5%; height:50px;">
      <div style="display:inline-block; float :left">
        <input type="button" value="Login" @click="login" class="btn btn-primary btn-md text-white">
      </div>
      <div style="display:inline-block; float:right">
        <input type="button" value="FindPw" @click="findpw" class="btn btn-success btn-md text-white">
      </div>
    </div> -->
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
                    this.$store.dispatch('FETCH_NOTI', this.user_id);
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
      register(){
        this.$router.push("/register");
      }
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
      // console.log(document.querySelector("ul").remove());
          $('.js-clone-nav').each(function() {
            var $this = $(this);
            $this.clone().attr('class', 'site-nav-wrap').appendTo('.site-mobile-menu-body');
          });
          document.querySelector("ul").remove();
          // document.querySelector("ul").remove();
      });
    }
  }
</script>

<style scoped>
.logo-image{
    height:500px;
    background-size: cover;
}
.logo_name{
  font-size:8rem;
  display:inline-block;
  font-family: hand_mail;

}
@media(max-width:429px){
  .logo_name{
    font-size:5rem;
  }
}
@media(min-width:992px){
  .logo_name{
    font-size:6rem;
  }
}
@media(max-width:1124px){
  .logo_name{
    font-size:6rem;
  }
}
@media(min-width:1125px){
  .logo_name{
    font-size:8rem;
  }
}
</style>