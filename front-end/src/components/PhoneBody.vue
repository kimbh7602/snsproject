<template>
  <div class="phone-body">
    <div class="selected-image"
          :class="filterType"
          :style="{ backgroundImage: 'url(' + image + ')' }"></div>
      <div class="all-scroll pos-relative mt-50" style="display:none;">
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
    <div class="feed" v-if="step === 1">
    
    </div>
    <div v-if="step === 2">
      <div class="selected-image"
          :class="filterType"
          :style="{ backgroundImage: 'url(' + image + ')' }"></div>
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
      <!-- <div class="selected-image"
          :class="filterType"
          :style="{ backgroundImage: 'url(' + image + ')' }"></div>
      <div class="filter-container">
        <filter-type v-for="filter in filters"
                    :filter="filter"
                    :image="image"
                    :key="filter.name">
        </filter-type>
      </div> -->


    </div>

    <!-- <a download="temp" :href="image">다운로드으으으으으으</a> -->
    <div v-if="step === 3">
      <div class="selected-image"
          :class="filterType"
          :style="{ backgroundImage: 'url(' + image + ')' }"></div>
      <div class="caption-container">
        <textarea class="caption-input"
                  placeholder="Write a caption..."
                  type="text"
                  :value="value" @input="$emit('input', $event.target.value)">
        </textarea>
      </div>
    </div>
  </div>
</template>

<script>
// import InstagramPost from "./InstagramPost";
import FilterType from "./FilterType";

export default {
  name: "PhoneBody",
  props: ["step", "image", "posts", "filters", "filterType", "value"],
  components: {
    // "instagram-post": InstagramPost,
    "filter-type": FilterType
  },
};
</script>

<style scoped>
.selected-image{
    height:700px;
    background-size: cover;
}
</style>