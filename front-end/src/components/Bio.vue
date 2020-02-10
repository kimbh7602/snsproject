<template>
  <div class="container-fluid photos">
    <div class="row justify-content-center">
      <div class="col-10" data-aos="fade-up">
        <div class="d-block photo-item">
          <div class="postcard" style="height:auto;">
            <div class="content" style="height:auto;">
              <!-- 우표 -->
              <div>
                <div class="stamp-cover" style="background:black; height:52px; width:52px; z-index:3;">
                  <div class="stamp" style=" margin:1px; float:right; background-color:white; height:50px; width:50px;">
                  </div>
                </div>
                <div :class="items.profile_filter">
                  <img :src="items.profile_url" style="width:37px;height:37px; z-index:4; background: none;"
                    class="stamp-img" />
                </div>
                <!-- <img :src="items.profile_url" :class="items.profile_filter" style="width:37px;height:37px; z-index:4;"
                  class="stamp-img" /> -->
                <img src="../../public/theme/images/stamp1.png" style="width:45px;height:45px; z-index:5;"
                  alt="Postage mark" class="postmark">
                <!-- 끝 -->
                <div class="col-12 col-lg-6"
                  style="display:inline-block; vertical-align:middle; padding-top:0px; margin-top:0px; padding-bottom:50px">
                  <div class="mail-title offset-1 col-9" style="text-align:left;">
                    <p style="color:black; font-size:2em; font-family: loveson;">Dear {{uid}}</p>
                  </div>
                  <div class="detail-mail-message mail-message offset-2 col-8"
                    style="color:black; font-family: loveson; word-break:break-all;text-align:left;"
                    v-html="items.content_val">
                  </div>
                  <div class="col-11 col-offset-1"
                    style="color:black; font-family: loveson; word-break:break-all; vertical-align:bottom; text-align:right;">
                    from {{items.user_id}}</div>
                  <!-- 댓글 -->
                  <div class="col-lg-12 col-sm-12 row"
                    style="margin:0; color:black; vertical-align:bottom; text-align:left;">
                    <div class="col-2 col-sm-2 col-md-1 col-lg-2 "
                      style="font-size:1.5em; font-family: loveson; word-break:break-all; ">PS.
                    </div>
                    <div class="col-10 col-sm-10 col-md-1 col-lg-2 "
                      style="display:inline-block; font-weight:bold; font-size:1.2em; font-family: loveson; word-break:break-all; vertical-align:top;">
                      {{uid}}</div>
                    <!-- 댓글입력창 -->
                    <div class="col-11 col-lg-7 col-sm-10 col-md-9 col-lg-7"><input type="text"
                        class="text-black form-control" v-model="comment_val" v-on:keyup.enter="insertComment" /></div>
                    <!-- 댓글입력버튼 -->
                    <div class="col-1 col-sm-2 col-md-1 col-lg-1 ">
                      <div class="icon-check" @click="insertComment">
                      </div>
                    </div>
                  </div>
                  <!-- 댓글출력 -->
                  <div v-if="!isComment">
                    <div class="row" style="text-align:left;" v-for="(comment, index) in tempComments" :key="index">
                      <div class="col-lg-1"></div>
                      <div class="col-lg-3 col-sm-3"
                        style="display:inline-block; font-weight:bold; font-size:1.2em; font-family: loveson; word-break:break-all; vertical-align:top;">
                        {{comment.user_id}}</div>
                      <!-- <div class="col-1"></div> -->
                      <div class="comment-val col-lg-7 col-sm-9"
                        style="display:inline-block; padding-left:5px; font-size:1.2em; font-family: loveson; word-break:break-all; vertical-align:bottom;">
                        {{comment.comment_val}}</div>
                    </div>
                    <div style="padding-top:20px; text-align:center;">
                      <svg @click="isComment = true;" style="cursor:pointer;" xmlns="http://www.w3.org/2000/svg"
                        height="25" viewBox="0 0 512 512" width="25" class="">
                        <g>
                          <path
                            d="m437.019531 74.980469c-48.351562-48.351563-112.640625-74.980469-181.019531-74.980469s-132.667969 26.628906-181.019531 74.980469c-48.351563 48.351562-74.980469 112.640625-74.980469 181.019531s26.628906 132.667969 74.980469 181.019531c48.351562 48.351563 112.640625 74.980469 181.019531 74.980469s132.667969-26.628906 181.019531-74.980469c48.351563-48.351562 74.980469-112.640625 74.980469-181.019531s-26.628906-132.667969-74.980469-181.019531zm-181.019531 397.019531c-119.101562 0-216-96.898438-216-216s96.898438-216 216-216 216 96.898438 216 216-96.898438 216-216 216zm20-236.019531h90v40h-90v90h-40v-90h-90v-40h90v-90h40zm0 0"
                            data-original="#000000" class="active-path" data-old_color="#000000" fill="#5EB2FD" />
                        </g>
                      </svg>
                    </div>
                  </div>
                  <div v-if="isComment">
                    <div class="row" style="text-align:left;" v-for="(comment, index) in comments" :key="index">
                      <!-- 댓글출력 -->
                      <div class="row col-12 col-sm-12 col-lg-12 " v-if="comment.comment_id == comment.re_comment_id">
                        <div class="col-sm-1 col-md-1 col-lg-1 "></div>
                        <div class="col-2 col-sm-2 col-md-1 col-lg-2"
                          style="display:inline-block; font-weight:bold; font-size:1.2em; font-family: loveson; word-break:break-all; vertical-align:top;">
                          {{comment.user_id}}</div>
                        <!-- <div class="col-1"></div> -->
                        <div class="comment-val col-6 col-sm-6 col-md-8 col-lg-6" v-bind:id="'view'+comment.comment_id"
                          style="display:inline-block; padding-left:5px; font-size:1.2em; font-family: loveson; word-break:break-all; vertical-align:bottom;">
                          {{comment.comment_val}}
                        </div>
                        <div class="comment-val col-6 col-sm-6 col-md-8 col-lg-7" v-bind:id="'edit'+comment.comment_id"
                          style="display:none;">
                          <input type="text" class="text-black form-control" v-model="comment.comment_val"
                            v-on:keyup.enter="editcomment(comment.comment_id, comment.comment_val, comment.re_comment_id)" />
                        </div>

                        <div class="row col-3 col-sm-3 col-md-2 col-lg-3" style="display:flex;"
                          v-bind:id="'view_button'+comment.comment_id">
                          <div class="col-4 col-sm-4 col-md-4 col-lg-4">
                            <div class="icon-plus" @click="recommentdisplay('comment'+comment.comment_id)"></div>
                          </div>
                          <div class="col-4 col-sm-4 col-md-4 col-lg-4">
                            <div v-if="comment.check===true" class="icon-pencil"
                              @click="changeedit(comment.comment_id)"></div>
                          </div>
                          <div class="col-4 col-sm-4 col-md-4 col-lg-4">
                            <div v-if="comment.check===true" class="icon-close" @click="delcomment(comment.comment_id)">
                            </div>
                          </div>
                        </div>
                        <div class="col-2 col-sm-2 col-md-2 col-lg-2" v-bind:id="'edit_button'+comment.comment_id"
                          style="display:none;">
                          <div class="col-6 col-sm-6  col-md-6 col-lg-6">
                            <div class="icon-arrow_back" @click="changeedit(comment.comment_id)">
                            </div>
                          </div>
                          <div class="col-6 col-sm-6 col-me-6 col-lg-6">
                            <div class="icon-check"
                              @click="editcomment(comment.comment_id, comment.comment_val, comment.re_comment_id)">
                            </div>
                          </div>
                        </div>
                        <!-- 대댓글추가 인풋 -->
                        <div class="row col-sm-12 col-lg-12 " v-bind:id="'comment'+comment.comment_id"
                          style="display:none; margin:0; padding:0;">
                          <div class="col-sm-1 col-md-1 col-lg-1 "></div>
                          <div class="col-1 col-sm-1 col-md-1 col-lg-1">ㄴ</div>
                          <div class="col-2 col-sm-2 col-md-1 col-lg-2"
                            style="display:inline-block; font-weight:bold; font-size:1.2em; font-family: loveson; word-break:break-all; vertical-align:top;">
                            {{comment.user_id}}</div>
                          <!-- <div class="col-1"></div> -->
                          <div class="col-7 col-sm-7 col-md-8 col-lg-7"><input type="text"
                              class="text-black form-control" v-model="re_comment_val"
                              v-on:keyup.enter="insertReComment(comment.comment_id),recommentdisplay('comment'+comment.comment_id)" />
                          </div>
                          <div class="col-1 col-sm-1 col-md-1 col-lg-1">
                            <div class="icon-check"
                              @click="insertReComment(comment.comment_id),recommentdisplay('comment'+comment.comment_id)">
                            </div>
                          </div>
                        </div>
                      </div>
                      <!-- 댓글출력끝 -->
                      <!-- 대댓글출력 -->
                      <div class="row col-12 col-sm-12 col-lg-12" v-if="comment.comment_id != comment.re_comment_id"
                        style="margin-top:-30px; margin:0; padding:0">
                        <div class="col-1 col-sm-1 col-md-1 col-lg-1"></div>
                        <div class="col-1 col-sm-1 col-md-1 col-lg-1">ㄴ</div>
                        <div class="col-2 col-sm-2 col-md-1 col-lg-2" v-bind:id="comment.user_id"
                          style="display:inline-block; font-weight:bold; font-size:1.2em; font-family: loveson; word-break:break-all; vertical-align:top;">
                          {{comment.user_id}}</div>
                        <div class="comment-val col-6 col-sm-6 col-md-7 col-lg-6" v-bind:id="'view'+comment.comment_id"
                          style="display:inline-block; padding-left:5px; font-size:1.2em; font-family: loveson; word-break:break-all; vertical-align:bottom;">
                          {{comment.comment_val}}</div>
                        <div class="comment-val col-6 col-sm-6 col-md-7 col-lg-6" v-bind:id="'edit'+comment.comment_id"
                          style="display:none;">
                          <input type="text" class="text-black form-control" v-model="comment.comment_val"
                            v-on:keyup.enter="editcomment(comment.comment_id, comment.comment_val, comment.re_comment_id)" />
                        </div>
                        <div v-if="comment.check===true" class="row col-2 col-sm-2 col-md-2 col-lg-2"
                          style="display:flex;" v-bind:id="'view_button'+comment.comment_id">
                          <div class="col-1 col-sm-1 col-md-1 col-lg-1">
                            <div class="icon-pencil" @click="changeedit(comment.comment_id)">
                            </div>
                          </div>
                          <div class="col-1 col-sm-1 col-md-1 col-lg-1">
                            <div class="icon-close" @click="delcomment(comment.comment_id)">
                            </div>
                          </div>
                        </div>
                        <div class="row col-2 col-sm-2 col-md-2 col-lg-2" v-bind:id="'edit_button'+comment.comment_id"
                          style="display:none;">
                          <div class="col-1 col-sm-1 col-md-1 col-lg-1">
                            <div class="icon-arrow_back" @click="changeedit(comment.comment_id)">
                            </div>
                          </div>
                          <div class="col-1 col-sm-1 col-md-1 col-lg-1">
                            <div class="icon-pencil"
                              @click="editcomment(comment.comment_id, comment.comment_val, comment.re_comment_id)">
                            </div>
                          </div>
                        </div>
                      </div>
                    </div>
                  </div>
                  <!-- /댓글 -->
                </div>
                <div class="col-12 col-lg-6"
                  style="display:inline-block; vertical-align:middle; z-index:0; padding-top:15px; padding-bottom:15px;">
                  <div class="all-scroll pos-relative mt-50">
                    <div class="swiper-scrollbar"></div>
                    <div class="swiper-container oflow-visible" data-slide-effect="slide" data-autoheight="false"
                      data-swiper-wheel-control="true" data-swiper-speed="8000" data-swiper-margin="25"
                      data-swiper-slides-per-view="1" data-swiper-breakpoints="true" data-swiper-autoplay="true"
                      data-scrollbar="true" data-swiper-loop="true" data-swpr-responsive="[1, 2, 1, 2]">
                      <div class="swiper-wrapper">
                        <div class="swiper-slide" style="" v-for="img in items.imageList" :key="img.index">
                          <a :href="img.image_url" :class="img.filter" data-fancybox="gallery">
                            <div :class="img.filter" style="width:100%; height:100%;">
                              <img :src="img.image_url" style="width:100%; height:100%; margin-bottom:0px"
                                alt="Image" />
                            </div>
                          </a>
                        </div>
                      </div>
                    </div>
                  </div>
                  <div>
                    <span v-for="(tag, index) in items.hashtagList" :key="index"><input @click="contentListHashtag(tag)"
                        style="margin-left:5px; margin-right:5px;" type="button" class="btn btn-outline-info"
                        :value="'#'+tag" /></span>
                  </div>
                  <div v-if="items.location_name != undefined"
                    style="margin-top:20px; padding-left:20px; color:#007acc;">
                    <img style="width:20px; height:20px; margin-bottom:7px;" src="/theme/images/placeholder.png" />
                    <span style="cursor:pointer;" @click="findLocation"> {{items.location_name}}</span>
                  </div>
                  <div v-if="items.user_id == uid"
                    style="margin-top:20px; padding-left:20px; color:#007acc;">
                    <input type="button" @click="updateContent" class="bio-btn btn btn-outline-info btn-block" value="수정">
                    <input type="button" @click="deleteContent" class="bio-btn btn btn-outline-primary btn-block" value="삭제">
                    <!-- <span style="cursor:pointer;" @click="findLocation"> {{items.location_name}}</span> -->
                  </div>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
  import $ from "jquery"
  import http from '../http-common';
  import store from '../store'
  export default {
    props: ['cid'],
    data() {
      return {
        tmpconid: "0",
        bell: this.iconbell,
        contentId: "",
        items: {},
        uid: this.$store.state.user_id,
        isComment: false,
        comment_val: "",
        re_comment_val: "",
        comments: {},
        tempComments: [],
        editflg: false,
      }
    },
    methods: {
      changeedit(e) {
        var text = document.getElementById('view' + e);
        var input = document.getElementById('edit' + e);
        var text_button = document.getElementById("view_button" + e)
        var input_button = document.getElementById("edit_button" + e)


        if (text.style.display === "inline-block" && input_button.style.display === "none" && this.editflg === false) {
          text.style.display = "none"
          input.style.display = "inline-block"
          text_button.style.display = "none"
          input_button.style.display = "flex"
          this.editflg = true;
          this.getData();

        } else if (text.style.display === "none" && text_button.style.display === "none") {
          text.style.display = "inline-block"
          input.style.display = "none"
          text_button.style.display = "flex"
          input_button.style.display = "none"
          this.editflg = false;
          this.getData();
        }
      },
      editcomment(id, value, rid) {
        if (value === "") {
          alert("수정할댓글 내용을 입력하세요")
        } else {

          http
            .put("/comment/updateComment", {
              comment_id: id,
              content_id: 0,
              re_comment_id: rid,
              user_id: this.uid,
              target_id: "",
              comment_val: value,
            })
            .then(response => {
              if (response.data['resmsg'] == "수정성공") {
                this.changeedit(id);
                this.getData();
              } else {
                alert("댓글수정 실패!");
              }

            })
            .catch(() => {
              this.errored = true;
              alert("error");
            })
            .finally(() => (this.loading = false));
        }
      },
      delcomment(e) {
        http
          .delete("/comment/deleteComment/" + e)
          .then(response => {
            if (response.data['resmsg'] == "댓글 삭제성공") {
              this.getData();
            } else {
              alert("대댓글추가 실패!");
            }
          })
          .catch(() => {
            this.errored = true;
            alert("error");
          })
          .finally(() => (this.loading = false));

      },
      recommentdisplay(e) {
        var a = document.getElementById(e);
        if (a.style.display === "none" && this.editflg === false) {
          a.style.display = "";
          this.editflg = true;
          return;
        }
        if (a.style.display === "" && this.editflg === true) {
          a.style.display = "none"
          this.editflg = false;
          return;
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
      getData() {
        // 게시물출력
        http
          .get('/content/detail/' + this.cid)
          .then((res) => {
            if (res.data.resmsg == "게시물 출력 성공") {
              this.items = res.data.resValue;
              window.console.log(this.items);
              if (this.items.profile_filter == null) {
                this.items.profile_filter = "normal";
              }
              // this.items['content_val'] = this.items['content_val'].replace(/\r/g, "<br />");
              this.items['content_val'] = this.items['content_val'].replace(/\n/g, "<br />");
              window.console.log(this.items['location_name']);
            }
          });
        // 댓글출력
        http
          .get('/comment/commentsList/' + this.tmpconid)
          .then((res) => {
            if (res.data.resmsg == "댓글 출력성공") {
              this.comments = res.data.resvalue;
              for (var i = 0; i < this.comments.length; i++) {
                if (this.comments[i].user_id === this.uid) {
                  this.comments[i].check = true;
                } else {
                  this.comments[i].check = false;
                }
              }
              console.log(this.comments)
              if (this.comment[i] > 3) {
                this.isComment = true;
              } else {
                this.isComment = false;
              }
            } else {
              this.$store.commit('setModalText', "댓글 출력 실패");
              document.getElementById('modalBtn').click();
            }

          });

        if (this.comments.length != 0) {
          this.comments.some(comment => {
            if (this.tempComments.length >= 2) {
              return true;
            }
            this.tempComments.push(comment);
          })
        }
      },
      // fancy() {
      //   $.fancybox.open([{
      //       href: 'http://fancyapps.com/fancybox/demo/1_b.jpg',
      //       title: '1st title'
      //     },
      //     {
      //       href: 'http://fancyapps.com/fancybox/demo/2_b.jpg',
      //       title: '2nd title'
      //     },
      //     {
      //       href: 'http://fancyapps.com/fancybox/demo/3_b.jpg',
      //       title: '3rd title'
      //     }
      //   ], {
      //     padding: 0
      //   });

      //   return false;
      // },
      insertComment() {
        if (this.comment_val === "") {
          alert("댓글을 입력하세요")
        } else {
          http
            .post("comment/insertComment", {
              re_comment_id: "",
              content_id: this.contentId,
              user_id: this.$store.state.user_id,
              target_id: "",
              comment_val: this.comment_val,
            })
            .then(response => {
              if (response.data['resmsg'] == "댓글 추가 성공") {
                this.comment_val = "",
                  this.getData();

              } else {
                this.$store.commit('setModalText', "댓글 추가 실패");
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
      insertReComment(commentid) {
        http
          .post("comment/insertReComment", {
            comment_id: commentid,
            content_id: this.contentId,
            user_id: this.$store.state.user_id,
            target_id: "",
            comment_val: this.re_comment_val,
          })
          .then(response => {
            if (response.data['resmsg'] == "대댓글 추가 성공") {
              this.re_comment_val = "",
                this.getData();
            } else {
              this.$store.commit('setModalText', "대댓글 추가 실패");
              document.getElementById('modalBtn').click();
            }

          })
          .catch((error) => {
            this.errored = true;
            alert(error);
          })
          .finally(() => (this.loading = false));

      },

      findLocation() {
        window.console.log(this.items['lat']);
        this.$router.push({
          name: "findcontent",
          params: {
            location_name: this.items['location_name'],
            lat: this.items['lat'],
            lng: this.items['lng']
          }
        });
      },

      contentListHashtag(tag) {
        this.$router.push({
          name: "contentListHashtag",
          params: {
            tag: tag
          }
        });
      },
      deleteContent(){
        http
          .delete("content/deleteContent/" + this.items.content_id)
          .then(response => {
            if (response.data['resmsg'] == "게시물 삭제 성공") {
              this.$store.commit('setModalText', '게시물 삭제 성공');
              document.getElementById('modalBtn').click();
              this.$router.go(-1);
            } else {
              this.$store.commit('setModalText', "게시물 삭제 실패");
              document.getElementById('modalBtn').click();
            }

          })
          .catch((error) => {
            this.errored = true;
            alert(error);
          })
          .finally(() => (this.loading = false));
      },
      updateContent(){
        this.$router.push({
          name: 'updatecontent',
          params:{
            items: this.items,
            prevpage: "bio",
          }
        })
      }
    },
    created() {
      this.getData()
    },
    mounted() {
      $('html').scrollTop(0);
      this.uid = store.state.user_id;
    },
    updated() {
      let recaptchaScripta = document.createElement('script')
      recaptchaScripta.setAttribute('type', "text/javascript")
      recaptchaScripta.setAttribute('src', "./theme/js/script.js")
      document.body.appendChild(recaptchaScripta)
      let recaptchaScriptb = document.createElement('script')
      recaptchaScriptb.setAttribute('type', "text/javascript")
      recaptchaScriptb.setAttribute('src', "./theme/js/swiper.js")
      document.body.appendChild(recaptchaScriptb)
    }
  }
</script>

<style>
  .size {
    font-size: 2em;
  }
</style>

<style>
  .fancy-box-mask {
    position: fixed;
    left: 0;
    top: 0;
    width: 100%;
    height: 100%;
    background-color: #333;
    z-index: 22;
  }

  .fancy-box-image-wrapper {
    position: fixed;
    left: 0;
    top: 0;
    width: 100%;
    height: 100%;
    z-index: 33;
  }

  .fancy-box-image-wrapper div {
    position: absolute;
    left: 0;
    top: 0;
    width: 100%;
    height: 100%;
    transform-origin: left top 0px;
    transition: transform .3s cubic-bezier(0.4, 0, 0.22, 1);
  }

  .fancy-box-image-wrapper img {
    max-width: 100%;
    max-height: 100%;
  }

  .fancy-enter,
  .fancy-leave-active {
    opacity: 0;
  }

  .fancy-enter-active,
  .fancy-leave-active {
    transition: .2s;
  }

  .detail-mail-message {
    margin-top: 70px;
    margin-bottom: 70px;
    font-size: 23px;
  }

  @media screen and (max-width: 991.98px) {
    .detail-mail-message {
      margin-top: 30px;
      margin-bottom: 30px;
      /* font-size: 20; */
    }
  }

  .bio-btn{
    color:black;
  }
  .bio-btn:hover{
    color:white;
  }



  /* .comment-val {
    line-height: 1.3em;
  } */
</style>