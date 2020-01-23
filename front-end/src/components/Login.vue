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

  export default {
    data (){
        return{
            user_id:"",
            password:"",
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
    },
    mounted(){
        var scrollUpDelay = 1;
        var scrollUpSpeed = 30;
        if(document.body.scrollTop<1)
        {
        return;
        }
        document.body.scrollTop=document.body.scrollTop-scrollUpSpeed;
        setTimeout('scrollUp()',scrollUpDelay);
    }
  }
</script>

<style scoped>
.logo-image{
    height:500px;
    background-size: cover;
}
</style>