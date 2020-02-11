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
        <div style="margin-top:1%; margin-left:5%;margin-right:5%; height:50px;">
          <div v-if="prevpage=='addimage'" class="col-4 col-md-4 col-lg-4" style="display:inline-block;">
            <input type="button" value="이전" @click="goPrev" class="btn btn-primary btn-md text-white">
          </div>
          <div v-else class="col-4 col-md-4 col-lg-4" style="display:inline-block;">
            <input type="button" value="이전" @click="goReg" class="btn btn-primary btn-md text-white">
          </div>
          <div v-if="prevpage=='addimage'" class="col-4 col-md-4 col-lg-4" style="display:inline-block; text-align:center;">
            <input type="button" value="추가" @click="goAddImage" class="btn btn-info btn-md text-white">
          </div>
          <div v-if="prevpage=='addimage'" class="col-4 col-md-4 col-lg-4" style="display:inline-block; text-align:right;">
            <input type="button" value="다음" @click="goNext" class="btn btn-success btn-md text-white">
          </div>
           <div v-else-if="prevpage=='useredit'" class="offset-4 col-4 col-md-4 col-lg-4" style="display:inline-block; text-align:right;">
            <input type="button" value="다음" @click="goNextEdit" class="btn btn-success btn-md text-white">
          </div>
           <div v-else-if="prevpage=='contentupdate'" class="offset-4 col-4 col-md-4 col-lg-4" style="display:inline-block; text-align:right;">
            <input type="button" value="다음" @click="goNextUpdate" class="btn btn-success btn-md text-white">
          </div>
          <div v-else class="offset-4 col-4 col-md-4 col-lg-4" style="display:inline-block; text-align:right;">
            <input type="button" value="다음" @click="goNextReg" class="btn btn-success btn-md text-white">
          </div>
        </div>
    </div>
</div>
</template>



<script>
import $ from "jquery"
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
      this.$router.push({
        name: 'addimage', 
        params: {
          fimgs: this.imgs, 
        }
      });
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
            console.log(this.items)
      $('html').scrollTop(0);
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