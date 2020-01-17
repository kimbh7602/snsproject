<template>
  <div class="container-fluid photos">
    <div class="row justify-content-center">
      <div class="col-md-6 pt-4" data-aos="fade-up">
        <h2 class="text-white mb-4">비밀번호 찾기</h2>

        <div class="row">
          <div class="col-md-12">
            <p class="mb-5">비밀번호를 찾고자 하는 아이디를 입력해 주세요.</p>              
            <div class="row">
              <div class="col-md-12">

                <form action="" method="post" @submit.prevent="regist">
                  <div style="display:none">
                    <input type="submit" onclick="return false;" />
                    <input type="text"/>
                  </div>

                  <!-- id -->
                  <div class="row form-group">
                    <div class="col-md-12">
                      <label class="text-white" for="uid">ID</label>
                      <div class="d-flex bd-highlight">
                        <div class="w-100 bd-highlight">
                          <input type="text" v-model="uid" id="uid" class="form-control">
                        </div>
                        <div class="flex-shrink-1 bd-highlight">
                          <input type="button" value="다음" class="btn btn-link" v-on:click="duplicateId()">
                        </div>
                      </div>
                    </div>
                  </div>

                  <div v-if="id">

                    <!-- email -->
                    <div class="row form-group">
                      <div class="col-md-12">
                        <label class="text-white" for="uemail">Email</label> 
                        <div class="d-flex bd-highlight">
                          <div class="w-100 bd-highlight">
                            <input type="email" id="email" v-model="uemail" class="form-control" pattern="[a-z0-9._%+-]+@[a-z0-9.-]+\.[a-z]{2,4}$" placeholder="XXXXX@XXXX.XXX">
                          </div>
                          <div class="flex-shrink-1 bd-highlight">
                            <input type="button" value="인증번호 받기" class="btn btn-link" v-on:click="duplicateEmail()">
                          </div>
                        </div>
                      </div>
                    </div>

                    <!-- 중복이 아닌 이메일인 경우 이메일 인증할 수 있게 화면에 띄우기 -->
                    <div v-show="errored">
                      <div class="row form-group">
                        <div class="col-md-12 ml-5">

                          <div class="d-flex bd-highlight">
                            <div class="w-100 bd-highlight">
                              <label class="text-white">인증번호입력</label>
                            </div>
                            <div class="flex bd-highlight">

                              <!-- 인증번호 입력 가능한 3분의 시간 화면에 나타내기 -->
                              <section id="app" class="hero is-info is-fullheight is-bold">
                                <div class="hero-body">
                                  <div class="container has-text-centered">
                                    <div id="timer" class="text-white">
                                      <span id="minutes">{{ minutes }}</span>
                                      <span id="middle">:</span>
                                      <span id="seconds">{{ seconds }}</span>
                                    </div>
                                  </div>
                                </div>
                              </section>

                            </div>
                          </div>

                          <!-- 인증번호  -->
                          <div class="d-flex bd-highlight">
                            <div class="w-100 bd-highlight">
                              <input type="email" v-model="uniqnum" class="form-control">
                            </div>
                            <div class="flex-shrink-1 bd-highlight">
                              <input type="button" value="인증번호 받기" class="btn btn-link" v-on:click.once="uniqueNum(), startTimer()">
                            </div>
                            <div class="flex-shrink-1 bd-highlight">
                              <input type="button" value="인증번호 확인" class="btn btn-link" v-on:click="uniqueNumCheck()">
                            </div>
                          </div>

                        </div>
                      </div>
                    </div>

                    <!-- register/reset button -->
                    <div class="row form-group">
                      <div class="col-md-12">
                        <input type="submit" value="임시 비밀번호 발급" class="btn btn-primary btn-md text-white">
                        <input type="reset" value="다시시도" @click="deleteDiv" class="btn btn-danger btn-md text-white">
                      </div>
                    </div>
                    
                  </div>
                </form>

              </div>
            </div>
          </div>
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
export default {
  name: "register",
  data(){
    return{
      uid:"",
      upw:"",
      uemail:"",
      uniqnum: "",
      itrlist:[],
      uitrlist:[],
      loading:true,
      errored:false,
      timer: null,
      totalTime: (3 * 60),
      id: false,
    }
  },
  methods:{
    startTimer: function() {
      this.timer = setInterval(() => this.countdown(), 1000);
      this.resetButton = true;
    },
    resetTimer: function() {
      this.totalTime = (3 * 60);
      clearInterval(this.timer);
      this.timer = null;
    },
    padTime: function(time) {
      return (time < 10 ? '0' : '') + time;
    },
    countdown: function() {
      if(this.totalTime >= 1){
        this.totalTime--;
      } else{
        this.totalTime = 0;
        this.resetTimer()
      }
    },
    duplicateId() {
      if (this.uid === "") {
        alert('아이디를 입력해주십시오.')
      } else {
        http
          .get('content/urls/1', {
            user_id: this.uid,
          })
            .then((response)=>{
              if (response.data['resmsg'] == "true") { // id가 존재할 때,
                  this.id = true;
              } else { // id가 존재하지 않을 때
                  alert("탈퇴한 아이디이거나 없는 아이디입니다.");
              }
            })
            .catch(() => {
              this.errored = true;
                alert("error");
            })
            .finally(() => (this.loading = false));
      }
    },
    duplicateEmail() {
      if (this.uemail === "") {
        alert('이메일을 입력해주십시오.')
      } else if (!this.uemail.includes("@")) {
        alert('이메일 형식으로 입력해주십시오.')
      } else if (this.uemail.length < 8) {
        alert('제대로 된 이메일 좀')
      } else {
        http
          .get("content/urls/' + 1 ", {
            email: this.uemail,
          })
            .then((response)=>{
              if (response.data['resmsg'] == "true") { // 중복일 때,
                  alert("이미 사용 중인 이메일입니다.");
              } else {
                  alert("사용 가능한 이메일입니다.");
              }
            })
            .catch(() => {
              this.errored = true;
                alert("error");
            })
            .finally(() => (this.loading = false));
      }
    },
    uniqueNum() {
      http
          .get('/url/ddd')
            .then((response)=>{
              this.uniqnum = response.data
            })
            .catch(() => {
              this.errored = true;
            })
            .finally(() => (this.loading = false));
    },
    uniqueNumCheck() {
      http
        .get('/url/ddd', {
          uniquenumber: this.uniqnum,
        })
          .then((response)=>{
            if (response.data['resmsg'] == "true") {
              alert('인증되었습니다.')
              this.resetTimer()
            } else {
              alert('인증번호가 일치하지 않습니다.')
            }
          })
          .catch(() => {
            this.errored = true;
            if (this.errored == true) {
              alert('인증번호가 일치하지 않습니다.');
            } else {
              alert('인증되었습니다.');
            }
          })
          .finally(() => (this.loading = false));
    },
    appendDiv() {
      var colorCode  = "#" + Math.round(Math.random() * 0xffffff).toString(16);
      var div = document.createElement('div');
      var span = document.createElement('span');
      var bold = document.createElement('bold')
      var text = document.getElementById('interest').value;
      // this.itrlist.push(text);
      bold.innerText = text;
      div.style.background=colorCode;
      div.classList.add('roundedge');
      div.classList.add('itrlone');
      div.classList.add('text-white');
      span.classList.add('icon-remove')
      span.addEventListener("click", function(event){
        event.target.parentNode.remove();
      });
      div.appendChild(bold);
      div.appendChild(span);
      document.getElementById('itrl').appendChild(div);
      document.getElementById('interest').value='';
      document.getElementById('interest').focus();
    },
    deleteDiv() {
      var r = document.getElementById('itrl');
      var u = document.getElementById('uitrl');
      document.getElementById('parentUitrl').removeChild(u);
      document.getElementById('parentItrl').removeChild(r);
      var rediv = document.createElement('div');
      var rediv2 = document.createElement('div');
      rediv.id = 'itrl';
      rediv2.id = 'uitrl';
      document.getElementById('parentItrl').appendChild(rediv);
      document.getElementById('parentUitrl').appendChild(rediv2);
      //관심사 리셋되며 배열도 리셋
      this.itrlist.splice(0);
    },
    appenduDiv() {
      var colorCode  = "#" + Math.round(Math.random() * 0xffffff).toString(16);
      var div = document.createElement('div');
      var span = document.createElement('span');
      var bold = document.createElement('bold')
      var text = document.getElementById('uninterest').value;
      // this.itrlist.push(text);
      bold.innerText = text;
      div.style.background=colorCode;
      div.classList.add('roundedge');
      div.classList.add('uitrlone');
      div.classList.add('text-white');
      span.classList.add('icon-remove')
      span.addEventListener("click", function(event){
        event.target.parentNode.remove();
      });
      div.appendChild(bold);
      div.appendChild(span);
      document.getElementById('uitrl').appendChild(div);
      document.getElementById('uninterest').value='';
      document.getElementById('uninterest').focus();
    },
    regist() {
      var itrltemp = document.getElementsByClassName('itrlone');
      var uitrltemp = document.getElementsByClassName('uitrlone');
      for(var i=0; i<itrltemp.length; i++){
        this.itrlist[i] = itrltemp[i].innerText;
      }
      for(var j=0; j<uitrltemp.length; j++){
        this.uitrlist[j] = uitrltemp[j].innerText;
      }
      this.loading = true;
      http
          .post("/user/signup",{
            user_id:this.uid,
            password:this.upw,
            tel:this.utel,
            email:this.uemail,
            interestList:this.itrlist,
            dislikeList:this.uitrlist,
            description:this.intro,
          })
          .then(response => {
            if (response.data['resmsg'] == "등록성공")
                alert("회원가입 성공!");
            else
                alert("회원가입 실패!");
            this.$router.push("/");
          })
          .catch(() => {
              this.errored = true;
              alert("error");
          })
          .finally(() => (this.loading = false));
    },
  },
  computed: {
    minutes: function() {
      const minutes = Math.floor(this.totalTime / 60);
      return this.padTime(minutes);
    },
    seconds: function() {
      const seconds = this.totalTime - (this.minutes * 60);
      return this.padTime(seconds);
    }
  }
}
</script>