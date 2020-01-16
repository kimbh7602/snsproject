<template>
    <div class="app__phone">
			<div class="phone-header">
				<!-- <img src="https://s3-us-west-2.amazonaws.com/s.cdpn.io/1211695/vue_gram_logo_cp.png" /> -->
				<a class="cancel-cta" v-if="step === 2 || step === 3" @click="goToHome">Cancel</a>
				<a class="next-cta" v-if="step === 2" @click="step++">Next</a>
				<a class="next-cta" v-if="step === 3" @click="sharePost">Share</a>
			</div>
      <phone-body
        :step="step"
        :image="image"
        :posts="posts"
        :filters="filters"
        :filterType="filterType"
        v-model="caption" />
			<div class="phone-footer">
				<div class="home-cta" @click="goToHome">
					<i class="fas fa-home fa-lg"></i>
				</div>
				<div class="upload-cta">
					<input type="file"
								name="file"
								id="file"
								class="inputfile"
                v-on:change="fileUpload"
								:disabled="step !== 1"/>
					<label for="file">
						<i class="icon-plus"></i>
					</label>
				</div>
			</div>
		</div>
</template>

<script>
import PhoneBody from "./PhoneBody.vue";
import EventBus from "../event-bus.js";

import posts from "../data/posts.js";
import filters from "../data/filters.js";

export default {
  name: "App",
  data() {
    return {
      step: 1,
      posts,
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
      };
    },
    goToHome() {
      this.image = "";
      this.caption = "";
      this.filterType = "normal";
      this.step = 1;
    },
    sharePost() {
      const post = {
        username: "codepen",
        userImage:
          "https://s3-us-west-2.amazonaws.com/s.cdpn.io/1211695/codepen_logo.png",
        postImage: this.image,
        likes: 0,
        caption: this.caption,
        filter: this.filterType
      };

      this.posts.unshift(post);
      this.goToHome();
    }
  },
  components: {
    "phone-body": PhoneBody
  }
};
</script>