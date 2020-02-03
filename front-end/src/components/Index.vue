<template>
  <div class="container-fluid photos">
    <div class="row align-items-stretch">
      
      <div class="col-6 col-md-6 col-lg-4" data-aos="fade-up" v-for="con in contents" :key="con.id">
        <div class="d-block photo-item">
          <img :src="con.images.imageUrl" alt="Image" class="img-fluid pa"/>
          <p class="ch text-right text-primary pt-0 mt-0" v-if="con.scrapButton">📥 {{con.userId}}님</p>

          <div class="photo-text-more">
            <h3 class="heading mx-2 ellipsis" v-on:click="goDetail(con.contentId)">{{con.contentId}} {{con.contentValue}}</h3>
            <span v-if="con.imageLength == 0" class="meta">there's no photo</span>
            <span v-else-if="con.imageLength == 1" class="meta">photo just {{con.imageLength}}</span>
            <span v-else class="meta">photos {{con.imageLength}}</span>

            <div class="meta">
              <div class="mb-3 my-3 d-flex justify-content-around size">
                <div @click="clickHeart(con.contentId)">
                  <i class="icon-heart" v-if="con.likeButton"></i>
                  <i class="icon-heart-o" v-else></i>
                </div>
                <div @click="clickFollow(con.userId)">
                  <i class="icon-check" v-if="followList.includes(con.userId)">{{con.userId}}</i>
                  <i class="icon-user" v-else-if="con.userId==loginId">나</i>
                  <i class="icon-user-plus" v-else>{{con.userId}}</i>
                </div>
                <div @click="clickScrap(con.contentId, con.scrapButton)">
                  <i class="icon-bookmark" v-if="con.scrapButton"></i>
                  <i class="icon-bookmark-o" v-else></i>
                </div>
                <!-- <div v-on:click="clickBell()">
                  <i class="icon-bell" v-if="bell"></i>
                  <i class="icon-bell-o" v-else></i>
                </div> -->
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
        images: {
          imageUrl: "",
          filter: "",
        },
        scrapButton: false,
      }],
      loginId: "",
      contentErrorMsg: "",
      errored: false,
      userLikeList: [],
      followList: [],
      scrapList: [],
    }
  },
  methods: {
    getLike() {
      this.loginId = store.state.user_id
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
              images: {
                imageUrl: res.data.resvalue[i].imageList[0].image_url,
                filter: res.data.resvalue[i].imageList[0].filter,
              },
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
                  this.contents.push({
                    contentId: this.userLikeList[idx2].contentId,
                    contentValue: this.userLikeList[idx2].contentValue,
                    timestamp: this.userLikeList[idx2].timestamp,
                    likeButton: this.userLikeList[idx2].likeButton,
                    userId: this.userLikeList[idx2].userId,
                    imageLength: this.userLikeList[idx2].imageLength,
                    images: this.userLikeList[idx2].images,
                    scrapButton: false,
                  })
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
                  images: {
                    imageUrl: res.data.resValue[idx].imageList[0].image_url,
                    filter: res.data.resValue[idx].imageList[0].filter,
                  },
                  scrapButton: true,
                })
              } else {
                this.contents.push({
                  contentId: res.data.resValue[idx].content_id,
                  contentValue: res.data.resValue[idx].content_val,
                  timestamp: res.data.resValue[idx].timestamp,
                  likeButton: res.data.resValue[idx].user_like,
                  userId: res.data.resValue[idx].user_id,
                  imageLength: res.data.resValue[idx].imageList.length,
                  images: {
                    imageUrl: res.data.resValue[idx].imageList[0].image_url,
                    filter: res.data.resValue[idx].imageList[0].filter,
                  },
                  scrapButton: false,
                })
              }
            }
            this.sortList()
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
          this.followList = res.data.resvalue
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
          .catch(()=>{
            this.errored = true;
          })
        const del = []
        for (var i = 0; i < this.contents.length; i++) {
          // if (this.contents[i].userId == user && !this.contents[i].likeButton && !this.contents[i].scrapButton) {
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
      } else if (user == this.loginId) {
        console.log("it's me")
      } else {
        http
          .post('/follow/insertFollow', {
            follow_id: user,
            follower_id: this.loginId
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
                    images: {
                      imageUrl: idx.imageList[0].image_url,
                      filter: idx.imageList[0].filter,
                    },
                    scrapButton: true,
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
                    images: {
                      imageUrl: idx.imageList[0].image_url,
                      filter: idx.imageList[0].filter,
                    },
                    scrapButton: false,
                  })
                }
              }
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
                  category: res.data.resValue.category
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
              this.contents[idx2].scrapButton = false
            } else {
              console.log(res.data.resmsg)
              this.contents[idx2].scrapButton = false
            }
          })
          .catch(()=>{
            this.errored = true;
          })
      }
    }
  },
  created() {
    this.getLike()
    this.getScrap()
    this.getData()
    this.getFollow()
  },
  mounted() {
    $('html').scrollTop(0);
    this.$nextTick(() => {
      // 모든 화면이 렌더링된 후 호출됩니다.
      if(document.querySelector(".site-nav-wrap")==null){
        $('.js-clone-nav').each(function() {
          var $this = $(this);
          $this.clone().attr('class', 'site-nav-wrap').appendTo('.site-mobile-menu-body');
        });
      }
    });
    // if(document.querySelector(".site-nav-wrap")==null){
    //   $('.js-clone-nav').each(function() {
    //     var $this = $(this);
    //     $this.clone().attr('class', 'site-nav-wrap').appendTo('.site-mobile-menu-body');
    //   });
    // }

    // setTimeout (() => {
    //   if(document.querySelectorAll("ul").length>4){
    //   document.querySelector("ul").remove();
    //   document.querySelector("ul").remove();
    //   }
    // }, 1000);
      
  },
}
</script>
<style>
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
</style>