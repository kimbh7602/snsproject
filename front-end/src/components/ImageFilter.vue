<template>
  <div class="offset-3 col-6" data-aos="fade-up">
    <div class="col-12">
      <div :class="filterType" id="img-select" class="img-fluid" style="text-align:center">
            <img :src = imgs[imgs.length-1].base64 class="img-fluid">
      </div>
      <div class="all-scroll pos-relative mt-50">
          <h5 class="mb-50"><b>Filters</b></h5>                                            
          <div class="swiper-scrollbar"></div>
          <div class="swiper-container oflow-visible" data-slide-effect="coverflow" data-autoheight="false" data-wheel-control="true"
                                    data-swiper-speed="200" data-swiper-margin="25" data-swiper-slides-per-view="3"
                                    data-swiper-breakpoints="true" data-scrollbar="true" data-swiper-loop="true"
                                    data-swpr-responsive="[1, 2, 1, 2]">
              <div class="swiper-wrapper">
                  <div class="swiper-slide" v-for="filter in filters" :key="filter.name" @click="selectFilter(filter.name)">
                    <p class="text-white">{{filter.name}}</p>
                    <div :class="filter.name" class="img-fluid">
                      <img :src = imgs[imgs.length-1].base64 class="img-fluid">
                    </div>
                </div>
            </div>
        </div>
      </div>

      <div class="container col-md-12 px-0">
        <div class="btn-group col-12 px-0" role="group" aria-label="Basic example">
          <button type="button" class="btn btn-outline-light col-sm btnprev p-2" v-if="prevpage == 'addimage'" @click="goPrev">이전</button>
          <button type="button" class="btn btn-outline-light col-sm btnprev p-2" v-else @click="goReg">이전</button>
          <button type="button" class="btn btn-outline-light col-sm btnadd p-2" v-if="prevpage == 'addimage'" @click="goAddImage">추가</button>
          <button type="button" class="btn btn-outline-light col-sm btnnext p-2" v-if="prevpage == 'addimage'" @click="goNext">다음</button>
          <button type="button" class="btn btn-outline-light col-sm btnprev p-2" v-else-if="prevpage == 'useredit'" @click="goNextEdit">다음</button>
          <button type="button" class="btn btn-outline-light col-sm btnprev p-2" v-else-if="prevpage=='contentupdate'" @click="goNextUpdate">다음</button>
          <button type="button" class="btn btn-outline-light col-sm btnprev p-2" v-else @click="goNextReg">다음</button>
        </div>
      </div>
      
    </div>
</div>
</template>



<script>
import $ from "jquery"
import http from "../http-common"

export default {
  name: "ImageFilter",
  props: ["imgs","prevpage","oldpw","items"],
  data(){
      return{
        filters:[{ name: "normal" },
          { name: "clarendon" },
          { name: "gingham" },
          { name: "moon" },
          { name: "lark" },
          { name: "reyes" },
          { name: "juno" },
          { name: "slumber" },
          { name: "aden" },
          { name: "perpetua" },
          { name: "mayfair" },
          { name: "rise" },
          { name: "hudson" },
          { name: "valencia" },
          { name: "xpro2" },
          { name: "willow" },
          { name: "lofi" },
          { name: "inkwell" },
          { name: "nashville" }],
        filterType:"normal",
      }
  },
  methods: {
    selectFilter(filtername) {
      this.filterType = filtername;
    },
    goPrev() {
      this.image = "";
      this.caption = "";
      this.filterType = "normal";
      this.step = 1;
      this.$router.push('/addimage');
    },
    goReg() {
      this.image = "";
      this.caption = "";
      this.filterType = "normal";
      this.step = 1;
      this.$router.go(-1);
    },
    goAddImage() {
      this.imgs[this.imgs.length-1].filter = this.filterType;
      
      http
        .post(`/content/tempImage`, {
          user_id: this.$store.state.user_id,
          base64: this.imgs[this.imgs.length-1].base64,
          filter: this.imgs[this.imgs.length-1].filter
        })
        .then((res) => {
          window.console.log(res.data.resValue);
          // const img_url = res.data.resValue;
          // axios.post("http://192.168.100.41:5000/tag", {
          //           img_url: img_url
          //       })
          //       .then((res) => {
          //           window.console.log(res.data);
          //           this.$router.push({
          //             name: 'addimage', 
          //             params: {
          //               fimgs: this.imgs, 
          //             }
          //           });
          //       })
          this.$router.push({
            name: 'addimage', 
            params: {
              fimgs: this.imgs, 
            }
          });
        })

      // this.$router.push({
      //   name: 'addimage', 
      //   params: {
      //     fimgs: this.imgs, 
      //   }
      // });
    },
    goNext() {
      this.imgs[this.imgs.length-1].filter = this.filterType;
      this.$router.push({
        name: 'writecontent', 
        params: {
          imgs: this.imgs, 
        }
      });
    },
    goNextUpdate() {
      window.console.log(this.prevpage)
      this.imgs[this.imgs.length-1].filter = this.filterType;
      this.$router.push({
        name: 'updatecontent', 
        params: {
          uimgs: this.imgs, 
          items: this.items,
          prevpage: this.prevpage,
        }
      });
    },
    goNextReg() {
      this.imgs[this.imgs.length-1].filter = this.filterType;
      this.$router.push({
        name: 'register', 
        params: {
          imgs: this.imgs, 
          filter : this.filterType,
          profilebase64: this.imgs[this.imgs.length-1].base64,
        }
      });
    },
    goNextEdit() {
      this.imgs[this.imgs.length-1].filter = this.filterType;
      this.$router.push({
        name: 'useredit', 
        params: {
          oldpw: this.oldpw,
          imgs: this.imgs, 
          filter : this.filterType,
          profilebase64: this.imgs[this.imgs.length-1].base64,
        }
      });
    },
  },
  mounted() {
      $('html').scrollTop(0);
      document.querySelector('script[src$="script.js"]').remove()
      document.querySelector('script[src$="swiper.js"]').remove()
      let recaptchaScripta = document.createElement('script')
      recaptchaScripta.setAttribute('type',"text/javascript")
      recaptchaScripta.setAttribute('src', "./theme/js/script.js")
      document.body.appendChild(recaptchaScripta)
      let recaptchaScript = document.createElement('script')
      recaptchaScript.setAttribute('type',"text/javascript")
      recaptchaScript.setAttribute('src', "./theme/js/swiper.js")
      document.body.appendChild(recaptchaScript)
  },
};
</script>

<style>
.normal img{
  width:100%;
  z-index:1;
}
.juno img{
  width:100%;
  z-index: 1;
}
</style>

<style scoped>
.btnprev:hover {
  background-color: #fff;
  border-color: #fff;
  color: #333;
} 
.btnadd:hover {
  background-color: #fff;
  border-color: #fff;
  color: #333;
} 
.btnnext:hover {
  background-color: #fff;
  border-color: #fff;
  color: #333;
} 
</style>