<template>
    <div class="container-fluid photos">
      <div v-if="!isLoading&&(Items == null || Items.length == 0)" style="text-align:center;">
                    검색 결과가 없습니다.
                </div>
                <div style="text-align:center;" v-if="isLoading&&(Items == null || Items.length == 0)">
                    <img class="col-md-12" src="/theme/images/loading10.gif" />
                </div>
      <div v-else class="row align-items-stretch">
        <div class="col-6 col-md-6 col-lg-4" style="padding: 10px 10px" v-for="item in Items" :key="item.id">
          <div class="d-block photo-item">
            <div class="polaroid">
                <div v-on:click="goDetail(item.content_id)" :class="item.imageList[0].filter" class="" style="width:100%; height:300px">
                  <img :src="item.imageList[0].image_url" style="box-shadow: 3px 3px 3px;" alt="Image"/>
                  <!-- <div style="text-align:right;">from {{item.user_id}}</div> -->
                </div>
            </div>
            <!-- -->
              <div class="photo-text-more">
                <!-- <div class="" data-aos="fade-up"> -->
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
                          <div v-if="item.profile_url != null" style="width:37px;height:37px" class="stamp-img" :class="item.profile_filter">
                            <img :src="item.profile_url" style="width:37px;height:37px; background: none;" />
                          </div>
                          <div v-else>
                            <img src="../../public/theme/images/ai.jpg" style="width:37px;height:37px;" class="stamp-img"/>
                          </div>
                          <img src="../../public/theme/images/stamp1.png" style="width:45px;height:45px;" alt="Postage mark" class="postmark">
                          <!-- 끝 -->
                          <div class="mail-title offset-1 col-9" style="text-align:left;"><p style="color:black; font-size:2em; font-family: loveson;">Dear {{uid}}</p></div>
                          <div class="mail-message offset-2 col-8 ellipsis" style="color:black; font-family: loveson; word-break:break-all;text-align:left;">{{item.content_val}}</div>
                          <div class="col-11 col-offset-1" style="color:black; font-family: loveson; word-break:break-all;text-align:right;">from {{item.user_id}}</div>
                        </div>
                        <div class="mb-3 my-3 d-flex justify-content-around size content-button">
                          <div @click="clickHeart(item.content_id)">
                            <i class="icon-heart" v-if="item.user_like"></i>
                            <i class="icon-heart-o" v-else></i>
                          </div>
                          <div v-on:click="clickFollow()">
                            <i class="icon-check" v-if="follow"></i>
                            <i class="icon-user-plus" v-else></i>
                          </div>
                          <div v-on:click="clickBell()">
                            <i class="icon-bell" v-if="bell"></i>
                            <i class="icon-bell-o" v-else></i>
                          </div>
                        </div>
                      </div>
                    </div>
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
import axios from 'axios'
export default {
  // props:["userId"],
  data() {
    return {
      userId:"",
      myInterest:[],
      Interests:[],
      follow: false,
      bell: false,
      errored: false,
      uid: "",
      contentIds: [],
      contentErrorMsg: "",
      Items:[],
      isLoading:false
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
      this.isLoading = true;
      http
        .get(`/user/myInterest/${this.userId}`)
        .then((res) => {
          window.console.log(res.data.resValue);
          res.data.resValue.forEach(element => {
            this.myInterest.push(element);
            this.Interests.push(element);
          })
          axios.post("https://translation.googleapis.com/language/translate/v2?key=AIzaSyAcnkt6IBUt-bGIMw4u-VEIYpesgw4-2Lk",{
                  "q": this.myInterest,
                  "target": "en"
                })
                .then((res) => {
                  res.data.data.translations.forEach(element => {
                    const keyword = element.translatedText;
                    axios
                      .get("http://192.168.100.41:5000/searchByKeyword/" + keyword)
                      .then((res) => {
                        axios.post("https://translation.googleapis.com/language/translate/v2?key=AIzaSyAcnkt6IBUt-bGIMw4u-VEIYpesgw4-2Lk",{
                                "q": res.data,
                                "target": "ko"
                              })
                              .then((res) => {
                                let num = 0;
                                res.data.data.translations.some( element => {
                                  if(element.translatedText!=""&&!element.translatedText.includes(" ")&&!this.Interests.includes(element.translatedText)){
                                    this.Interests.push(element.translatedText);
                                    num++;
                                  }
                                  return(num>=3);
                                });

                                http
                                .get(`/user/allInterestList`)
                                .then((res) => {
                                  res.data.resValue.forEach( element => {
                                    if(element != "" && !this.Interests.includes(element)){
                                      this.Interests.push(element);
                                    }
                                  })

                                  http
                                    .post(`/content/contentListHashtagList`, this.Interests)
                                    .then((res) => {
                                      this.isLoading = false;
                                      this.Items = res.data.resValue;
                                      this.Items.forEach(element => {
                                        element.value = 0;
                                        this.myInterest.forEach(interest => {
                                          if(element.hashtag.includes(interest)){
                                            element.value++;
                                          }
                                        })
                                      });
                                      this.Items.sort(function(a, b){
                                        return a.value > b.value ? -1 : a.value < b.value ? 1 : 0;
                                      });
                                    })
                                })
                              })
                      })
                  });

                  
                })

                
        })
        .finally(() => {
          
        })
      // http
      // .get('content/contentUserList/'+this.uid)
      // .then((res)=>{
      //   if (res.data.resValue.length > 0) {
      //     this.contentErrorMsg = ""
      //     if (res.data.resmsg == "개인 게시물 리스트 출력 성공") {
      //       for (var i = 0; i < res.data.resValue.length; i++) {
      //         for (var j = 0; j < this.contentIds.length; j++) {
      //           if (res.data.resValue[i].content_id == this.contentIds[j].con_id) {
      //             res.data.resValue[i].user_like = true
      //           }
      //         }
      //       }
      //       this.Items = res.data.resValue;
      //     }
      //   } else {
      //     this.contentErrorMsg = "게시물이 없습니다."
      //   }
      // })
      // .catch(()=>{
      //   this.errored = true;
      // })
      // .then(response => {
      //   // console.log(response.data)
      //   this.Items = response.data.resValue;
      // })
      // .catch(e => console.log(e))
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
      // .then(response => {
      //   // console.log(response.data)
      //   this.Items = response.data.resValue;
      // })
      .catch(e => console.log(e))
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
      // .then(response => {
      //   // console.log(response.data)
      //   this.Items = response.data.resValue;
      // })
      .catch(e => console.log(e))
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
    clickFollow() {
      if (this.follow === true) {
        this.follow = false;
      } else {
        this.follow = true;
      }
    },
    clickBell() {
      if (this.bell === true) {
        this.bell = false;
      } else {
        this.bell = true;
        this.$store.commit('setModalText', '신고가 접수되었습니다.');
        document.getElementById('modalBtn').click();
      }
    },
  },
  created() {
    this.userId = this.$store.state.user_id;
    this.getLike();
    this.getData();
  },
  mounted() {
    $('html').scrollTop(0);
    this.$nextTick(() => {
      
    })
  },
  updated(){
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