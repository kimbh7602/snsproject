<template>
  <div class="container-fluid photos">
    <div class="row justify-content-center">

      <div class="col-md-6 pt-4" data-aos="fade-up">
        <h2 class="text-white mb-4">PwConfirm</h2>


        <div class="row">
          <div class="col-md-12">
            <p class="mb-5">Lorem ipsum dolor sit amet, consectetur <a href="#">adipisicing</a> elit.</p>
            <div class="row">
              <div class="col-md-12">
                <form action="" method="post" @submit.prevent="confirm">
                  <div style="display:none">
                    <input type="submit" onclick="return false;" />
                    <input type="text" />
                  </div>

                  <!-- password -->
                  <div class="row form-group">
                    <div class="col-md-12">
                      <label class="text-white" for="upw">현재 패스워드</label>
                      <input type="password" v-model="upw" id="upw" class="form-control" v-on:keyup.enter="submit()">
                    </div>
                  </div>

                  <!-- confirm/reset button -->
                  <div class="row form-group">
                    <div class="col-md-12">
                      <input type="submit" value="확인" class="offset-md-3 col-md-2 btn btn-primary btn-md text-white">
                      <input type="button" value="취소" @click="cancle"
                        class="offset-md-3 col-md-2 btn btn-danger btn-md text-white">
                    </div>
                  </div>
                </form>
              </div>

            </div>
          </div>
        </div>
      </div>

    </div>
  </div>
</template>


<script>
  // import EventBus from "../event-bus"
  import http from "../http-common"
  import $ from "jquery"
  export default {
    name: "confirm",
     props: ["oldpw"],
    data() {
      return {
        uid: this.$store.state.user_id,
        upw: "",
        utel: "",
        uemail: "",
        uniqnum: "",
        uintr: "",
        itrlist: [],
        uitrlist: [],
        intro: ""
      }
    },
    methods: {
      cancle() {
        this.$router.push("/");
      },
      confirm() {
        if (this.upw === "") {
          this.$store.commit('setModalText', "비밀번호를 입력하세요");
          document.getElementById('modalBtn').click();
        } else {
          http
            .get("/user/pwconfirm/" + this.upw + "," + this.uid)
            .then(response => {
              if (response.data['resmsg'] == "비밀번호확인 성공") {
                this.$router.push({
                  name:'useredit',
                  params:{
                    oldpw:this.upw,
                    prevpage: "confirm",
                  }
                });
              } else {
                this.$store.commit('setModalText', "비밀번호를 확인하세요");
                document.getElementById('modalBtn').click();
                this.$router.push("/");
              }
            })
            .catch((error) => {
              this.errored = true;
              alert(error);
            })
            .finally(() => (this.loading = false));

        }
      }
    },
    mounted(){
      $('html').scrollTop(0);
    }
  }
</script>