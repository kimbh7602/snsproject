<template>
    <div class="container-fluid photos">
      <div class="row justify-content-center">
        
        <div class="col-md-6 pt-4" v-for="item in items" :key="item.id">
          <figure class="mb-5" data-aos="fade-up">
            <!-- <img :src="'./theme/' + images" alt="Image" class="img-fluid"> -->
            <div class="all-scroll pos-relative mt-50">
              <div class="swiper-scrollbar"></div>
              <div class="swiper-container oflow-visible" data-slide-effect="coverflow" data-autoheight="false"  data-swiper-wheel-control="true"
                                        data-swiper-speed="1000" data-swiper-margin="25" data-swiper-slides-per-view="1"
                                        data-swiper-breakpoints="true" data-swiper-autoplay="false" data-scrollbar="true"
                                        data-swiper-loop="false" data-swpr-responsive="[1, 2, 1, 2]">
                <div class="swiper-wrapper">
                    <div class="swiper-slide" v-for="img in item.imageList" :key="img.index">
                        <div :class="img.filter">
                          <img :src="img.image_url" style="width:100%; height:300px" alt="Image"/>
                        </div>
                    </div>
                </div>
                <div class="swiper-pagination"  slot="pagination"></div>
                <div class="swiper-button-prev" slot="button-prev"></div>
                <div class="swiper-button-next" slot="button-next"></div>
              </div>
            </div>
          </figure>

          <div class="d-flex bd-highlight" data-aos="fade-up" data-aos-delay="100">
            <div class="p-2 w-100 bd-highlight" >
              <h2 class="text-white mb-4 mt-2">TEXT {{item.content_title}}</h2>
            </div>
            <div class="p-2 flex-shrink-1 bd-highlight size" v-on:click="clickBell()">
              <i class="icon-bell" v-if="bell"></i>
              <i class="icon-bell-o" v-else></i>
            </div>
          </div>

          <div class="row" data-aos="fade-up" data-aos-delay="200">
            <div class="col-md-12">
              <p>{{item.content_val}}</p>
              <p cass="mt-4">Thanks! <br>{{ item.user_id }} 님</p>
            </div>
          </div>
        </div>
        
      </div>
    </div>
</template>

<script>
import $ from "jquery"
import http from '../http-common';
export default {
  props: [
    'cid',
    // 'text',
    // 'images',
    // 'iconbell'
  ],
  data () {
    return {
      bell: this.iconbell,
      contentId: "",
      items: [],
    }
  },
  methods: {
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
      this.contentId = this.cid
      http
        .get('/content/detail/' + this.cid)
        .then((res) => {
          if (res.data.resmsg == "게시물 출력 성공") {
            // for (var i=0; i < res.data.urls.length; i++) {
            //   this.items.push({
            //     image_url: res.data.urls[i]
            //   })

            this.items.push({
              imageList: res.data.resValue.imageList,
              content_val: res.data.resValue.content_val,
              // content_title: res.data.content.content_title,
              user_id: res.data.resValue.user_id
            })
          }
        })
    }
  },
  created() {
    this.getData()
  },
  mounted() {
    $('html').scrollTop(0);
    
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
  .size {
    font-size: 2em;
  }
</style>