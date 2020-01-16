<template>
  <div class="filter-type">
    <p>{{filter.name}}</p>
    <div class="img"
          :class="filter.name"
          :style="{ backgroundImage: 'url(' + image + ')' }"
          @click="selectFilter">
    </div> 
  </div>
</template>

<script>
import EventBus from "../event-bus.js";

export default {
  props: ["filter", "image"],
  methods: {
    selectFilter() {
      EventBus.$emit("selectFilter", { filter: this.filter.name });
    }
  }
};
</script>

<style scoped>
.img{
    height:300px;
}
</style>
