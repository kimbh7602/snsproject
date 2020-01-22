<template>
    <div class="container-fluid photos">
      <div class="row align-items-stretch">

        <div class="col-6 col-md-6 col-lg-4" data-aos="fade-up">
          <div class="d-block photo-item">
            <img src="../../public/theme/images/img_4.jpg" alt="Image" class="img-fluid my-0">
              <div class="photo-text-more">
                <h3 class="heading mx-2 ellipsis" v-on:click="goDetail()">{{message2}}</h3>
                <span class="meta">
                  <div class="mb-3 my-3 d-flex justify-content-around size">
                    <div v-on:click="clickHeart()">
                      <i class="icon-heart" v-if="like"></i>
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
                </span>
              </div>
          </div>
        </div>
        <!-- post end -->


        <div class="col-6 col-md-6 col-lg-4" data-aos="fade-up" data-aos-delay="100">
          <router-link to="/single" class="d-block photo-item">
          <div>
            <img src="../../public/theme/images/img_5.jpg" alt="Image" class="img-fluid my-0">
              <div id="bg">
                <p id="text-color" class="mx-3 ellipsis">
                 {{m3}}
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
        <div class="col-6 col-md-6 col-lg-4" data-aos="fade-up" data-aos-delay="200">
          <router-link to="/single" class="d-block photo-item">
          <div>
            <img src="../../public/theme/images/img_1.jpg" alt="Image" class="img-fluid my-0">
              <div id="bg" style="background-color:#00695C;">
                <p id="text-color" class="mx-3 ellipsis">
                 {{m3}}
                </p>
              </div>
            </div>
            <div class="photo-text-more">
              <h3 class="heading">Photos Title Here</h3>
              <span class="meta">42 Photos</span>
            </div>
          </router-link>
        </div>

        <div class="col-6 col-md-6 col-lg-4" data-aos="fade-up">
          <router-link to="/single" class="d-block photo-item">
          <div>
            <img src="../../public/theme/images/img_2.jpg" alt="Image" class="img-fluid my-0">
              <div id="bg" style="background-color:#0D47A1">
                <p id="text-color" class="mx-3 ellipsis">
                 {{m4}}
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
        <div class="col-6 col-md-6 col-lg-4" data-aos="fade-up" data-aos-delay="100">
          <router-link to="/single" class="d-block photo-item">
          <div>
            <img src="../../public/theme/images/img_3.jpg" alt="Image" class="img-fluid my-0">
              <div id="bg" style="background-color:#FF8800">
                <p id="text-color" class="mx-3 ellipsis">
                 {{message2}}
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
        <div class="col-6 col-md-6 col-lg-4" data-aos="fade-up" data-aos-delay="200">
          <router-link to="/single" class="d-block photo-item">
          <div>
            <img src="../../public/theme/images/img_6.jpg" alt="Image" class="img-fluid my-0">
              <div id="bg">
                <p id="text-color" class="mx-3 ellipsis">
                 {{message2}}
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

        <div class="col-6 col-md-6 col-lg-4" data-aos="fade-up">
          <router-link to="/single" class="d-block photo-item">
          <div>
            <img src="../../public/theme/images/img_7.jpg" alt="Image" class="img-fluid my-0">
              <div id="bg">
                <p id="text-color" class="mx-3 ellipsis">
                 {{message2}}
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
        <div class="col-6 col-md-6 col-lg-4" data-aos="fade-up" data-aos-delay="100">
          <router-link to="/single" class="d-block photo-item">
          <div>
            <img src="../../public/theme/images/img_8.jpg" alt="Image" class="img-fluid my-0">
              <div id="bg">
                <p id="text-color" class="mx-3 ellipsis">
                 {{message2}}
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
        <div class="col-6 col-md-6 col-lg-4" data-aos="fade-up" data-aos-delay="200">
          <router-link to="/single" class="d-block photo-item">
          <div>
            <img src="../../public/theme/images/img_9.jpg" alt="Image" class="img-fluid my-0">
              <div id="bg">
                <p id="text-color" class="mx-3 ellipsis">
                 {{message2}}
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


        <div class="col-6 col-md-6 col-lg-4" data-aos="fade-up">
          <router-link to="/single" class="d-block photo-item">
          <div>
            <img src="../../public/theme/images/img_10.jpg" alt="Image" class="img-fluid my-0">
              <div id="bg">
                <p id="text-color" class="mx-3 ellipsis">
                 {{message2}}
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
        <div class="col-6 col-md-6 col-lg-4" data-aos="fade-up" data-aos-delay="100">
          <router-link to="/single" class="d-block photo-item">
          <div>
            <img src="../../public/theme/images/img_1.jpg" alt="Image" class="img-fluid my-0">
              <div id="bg">
                <p id="text-color" class="mx-3 ellipsis">
                 {{message2}}
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
        <div class="col-6 col-md-6 col-lg-4" data-aos="fade-up" data-aos-delay="200">
          <router-link to="/single" class="d-block photo-item">
          <div>
            <img src="../../public/theme/images/img_2.jpg" alt="Image" class="img-fluid my-0">
              <div id="bg">
                <p id="text-color" class="mx-3 ellipsis">
                 {{message2}}
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

        <div class="col-6 col-md-6 col-lg-4" data-aos="fade-up">
          <router-link to="/single" class="d-block photo-item">
          <div>
            <img src="../../public/theme/images/img_3.jpg" alt="Image" class="img-fluid my-0">
              <div id="bg">
                <p id="text-color" class="mx-3 ellipsis">
                 {{message2}}
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
        <div class="col-6 col-md-6 col-lg-4" data-aos="fade-up" data-aos-delay="100">
          <router-link to="/single" class="d-block photo-item">
          <div>
            <img src="../../public/theme/images/img_4.jpg" alt="Image" class="img-fluid my-0">
              <div id="bg">
                <p id="text-color" class="mx-3 ellipsis">
                 {{message2}}
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
        <div class="col-6 col-md-6 col-lg-4" data-aos="fade-up" data-aos-delay="200">
          <router-link to="/single" class="d-block photo-item">
          <div>
            <img src="../../public/theme/images/img_5.jpg" alt="Image" class="img-fluid my-0">
              <div id="bg">
                <p id="text-color" class="mx-3 ellipsis">
                 {{message2}}
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

        <div class="col-6 col-md-6 col-lg-4" data-aos="fade-up">
          <router-link to="/single" class="d-block photo-item">
          <div>
            <img src="../../public/theme/images/img_6.jpg" alt="Image" class="img-fluid my-0">
              <div id="bg">
                <p id="text-color" class="mx-3 ellipsis">
                 {{message2}}
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
        <div class="col-6 col-md-6 col-lg-4" data-aos="fade-up" data-aos-delay="100">
          <router-link to="/single" class="d-block photo-item">
          <div>
            <img src="../../public/theme/images/img_7.jpg" alt="Image" class="img-fluid my-0">
              <div id="bg">
                <p id="text-color" class="mx-3 ellipsis">
                 {{message2}}
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
        <div class="col-6 col-md-6 col-lg-4" data-aos="fade-up" data-aos-delay="200">
          <router-link to="/single" class="d-block photo-item">
          <div>
            <img src="../../public/theme/images/img_8.jpg" alt="Image" class="img-fluid my-0">
              <div id="bg">
                <p id="text-color" class="mx-3 ellipsis">
                 {{message2}}
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

        <div class="col-6 col-md-6 col-lg-4" data-aos="fade-up" data-aos-delay="100">
          <router-link to="/single" class="d-block photo-item">
          <div>
            <img src="../../public/theme/images/img_9.jpg" alt="Image" class="img-fluid my-0">
              <div id="bg">
                <p id="text-color" class="mx-3 ellipsis">
                 {{message2}}
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
        <div class="col-6 col-md-6 col-lg-4" data-aos="fade-up" data-aos-delay="100">
          <router-link to="/single" class="d-block photo-item">
          <div>
            <img src="../../public/theme/images/img_4.jpg" alt="Image" class="img-fluid my-0">
              <div id="bg">
                <p id="text-color" class="mx-3 ellipsis">
                 {{message2}}
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
        <div class="col-6 col-md-6 col-lg-4" data-aos="fade-up" data-aos-delay="200">
          <router-link to="/single" class="d-block photo-item">
          <div>
            <img src="../../public/theme/images/img_5.jpg" alt="Image" class="img-fluid my-0">
              <div id="bg">
                <p id="text-color" class="mx-3 ellipsis">
                 {{message2}}
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
  data() {
    return {
      message: "안녕하세요",
      message2: "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.",
      m3: "세줄일기, 세줄일기, 세줄일기",
      m4: "hi",
      image: "images/img_4.jpg",
      like : false,
      follow: false,
      bell: false,
      getImageUrls: [],
      errored: false,
    }
  },
  methods: {
    getData: function() {
      const DATA_URL = "더미데이터가 담겨있는 url"
      axios.get(DATA_URL)
        .then((response)=>{
          this.getImageUrls = response.data.urls;
          this.getImageFilters = response.data;
          this.contentIds = response.data;
          this.contentTitles = response.data;
          this.contentValues = response.data;
          // 이런 식으로 해보고 경로가 다를 시에는 예를 들어 getImageList: function() 이렇게 하나 더 만들던가
          // 그리고 image_url과 filter는 딕셔너리 형태로 묶여있으면 좋겠음.
          // 예) [{'image_url': 'filter'}, {}, {}, ...]
          // console.log(this.getImages)
        })
        .catch(()=>{
          this.errored = true;
          // console.log(error)
        })
    },
    goDetail() {
      this.$router.push({
        name: 'bio', 
        params: {
          text: this.message2, 
          images: this.image, 
          iconbell: this.bell,
        }
      })
    },
    clickHeart() {
      if (this.like === true) {
        this.like = false;
      } else {
        this.like = true;
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
    }
  },
  mounted() {
    let recaptchaScript = document.createElement('script')
    recaptchaScript.setAttribute('src', "./theme/js/jquery.fancybox.min.js")
    document.head.appendChild(recaptchaScript)
    // let recaptchaScripta = document.createElement('script')
    // recaptchaScripta.setAttribute('src', "./theme/common/scripts.js")
    // document.head.appendChild(recaptchaScripta)
    var scrollUpDelay = 1;
    var scrollUpSpeed = 30;
    if(document.body.scrollTop<1)
    {
      return;
    }
    document.body.scrollTop=document.body.scrollTop-scrollUpSpeed;
    setTimeout('scrollUp()',scrollUpDelay);
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
  .size {
    font-size: 2em;
  }
</style>