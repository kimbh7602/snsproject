<template>
<div class="col-md-12">
    <div class="offset-md-1 col-md-10">
    <div style="height:10px;"></div>
    <div :class="filterType" id="img-select" class="img-fluid"
          :style="{ backgroundImage: 'url(' + imgs[imgs.length-1].base64 + ')' }"></div>
      <div class="all-scroll pos-relative mt-50">
          <h5 class="mb-50"><b>Filters</b></h5>                                            
          <div class="swiper-scrollbar"></div>
          <div class="swiper-container oflow-visible" data-slide-effect="coverflow" data-autoheight="false" 
                                    data-swiper-speed="200" data-swiper-margin="25" data-swiper-slides-per-view="3"
                                    data-swiper-breakpoints="true" data-scrollbar="true" data-swiper-loop="true"
                                    data-swpr-responsive="[1, 2, 1, 2]">
              <div class="swiper-wrapper" style="width:50px;">
                  <div class="swiper-slide" v-for="filter in filters" :key="filter.name" style="height:300px;" @click="selectFilter(filter.name)">
                    <p class="text-white">{{filter.name}}</p>
                    <div id="ex" :class="filter.name" :style="{ backgroundImage: 'url(' + imgs[imgs.length-1].base64 + ')' }"></div>
                </div>
            </div>
        </div>
      </div>
        <div style="margin-top:1%; margin-left:5%;margin-right:5%; height:50px;">
          <div class="col-4 col-md-4 col-lg-4" style="display:inline-block;">
            <input type="button" value="이전" @click="goPrev" class="btn btn-primary btn-md text-white">
          </div>
          <div class="col-4 col-md-4 col-lg-4" style="display:inline-block; text-align:center;">
            <input type="button" value="추가" @click="goAddImage" class="btn btn-info btn-md text-white">
          </div>
          <div class="col-4 col-md-4 col-lg-4" style="display:inline-block; text-align:right;">
            <input type="button" value="다음" @click="goNext" class="btn btn-success btn-md text-white">
          </div>
        </div>
    </div>
</div>
</template>



<script>
export default {
  name: "ImageFilter",
  props: ["imgs"],
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
  },
  created() {
    
  },
  mounted() {
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
#img-select{
  margin-bottom:0px;
  border:5px solid white;
  height:700px;
  background-size:cover;
  background-position:center center;
  background-repeat: no-repeat;
}
#ex{
  height:100%;
  background-size:cover;
}
/* .selected-image{
    margin:5%; margin-bottom:0px; border:5px solid white; height:700px;
} */
</style>