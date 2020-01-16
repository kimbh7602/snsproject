<template>
  <div class="container-fluid photo">
    <div style="height:10px;"></div>
    <div class="selected-image" style="margin-bottom:0px; border:5px solid white;">
      <div style="height:35%"></div>
      <div @click="$refs.fileInput.click()" style="margin:auto; width:20%; height:35%; background-size:contain; background-repeat:no-repeat; background-image:url('./theme/images/plus.png')">
      </div>
    </div>
    <div style="margin-top:1%;margin-left:5%;margin-right:5%;">
      <div style="display:inline-block; float :left">
        <input type="button" value="취소" @click="goPrev" class="btn btn-primary btn-md text-white">
      </div>
      <div style="display:inline-block; float:right">
        <input type="button" value="다음" @click="goNext" class="btn btn-success btn-md text-white">
      </div>
    </div>
    <input ref="fileInput" type="file" style="display:none;" name="file" id="file" class="inputfile" @drop.prevent="dragupload" @dragover.prevent @dragenter.prevent v-on:change="fileUpload"/>
	</div>
</template>

<script>
export default {
  name: "App",
  // props:["imgs"],
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
      caption: ""
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
      let droppedfiles = e.dataTransfer.files;
      if(!droppedfiles) return;
      ([...droppedfiles]).forEach(f=>{
        this.files.push(f);
      });
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
      this.$router.push({
          name: 'writecontent', 
          params: {
            imgs: this.imgs, 
          }
        })
    },
  },
};
</script>

<style scoped>
.selected-image{
    height:700px;
    background-size: cover;
}
</style>