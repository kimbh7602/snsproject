<template>
    <div class="container-fluid photos">
      <div class="row align-items-stretch">
        
        <div class="col-6 col-md-6 col-lg-4" data-aos="fade-up">
          <router-link to="/single" class="d-block photo-item">
            <div>
            <img src="../../public/theme/images/img_4.jpg" alt="Image" class="img-fluid my-0">
              <!-- 이미지가 있으면 이미지와 함께, 없으면 글만. -->
              <div id="bg">
                <p id="text-color" class="mx-3 ellipsis">
                 {{message2}}
                </p>
              </div>
              <!-- <textarea data-aos="fade-up" name="" id="textarea" v-model="message"></textarea> -->
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
              <div id="bg">
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
              <div id="bg">
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
export default {
  data() {
    return {
      message: "안녕하세요",
      message2: "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.",
      m3: "세줄일기, 세줄일기, 세줄일기",
      m4: "hi"
    }
  },

  mounted() {
    let recaptchaScript = document.createElement('script')
    recaptchaScript.setAttribute('src', "./theme/js/jquery.fancybox.min.js")
    document.head.appendChild(recaptchaScript)
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