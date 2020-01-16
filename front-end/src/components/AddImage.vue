<template>
  <div>
    <input ref="fileInput" type="file" style="display:none;" name="file" id="file" class="inputfile" v-on:change="fileUpload"/>
    <div class="selected-image" style="margin:5%; margin-bottom:0px; border:5px solid white;">
      <div style="height:35%"></div>
      <div @click="$refs.fileInput.click()" style="margin:auto; width:30%; height:35%; background-size:contain; background-repeat:no-repeat; background-image:url('./theme/images/plus.png')">
      </div>
    </div>
    <div style="margin-left:5%;margin-right:5%;">
      <div style="display:inline-block; float :left">
        <input type="button" value="취소" @click="goPrev" class="btn btn-primary btn-md text-white">
      </div>
      <div style="display:inline-block; float:right">
        <input type="button" value="추가" @click="$refs.fileInput.click()" class="btn btn-success btn-md text-white">
      </div>
    </div>
	</div>
</template>

<script>
import EventBus from "../event-bus.js";
import filters from "../data/filters.js";

export default {
  name: "App",
  data() {
    return {
      step: 1,
      filters,
      filterType: "",
      image: "",
      caption: ""
    };
  },
  created() {
    EventBus.$on("selectFilter", evt => {
      this.filterType = evt.filter;
    });
  },
  methods: {
    fileUpload(e) {
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
        this.step = 2;
        EventBus.$emit("imglink", { image: this.image });
        this.$router.push("/imagefilter");
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
  },
};
</script>

<style scoped>
.selected-image{
    height:700px;
    background-size: cover;
}
</style>