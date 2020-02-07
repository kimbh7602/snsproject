<template>
    <div class="container-fluid photos">

      <div class="row pt-4 mb-5 text-center">
        <div class="col-12">
          <h2 class="text-white mb-4">'Nature' &mdash; 19 Photos</h2>
        </div>
      </div>

      <!-- icons used in the project -->
<svg viewBox="0 0 100 100" style="display: none;">
    <!-- shopping cart -->
    <symbol id="store" viewBox="0 0 100 100" width="100" height="100">
            <g class="support">
                <path
                    stroke="currentColor"
                    stroke-width="5"
                    stroke-linejoin="round"
                    fill="none"
                    d="M 5 18.25 h 9 l 19 38 q -10 6 -5 12 h 45">
                </path>
            </g>
            <g
                class="cart"
                transform="translate(50 26.5)">
                <path
                    fill="currentColor"
                    stroke-linejoin="round"
                    stroke-linecap="round"
                    stroke="currentColor"
                    stroke-width="5"
                    d="M -32 0 h 64 l -15 30 h -34 l -15 -30">
                </path>
            </g>
            <g
                fill="currentColor"
                transform="translate(50 79.5)"
                class="wheels">
                <circle
                    cx="-15"
                    cy="0"
                    r="5">
                </circle>
                <circle
                    cx="15"
                    cy="0"
                    r="5">
                </circle>
            </g>
    </symbol>

    <!-- school hat -->
    <symbol id="school" viewBox="0 0 100 100" width="100" height="100">
        <g
            class="top"
            transform="translate(50 25)">
            <path
                fill="black"
                d="M 0 0 l 34 17 -34 17 -34 -17 z">
            </path>
            <path
                transform="translate(31.5 17)"
                fill="none"
                stroke="currentColor"
                stroke-width="5"
                d="M 0 0 v 20">
            </path>
        </g>
        <g
            class="base"
            transform="translate(50 65)">
            <path
                fill="black"
                d="M 0 0 l 20 -10 v 10 l -20 10 l -20 -10 v -10">
            </path>
        </g>
    </symbol>

    <!-- tree -->
    <symbol id="park" viewBox="0 0 100 100" width="100" height="100">
        <g
            class="top"
            transform="translate(50 20)">
            <path
                fill="black"
                d="M 0 0 l 12 14 h -6 l 14 16 h -8 l 16 22 h -56 l 16 -22 h -8 l 14 -16 h -6">
            </path>


        </g>
        <g
            class="trunk"
            transform="translate(50 72)">
            <rect
                fill="currentColor"
                x="-3"
                y="0"
                width="6"
                height="8">
            </rect>
        </g>
    </symbol>

    <!-- coffee mug, or tea cup depending on your interpretation -->
    <symbol id="bistro" viewBox="0 0 100 100" width="100" height="100">
        <g
            class="cup"
            transform="translate(50 28)">
            <path
                fill="black"
                d="M -20 0 h 38 v 30 a 8 8 0 0 1 -8 8 h -22 a 8 8 0 0 1 -8 -8">
            </path>
            <g
                class="handle"
                transform="translate(18 2.5)">
                <path
                    fill="none"
                    stroke="black"
                    stroke-linecap="square"
                    stroke-width="5"
                    d="M 0 0 h 4 a 4 4 0 0 1 4 4 v 6 a 4 4 0 0 1 -4 4 h -4">
                </path>
            </g>
        </g>
        <g
            class="coaster"
            transform="translate(50 72)">
            <rect
                fill="black"
                x="-20"
                y="0"
                width="40"
                height="4">
            </rect>
        </g>
    </symbol>

    <svg id="logout" height="80" viewBox="0 0 512.016 512" width="80" xmlns="http://www.w3.org/2000/svg">
    <path d="m496 240.007812h-202.667969c-8.832031 0-16-7.167968-16-16 0-8.832031 7.167969-16 16-16h202.667969c8.832031 0 16 7.167969 16 16 0 8.832032-7.167969 16-16 16zm0 0" />
    <path d="m416 320.007812c-4.097656 0-8.191406-1.558593-11.308594-4.691406-6.25-6.253906-6.25-16.386718 0-22.636718l68.695313-68.691407-68.695313-68.695312c-6.25-6.25-6.25-16.382813 0-22.632813 6.253906-6.253906 16.386719-6.253906 22.636719 0l80 80c6.25 6.25 6.25 16.382813 0 22.632813l-80 80c-3.136719 3.15625-7.230469 4.714843-11.328125 4.714843zm0 0" />
    <path d="m170.667969 512.007812c-4.566407 0-8.898438-.640624-13.226563-1.984374l-128.386718-42.773438c-17.46875-6.101562-29.054688-22.378906-29.054688-40.574219v-384c0-23.53125 19.136719-42.6679685 42.667969-42.6679685 4.5625 0 8.894531.6406255 13.226562 1.9843755l128.382813 42.773437c17.472656 6.101563 29.054687 22.378906 29.054687 40.574219v384c0 23.53125-19.132812 42.667968-42.664062 42.667968zm-128-480c-5.867188 0-10.667969 4.800782-10.667969 10.667969v384c0 4.542969 3.050781 8.765625 7.402344 10.28125l127.785156 42.582031c.917969.296876 2.113281.46875 3.480469.46875 5.867187 0 10.664062-4.800781 10.664062-10.667968v-384c0-4.542969-3.050781-8.765625-7.402343-10.28125l-127.785157-42.582032c-.917969-.296874-2.113281-.46875-3.476562-.46875zm0 0" />
    <path d="m325.332031 170.675781c-8.832031 0-16-7.167969-16-16v-96c0-14.699219-11.964843-26.667969-26.664062-26.667969h-240c-8.832031 0-16-7.167968-16-16 0-8.832031 7.167969-15.9999995 16-15.9999995h240c32.363281 0 58.664062 26.3046875 58.664062 58.6679685v96c0 8.832031-7.167969 16-16 16zm0 0" />
    <path d="m282.667969 448.007812h-85.335938c-8.832031 0-16-7.167968-16-16 0-8.832031 7.167969-16 16-16h85.335938c14.699219 0 26.664062-11.96875 26.664062-26.667968v-96c0-8.832032 7.167969-16 16-16s16 7.167968 16 16v96c0 32.363281-26.300781 58.667968-58.664062 58.667968zm0 0" />
</svg>


<svg id="write" height="70" viewBox="0 -1 401.52289 401" width="70" xmlns="http://www.w3.org/2000/svg"><path d="m370.589844 250.972656c-5.523438 0-10 4.476563-10 10v88.789063c-.019532 16.5625-13.4375 29.984375-30 30h-280.589844c-16.5625-.015625-29.980469-13.4375-30-30v-260.589844c.019531-16.558594 13.4375-29.980469 30-30h88.789062c5.523438 0 10-4.476563 10-10 0-5.519531-4.476562-10-10-10h-88.789062c-27.601562.03125-49.96875 22.398437-50 50v260.59375c.03125 27.601563 22.398438 49.96875 50 50h280.589844c27.601562-.03125 49.96875-22.398437 50-50v-88.792969c0-5.523437-4.476563-10-10-10zm0 0"/><path d="m376.628906 13.441406c-17.574218-17.574218-46.066406-17.574218-63.640625 0l-178.40625 178.40625c-1.222656 1.222656-2.105469 2.738282-2.566406 4.402344l-23.460937 84.699219c-.964844 3.472656.015624 7.191406 2.5625 9.742187 2.550781 2.546875 6.269531 3.527344 9.742187 2.566406l84.699219-23.464843c1.664062-.460938 3.179687-1.34375 4.402344-2.566407l178.402343-178.410156c17.546875-17.585937 17.546875-46.054687 0-63.640625zm-220.257812 184.90625 146.011718-146.015625 47.089844 47.089844-146.015625 146.015625zm-9.40625 18.875 37.621094 37.625-52.039063 14.417969zm227.257812-142.546875-10.605468 10.605469-47.09375-47.09375 10.609374-10.605469c9.761719-9.761719 25.589844-9.761719 35.351563 0l11.738281 11.734375c9.746094 9.773438 9.746094 25.589844 0 35.359375zm0 0"/></svg>

    
    <!-- tooltip outline -->
    <symbol id="tooltip" viewBox="0 0 100 116" width="100" height="116">
        <path
            fill="currentColor"
            d="m100 50c0 13.807-5.5964 26.307-14.645 35.355s-35.355 30.645-35.355 30.645-26.307-21.596-35.355-30.645c-9.0482-9.0482-14.645-21.548-14.645-35.355 0-27.614 22.386-50 50-50s50 22.386 50 50z">
        </path>
    </symbol>

</svg>


<!-- in a wrapping container include the anchor link elements with the SVG icons and the explanatory text
! the svg icons use the defined symbol to create tooltips matching the specific link
-->
<main>
    <a href="#" style="color: #ff0000;">
        <!-- svg structure (for each tooltip)
        use, describing the tooltip
        svg, describing the nested icon
            use, describing the actual icon
        -->
        <svg
            viewBox="0 0 100 116"
            width="100"
            height="116">
            <use href="#tooltip"></use>
            <!-- scale down the icon and use a specific hue -->
            <svg
                color="#0e4637"
                x="26"
                y="26"
                viewBox="0 0 100 100"
                width="70"
                height="70">
                <use href="#logout"></use>
            </svg>
        </svg>
        <span>Logout</span>
    </a>
    

    <a href="#" style="color: #9cbcc9;">
        <svg viewBox="0 0 100 116" width="100" height="116">
            <use href="#tooltip"></use>
            <svg color="#0e4637" x="26" y="26" viewBox="0 0 100 100" width="70" height="70">
                <use href="#write"></use>
            </svg>
        </svg>
        <span>Write</span>
    </a>

    <a href="#" style="color: #e7c9d5;">
        <svg viewBox="0 0 100 116" width="100" height="116">
            <use href="#tooltip"></use>
            <svg color="#0e4637" x="15" y="15" viewBox="0 0 100 100" width="70" height="70">
                <use href="#school"></use>
            </svg>
        </svg>
        <span>School</span>
    </a>

    <a href="#" style="color: #3cb066;">
        <svg viewBox="0 0 100 116" width="100" height="116">
            <use href="#tooltip"></use>
            <svg color="#0e4637" x="15" y="15" viewBox="0 0 100 100" width="70" height="70">
                <use href="#park"></use>
            </svg>
        </svg>
        <span>Park</span>
    </a>
</main>

      <div class="row align-items-stretch">
        
        <div class="col-6 col-md-6 col-lg-3" data-aos="fade-up">
          <a href="./theme/images/img_1.jpg" class="d-block photo-item" data-fancybox="gallery">
            <img src="../../public/theme/images/img_1.jpg" alt="Image" class="img-fluid">
            <div class="photo-text-more">
              <span class="icon icon-search"></span>
            </div>
          </a>
        </div>
        <div class="col-6 col-md-6 col-lg-6" data-aos="fade-up" data-aos-delay="100">
          <a href="./theme/images/img_2.jpg" class="d-block photo-item" data-fancybox="gallery">
            <img src="../../public/theme/images/img_2.jpg" alt="Image" class="img-fluid">
            <div class="photo-text-more">
              <span class="icon icon-search"></span>
            </div>
          </a>
        </div>
        <div class="col-6 col-md-6 col-lg-3" data-aos="fade-up" data-aos-delay="200">
          <a href="./theme/images/img_3.jpg" class="d-block photo-item" data-fancybox="gallery">
            <img src="../../public/theme/images/img_3.jpg" alt="Image" class="img-fluid">
            <div class="photo-text-more">
              <span class="icon icon-search"></span>
            </div>
          </a>
        </div>

        <div class="col-6 col-md-6 col-lg-8" data-aos="fade-up">
          <a href="./theme/images/img_4.jpg" class="d-block photo-item" data-fancybox="gallery">
            <img src="../../public/theme/images/img_4.jpg" alt="Image" class="img-fluid">
            <div class="photo-text-more">
              <span class="icon icon-search"></span>
            </div>
          </a>
        </div>
        <div class="col-6 col-md-6 col-lg-4" data-aos="fade-up" data-aos-delay="100">
          <a href="./theme/images/img_5.jpg" class="d-block photo-item" data-fancybox="gallery">
            <img src="../../public/theme/images/img_5.jpg" alt="Image" class="img-fluid">
            <div class="photo-text-more">
              <span class="icon icon-search"></span>
            </div>
          </a>
        </div>

        <div class="col-6 col-md-6 col-lg-6" data-aos="fade-up">
          <a href="./theme/images/img_6.jpg" class="d-block photo-item" data-fancybox="gallery">
            <img src="../../public/theme/images/img_6.jpg" alt="Image" class="img-fluid">
            <div class="photo-text-more">
              <span class="icon icon-search"></span>
            </div>
          </a>
        </div>
        <div class="col-6 col-md-6 col-lg-6" data-aos="fade-up" data-aos-delay="100">
          <a href="./theme/images/img_7.jpg" class="d-block photo-item" data-fancybox="gallery">
            <img src="../../public/theme/images/img_7.jpg" alt="Image" class="img-fluid">
            <div class="photo-text-more">
              <span class="icon icon-search"></span>
            </div>
          </a>
        </div>


        <div class="col-6 col-md-6 col-lg-4" data-aos="fade-up">
          <a href="./theme/images/img_8.jpg" class="d-block photo-item" data-fancybox="gallery">
            <img src="../../public/theme/images/img_8.jpg" alt="Image" class="img-fluid">
            <div class="photo-text-more">
              <span class="icon icon-search"></span>
            </div>
          </a>
        </div>
        <div class="col-6 col-md-6 col-lg-4" data-aos="fade-up" data-aos-delay="100">
          <a href="./theme/images/img_9.jpg" class="d-block photo-item" data-fancybox="gallery">
            <img src="../../public/theme/images/img_9.jpg" alt="Image" class="img-fluid">
            <div class="photo-text-more">
              <span class="icon icon-search"></span>
            </div>
          </a>
        </div>
        <div class="col-6 col-md-6 col-lg-4" data-aos="fade-up" data-aos-delay="200">
          <a href="./theme/images/img_10.jpg" class="d-block photo-item" data-fancybox="gallery">
            <img src="../../public/theme/images/img_10.jpg" alt="Image" class="img-fluid">
            <div class="photo-text-more">
              <span class="icon icon-search"></span>
            </div>
          </a>
        </div>


        <div class="col-6 col-md-6 col-lg-3" data-aos="fade-up">
          <a href="./theme/images/img_1.jpg" class="d-block photo-item" data-fancybox="gallery">
            <img src="../../public/theme/images/img_1.jpg" alt="Image" class="img-fluid">
            <div class="photo-text-more">
              <span class="icon icon-search"></span>
            </div>
          </a>
        </div>
        <div class="col-6 col-md-6 col-lg-6" data-aos="fade-up" data-aos-delay="100">
          <a href="./theme/images/img_2.jpg" class="d-block photo-item" data-fancybox="gallery">
            <img src="../../public/theme/images/img_2.jpg" alt="Image" class="img-fluid">
            <div class="photo-text-more">
              <span class="icon icon-search"></span>
            </div>
          </a>
        </div>
        <div class="col-6 col-md-6 col-lg-3" data-aos="fade-up" data-aos-delay="200">
          <a href="./theme/images/img_3.jpg" class="d-block photo-item" data-fancybox="gallery">
            <img src="../../public/theme/images/img_3.jpg" alt="Image" class="img-fluid">
            <div class="photo-text-more">
              <span class="icon icon-search"></span>
            </div>
          </a>
        </div>

        <div class="col-6 col-md-6 col-lg-8" data-aos="fade-up">
          <a href="./theme/images/img_4.jpg" class="d-block photo-item" data-fancybox="gallery">
            <img src="../../public/theme/images/img_4.jpg" alt="Image" class="img-fluid">
            <div class="photo-text-more">
              <span class="icon icon-search"></span>
            </div>
          </a>
        </div>
        <div class="col-6 col-md-6 col-lg-4" data-aos="fade-up" data-aos-delay="100">
          <a href="./theme/images/img_5.jpg" class="d-block photo-item" data-fancybox="gallery">
            <img src="../../public/theme/images/img_5.jpg" alt="Image" class="img-fluid">
            <div class="photo-text-more">
              <span class="icon icon-search"></span>
            </div>
          </a>
        </div>

        <div class="col-6 col-md-6 col-lg-6" data-aos="fade-up">
          <a href="./theme/images/img_6.jpg" class="d-block photo-item" data-fancybox="gallery">
            <img src="../../public/theme/images/img_6.jpg" alt="Image" class="img-fluid">
            <div class="photo-text-more">
              <span class="icon icon-search"></span>
            </div>
          </a>
        </div>
        <div class="col-6 col-md-6 col-lg-6" data-aos="fade-up" data-aos-delay="100">
          <a href="./theme/images/img_7.jpg" class="d-block photo-item" data-fancybox="gallery">
            <img src="../../public/theme/images/img_7.jpg" alt="Image" class="img-fluid">
            <div class="photo-text-more">
              <span class="icon icon-search"></span>
            </div>
          </a>
        </div>


        <div class="col-6 col-md-6 col-lg-4" data-aos="fade-up">
          <a href="./theme/images/img_8.jpg" class="d-block photo-item" data-fancybox="gallery">
            <img src="../../public/theme/images/img_8.jpg" alt="Image" class="img-fluid">
            <div class="photo-text-more">
              <span class="icon icon-search"></span>
            </div>
          </a>
        </div>
        <div class="col-6 col-md-6 col-lg-8" data-aos="fade-up" data-aos-delay="100">
          <a href="./theme/images/img_9.jpg" class="d-block photo-item" data-fancybox="gallery">
            <img src="../../public/theme/images/img_9.jpg" alt="Image" class="img-fluid">
            <div class="photo-text-more">
              <span class="icon icon-search"></span>
            </div>
          </a>
        </div>
      </div>
    </div>
</template>

<script>
import $ from "jquery"
export default {
  mounted(){
    $('html').scrollTop(0);
  }
}
</script>

<style>
@import url("https://fonts.googleapis.com/css?family=Source+Sans+Pro:700&display=swap");


/* position the icons in the center of the viewport, in a wrapping row */
main {
  min-height: 100vh;
  display: flex;
  align-items: center;
  justify-content: center;
  flex-wrap: wrap;
}

/* position relative to have the svg icons laid on top of the anchor links */
a {
  position: relative;
  padding: 0.25rem;
  font-size: 1.1rem;
  font-weight: 700;
  margin: 1rem;
  text-decoration: none;
}
/* position the icons atop the parent anchor link elements */
a svg {
  width: 62px;
  height: auto;
  position: absolute;
  bottom: 100%;
  left: 50%;
  /* hide the icons by default */
  transform: translate(-50%, 0) scale(0);
  opacity: 0;
  visibility: none;
  /* transition for the change on hover/focus */
  transition: all 0.2s ease-out;
  transform-origin: 50% 100%;
}
/* on hover/focus scale the icons to visibility */
a:hover svg,
a:focus svg {
  transform: translate(-50%, 0) scale(1);
  visibility: visible;
  opacity: 1;
}
/* delay the nested icon to have the image connected to the anchor link follow the tooltip */
a:hover svg svg,
a:focus svg svg {
  transition-delay: 0.2s;
}
</style>
