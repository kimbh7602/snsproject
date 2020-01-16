<template>
    <div class="container-fluid photos">
      <div class="row justify-content-center">
        
        <div class="col-md-6 pt-4"  data-aos="fade-up">
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

                    <div class="row form-group">
                      <div class="col-md-12">
                        <label class="text-white" for="upw">PW</label>
                        <input type="password" v-model="upw" id="upw" class="form-control">
                      </div>
                    </div>

                    <div class="row form-group">
                      
                      <div class="col-md-12">
                        <label class="text-white" for="utel">Tel</label> 
                        <input type="tel" id="tel" v-model="utel" class="form-control" pattern="(010)-\d{3,4}-\d{4}" placeholder="010-XXXX-XXXX">
                      </div>
                    </div>

                    <div class="row form-group">
                      <div class="col-md-12">
                        <label class="text-white" for="uemail">Email</label> 
                        <div class="d-flex bd-highlight">
                          <div class="w-100 bd-highlight">
                        <input type="email" id="email" v-model="uemail" class="form-control" pattern="[a-z0-9._%+-]+@[a-z0-9.-]+\.[a-z]{2,4}$" placeholder="XXXXX@XXXX.XXX">
                          </div>
                          <div class="flex-shrink-1 bd-highlight">
                            <input type="button" value="중복체크" class="btn btn-link" v-on:click="duplicateEmail()">

                              <div style="display:none">
                                  <input type="submit" onclick="return false;" />
                                  <input type="text"/>
                              </div>

                          </div>
                        </div>
                      </div>
                    </div>

                    <div class="row form-group">
                      
                      <div class="col-md-12" id="parentItrl">
                        <label class="text-white" for="itrlist">Interest</label>
                        <div id="" style="display:inline-block"></div>
                        <input type="subject" autocomplete='off' @keyup.enter.prevent='appendDiv' @keydown.space.prevent='appendDiv' id="interest" class="form-control">
                        <div id="itrl"></div>
                      </div>
                    </div>

                    <div class="row form-group">
                      
                      <div class="col-md-12" id="parentUitrl">
                        <label class="text-white" for="uitrlist">Uninterest</label>
                        <div id="" style="display:inline-block"></div>
                        <input type="subject" autocomplete='off' @keyup.enter.prevent='appenduDiv' @keydown.space.prevent='appenduDiv' id="uninterest" class="form-control">
                        <div id="uitrl"></div>
                      </div>
                    </div>

                    <div class="row form-group mb-5">
                      <div class="col-md-12">
                        <label class="text-white" for="uintr">Introduce</label> 
                        <textarea name="message" id="message" v-model="intro" cols="30" rows="7" class="form-control" style="resize:none;" placeholder="Please introduce yourself"></textarea>
                      </div>
                    </div>

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
            uintr:"",
            itrlist:[],
            uitrlist:[],
            intro:"",
            loading:true,
            errored:false,
        }
    },
    methods:{
        duplicateId() {
          if (this.uid === "") {
            alert('아이디를 입력해주십시오.')
          } else {
            http
              .get('content/urls/1', {
                user_id: this.uid,
                email: this.uemail,
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
          if (this.uemail === "") {
            alert('이메일을 입력해주십시오.')
          } else if (!this.uemail.includes("@")) {
            alert('이메일 형식으로 입력해주십시오.')
          } else if (this.uemail.length < 8) {
            alert('제대로 된 이메일 좀')
          } else {
            http
              .get("content/urls/' + 1 ", {
                user_id: this.uid,
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
}
</script>