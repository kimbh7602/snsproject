<template>
  <div class="container-fluid photos">
    <div class="row justify-content-center">
      <div class="col-md-6 pt-4" data-aos="fade-up">
        <h2 class="text-white mb-4">비밀번호 찾기</h2>

        <div class="row" v-if="!axios_ing">
          <div class="col-md-12">
            <!-- <p class="mb-5">비밀번호를 찾고자 하는 아이디를 입력해 주세요.</p>               -->
            <div class="row">
              <div class="col-md-12">
                <form action="" method="post" @submit.prevent="temppw">
                  <div style="display:none">
                    <input type="submit" onclick="return false;" />
                    <input type="text"/>
                  </div>

                  <!-- id -->
                  <div v-if="!idcheck" class="row form-group">
                    <div class="col-md-12">
                      <label class="text-white" for="uid">ID</label>
                      <div class="d-flex bd-highlight">
                        <div class="w-100 bd-highlight">
                          <input type="text" v-model="uid" id="uid" class="form-control">
                        </div>
                        <div class="flex-shrink-1 bd-highlight">
                          <input type="button" value="다음" class="btn btn-link" v-on:click="checkId()">
                        </div>
                      </div>
                    </div>
                  </div>

                  <div v-if="idcheck">

                    <!-- email -->
                    <div class="row form-group">
                      <div class="col-md-12">
                        <label class="text-white" for="uemail">Email</label> 
                        <div class="d-flex bd-highlight">
                          <div class="w-100 bd-highlight">
                            <input type="email" id="email" v-model="uemail" class="form-control" pattern="[a-z0-9._%+-]+@[a-z0-9.-]+\.[a-z]{2,4}$" placeholder="XXXXX@XXXX.XXX">
                          </div>
                          <div class="flex-shrink-1 bd-highlight">
                            <input type="button" value="이메일 확인" class="btn btn-link" v-on:click="duplicateEmail()">
                          </div>
                        </div>
                      </div>
                    </div>

                    <!-- register/reset button -->
                    <div class="row form-group">
                      <div class="col-md-12">
                        <input type="submit" value="임시 비밀번호 발급" class="btn btn-primary btn-md text-white">
                        <input type="reset" value="다시시도" @click="resetboolean" class="btn btn-danger btn-md text-white">
                      </div>
                    </div>
                    
                  </div>
                </form>
              </div>
            </div>
          </div>
        </div>

        <div class="row" v-else>
          <img class="col-md-12" src="/theme/images/loading10.gif" />
        </div>

      </div>
    </div>
  </div>
</template>

<style>
  .roundedge {
    border-radius: 5px;
    display: inline-block;
    margin-top: 5px;
    margin-right: 3px;
    border-color: white;
    border-style: solid;
    border-width: 1px;
  }
</style>

<script>
import http from "../http-common"
import $ from "jquery"
export default {
  name: "register",
  data(){
    return{
      uid:"",
      upw:"",
      uemail:"",
      checkemail:"",
      loading:true,
      errored:false,
      idcheck: false,
      booleanemail: false,
      checkid:"",
      axios_ing:false,
    }
  },
  methods:{
    checkId() {
      if (this.uid === "") {
        this.$store.commit('setModalText', '아이디를 입력해주세요.');
        document.getElementById('modalBtn').click();
      } else {
        http
          .get('/user/info/'+this.uid)
            .then((response)=>{
              if (response.data['resmsg'] == "조회성공") { // id가 존재할 때,
                  this.idcheck = true;
                  var info = response.data['resvalue'];
                  this.checkid = info.user_id;
                  this.upw = info.password;
                  this.checkemail = info.email;
              } else { // id가 존재하지 않을 때
                this.$store.commit('setModalText', "탈퇴한 아이디이거나 없는 아이디입니다.");
                document.getElementById('modalBtn').click();
              }
            })
            .catch((error) => {
              this.errored = true;
                alert(error);
            })
            .finally(() => (this.loading = false));
      }
    },
    duplicateEmail() {
      var regExp = /^[a-z0-9._%+-]+@[a-z0-9.-]+\.[a-z]{2,4}$/i;
          // var regExp = /^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}$/i;
      if (this.uemail === "") {
        this.$store.commit('setModalText', "이메일을 입력해주세요.");
        document.getElementById('modalBtn').click();            
      }else if (!regExp.test(this.uemail)) {
        this.$store.commit('setModalText', "이메일 형식으로 입력해주세요.");
        document.getElementById('modalBtn').click();
      }else if(this.checkemail != this.uemail){
        this.$store.commit('setModalText', "회원정보의 이메일과 다릅니다.");
        this.booleanemail = false;
        document.getElementById('modalBtn').click();
      }else {
        this.booleanemail = true;
        this.$store.commit('setModalText', "회원정보의 이메일과 일치합니다.");
        document.getElementById('modalBtn').click();
      }
    },

    temppw() {
      if(!this.booleanemail && this.idcheck){
        this.$store.commit('setModalText', "이메일 확인을 진행해주세요.");
        document.getElementById('modalBtn').click();        
      }
      else if(this.booleanemail && this.idcheck){
        this.axios_ing=  true;

        http
          .post("email/findpassword/" +this.uemail)
          .then((response)=>{
              if (response.data['resmsg'] == "메일 보내기 성공") { 
                document.getElementById('modalClose-btn').click();
                this.$store.commit('setModalText', "임시 비밀번호 발급 성공.");
                document.getElementById('modalBtn').click();
                this.$router.push("/login")
              } else {
                this.$store.commit('setModalText', "임시 비밀번호 발급 실패.");
                document.getElementById('modalBtn').click();
              }
            })
            .catch((error) => {
              this.errored = true;
                alert(error);
            })
            .finally(() => (this.loading = false));
      }
    },
    resetboolean() {
      this.booleanemail = false;
      this.idcheck = false;
      this.uid = "";
    }
  },
  mounted(){
    $('html').scrollTop(0);
  }
}
</script>