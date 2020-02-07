<template>
  <div class="container-fluid photos">
    <div class="row justify-content-center">

      <div class="col-6 pt-4" data-aos="fade-up">
        <h2 class="text-white mb-4">Useredit</h2>


        <div class="row">
          <div class="col-12">
            <!-- <p class="mb-5">Lorem ipsum dolor sit amet, consectetur <a href="#">adipisicing</a> elit.</p> -->
            <div class="row">
              <div class="col-12">
                <form action="" method="post" @submit.prevent="edit">
                  <div style="display:none">
                    <input type="submit" onclick="return false;" />
                    <input type="text" />
                  </div>
                  <!-- image -->
                  <input ref="fileInput" type="file" accept="image/*" style="display:none;" name="file" id="file"
                    class="inputfile" @dragover.prevent @dragenter.prevent @drop.prevent="dragupload"
                    v-on:change="fileUpload" />
                  <div class="row form-group">
                    <div class="col-12">
                      <label class="text-white" for="uid">Profile</label>
                      <div class="d-flex bd-highlight">
                        <div class="col-12 bd-highlight">
                          <div v-if="!this.imginfo.base64" class="selected-image"
                            style="border:2px solid white;" @dragover.prevent @dragenter.prevent
                            @drop.prevent="dragupload" v-on:change="fileUpload"  @click="$refs.fileInput.click()">
                            <div style="height:35%"></div>
                            <div
                              style="margin:auto; width:20%; height:35%; background-size:contain; background-repeat:no-repeat; background-image:url('./theme/images/plus.png')">
                            </div>
                            <!-- <span>이미지를 drag&drop하거나 +를 클릭하여 추가해주세요.</span> -->
                          </div>

                          <div v-else @click="$refs.fileInput.click()" v-on:change="fileUpload" :class="imginfo.filter" class="selected-image" id="img-select">
                            <img :src=imginfo.base64 class="img-fluid" style="height:30vw;">
                          </div>
                          <input type="button" @click="imgdel" value="x"/>

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
                          <input type="text" readonly v-model="uid" id="uid">
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
                      <input type="tel" id="tel" v-model="utel" class="form-control" pattern="(010)-\d{3,4}-\d{4}"
                        placeholder="010-XXXX-XXXX">
                    </div>
                  </div>
                  <!-- email -->
                  <div class="row form-group">
                    <div class="col-md-12">
                      <label class="text-white" for="uemail">Email</label>
                      <div class="d-flex bd-highlight">
                        <div class="w-100 bd-highlight">
                          <input readonly type="email" id="email" v-model="uemail"
                            pattern="[a-z0-9._%+-]+@[a-z0-9.-]+\.[a-z]{2,4}$" placeholder="XXXXX@XXXX.XXX">
                        </div>
                      </div>
                    </div>
                  </div>

                  <!-- interest -->
                  <div class="row form-group">
                    <div class="col-md-12" id="parentItrl">
                      <label class="text-white" for="itrlist">Interest</label>
                      <div id="" style="display:inline-block"></div>
                      <input type="subject" autocomplete='off' @keyup.enter.prevent='appendDiv'
                        @keydown.space.prevent='appendDiv' id="interest" class="form-control">
                      <div id="itrl" v-if='itrlist !== null'>
                        <div v-for="inter in itrlist" :key="inter" class="roundedge itrlone text-white"
                          style="background: rgb(53, 219, 181);">
                          <b>{{inter}}</b>
                          <span class="icon-remove">
                          </span>
                        </div>
                      </div>
                      <div id="itrl" v-if='itrlist === null'>
                      </div>
                    </div>
                  </div>
                  <!-- uninterest -->
                  <div class="row form-group">
                    <div class="col-md-12" id="parentUitrl">
                      <label class="text-white" for="uitrlist">Uninterest</label>
                      <div id="" style="display:inline-block"></div>
                      <input type="subject" autocomplete='off' @keyup.enter.prevent='appenduDiv'
                        @keydown.space.prevent='appenduDiv' id="uninterest" class="form-control">
                      <div id="uitrl" v-if='uitrlist !== null'>
                        <div v-for="uinter in uitrlist" :key="uinter" class="roundedge uitrlone text-white"
                          style="background: rgb(53, 219, 181);">
                          <b>{{uinter}}</b>
                          <span class="icon-remove">
                          </span>
                        </div>
                      </div>
                      <div id="uitrl" v-if='uitrlist === null'>
                      </div>
                    </div>
                  </div>
                  <!-- introduce -->
                  <div class="row form-group mb-5">
                    <div class="col-md-12">
                      <label class="text-white" for="uintr">Introduce</label>
                      <textarea name="message" id="message" v-model="intro" cols="30" rows="7" class="form-control"
                        style="resize:none;" placeholder="Please introduce yourself"></textarea>
                    </div>
                  </div>
                  <!-- register/reset button -->
                  <div class="row form-group">
                    <div class="col-md-12">
                      <input type="submit" value="수정" class="col-md-3 btn btn-primary btn-md text-white">
                      <input type="button" value="삭제" @click="del"
                        class="offset-md-6 col-md-3 btn btn-danger btn-md text-white">
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
  input:read-only {
    display: block;
    width: 100%;
    height: calc(2.25rem + 2px);
    padding: 0.375rem 0.75rem;
    font-size: 1rem;
    line-height: 1.5;
    border: none;
    border-radius: 0;
    background: none;
    color: gray;
    padding-left: 0;
    padding-right: 0;
  }
  .selected-image{
    margin-bottom:0px;
    height:30vw;
    background-size: cover;
  }
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
  // import EventBus from "../event-bus"
  import $ from "jquery"
  import http from "../http-common"
  export default {
    name: "useredit",
    props: ["imgs", "oldpw", "prevpage"],
    data() {
      return {
        uid: this.$store.state.user_id,
        upw: this.oldpw,
        utel: "",
        uemail: "",
        uniqnum: "",
        uintr: "",
        itrlist: [],
        uitrlist: [],
        intro: "",
        loading: true,
        errored: false,
        timer: null,
        totalTime: (3 * 60),
        modal: false,
        emailKey: "",
        emailDupl: false,
        emailCheck: false,
        emailKeysucc: false,
        timeout: false,
        modalText: "",
        idOk: false,
        image: "",
        imginfo: {
          base64: "",
          filter: ""
        },
        regimgs: [],
        originimg: [],
      }
    },


    mounted() {
      if(this.prevpage!="confirm"){
        this.$router.push("/pwconfirm");
      }
      var tmp;
      $('html').scrollTop(0);
      http
        .get("user/info/" + this.$store.state.user_id)
        .then(response => {
          if (response.data['resmsg'] == "조회성공") {
            tmp = response.data['resvalue'];
            this.uid = tmp.user_id;
            this.utel = tmp.tel;
            this.uemail = tmp.email;
            this.itrlist = tmp.interestList;
            this.uitrlist = tmp.dislikeList;
            this.intro = tmp.description;
            this.originimg = tmp.profile_url;
            if (this.imgs != undefined) {
              this.imginfo.filter = this.imgs[this.imgs.length - 1].filter;
              this.imginfo.base64 = this.imgs[this.imgs.length - 1].base64;
              this.regimgs = this.imgs;
            } else {
              this.imginfo.base64 = tmp.profileImage.base64;
              this.imginfo.filter = tmp.profileImage.filter;
            }
          } else{
            this.$store.commit('setModalText', "회원조회 실패!");
            document.getElementById('modalBtn').click();
          }
        })
        .catch((error) => {
          this.errored = true;
          alert(error);
        })
        .finally(() => (this.loading = false));



    },
    methods: {
      appendDiv() {
        var colorCode = "#" + Math.round(Math.random() * 0xffffff).toString(16);
        var div = document.createElement('div');
        var span = document.createElement('span');
        var bold = document.createElement('bold');
        var text = document.getElementById('interest').value;
        // this.itrlist.push(text);
        bold.innerText = text;
        div.style.background = colorCode;
        div.classList.add('roundedge');
        div.classList.add('itrlone');
        div.classList.add('text-white');
        span.classList.add('icon-remove')
        span.addEventListener("click", function (event) {
          event.target.parentNode.remove();
        });
        div.appendChild(bold);
        div.appendChild(span);
        document.getElementById('itrl').appendChild(div);
        document.getElementById('interest').value = '';
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
        var colorCode = "#" + Math.round(Math.random() * 0xffffff).toString(16);
        var div = document.createElement('div');
        var span = document.createElement('span');
        var bold = document.createElement('bold');
        var text = document.getElementById('uninterest').value;
        bold.innerText = text;
        div.style.background = colorCode;
        div.classList.add('roundedge');
        div.classList.add('uitrlone');
        div.classList.add('text-white');
        span.classList.add('icon-remove');
        span.addEventListener("click", function (event) {
          event.target.parentNode.remove();
        });
        div.appendChild(bold);
        div.appendChild(span);
        document.getElementById('uitrl').appendChild(div);
        document.getElementById('uninterest').value = '';
        document.getElementById('uninterest').focus();
      },
      edit() {
        var itrltemp = document.getElementsByClassName('itrlone');
        var uitrltemp = document.getElementsByClassName('uitrlone');
        for (var i = 0; i < itrltemp.length; i++) {
          this.itrlist[i] = itrltemp[i].innerText;
        }
        for (var j = 0; j < uitrltemp.length; j++) {
          this.uitrlist[j] = uitrltemp[j].innerText;
        }
        if (this.upw === "") {
          this.$store.commit('setModalText', "비밀번호를 입력하세요");
          document.getElementById('modalBtn').click();
        } else {
          http
            .put("user/update", {
              user_id: this.uid,
              password: this.upw,
              tel: this.utel,
              email: this.uemail,
              interestList: this.itrlist,
              dislikeList: this.uitrlist,
              description: this.intro,
              profileImage: this.imginfo,
            })
            .then(response => {
              if (response.data['resmsg'] == "수정성공"){
                this.$store.commit('setModalText', "회원정보 수정 성공!");
                document.getElementById('modalBtn').click();
              }
              else{
                this.$store.commit('setModalText', "회원정보 수정 실패!");
                document.getElementById('modalBtn').click();
              }
              this.$router.push("/");
            })
            .catch((error) => {
              this.errored = true;
              alert(error);
            })
            .finally(() => (this.loading = false));

        }

      },
      imgdel(){
        this.imginfo.base64  = "",
        this.imginfo.filter = "normal",
        // this.imginfo.img ="",
        this.image="",
        this.regimgs=""
      },
      
      del() {
        http
          .delete("user/delete/" + this.uid)
          .then(response => {
            if (response.data['resmsg'] == "삭제성공"){
              this.$store.commit('setModalText', "회원정보 삭제 성공!");
              document.getElementById('modalBtn').click();
            }
            else{
              this.$store.commit('setModalText', "회원정보 삭제 실패!");
              document.getElementById('modalBtn').click();
            }
            this.$router.push("/");
          })
          .catch((error) => {
            this.errored = true;
            alert(error);
          })
          .finally(() => (this.loading = false));
      },
      fileUpload(e) {
        const files = e.target.files || e.dataTransfer.files;
        if (!files.length) return;
        this.image = files[0];
        this.createImage();
      },
      dragupload(e) {
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
              oldpw: this.upw,
              prevpage: "useredit",
            }
          })
        };
      },
    },
  }
</script>