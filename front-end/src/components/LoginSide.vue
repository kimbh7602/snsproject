<template>
<div>
    <div class="site-mobile-menu">
        <div class="site-mobile-menu-header">
        <div class="site-mobile-menu-close mt-3">
            <span class="icon-close2 js-menu-toggle"></span>
        </div>
        </div>
        <div class="site-mobile-menu-body"></div>
    </div>
    <header class="header-bar d-flex d-lg-block align-items-center" data-aos="fade-left">
        <div class="site-logo" style="margin-left:3%;">
            <router-link to="/login">Shutter</router-link>
        </div>
        <div class="site-logo">
        </div>
        <div class="d-inline-block d-xl-none ml-md-0 ml-auto py-3" style="position: relative; top: 3px;"><a href="#" class="site-menu-toggle js-menu-toggle text-white"><span class="icon-menu h3"></span></a></div>

        <div class="main-menu">
        <ul class="js-clone-nav">
            <li class="active"><router-link to="/login">Home</router-link></li>
            <li><router-link to="/register">Register</router-link></li>
            <li><router-link to="/password">Password</router-link></li>
        </ul>
        <ul class="social js-clone-nav">
            <li><a href="#"><span class="icon-facebook"></span></a></li>
            <li><a href="#"><span class="icon-twitter"></span></a></li>
            <li><a href="#"><span class="icon-instagram"></span></a></li>
        </ul>
        </div>
    </header>
</div>
</template>
<script>
// import $ from "jquery"
import store from "../store" 
export default {
    name:"sidebar",
    data(){
        return{
            notify:0,
            // check:false,
        }
    },
    methods: {
        goWrite(){
            this.$router.push("/addimage");
        },
        logout(){
            // this.$socket.emit('logout', this.$store.state.user_id);
            this.$store.commit("logout");
            document.getElementById('modalBtn').click();
            this.$router.push("/login");
        }
    },
    computed: {
        loginCheck: () => {
            return store.state.islogin;
        },
    },
    created() {
    // this.socket = io('http://192.168.100.41:3000');
    this.$socket.on('notification', (data) => {
    //   window.console.log('notification', data, this.$store.state.user_id);
      if(data.target_user_id == this.$store.state.user_id){
        this.$snotify.simple('알림을 확인해보세요!', data.user_id + "님의 " + data.category+"!", {
            icon : '/theme/images/'+data.category+'.png',
            // html : '<div>Like!</div><div>알림을 확인해보세요!</div>'
          });
      }
    });
  }
}
</script>

<style scoped>
.notification {
  text-align: center;
  text-decoration: none;
  padding: 5px 5px;
  position: relative;
  display: inline-block;
  border-radius: 2px;
}

.notification:hover {
  background-color: red;
}

.notification .badge {
  position: absolute;
  top: -10px;
  right: -10px;
  padding: 5px 7px;
  border-radius: 50%;
  background: red;
  /* text-align:center; */
  color: white;
}
.snotifyToast__title {
  font-size: 17px;
  }
  .snotifyToast__body {
    font-size: 12px;
  }
</style>