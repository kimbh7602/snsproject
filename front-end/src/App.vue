<template>
  <div id="app">
    <Sidebar />
    <main class="main-content">
      <router-view></router-view>
      <Footer />
    </main>
  </div>
</template>

<script>
import Sidebar from "@/components/Sidebar.vue"
import Footer from "@/components/Footer.vue"
export default {
  name: 'app',
  components: {
    Sidebar,
    Footer,
  },

}
</script>