<template>
  <div id="app">
      <wordcloud
      :data="defaultWords"
      nameKey="name"
      valueKey="value"
      :fontSize=[25,100]
      color="Category10"
      :showTooltip="true"
      >
      </wordcloud>
  </div>
</template>

<script>
import wordcloud from 'vue-wordcloud'
import http from '../http-common';

export default {
  name: 'wordcloudVue',
  components: {
    wordcloud
  },
  methods: {
    // wordClickHandler(name, value, vm) {
    //   console.log('wordClickHandler', name, value, vm);
    // }
  },
  data() {
    return {
      myColors: ['#1f77b4', '#629fc9', '#94bedb', '#c9e0ef'],
      defaultWords: []
    }
  },

  mounted() {
      http.get("/user/wordCloud")
      .then((response) => {
          // window.console.log(response.data['resValue']);
          this.defaultWords = response.data.resValue;
      })
      .catch((error) => {
          window.console.log(error);
      })
  }
}
</script>