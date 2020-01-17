<template>
    <div class="container-fluid photos">
      <div class="row justify-content-center">
        
        <div class="col-md-6 pt-4" data-aos="fade-up">
          <h2 class="text-white mb-4">Register</h2>
          

          <div class="row">
            <div class="col-md-12">
              <p class="mb-5">Lorem ipsum dolor sit amet, consectetur <a href="#">adipisicing</a> elit.</p>              
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
                            <input type="button" value="중복체크" class="btn btn-link" v-on:click="duplicateId()">
                          </div>
                        </div>
                      </div>
                    </div>
                    <!-- password -->
                    <div class="row form-group">
                      <div class="col-md-12">
                        <label class="text-white" for="upw">PW</label>
                        <input type="password" v-model="upw" id="upw" class="form-control">
                      </div>
                    </div>
                    <!-- phonenumber -->
                    <div class="row form-group">
                      <div class="col-md-12">
                        <label class="text-white" for="utel">Tel</label> 
                        <input type="tel" id="tel" v-model="utel" class="form-control" pattern="(010)-\d{3,4}-\d{4}" placeholder="010-XXXX-XXXX">
                      </div>
                    </div>
                    <!-- email -->
                    <div class="row form-group">
                      <div class="col-md-12">
                        <label class="text-white" for="uemail">Email</label> 
                        <div class="d-flex bd-highlight">
                          <div class="w-100 bd-highlight">
                            <input type="email" id="email" v-model="uemail" class="form-control" pattern="[a-z0-9._%+-]+@[a-z0-9.-]+\.[a-z]{2,4}$" placeholder="XXXXX@XXXX.XXX">
                          </div>
                          <div class="flex-shrink-1 bd-highlight">
                            <input type="button" value="중복체크" class="btn btn-link" v-on:click="duplicateEmail()">
                          </div>
                        </div>
                      </div>
                    </div>
                    <!-- 중복이 아닌 이메일인 경우 이메일 인증할 수 있게 화면에 띄우기 -->
                    <div v-show="emailKeysucc">
                      이메일 인증 완료
                    </div>
                    <div v-show="emailCheck">
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
                              <input type="button" value="인증번호 받기" class="btn btn-link" v-on:click="uniqueNum(), startTimer()">
                            </div>
                            <div class="flex-shrink-1 bd-highlight">
                              <input type="button" value="인증번호 확인" class="btn btn-link" v-on:click="uniqueNumCheck(), resetTimer()">
                            </div>
                          </div>
                        </div>
                      </div>
                    </div>

                    <!-- interest -->
                    <div class="row form-group">
                      <div class="col-md-12" id="parentItrl">
                        <label class="text-white" for="itrlist">Interest</label>
                        <div id="" style="display:inline-block"></div>
                        <input type="subject" autocomplete='off' @keyup.enter.prevent='appendDiv' @keydown.space.prevent='appendDiv' id="interest" class="form-control">
                        <div id="itrl"></div>
                      </div>
                    </div>
                    <!-- uninterest -->
                    <div class="row form-group">
                      <div class="col-md-12" id="parentUitrl">
                        <label class="text-white" for="uitrlist">Uninterest</label>
                        <div id="" style="display:inline-block"></div>
                        <input type="subject" autocomplete='off' @keyup.enter.prevent='appenduDiv' @keydown.space.prevent='appenduDiv' id="uninterest" class="form-control">
                        <div id="uitrl"></div>
                      </div>
                    </div>
                    <!-- introduce -->
                    <div class="row form-group mb-5">
                      <div class="col-md-12">
                        <label class="text-white" for="uintr">Introduce</label> 
                        <textarea name="message" id="message" v-model="intro" cols="30" rows="7" class="form-control" style="resize:none;" placeholder="Please introduce yourself"></textarea>
                      </div>
                    </div>
                    <!-- register/reset button -->
                    <div class="row form-group">
                      <div class="col-md-12">
                        <input type="submit" value="Register" class="btn btn-primary btn-md text-white">
                        <input type="reset" value="Reset" @click="deleteDiv" class="btn btn-danger btn-md text-white">
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
    .roundedge{
        border-radius: 5px;
        display: inline-block;
        margin-top: 5px;
        margin-right:3px;
        border-color:white;
        border-style:solid;
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
            utel:"",
            uemail:"",
            uniqnum: "",
            uintr:"",
            itrlist:[],
            uitrlist:[],
            intro:"",
            loading:true,
            errored:false,
            timer: null,
            totalTime: (3 * 60),
            modal : false,
            emailKey:"",
            emailDupl:false,
            emailCheck:false,
            emailKeysucc:false,
        }
    },
    methods:{
      // modal(){
      //   this.modal = !this.modal;
      // },
        startTimer: function() {
          this.timer = setInterval(() => this.countdown(), 1000);
          this.resetButton = true;
          this.title = "Greatness is within sight!!"
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
                  if (response.data['resmsg'] == "true") { // id가 중복일 때,
                      alert("이미 사용 중인 아이디입니다.");
                  } else { // 중복되지 않을 때
                      alert("사용 가능한 아이디입니다");
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
          var regExp = /^[a-z0-9._%+-]+@[a-z0-9.-]+\.[a-z]{2,4}$/i;
          // var regExp = /^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}$/i;
          if (this.uemail === "") {
            alert('이메일을 입력해주십시오.')
          }
          if (!regExp.test(this.uemail)) {
              alert('이메일 형식으로 입력해주십시오.')
          } else {
            http
              .get("/user/emailCheck/"+this.uemail)
                .then((response)=>{
                  this.emailDupl = response.data['resValue'];
                  if (this.emailDupl) { // 중복일 때,
                    this.emailCheck = true;
                    this.startTimer();
                    alert("사용 가능한 이메일입니다.");
                  } else {
                    alert("이미 사용 중인 이메일입니다.");
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
              .post('/email/checkEmail/'+this.uemail)
                .then((response)=>{
                  this.emailKey = response.data['resValue'];
                  alert("인증 이메일이 발송되었습니다.");
                })
                .catch(() => {
                  this.errored = true;
                })
                .finally(() => (this.loading = false));
        },
        uniqueNumCheck() {
          if(this.emailKey == this.uniqnum){
            alert("인증완료");
            this.emailKeysucc = true;
          }
          else{
            alert("인증실패");
          }
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