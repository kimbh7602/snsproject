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

    <!-- <p id="notiBtn" style="display:none;" data-toggle="modal" data-target="#myModal"></p> -->
    <div class="modal fade" id="notiModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
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


    <header class="header-bar d-flex d-lg-block align-items-center" data-aos="fade-left">
        <div class="notification" id="notiBtn">
            <i class="icon-bell text-white" style="font-size:1.5em;">
                <span class="badge" style="font-size:0.5em;">
                    <em>{{notify}}</em>
                </span>
            </i>
        </div>
        <div class="site-logo" style="margin-left:3%;">
            <router-link to="/">Shutter</router-link>
        </div>
        <div class="site-logo">
            <input type="button" class="site-logo btn btn-danger btn-md text-white" @click="goWrite" value="Write" />
            <input type="button" class="site-logo btn btn-success btn-md text-white" @click="logout" value="Logout" />
            <!-- <router-link to="/addimage">Write</router-link> -->
        </div>
        <div class="d-inline-block d-xl-none ml-md-0 ml-auto py-3" style="position: relative; top: 3px;"><a href="#" class="site-menu-toggle js-menu-toggle text-white"><span class="icon-menu h3"></span></a></div>

        <div class="main-menu">
        <ul class="js-clone-nav">
            <li class="active"><router-link to="/">Home</router-link></li>
            <!-- <li><router-link to="/bio">Bio</router-link></li> -->
            <li><router-link to="/blog">Blog</router-link></li>
            <li><router-link to="/single">Single</router-link></li>
            <!-- <li><router-link to="/register">Register</router-link></li> -->
            <!-- <li><router-link to="/password">Password</router-link></li> -->
            <li><router-link to="/chating">Chating</router-link></li>
            <li><router-link to="/addimage">Write</router-link></li>
            <li><router-link to="/findfriend">Find a Friend</router-link></li>
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
// import $ fromd "jquery"
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
            window.console.log(this.$store.state.user_id);
            this.$logout({
                user_id : this.$store.state.user_id
            });
            // this.$socket.emit('disconnectEvt', function(){
            //     this.$socket.disconnet();
            // });
            this.$store.commit("logout");
            document.getElementById('modalBtn').click();
            this.$router.push("/login");
        },
    },
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
</style>