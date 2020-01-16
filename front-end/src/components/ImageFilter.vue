<template>
<div>
    <!-- <div class="selected-image"
          :class="filterType"
          :style="{ backgroundImage: 'url(' + image + ')' }"></div> -->
      <div class="all-scroll pos-relative mt-50">
          <h5 class="mb-50"><b>List</b></h5>                                            
          <div class="swiper-scrollbar"></div>
          <div class="swiper-container oflow-visible" data-slide-effect="coverflow" data-autoheight="false" 
                                    data-swiper-speed="200" data-swiper-margin="25" data-swiper-slides-per-view="3"
                                    data-swiper-breakpoints="true" data-scrollbar="true" data-swiper-loop="true"
                                    data-swpr-responsive="[1, 2, 1, 2]">
              <div class="swiper-wrapper" style="width:50px;">
                <!-- <div class="filter-container"> -->
                  <filter-type class="swiper-slide" v-for="filter in filters" :filter="filter" :image="image" :key="filter.name">
                      <!-- <p> swiper {{filter.name}} </p>
                      <div class="pos-relative img" :class="item.name" :style="{ backgroundImage: 'url(' + image + ')' }" @click="selectFilter">
                      </div> -->
                  </filter-type>
                <!-- </div>                      -->
              </div>
          </div>
        </div>    
</div>
</template>



<script>
import FilterType from "./FilterType";
import EventBus from "../event-bus.js";

export default {
  name: "PhoneBody",
  props: ["image", "value"],
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
    { name: "nashville" }]
      }
  },
  components: {
    "filter-type": FilterType
  },
  created() {
    EventBus.$on("selectFilter", evt => {
      this.filterType = evt.filter;
    });
  },
  mounted() {
        let recaptchaScript = document.createElement('script')
        recaptchaScript.setAttribute('type',"text/javascript")
        recaptchaScript.setAttribute('src', "./theme/js/swiper.js")
        document.body.appendChild(recaptchaScript)
        let recaptchaScripta = document.createElement('script')
        recaptchaScript.setAttribute('type',"text/javascript")
        recaptchaScripta.setAttribute('src', "./theme/js/scripts.js")
        document.body.appendChild(recaptchaScripta)
  },
};
</script>

<style>
.selected-image{
    margin:5%; margin-bottom:0px; border:5px solid white;
}
</style>