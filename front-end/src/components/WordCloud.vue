<template>
  <div id="app">
      <wordcloud
      :data="defaultWords"
      nameKey="name"
      valueKey="value"
      :fontSize=[25,100]
      color="Category10"
      :showTooltip="true"
      :wordClick="wordClickHandler">
      </wordcloud>
  </div>
</template>

<script>
import wordcloud from 'vue-wordcloud'
import http from '../http-common';

export default {
  name: 'wordcloud',
  components: {
    wordcloud
  },
  methods: {
    wordClickHandler(name, value, vm) {
      console.log('wordClickHandler', name, value, vm);
    }
  },
  data() {
    return {
      myColors: ['#1f77b4', '#629fc9', '#94bedb', '#c9e0ef'],
      defaultWords: [{
          "name": "Cat",
          "value": 26
        },
        {
          "name": "fish",
          "value": 19
        },
        {
          "name": "look",
          "value": 16
        },
        {
          "name": "two",
          "value": 15
        },
        {
          "name": "fun",
          "value": 9
        },
        {
          "name": "know",
          "value": 9
        },
        {
          "name": "good",
          "value": 9
        },
        {
          "name": "things",
          "value": 18
        },
        {
          "name": "play",
          "value": 6
        }
      ]
    }
  },

  mounted() {
      http.get("/user/wordCloud")
      .then((response) => {
          window.console.log(response.data['resValue']);
          this.defaultWords = response.data.resValue;
      })
      .catch((error) => {
          window.console.log(error);
      })
  }
}
</script>