<template>
  <div class="container-fluid photos">
    <div class="row align-items-stretch">

      <div v-for="images in getImages" :key="images.id" class="col-6 col-md-6 col-lg-4" data-aos="fade-up" data-aos-delay="100">
        <router-link to="/single" class="d-block photo-item">
          <div>
            <img :src="images" alt="Image" class="img-fluid my-0">
            <div id="bg">
              <p id="text-color" class="mx-3 ellipsis">
                image출력
              </p>
            </div>
          </div>
          <div class="photo-text-more">
            <div class="photo-text-more">
              <h3 class="heading">Photos Title Here</h3>
              <span class="meta">42 Photos</span>
            </div>
          </div>
        </router-link>
      </div>

    </div>
  </div>
</template>

<script>
import axios from 'axios'
export default {
  data: function() {
    // data() {}도 가능!
    return {
     getImages: [],
    }
  },
  methods: {
    GetImageUrl: function() {
      const IMG_URL = "http://192.168.100.41:8080/api/content/urls/1"
      axios.get(IMG_URL)
        .then((response)=>{
          this.getImages = response.data.urls;
          // console.log(this.getImages)
        })
        .catch((error)=>{
          alert(error)
        })
    }
  },
  mounted() {
    let recaptchaScript = document.createElement('script')
    recaptchaScript.setAttribute('src', "./theme/js/jquery.fancybox.min.js")
    document.head.appendChild(recaptchaScript)
    this.GetImageUrl()
    // let recaptchaScripta = document.createElement('script')
    // recaptchaScripta.setAttribute('src', "./theme/common/scripts.js")
    // document.head.appendChild(recaptchaScripta)
  },
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
</style>