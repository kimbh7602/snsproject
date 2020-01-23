<template>
  <div id="app">
    <Sidebar v-if="loginCheck" />
    <LoginSide v-else />
    <main class="main-content">
      <router-view></router-view>
      <Footer />
    </main>
    <!-- modal -->
    <p id="modalBtn" style="display:none;" data-toggle="modal" data-target="#myModal"></p>
    <div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
      <div class="modal-dialog" role="document">
        <div class="modal-content">
          <div class="modal-body" style="text-align:center;">
            {{$store.state.modalText}}
          </div>
          <div class="modal-footer">
            <button type="button" class="btn btn-danger text-white" data-dismiss="modal">닫기</button>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import Sidebar from "@/components/Sidebar.vue"
import LoginSide from "@/components/LoginSide.vue"
import Footer from "@/components/Footer.vue"
import store from "@/store.js"
export default {
  name: 'app',
  // data(){
  //   return{
  //     logincheck:false,
  //   }
  // },
  components: {
    LoginSide,
    Sidebar,
    Footer,
  },
  data(){
    return{
      lCheck:false,
    }
  },
  computed: {
    loginCheck: () => {
        return store.state.islogin;
    },
  },
}
</script>