<template>
    <div class="container-fluid photos">
      <div class="row align-items-stretch">
        <div class="col-6 col-md-6 col-lg-4" data-aos="fade-up" style="padding: 10px 10px" v-for="item in Items" :key="item.id">
          <div class="d-block photo-item">
            <div class="polaroid" v-if="item.dislike < 5 && !reportMyList.includes(item.content_id) || readReportList.includes(item.content_id)">
              <div v-on:click="goDetail(item.content_id)" :class="item.imageList[0].filter" class="" style="width:100%; height:100%">
                <img :src="item.imageList[0].image_url" style="box-shadow: 3px 3px 3px;" alt="Image"/>
              </div>
            </div>
            <div class="polaroid" v-show="item.dislike > 4 && !readReportList.includes(item.content_id) && !reportMyList.includes(item.content_id)">
              <div style="width:100%; height:100%" :class="item.imageList[0].filter">
                <img :src="item.imageList[0].image_url" style="box-shadow: 3px 3px 3px;" alt="Image" class="img-fluid pa blur m-0"/>
                <div class="centertext">
                  <p class="text-white">신고가 누적된 게시물입니다.</p>
                  <button class="btn btn-outline-light btn-sm py-0" @click="readReCon(item.content_id)">보기</button>
                </div>
              </div>
            </div>
            <div class="polaroid" v-show="reportMyList.includes(item.content_id) && !readReportList.includes(item.content_id)">
              <div style="width:100%; height:100%" :class="item.imageList[0].filter">
                <img :src="item.imageList[0].image_url" alt="Image" class="img-fluid pa blur m-0"/>
                <div class="centertext">
                  <p class="text-white">내가 신고한 게시물입니다.</p>
                  <button class="btn btn-outline-light btn-sm py-0" @click="readReCon(item.content_id)">보기</button>
                </div>
              </div>
            </div>
              <div class="photo-text-more" v-if="item.dislike < 5 && !reportMyList.includes(item.content_id) || readReportList.includes(item.content_id)">
                <div class="">
                  <div class="d-block photo-item">
                    <div class="postcard">
                      <div class="content">
                        <!-- 우표 -->
                        <div v-on:click="goDetail(item.content_id)">
                          <div class="stamp-cover" style="background:black; height:52px; width:52px;">
                            <div class="stamp" style=" margin:1px; float:right; background-color:white; height:50px; width:50px;">
                            </div>
                          </div>
                          <div class="stamp-img" style="top:25px;right:25px;height:43px;width:43px;background-color:white;"></div>
                          <div style="width:37px;height:37px" class="stamp-img" :class="item.profile_filter">
                            <img :src="item.profile_url" style="width:37px;height:37px; background: none;" />
                          </div>
                          <img src="../../public/theme/images/stamp1.png" style="width:45px;height:45px;" alt="Postage mark" class="postmark">
                          <!-- 끝 -->
                          <div class="mail-title offset-1 col-9 mt-2 ml-3" style="text-align:left;"><p class="mail-title-val">Dear {{uid}} {{item.content_id}}</p></div>
                          <div class="mail-message offset-2 col-8 ellipsis pt-0 pb-0 mail-message-val">{{item.content_val}}</div>
                          <div class="col-11 col-offset-1 pt-0 pr-0 mail-from-val">from {{item.user_id}}</div>
                        </div>
                        <div class="mb-4 pb-2 d-flex justify-content-around">
                          <div @click="clickHeart(item.content_id)">
                            <i class="icon-heart" v-if="item.user_like"></i>
                            <i class="icon-heart-o" v-else></i>
                          </div>
                          <div @click="clickFollow(item.user_id)" v-if="item.user_id !== uid">
                            <i class="icon-check" v-if="followList.includes(item.user_id)"></i>
                            <i class="icon-user-plus" v-else></i>
                          </div>
                          <div @click="clickScrap(item.content_id)" v-if="item.user_id !== uid">
                            <i class="icon-bookmark" v-if="scrapList.includes(item.content_id)"></i>
                            <i class="icon-bookmark-o" v-else></i>
                          </div>
                          <div v-if="item.user_id !== uid && !myPage && readReportList.includes(item.content_id)">
                            <!-- <i class="icon-bell-o" v-if="!reportMyList.includes(item.content_id)" v-show="myPage" data-toggle="modal" data-target="#exampleModal" @click="sendInfo(item.content_id, item.timestamp)">신고</i> -->
                            <i class="icon-bell" @click="cancel(item.content_id)">취소</i>
                          </div>
                          <div v-if="item.user_id !== uid && myPage">
                            <i class="icon-bell-o" v-if="!reportMyList.includes(item.content_id)" data-toggle="modal" data-target="#exampleModal" @click="sendInfo(item.content_id, item.timestamp)">신고</i>
                            <i class="icon-bell" v-else @click="cancel(item.content_id)">취소</i>
                          </div>
                          <!-- <div v-if="item.user_id !== uid">
                            <i class="icon-bell-o" v-if="!reportMyList.includes(item.content_id)" v-show="myPage" data-toggle="modal" data-target="#exampleModal" @click="sendInfo(item.content_id, item.timestamp)">신고</i>
                            <i class="icon-bell" v-if="reportMyList.includes(item.content_id)" @click="cancel(item.content_id)">취소</i>
                          </div> -->
                        </div>
                      </div>
                    </div>
                  </div>
                </div>
              </div>
          </div>
        </div>

        <!-- Modal -->
        <div class="modal fade mt-5" id="exampleModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true" style="z-index: 99999;">
          <div class="modal-dialog" role="document">
            <div class="modal-content">
              <div class="modal-header">
                <h5 class="modal-title" id="exampleModalLabel">신고하기</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                  <span aria-hidden="true">&times;</span>
                </button>
              </div>
              <div class="modal-body">
                <div class="btn-group dropright">
                  <button class="btn btn-sm dropdown-toggle" style="font-size:13px;" type="button" id="dropdownMenu2" data-toggle="dropdown" data-display="static" aria-haspopup="true" aria-expanded="false">
                    신고 사항 선택
                  </button>
                  <div class="dropdown-menu" aria-labelledby="dropdownMenu2">
                    <button class="dropdown-item" type="button" @click="sendReport(options[0].op1), reportOption(1)">{{ options[0].op1 }}</button>
                    <button class="dropdown-item" type="button" @click="sendReport(options[0].op2), reportOption(2)">{{ options[0].op2 }}</button>
                    <button class="dropdown-item" type="button" @click="sendReport(options[0].op3), reportOption(3)">{{ options[0].op3 }}</button>
                    <button class="dropdown-item" type="button" @click="sendReport(options[0].op4), reportOption(4)">{{ options[0].op4 }}</button>
                    <button class="dropdown-item" type="button" @click="sendReport(options[0].op5), reportOption(5)">{{ options[0].op5 }}</button>
                    <button class="dropdown-item" type="button" @click="sendReport(options[0].op6), reportOption(6)">{{ options[0].op6 }}</button>
                  </div>
                </div>
                <p class="mt-3 ml-2">신고 사항 : {{ whatoption }}</p>
                <div class="card card-body" v-show="optionButton">
                  <input type="text" v-model="options[0].op7" placeholder="신고 내용">
                </div>
              </div>
              <div class="modal-footer d-flex justify-content-end">
                <div class="d-block">
                  <button type="button" class="btn btn-danger mr-2" data-dismiss="modal" @click="clickBell()">신고하기</button>
                  <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                </div>
              </div>
            </div>
          </div>
        </div>
        
      </div>
      <div class="text-white text-center" v-if="this.contentErrorMsg">
        <h5>{{this.contentErrorMsg}}</h5>
      </div>
    </div>
</template>

<script>
import $ from "jquery"
import http from '../http-common';
import store from '../store'
export default {
  props:["userId", "myPage"],
  data() {
    return {
      errored: false,
      uid: "",
      contentIds: [],
      contentErrorMsg: "",
      Items:[],
      followList: [],
      readReportList: [],
      scrapList: [],
      reportMyList: [],
      options: [{
        op1: "선정적인 게시물",
        op2: "폭력적인 게시물",
        op3: "혐오적인 게시물",
        op4: "허위 게시물",
        op5: "스팸 게시물",
        op6: "기타",
        op7: "",
      }],
      info: [],
      optionButton: false,
      whatoption: "",
    }
  },
  watch: {
    myPage: {
      deep: true,
      handler() {
        this.fetchData();
      }
    }
  },
  methods: {
    reportOption(id) {
      if (id === 6) {
        this.optionButton = true
      } else {
        this.optionButton = false
      }
    },
    sendInfo(cid, time) {
      this.info = []
      this.info.push({
        content_id: cid,
        user_id: this.uid,
        report_category: "",
        report_val: "",
        timestamp: time,
      })
    },
    sendReport(report_category) {
      this.whatoption = report_category
      this.info[0].report_category = report_category
      this.info[0].report_val = this.options[0].op7
    },
    readReCon(cid) {
      this.readReportList.push(cid)
    },
    getReport() {
      this.readReportList = []
      this.reportMyList = []
      http
        .get('/userReport/myReportList/' + this.uid)
        .then((res) => {
          if (res.data.resvalue.length > 0) {
            // console.log(res.data.resvalue)
            for (var i = 0; i < res.data.resvalue.length; i++) {
              this.reportMyList.push(res.data.resvalue[i].content_id)
            }
          }
        })
        .catch(() => {
          this.errored = true
        })
    },
    clickBell() {
      if (this.info[0].report_category == "") {
        this.$store.commit('setModalText', "신고 사항을 선택해주십시오.");
        document.getElementById('modalBtn').click();
      } else if (this.info[0].report_category == "기타" && this.options[0].op7 == "") {
        this.$store.commit('setModalText', "기타를 선택하신 이유를 적어주십시오.");
        document.getElementById('modalBtn').click();
      } else {
        http
          .post('/userReport/report', {
            content_id: this.info[0].content_id,
            report_category: this.info[0].report_category,
            report_val: this.options[0].op7,
            timestamp: this.info[0].timestamp,
            user_id: this.uid,
          })
          .then((res) => {
            this.reportMyList = []
            this.$socket.emit('notification', {
              user_id: res.data.resValue.user_id,
              target_user_id: res.data.resValue.target_user_id,
              category: res.data.resValue.category,
              flag: true,
            });
            if (res.data.resmsg == "신고 성공") {
              this.$store.commit('setModalText', "신고가 접수되었습니다.");
              document.getElementById('modalBtn').click();
            } else {
              this.$store.commit('setModalText', "신고가 접수되지 않았습니다.");
              document.getElementById('modalBtn').click();            
            }
            this.options[0].op7 = ""
            this.info = []
            this.whatoption = ""
            this.getReport()
          })
          .catch(()=>{
            this.errored = true;
          })
      }
    },    
    cancel(cid) {
      if (this.reportMyList.includes(cid)) {
        http
          .delete('/userReport/deleteReport', {
            data: {
              content_id: cid,
              report_category: null,
              report_val: null,
              timestamp: null,
              user_id: this.uid,
            }
          })
          .then((res) => {
            this.$socket.emit('notification', {
              user_id: res.data.resValue.user_id,
              target_user_id: res.data.resValue.target_user_id,
              category: res.data.resValue.category,
              flag: false,
            });
            if (res.data.resmsg == "신고취소성공") {
              const idx = this.reportMyList.findIndex(function(item) {
                return item === res.data.resValue.target_event_id
              })
              this.reportMyList.splice(idx, 1)
              this.$store.commit('setModalText', "신고가 취소되었습니다.");
              document.getElementById('modalBtn').click();            
            } else {
              this.$store.commit('setModalText', "신고가 취소되지 않았습니다.");
              document.getElementById('modalBtn').click();            
            }
          })
          .catch(()=>{
            this.errored = true;
          })
      }
    },
    getLike() {
      this.uid = store.state.user_id
      http
        .get('/userLike/userLikeList/' + this.uid)
        .then((res) => {
          for (var i = 0; i < res.data.resvalue.length; i++) {
            this.contentIds.push({
              con_id: res.data.resvalue[i].content_id
            })
          }
        })
        .catch(()=>{
          this.errored = true;
        })
    },
    getData() {
      http
      .get('content/contentUserList/'+this.uid)
      .then((res)=>{
        console.log(res)
        if (res.data.resValue.length > 0) {
          this.contentErrorMsg = ""
          if (res.data.resmsg == "개인 게시물 리스트 출력 성공") {
            for (var i = 0; i < res.data.resValue.length; i++) {
              for (var j = 0; j < this.contentIds.length; j++) {
                if (res.data.resValue[i].content_id == this.contentIds[j].con_id) {
                  res.data.resValue[i].user_like = true
                }
              }
            }
            this.Items = res.data.resValue;
          }
        } else {
          this.contentErrorMsg = "게시물이 없습니다."
        }
      })
      .catch(()=>{
        this.errored = true;
      })
    },
    getMypage() {
      http
      .get('content/contentUserList/'+this.userId)
      .then((res)=>{
        if (res.data.resValue.length > 0) {
          this.contentErrorMsg = ""
          if (res.data.resmsg == "개인 게시물 리스트 출력 성공") {
            for (var i = 0; i < res.data.resValue.length; i++) {
              for (var j = 0; j < this.contentIds.length; j++) {
                if (res.data.resValue[i].content_id == this.contentIds[j].con_id) {
                  res.data.resValue[i].user_like = true
                }
              }
            }
            this.Items = res.data.resValue;
          }
        } else {
          this.contentErrorMsg = "게시물이 없습니다."
        }
      })
      .catch(()=>{
        this.errored = true;
      })
    },
    getUserScrap() {
      this.scrapList = []
      http
      .get('scrap/scrapList/' + this.uid)
      .then((res)=>{
        if (res.data.resvalue.length > 0) {
          this.contentErrorMsg = ""
          if (res.data.resmsg == "스크랩목록성공") {
            for (var i = 0; i < res.data.resvalue.length; i++) {
              for (var j = 0; j < this.contentIds.length; j++) {
                if (res.data.resvalue[i].content_id == this.contentIds[j].con_id) {
                  res.data.resvalue[i].user_like = true
                }
              }
              this.scrapList.push(res.data.resvalue[i].content_id)
            }
          }
        } else {
          this.contentErrorMsg = "게시물이 없습니다."
        }
      })
      .catch(()=>{
        this.errored = true;
      })
    },
    getScrap() {
      http
      .get('scrap/scrapList/'+this.uid)
      .then((res)=>{
        if (res.data.resvalue.length > 0) {
          this.contentErrorMsg = ""
          if (res.data.resmsg == "스크랩목록성공") {
            for (var i = 0; i < res.data.resvalue.length; i++) {
              for (var j = 0; j < this.contentIds.length; j++) {
                if (res.data.resvalue[i].content_id == this.contentIds[j].con_id) {
                  res.data.resvalue[i].user_like = true
                }
              }
            }
            this.Items = res.data.resvalue;
          }
        } else {
          this.contentErrorMsg = "게시물이 없습니다."
        }
      })
      .catch(()=>{
        this.errored = true;
      })
    },
    getFollow() {
      this.followList = []
      http
        .get('/follow/followList/' + this.uid)
        .then((res) => {
          for (var i = 0; i < res.data.resvalue.length; i++) {
            this.followList.push(res.data.resvalue[i].user_id)
          }
        })
        .catch(()=>{
          this.errored = true;
        })
    },
    goDetail: function(con_id) {
      this.$router.push({
        name: 'bio',
        params: {
          cid: con_id
        }
      })
    },
    clickHeart(num) {
      var idx = 0
      for (idx = 0; idx < this.Items.length; idx++) {
        if (this.Items[idx].content_id == num) {
          if (this.Items[idx].user_like == false) {
            this.Items[idx].user_like = true
            http
              .post('/userLike/like', {
                content_id: this.Items[idx].content_id,
                timestamp: this.Items[idx].timestamp,
                user_id: this.uid
              })
              .then((response) =>{
                this.$socket.emit('notification', {
                  user_id: response.data.resValue.user_id,
                  target_user_id: response.data.resValue.target_user_id,
                  category: response.data.resValue.category,
                  flag: true,
                });
              })
              .catch(()=>{
                this.errored = true;
              })
          } else {
            this.Items[idx].user_like = false       
            http
              .delete('/userLike/dislike', {
                data: {
                  content_id: this.Items[idx].content_id,
                  timestamp: this.Items[idx].timestamp,
                  user_id: this.uid
                }
              })
              .then((response) =>{
                this.$socket.emit('notification', {
                  user_id: response.data.resValue.user_id,
                  target_user_id: response.data.resValue.target_user_id,
                  category: response.data.resValue.category,
                  flag: false,
                });
              })
              .catch(()=>{
                this.errored = true;
              })
          }
        }
      }
    },
    clickFollow(user) {
      if (this.followList.includes(user) == true) {
        http
          .delete('/follow/deleteFollow', {
            data: {
              follow_id: user,
              follower_id: this.uid
            }
          })
          .then(response => {
            this.$socket.emit('notification', {
              user_id: response.data.resValue.user_id,
              target_user_id: response.data.resValue.target_user_id,
              category: response.data.resValue.category,
              flag: false
            });
          })
          .catch(()=>{
            this.errored = true;
          })
        const idx = this.followList.indexOf(user)
        if (idx > -1) {
          this.followList.splice(idx, 1)
        }
      } else {
        http
          .post('/follow/insertFollow', {
            follow_id: user,
            follower_id: this.uid
          })
          .then(response => {
            this.$socket.emit('notification', {
              user_id: response.data.resValue.user_id,
              target_user_id: response.data.resValue.target_user_id,
              category: response.data.resValue.category,
              flag: true
            });
          })
          .catch(()=>{
            this.errored = true;
          })
        const idx = this.followList.indexOf(user)
        if (idx == -1) {
          this.followList.push(user)
        }
      }
    },
    clickScrap(cid) {
      if (this.scrapList.includes(cid)) {
        http
          .delete('/scrap/deleteScrap', {
            data: {
              content_id: cid,
              user_id : this.uid,
            }
          })
          .then((res) => {
            if (res.data.resmsg === "스크랩취소성공") {
              this.$socket.emit('notification', {
                user_id: res.data.resValue.user_id,
                target_user_id: res.data.resValue.target_user_id,
                category: res.data.resValue.category,
                flag:false
              });
              const idx = this.scrapList.findIndex(function(item) {
                return item === cid
              })
              this.scrapList.splice(idx, 1)
              if (this.myPage === false) {
                const idx2 = this.Items.findIndex(function(item2) {
                  return item2.content_id === cid
                })
                this.Items.splice(idx2, 1)
              }
            } else {
              console.log(res.data.resmsg)
            }
          })
          .catch(()=>{
            this.errored = true;
          })
      } else {
        http
          .post('/scrap/insertScrap', {
            content_id: cid,
            user_id : this.uid,
          })
          .then((res) => {
            if (res.data.resmsg === "스크랩성공") {
              this.$socket.emit('notification', {
                user_id: res.data.resValue.user_id,
                target_user_id: res.data.resValue.target_user_id,
                category: res.data.resValue.category,
                flag:true
              });
              this.scrapList.push(cid)
            } else {
              console.log(res.data.resmsg)
            }
          })
          .catch(()=>{
            this.errored = true;
          })
      }
    },
    fetchData() {
      if(this.myPage == undefined){
        this.getData()
      }else if(this.myPage == true){
        this.getMypage();
        this.getFollow();
        this.getUserScrap();
        this.getReport();
      }else if(this.myPage == false){
        this.getFollow();
        this.getScrap();
        this.getUserScrap();
        this.getReport();
      }
    }
  },
  created() {
    this.getLike();
    this.fetchData();
  },
  mounted() {
    $('html').scrollTop(0);
  },
  updated(){
    document.querySelector('script[src$="script.js"]').remove()
    document.querySelector('script[src$="swiper.js"]').remove()
    let recaptchaScripta = document.createElement('script')
    recaptchaScripta.setAttribute('type',"text/javascript")
    recaptchaScripta.setAttribute('src', "./theme/js/script.js")
    document.body.appendChild(recaptchaScripta)
    let recaptchaScriptb = document.createElement('script')
    recaptchaScriptb.setAttribute('type',"text/javascript")
    recaptchaScriptb.setAttribute('src', "./theme/js/swiper.js")
    document.body.appendChild(recaptchaScriptb)
  }
}
</script>

<style>
  #textarea {
    width: 100%;
  }
  #bg {
    background-color: rgba(255, 255, 255, 0.2);
  }
  #text-color {
    color: white;
  }
  .ellipsis {
    overflow: hidden;
    text-overflow: ellipsis;
    display: -webkit-box;
    -webkit-line-clamp: 3;
    -webkit-box-orient: vertical;
    word-wrap: break-word;
    line-height: 2;
    height: 6rem;
  }
  .pa {
    position: relative;
  }
  .centertext {
    z-index: 10;
    position: absolute;
    text-align: center;
    padding: 5px;
    display: block;
    top: 37%;
    left: 50%;
    background-color: #333;
    font-size: 13px;
    transform: translateY(-50%);
    transform: translateX(-50%);
  }
  .blur {
    -webkit-filter: blur(5px);
    -moz-filter: blur(5px);
    -o-filter: blur(5px);
    -ms-filter: blur(5px);
    filter: blur(5px);
    /* opacity: 0.7; */
    transform: scale(0.99);
    overflow: hidden;
    display: block;
    width: 80%;
    height: 80%;
  }
  .size {
    font-size: 1em;
  }
  .snotifyToast__title {
  font-size: 17px;
  }
  .snotifyToast__body {
    font-size: 12px;
  }
</style>