<template>
  <div class="offset-md-2 col-md-8" data-aos="fade-up">
    <div class="offset-md-1 col-md-10">
    <div style="height:10px;"></div>
    <div class="selected-image" style="margin-bottom:0px; border:5px solid white;" @dragover.prevent @dragenter.prevent @drop.prevent="dragupload" v-on:change="fileUpload">
      <div style="height:35%"></div>
      <div @click="$refs.fileInput.click()" style="margin:auto; width:20%; height:35%; background-size:contain; background-repeat:no-repeat; background-image:url('./theme/images/plus.png')">
      </div>
      <!-- <span>이미지를 drag&drop하거나 +를 클릭하여 추가해주세요.</span> -->
    </div>
    <div style="margin-top:1%;margin-left:5%;margin-right:5%; height:50px;">
      <div style="display:inline-block; float :left">
        <input type="button" value="취소" @click="goPrev" class="btn btn-primary btn-md text-white">
      </div>
      <div style="display:inline-block; float:right">
        <input type="button" value="다음" @click="goNext" class="btn btn-success btn-md text-white">
      </div>
    </div>
    <input ref="fileInput" type="file" accept="image/*" style="display:none;" name="file" id="file" class="inputfile" @dragover.prevent @dragenter.prevent @drop.prevent="dragupload" v-on:change="fileUpload"/>
    </div>
  </div>
</template>

<script>
import $ from "jquery"
export default {
  name: "App",
  props:["fimgs"],
  data() {
    return {
      step: 1,
      filterType: "",
      image: "",
      imginfo:{
        base64:"",
        filter:""
      },
      imgs:[],
      caption: "",
      first:true,
      defaultImag:{base64:"",filter:"normal"},
    };
  },
  created() {
    // EventBus.$on("selectFilter", evt => {
    //   this.filterType = evt.filter;
    // });
  },
  methods: {
    fileUpload(e) {
      const files = e.target.files || e.dataTransfer.files;
      if (!files.length) return;
      this.image = files[0];
      this.createImage();
    },
    dragupload(e){
      const files = e.target.files || e.dataTransfer.files;
      if (!files.length) return;
      this.image = files[0];
      this.createImage();
    },
    createImage() {
      const reader = new FileReader();
      reader.readAsDataURL(this.image);
      reader.onload = e => {
        this.image = e.target.result;
        this.imginfo.base64 = this.image;
        this.imgs.push(this.imginfo);
        this.step = 2;
        // EventBus.$emit("imglink", { image: this.image });
        this.$router.push({
          name: 'imagefilter', 
          params: {
            imgs: this.imgs, 
            step: this.step+1,
            prevpage: "addimage",
          }
        })
      };
    },
    goToHome() {
      this.image = "";
      this.caption = "";
      this.filterType = "normal";
      this.step = 1;
    },
    goPrev() {
      this.$router.go(-1);
    },
    goNext() {
      if(this.imgs.length==0){
        this.imgs.push(this.defaultImag);
      }
      this.$router.push({
          name: 'writecontent', 
          params: {
            imgs: this.imgs, 
          }
        })
    },
  },
  mounted(){
    // console.log(this.fimgs);
    if(this.fimgs!=undefined){
      this.imgs = this.fimgs;
      this.first = false;
    }
    $('html').scrollTop(0);
  }
};
</script>

<style scoped>
.selected-image{
    height:500px;
    background-size: cover;
}
</style>