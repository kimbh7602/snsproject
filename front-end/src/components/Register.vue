<template>
    <div class="container-fluid photos">
      <div class="row justify-content-center">
        
        <div class="col-6 pt-4" data-aos="fade-up">
          <h2 class="text-white mb-4">Register</h2>
          

          <div class="row">
            <div class="col-12">
              <div class="row">
                <div class="col-12">                
                  <form action="" method="post" @submit.prevent="regist">
                    <div style="display:none">
                        <input type="submit" onclick="return false;" />
                        <input type="text"/>
                    </div>
                    <!-- image -->
                    <input ref="fileInput" type="file" accept="image/*" style="display:none;" name="file" id="file" class="inputfile" @dragover.prevent @dragenter.prevent @drop.prevent="dragupload" v-on:change="fileUpload"/>
                    <div class="row form-group">
                      <div class="col-12">
                        <label class="text-white" for="uid">Profile</label>
                        <div class="d-flex bd-highlight">
                          <div class="col-12 bd-highlight">
                            <div v-if="!this.imgs" class="col-12 selected-image" style="margin-bottom:0px; border:2px solid white;" @dragover.prevent @dragenter.prevent @drop.prevent="dragupload" v-on:change="fileUpload"  @click="$refs.fileInput.click()" >
                              <div style="height:35%"></div>
                              <div style="margin:auto; width:20%; height:35%; background-size:contain; background-repeat:no-repeat; background-image:url('./theme/images/plus.png')">
                              </div>
                              <!-- <span>이미지를 drag&drop하거나 +를 클릭하여 추가해주세요.</span> -->
                            </div>

                            <div v-else @click="$refs.fileInput.click()" :class="imginfo.filter" id="img-select">
                              <img :src = imginfo.base64 class="img-fluid" style="height:30vw;">
                            </div>
                          </div>
                        </div>
                      </div>
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
                              <input type="text" v-model="uniqnum" class="form-control">
                            </div>
                            <div class="flex-shrink-1 bd-highlight">
                              <input type="button" value="인증번호 받기" class="btn btn-link" v-on:click="uniqueNum()">
                            </div>
                            <div class="flex-shrink-1 bd-highlight">
                              <input type="button" value="인증번호 확인" class="btn btn-link" v-on:click="uniqueNumCheck()">
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
                        <input type="submit" value="가입" class="col-md-3 btn btn-primary btn-md text-white">
                        <input type="reset" value="취소" @click="deleteDiv" class="offset-md-6 col-md-3 btn btn-danger btn-md text-white">
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
    .selected-image{
      height:30vw;
      background-size: cover;
    }
</style>

<script>
import http from "../http-common"
import $ from "jquery"

export default {
    name: "register",
    props: ["imgs"],
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
            timeout:false,
            modalText:"",
            idOk:false,
            image: "",
            imginfo:{
              base64:"",
              filter:""
            },
            regimgs:[],
        }
    },
    methods:{
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
            this.timeout = true;
            this.resetTimer()
          }
        },
        duplicateId() {
          // var replaceChar = /[~!@#$%^&*;/()_+|<>?:{}]/gi;
          // 완성형 아닌 한글 정규식
          // var replaceNotFullKorean = /[ㄱ-ㅎㅏ-ㅣ]/gi;
          var checkid = /^[A-Za-z0-9+]{4,12}$/;
          if (this.uid === "") {
            this.modalText = "아이디를 입력해주세요.";
            this.$store.commit('setModalText', this.modalText);
            document.getElementById('modalBtn').click();
          } else if(!checkid.test(this.uid)){
            this.modalText = "4~12자리의 영문 및 숫자로 입력해주세요.";
            this.$store.commit('setModalText', this.modalText);
            document.getElementById('modalBtn').click();
          }else {
            http
              .get('user/info/'+this.uid, {
                user_id: this.uid,
              })
                .then((response)=>{
                  if (response.data['resmsg'] == "조회성공") { // id가 중복일 때,
                    this.modalText = "이미 사용 중인 아이디입니다.";
                    this.$store.commit('setModalText', this.modalText);
                    document.getElementById('modalBtn').click();
                  } else { // 중복되지 않을 때
                    this.modalText = "사용 가능한 아이디입니다.";
                    this.$store.commit('setModalText', this.modalText);
                    document.getElementById('modalBtn').click();
                    this.idOk = true;
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
            this.modalText = "이메일을 입력해주세요.";
            this.$store.commit('setModalText', this.modalText);
            document.getElementById('modalBtn').click();            
          }
          if (!regExp.test(this.uemail)) {
            this.modalText = "이메일 형식으로 입력해주세요.";
            this.$store.commit('setModalText', this.modalText);
            document.getElementById('modalBtn').click();
          } else {
            // this.emailCheck = true;
            http
              .get("/user/emailCheck/"+this.uemail)
                .then((response)=>{
                  this.emailDupl = response.data['resValue'];
                  if (this.emailDupl) {
                    this.emailCheck = true;
                    this.modalText = "사용 가능한 이메일입니다.";
                    this.$store.commit('setModalText', this.modalText);
                    document.getElementById('modalBtn').click();
                  } else {
                    this.modalText = "이미 사용 중인 이메일입니다.";
                    this.$store.commit('setModalText', this.modalText);
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
        uniqueNum() {
          http
              .post('/email/checkEmail/'+this.uemail)
                .then((response)=>{
                  this.emailKey = response.data['resValue'];
                  this.modalText = "인증 이메일이 발송되었습니다.";
                  this.$store.commit('setModalText', this.modalText);
                  if(this.minutes=='03' && this.seconds=='00'){
                    this.startTimer();
                  }
                  document.getElementById('modalBtn').click();
                })
                .catch(() => {
                  this.errored = true;
                })
                .finally(() => (this.loading = false));
        },
        uniqueNumCheck() {
          if(this.emailKey!=""&& this.uniqnum!="" && this.emailKey == this.uniqnum && !this.timeout){
            this.modalText = "이메일 인증 완료";
            this.$store.commit('setModalText', this.modalText);
            document.getElementById('modalBtn').click();
            this.resetTimer()
            this.emailKeysucc = true;
          }
          else{
            this.modalText = "이메일 인증 실패";
            this.$store.commit('setModalText', this.modalText);
            document.getElementById('modalBtn').click();
          }
        },
        appendDiv() {
            var colorCode  = "#" + Math.round(Math.random() * 0xffffff).toString(16);
            var div = document.createElement('div');
            var span = document.createElement('span');
            var bold = document.createElement('bold')
            var text = document.getElementById('interest').value;
            if(text==""){
              return;
            }
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
            if(text==""){
              return;
            }
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

        fileUpload(e) {
          const files = e.target.files || e.dataTransfer.files;
          if (!files.length) return;
          this.image = files[0];
          this.createImage();
        },
        dragupload(e){
          const files = e.target.files || e.dataTransfer.files;
          if (!files.length) return;
          this.image = files[0];
          this.createImage();
        },
        createImage() {
          const reader = new FileReader();
          reader.readAsDataURL(this.image);
          reader.onload = e => {
            this.image = e.target.result;
            this.imginfo.base64 = this.image;
            this.regimgs.push(this.imginfo);
            // EventBus.$emit("imglink", { image: this.image });
            this.$router.push({
              name: 'editing', 
              params: {
                imgs: this.regimgs,
                prevpage: "register",
              }
            })
          };
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
          if(!this.idOk){
            this.modalText = "아이디 중복체크를 해주세요.";
            this.$store.commit('setModalText', this.modalText);
            document.getElementById('modalBtn').click();
          }else if(!this.emailDupl){
            this.modalText = "이메일 중복체크를 해주세요.";
            this.$store.commit('setModalText', this.modalText);
            document.getElementById('modalBtn').click();
          }else if(!this.emailKeysucc){
            this.modalText = "이메일 인증을 해주세요.";
            this.$store.commit('setModalText', this.modalText);
            document.getElementById('modalBtn').click();
          }
          else{
            http
                .post("/user/signup",{
                  user_id:this.uid,
                  password:this.upw,
                  tel:this.utel,
                  email:this.uemail,
                  interestList:this.itrlist,
                  dislikeList:this.uitrlist,
                  description:this.intro,
                  profileImage: this.imginfo,
                })
                .then(response => {
                  if (response.data['resmsg'] == "등록성공"){
                    this.modalText = "회원가입 성공";
                    this.$store.commit('setModalText', this.modalText);
                    // document.body.removeAttribute('class');
                    document.getElementById('modalBtn').click();
                    this.$router.push("/login");
                  }
                  else{
                    this.modalText = "회원가입 실패";
                    this.$store.commit('setModalText', this.modalText);
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
    },
    mounted(){
      $('html').scrollTop(0);
      if(this.imgs!=undefined){
        this.imginfo.filter = this.imgs[this.imgs.length-1].filter;
        this.imginfo.base64 = this.imgs[this.imgs.length-1].base64;
        this.regimgs = this.imgs;
      }
    }
}
</script>