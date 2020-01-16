<template>
  <div class="instagram-post">
    <div class="header level">
        <div class="level-left">
          <figure class="image is-32x32">
            <img :src="post.userImage" />
          </figure>
          <span class="username">{{post.username}}</span>
        </div>
    </div>
    <div class="image-container"
          :class="post.filter"
          :style="{ backgroundImage: 'url(' + post.postImage + ')' }"
          @dblclick="like">
    </div>
    <div class="content">
      <div class="heart">
        <i class="far fa-heart fa-lg"
          :class="{'fas': this.post.upVoted}"
          @click="like">
        </i>
      </div>
      <p class="likes">{{post.likes}} likes</p>
      <p class="caption"><span>{{post.username}}</span> {{post.caption}}</p>
    </div>
  </div>
</template>

<script>
export default {
  props: ["post"],
  methods: {
    like() {
      this.post.upVoted ? this.post.likes-- : this.post.likes++;
      this.post.upVoted = !this.post.upVoted;
    }
  }
};
</script>
<style scoped>
.is-32x32{
    width:32px;
    height:32px;
}
.image-container{
    width:800px;
    height:800px;
    background-repeat: no-repeat;
    background-size: cover;
}
</style>

