<template>
  <div class="container-fluid photos">
    <div class="row align-items-stretch">
      <!-- 게시물 하나 -->
      <div class="col-6 col-md-6 col-lg-4" data-aos="fade-up" style="padding: 10px 10px" v-for="con in contents" :key="con.id">
        <div class="d-block photo-item">
          <!-- 이미지 처리 -->
          <div class="polaroid" v-if="con.dislike < 5 && !reportMyList.includes(con.contentId) || readContents.includes(con.contentId)">
            <div :class="con.images[0].filter" style="width:100%; height:100%">
              <img :src="con.images[0].imageUrl" alt="Image" class="img-fluid pa m-0" style="box-shadow: 3px 3px 3px;"/>
            </div>
          </div>
          <div class="polaroid" v-show="con.dislike > 4 && !readContents.includes(con.contentId) && !reportMyList.includes(con.contentId)">
            <div class="m-0 pin" style="width:100%; height:100%">
              <img :src="con.images[0].imageUrl" alt="Image" class="img-fluid pa blur m-0" style="box-shadow: 3px 3px 3px;"/>
              <p class="chcenter text-center text-white">신고가 누적된 게시물입니다.</p>
              <button class="ch btn btn-primary btn-sm" @click="readReCon(con.contentId)">보기</button>
            </div>
          </div>
          <div class="polaroid" v-show="reportMyList.includes(con.contentId) && !readContents.includes(con.contentId)">
            <div class="m-0 pin" style="width:100%; height:100%">
              <img :src="con.images[0].imageUrl" alt="Image" class="img-fluid pa blur m-0" style="box-shadow: 3px 3px 3px;"/>
              <p class="chcenter text-center text-white">내가 신고한 게시물입니다.</p>
            </div>
          </div>
          <!-- 마우스 오버 했을 때 -->
          <div class="photo-text-more">
            <div class="">
              <div class="d-block photo-item">
                <div class="postcard">
                  <div class="content">
                    <!-- 누르면 상세 페이지로 -->
                    <div v-on:click="goDetail(con.content_id)">
                      <!-- 우표 -->
                      <div class="stamp-cover" style="background:black; height:52px; width:52px;">
                        <div class="stamp" style=" margin:1px; float:right; background-color:white; height:50px; width:50px;">
                        </div>
                      </div>
                      <div v-if="con.profile_url != null" style="width:37px;height:37px" class="stamp-img" :class="con.profile_filter">
                        <img :src="con.profile_url" style="width:37px;height:37px; background: none;" />
                      </div>
                      <div v-else>
                        <img src="../../public/theme/images/ai.jpg" style="width:37px;height:37px;" class="stamp-img"/>
                      </div>
                      <img src="../../public/theme/images/stamp1.png" style="width:45px;height:45px;" alt="Postage mark" class="postmark">
                      <!-- 우편 내용 -->
                      <div class="mail-title offset-1 col-9" style="text-align:left;"><p style="color:black; font-size:2em; font-family: loveson;">Dear {{ loginId }} {{ con.contentId }}</p></div>
                      <div class="mail-message offset-2 col-8 ellipsis" style="color:black; font-family: loveson; word-break:break-all;text-align:left;">{{ con.contentValue }}</div>
                      <div class="col-11 col-offset-1" style="color:black; font-family: loveson; word-break:break-all;text-align:right;">from {{ con.userId }}</div>
                    </div>
                    <!-- buttons -->
                    <div class="mb-3 my-3 d-flex justify-content-around size content-button">
                      <div @click="clickHeart(con.contentId)">
                        <i class="icon-heart" v-if="con.likeButton"></i>
                        <i class="icon-heart-o" v-else></i>
                      </div>
                      <div @click="clickFollow(con.userId)" v-if="con.userId !== loginId">
                        <i class="icon-check" v-if="followList.includes(con.userId)">{{con.userId}}</i>
                        <i class="icon-user-plus" v-else>{{con.userId}}</i>
                      </div>
                      <div @click="clickScrap(con.contentId, con.scrapButton)" v-if="con.userId !== loginId">
                        <i class="icon-bookmark" v-if="con.scrapButton"></i>
                        <i class="icon-bookmark-o" v-else></i>
                      </div>
                      <div v-if="con.userId !== loginId">
                        <i class="icon-bell" v-if="reportMyList.includes(con.contentId)" @click="cancel(con.contentId)">신고 취소</i>
                        <i class="icon-bell-o" v-else id="reportId" data-toggle="modal" data-target="#exampleModal" @click="sendInfo(con.contentId, con.timestamp)">신고</i>
                      </div>
                    </div>
                  </div>
                </div>
              </div>
            </div>
          </div>

        </div>
      </div>

      <!-- Modal -->
      <div class="modal fade mt-5" id="exampleModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
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
                <button class="btn btn-whatever btn-sm dropdown-toggle" style="font-size:13px;" type="button" id="dropdownMenu2" data-toggle="dropdown" data-display="static" aria-haspopup="true" aria-expanded="false">
                  신고 사항 선택
                </button>
                <div class="dropdown-menu" aria-labelledby="dropdownMenu2">
                  <button class="dropdown-item" type="button" @click="sendReport(options[0].op1)">{{ options[0].op1 }}</button>
                  <button class="dropdown-item" type="button" @click="sendReport(options[0].op2)">{{ options[0].op2 }}</button>
                  <button class="dropdown-item" type="button" data-toggle="collapse" data-target="#collapseExample" aria-expanded="false" aria-controls="collapseExample">{{ options[0].op3 }}</button>
                </div>
              </div>

              <div class="collapse" id="collapseExample">
                <div class="card card-body">
                  <input type="text" v-model="options[0].op4" placeholder="기타를 선택하신 분은 신고 내용을 입력해주세요.">
                </div>
                <input type="button" value="입력" data-toggle="collapse" data-target="#collapseExample" aria-expanded="false" aria-controls="collapseExample" @click="sendReport(options[0].op4)">
              </div>
            </div>
            <div class="modal-footer">
              <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
              <button type="button" class="btn btn-primary" data-dismiss="modal" @click="clickBell()">신고하기</button>
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
import http from "../http-common"
import store from '../store'
export default {
  data() {
    return {
      contents: [{
        contentId: "",
        contentValue: "",
        timestamp: "",
        likeButton: false,
        userId: "",
        imageLength: 0,
        images: [{
          imageUrl: "",
          filter: "",
        }],
        scrapButton: false,
        report: false,
      }],
      loginId: store.state.user_id,
      contentErrorMsg: "",
      errored: false,
      userLikeList: [],
      followList: [],
      scrapList: [],
      reportMyList: [],
      readContents: [],
      options: [{
        op1: "부적절한 게시물",
        op2: "광고 게시물",
        op3: "기타",
        op4: "",
      }],
      info: [],
    }
  },
  methods: {
    sendInfo(cid, time) {
      this.info = []
      this.info.push({
        content_id: cid,
        user_id: this.loginId,
        report_category: "report",
        report_val: "",
        timestamp: time,
      })
    },
    sendReport(report_val) {
      this.info[0].report_val = report_val
    },
    readReCon(cid) {
      this.readContents.push(cid)
    },
    getReport() {
      this.reportMyList = []
      http
        .get('/userReport/myReportList/' + this.loginId)
        .then((res) => {
          if (res.data.resvalue.length > 0) {
            console.log(res.data.resvalue)
            for (var i = 0; i < res.data.resvalue.length; i++) {
              this.reportMyList.push(res.data.resvalue[i].content_id)
            }
          }
        })
        .catch(() => {
          this.errored = true
        })
    },
    getLike() {
      // this.loginId = store.state.user_id
      http
        .get('/userLike/userLikeList/' + this.loginId)
        .then((res) => {
          for (var i = 0; i < res.data.resvalue.length; i++) {
            res.data.resvalue[i].user_like = true
            this.userLikeList.push({
              contentId: res.data.resvalue[i].content_id,
              contentValue: res.data.resvalue[i].content_val,
              timestamp: res.data.resvalue[i].timestamp,
              likeButton: res.data.resvalue[i].user_like,
              userId: res.data.resvalue[i].user_id,
              imageLength: res.data.resvalue[i].imageList.length,
              images: [{
                imageUrl: res.data.resvalue[i].imageList[0].image_url,
                filter: res.data.resvalue[i].imageList[0].filter,
              }],
              dislike: res.data.resvalue[i].dislike,
            })
          }
        })
        .catch(()=>{
          this.errored = true;
        })
    },
    getScrap() {
      http
        .get('/scrap/scrapList/' + this.loginId)
        .then((res) => {
          for (var i = 0; i < res.data.resvalue.length; i++) {
            if (!this.scrapList.includes(res.data.resvalue[i].content_id)) {
              this.scrapList.push(res.data.resvalue[i].content_id)
            }
          }
        })
        .catch(()=>{
          this.errored = true;
        })
    },
    getData() {
      if (this.contents[0].contentId == "") {
        this.contents = []
      }
      http
        .get('/content/contentMyList/' + this.loginId)
        .then((res) => {
          console.log(res)
          if (res.data.resValue.length > 0) {
            this.contentErrorMsg = ""
            for (var idx = 0; idx < res.data.resValue.length; idx++) {
              for (var idx2 = 0; idx2 < this.userLikeList.length; idx2++) {
                if (res.data.resValue[idx].content_id == this.userLikeList[idx2].contentId) {
                  res.data.resValue[idx].user_like = true
                  delete this.userLikeList[idx2].contentId
                }
                if (this.userLikeList[idx2].contentId && idx == res.data.resValue.length - 1) {
                  if (this.scrapList.includes(res.data.resValue[idx2].contentId)) {
                    this.contents.push({
                      contentId: this.userLikeList[idx2].contentId,
                      contentValue: this.userLikeList[idx2].contentValue,
                      timestamp: this.userLikeList[idx2].timestamp,
                      likeButton: this.userLikeList[idx2].likeButton,
                      userId: this.userLikeList[idx2].userId,
                      imageLength: this.userLikeList[idx2].imageLength,
                      images: this.userLikeList[idx2].images,
                      scrapButton: true,
                      dislike: this.userLikeList[idx2].dislike,
                    })
                  } else {
                    this.contents.push({
                      contentId: this.userLikeList[idx2].contentId,
                      contentValue: this.userLikeList[idx2].contentValue,
                      timestamp: this.userLikeList[idx2].timestamp,
                      likeButton: this.userLikeList[idx2].likeButton,
                      userId: this.userLikeList[idx2].userId,
                      imageLength: this.userLikeList[idx2].imageLength,
                      images: this.userLikeList[idx2].images,
                      scrapButton: false,
                      dislike: this.userLikeList[idx2].dislike,
                    })
                  }
                }
              }
              if (this.scrapList.includes(res.data.resValue[idx].content_id)) {
                this.contents.push({
                  contentId: res.data.resValue[idx].content_id,
                  contentValue: res.data.resValue[idx].content_val,
                  timestamp: res.data.resValue[idx].timestamp,
                  likeButton: res.data.resValue[idx].user_like,
                  userId: res.data.resValue[idx].user_id,
                  imageLength: res.data.resValue[idx].imageList.length,
                  images: [{
                    imageUrl: res.data.resValue[idx].imageList[0].image_url,
                    filter: res.data.resValue[idx].imageList[0].filter,
                  }],
                  scrapButton: true,
                  dislike: res.data.resValue[idx].dislike,
                })
              } else {
                this.contents.push({
                  contentId: res.data.resValue[idx].content_id,
                  contentValue: res.data.resValue[idx].content_val,
                  timestamp: res.data.resValue[idx].timestamp,
                  likeButton: res.data.resValue[idx].user_like,
                  userId: res.data.resValue[idx].user_id,
                  imageLength: res.data.resValue[idx].imageList.length,
                  images: [{
                    imageUrl: res.data.resValue[idx].imageList[0].image_url,
                    filter: res.data.resValue[idx].imageList[0].filter,
                  }],
                  scrapButton: false,
                  dislike: res.data.resValue[idx].dislike,
                })
              }
            }
            this.sortList()
            // this.getReport()
          } else {
            this.contentErrorMsg = "타임라인이 없습니다."
          }
        })
        .catch(()=>{
          this.errored = true;
        })
    },
    getFollow() {
      http
        .get('/follow/followList/' + this.loginId)
        .then((res) => {
          for (var i = 0; i < res.data.resvalue.length; i++) {
            this.followList.push(res.data.resvalue[i].user_id)
          }
        })
        .catch(()=>{
          this.errored = true;
        })
    },
    sortList() {
      this.contents.sort(function(a, b) {
        return (a.timestamp < b.timestamp) ? - 1 : (a.timestamp > b.timestamp) ? 1 : 0;
      })
      this.contents.reverse()
      // this.getReport()
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
      for (var idx = 0; idx < this.contents.length; idx++) {
        if (this.contents[idx].contentId == num) {
          if (this.contents[idx].likeButton == false) {
            this.contents[idx].likeButton = true
            http
              .post('/userLike/like', {
                content_id: this.contents[idx].contentId,
                timestamp: this.contents[idx].timestamp,
                user_id: this.loginId
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
            this.contents[idx].likeButton = false       
            http
              .delete('/userLike/dislike', {
                data: {
                  content_id: this.contents[idx].contentId,
                  timestamp: this.contents[idx].timestamp,
                  user_id: this.loginId
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
            if (this.contents[idx].userId !== this.loginId && this.followList.includes(this.contents[idx].userId) == false) {
              this.contents.splice(idx, 1)
            }
          }
        }
      }
    },
    clickFollow(user) {
      this.scrapList = []
      this.getScrap()
      if (this.followList.includes(user) == true) {
        http
          .delete('/follow/deleteFollow', {
            data: {
              follow_id: user,
              follower_id: this.loginId
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
        const del = []
        for (var i = 0; i < this.contents.length; i++) {
          if (this.contents[i].userId == user && !this.contents[i].likeButton) {
            del.push(this.contents[i].contentId)
          }
        }
        for (var j =0; j < del.length; j++) {
          const index = this.contents.findIndex(function(item) {return item.contentId === del[j]})
          this.contents.splice(index, 1)
        }
        const idx = this.followList.indexOf(user)
        if (idx > -1) {
          this.followList.splice(idx, 1)
        }
      } else {
        console.log(this.followList)
        // if (this.followList.includes(user)) {
        //   console.log("이미 있음")
        // } else {
        //   console.log("no no no ")
        // }
        http
          .post('/follow/insertFollow', {
            follow_id: user,
            follower_id: this.loginId
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
        http
          .get('/content/contentUserList/' + user)
          .then((res) => {
            const li = []
            this.contents.forEach(i => {
              li.push(i.contentId)
            })
            res.data.resValue.forEach(idx => {
              if (!li.includes(idx.content_id)) {
                if (this.scrapList.includes(idx.content_id)) {
                  this.contents.push({
                    contentId: idx.content_id,
                    contentValue: idx.content_val,
                    timestamp: idx.timestamp,
                    likeButton: idx.user_like,
                    userId: idx.user_id,
                    imageLength: idx.imageList.length,
                    images: [{
                      imageUrl: idx.imageList[0].image_url,
                      filter: idx.imageList[0].filter,
                    }],
                    scrapButton: true,
                    dislike: idx.dislike,
                  })
                } 
                else {
                  this.contents.push({
                    contentId: idx.content_id,
                    contentValue: idx.content_val,
                    timestamp: idx.timestamp,
                    likeButton: idx.user_like,
                    userId: idx.user_id,
                    imageLength: idx.imageList.length,
                    images: [{
                      imageUrl: idx.imageList[0].image_url,
                      filter: idx.imageList[0].filter,
                    }],
                    scrapButton: false,
                    dislike: idx.dislike,
                  })
                }
              }
              this.sortList()
            })
          })
        .catch(()=>{
          this.errored = true;
        })
      }
    },
    clickScrap(id, button) {
      if (button == false) {
        http
          .post('/scrap/insertScrap', {
            content_id: id,
            user_id : this.loginId,
          })
          .then((res) => {
            const idx = this.contents.findIndex(function(item) {
              return item.contentId === id
            })
            if (res.data.resmsg === "스크랩성공") {
              this.contents[idx].scrapButton = true
              this.$socket.emit('notification', {
                  user_id: res.data.resValue.user_id,
                  target_user_id: res.data.resValue.target_user_id,
                  category: res.data.resValue.category,
                  flag:true
                });
            } else {
              console.log(res.data.resmsg)
              this.contents[idx].scrapButton = true
            }
          })
          .catch(()=>{
            this.errored = true;
          })
      } else {
        http
          .delete('/scrap/deleteScrap', {
            data: {
              content_id: id,
              user_id : this.loginId,
            }
          })
          .then((res) => {
            const idx2 = this.contents.findIndex(function(item) {
              return item.contentId === id
            })
            if (res.data.resmsg === "스크랩취소성공") {
              this.contents[idx2].scrapButton = false;
              this.$socket.emit('notification', {
                  user_id: res.data.resValue.user_id,
                  target_user_id: res.data.resValue.target_user_id,
                  category: res.data.resValue.category,
                  flag:false
                });
            } else {
              console.log(res.data.resmsg)
              this.contents[idx2].scrapButton = false
            }
          })
          .catch(()=>{
            this.errored = true;
          })
      }
    },
    clickBell() {
      if (this.info[0].report_val == "") {
        this.$store.commit('setModalText', "신고 사항을 선택해주십시오.");
        document.getElementById('modalBtn').click();
      } else {
        http
          .post('/userReport/report', {
            content_id: this.info[0].content_id,
            report_category: this.info[0].report_category,
            report_val: this.info[0].report_val,
            timestamp: this.info[0].timestamp,
            user_id: this.loginId,
          })
          .then((res) => {
            this.reportMyList = []
            if (res.data.resmsg == "신고 성공") {
              this.$store.commit('setModalText', "신고가 접수되었습니다.");
              document.getElementById('modalBtn').click();
            } else {
              console.log("신고 실패")
            }
            this.options[0].op4 = ""
            this.info = []
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
              user_id: this.loginId,
            }
          })
          .then((res) => {
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
            this.sortList()
          })
          .catch(()=>{
            this.errored = true;
          })
      }
    },
  },
  created() {
    // this.getLike()
    // this.getScrap()
    // this.getData()
    // this.getFollow()
    // this.getReport()
    // this.sortList()
  },
  watch: {
    // reportMyList: {
    //   handler() {
    //     this.getReport()
    //     // this.sortList()
    //   }
    // },
    // contents: {
    //   handler() {
    //     this.sortList()
    //   }
    // },
    // readContents: {
    //   handler() {
    //     this.readReCon()
    //   }
    // }
  },
  mounted() {
    this.getLike()
    this.getScrap()
    this.getData()
    this.getFollow()
    this.sortList()
    this.getReport()
    $('html').scrollTop(0);
    this.$nextTick(() => {
      // 모든 화면이 렌더링된 후 호출됩니다.
      // console.log(document.querySelectorAll("ul").length)
        $('.js-clone-nav').each(function() {
          var $this = $(this);
          $this.clone().attr('class', 'site-nav-wrap').appendTo('.site-mobile-menu-body');
        });
        document.querySelector("ul").remove();
        // document.querySelector("ul").remove();
    }); 
  },
}
</script>
<style scoped>
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
  .ch {
    position: absolute;
    top: 1px;
    right: 10px;
    font-size: 13px;
  }
  .chcenter {
    position: absolute;
    top: 40%;
  }
  .blur {
    -webkit-filter: blur(5px);
    -moz-filter: blur(5px);
    -o-filter: blur(5px);
    -ms-filter: blur(5px);
    filter: blur(5px);
    transform: scale(0.99);
    overflow: hidden;
    display: block;
    width: 100%;
    height: 100%;
    clip: rect(100px, 100px, 100px, 100px);
  }
  .pin {
    left: 50%;
    top: 50%;
    margin-left: 50%
  }
  .modal-dialog { 
      width: 30%; 
      height: 50%;
  }
  .modal-content{
    height: auto;
    min-height: 100%;
  }
  .mydiv {
    margin-top: 7em;
  }
  .size {
    font-size: 1em;
  }
</style>