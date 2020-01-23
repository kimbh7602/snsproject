<template>
    <div class="container-fluid photos">
      <div class="row justify-content-center">
        
        <div class="col-md-6 pt-4">
          <figure class="mb-5" data-aos="fade-up">
            <img :src="'./theme/' + images" alt="Image" class="img-fluid">
          </figure>

          <div class="d-flex bd-highlight" data-aos="fade-up" data-aos-delay="100">
            <div class="p-2 w-100 bd-highlight" >
              <h2 class="text-white mb-4 mt-2">TEXT</h2>
            </div>
            <div class="p-2 flex-shrink-1 bd-highlight size" v-on:click="clickBell()">
              <i class="icon-bell" v-if="bell"></i>
              <i class="icon-bell-o" v-else></i>
            </div>
          </div>

          <div class="row" data-aos="fade-up" data-aos-delay="200">
            <div class="col-md-12">
              <p>{{text}}</p>
              <p cass="mt-4">Thanks! <br> Rhea M.</p>
            </div>
          </div>
        </div>
        
      </div>
    </div>
</template>

<script>
export default {
  props: [
    'text',
    'images',
    'iconbell'
  ],
  data () {
    return {
      bell: this.iconbell,
      
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
    }
  },
  mounted() {
    var scrollUpDelay = 1;
    var scrollUpSpeed = 30;
    if(document.body.scrollTop<1)
    {
      return;
    }
    document.body.scrollTop=document.body.scrollTop-scrollUpSpeed;
    setTimeout('scrollUp()',scrollUpDelay);
  }
}
</script>

<style>
  .size {
    font-size: 2em;
  }
</style>