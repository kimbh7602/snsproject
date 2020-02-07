<template>
  <div class="container-fluid photos">
      <div class="row justify-content-center">

        <div class="col-md-8 pt-4">
            <div class="row mb-5" data-aos="fade-up">
                <div class="col-12">
                    <h2 class="text-white mb-4 text-center">Direct Message</h2>
                </div>
            </div>
            
            <div class="row">
                <div class="col-md-12" data-aos="fade-up">
                    <p class="mb-5"></p>              
                    <div class="row">
                        <!-- chatinglist -->
                        <div class="col-md-4">
                            <div class="bg-danger">
                                <h5 class="text-white p-3">List</h5>
                            </div>
                            
                            <div class="list-group">
                              <virtual-list :size="70" :remain="8" v-if="check">
                                  <a v-for="(userDm, index) in fetchedUserDmList" :key="`userDm${index}`" :value="`userDm${index}`" @click="selectUserDm(userDm);" class="m-0 list-group-item list-group-item-action">
                                      <div class="row pl-2">
                                          <div class="col-md-2 d-flex justify-content-center align-self-center">
                                              <h1>🦱</h1>
                                          </div>
                                          <div class="col-md-10">
                                              <div class="d-flex w-100 justify-content-between">
                                                  <p v-if="userId == userDm.user_id" class="mb-0 ml-2">{{ userDm.other_id }}</p>
                                                  <p v-else class="mb-0 ml-2">{{ userDm.user_id }}</p>
                                                  <small>{{ userDm.timestamp }}</small>
                                              </div>
                                              <div class="d-flex justify-content-between">
                                                  <small>{{ userDm.recent_message }}</small>
                                                  <span class="badge badge-primary badge-pill align-self-center" v-if="fetchedUnReadCnt.cnt > 0"> {{ userDm.cnt }}</span>
                                              </div>
                                          </div>
                                      </div>
                                  </a>
                              </virtual-list>
                              <virtual-list :size="70" :remain="8" v-else>
                                  <a v-for="(follow, index) in fetchedFollowList" :key="index" class="m-0 list-group-item list-group-item-action">
                                    <div class="row pl-2">
                                      <div class="col-md-2 d-flex justify-content-center ">
                                        <h1 class="m-0">🦱</h1>
                                      </div>
                                      <div class="d-flex col-md-10 justify-content-between align-self-center">
                                        <p class="mb-0 ml-2">{{ follow }}</p>
                                        <button class="btn btn-sm btn-info" @click="insertUserDm(follow);">선택</button>
                                      </div>
                                    </div>
                                  </a>
                              </virtual-list>
                              <h3 v-if="check" @click="add">➕</h3>
                              <h3 v-else @click="add">➖</h3>
                            </div>
                        </div>
                        <!-- chating -->
                        <div v-show="check2" class="card col-md-8 p-0">
                            <!-- header -->
                            <div class="card-header bg-light d-flex justify-content-between align-items-center" style="height: 60px;">
                                <h4 class="text-dark mt-1 mx-auto">{{ userDm.other_id }} </h4><span @click="close">✖</span>
                            </div>

                            <!-- body -->
                            <message-list :userDm="userDm" :fetchedDirectMessageList="fetchedDirectMessageList"></message-list>
                            <!-- footer -->
                            <message-form id="container" :userDm="userDm" @insertDirectMessage="insertDirectMessage"></message-form>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        
      </div>
  </div>
</template>

<script>
import virtualList from 'vue-virtual-scroll-list';
import MessageList from './MessageList.vue';
import MessageForm from './MessageForm.vue';
import { mapGetters, mapMutations } from 'vuex';
import io from 'socket.io-client';
// import http from "../http-common"

export default {
    
  components: {
    virtualList,
    MessageList,
    MessageForm
  },
  data() {
    return {
        temp: [],
        userId: this.$store.state.user_id,
        check: true,
        check2: false,
        userDm: {},
        socket: null,
        targetDm: this.$store.state.targetDm
    }
  },
  computed: {
    ...mapGetters([
      'fetchedUserDmList',
      'fetchedDirectMessageList',
      'fetchedFollowList',
      'fetchedUnReadCnt'
    ]),
  },
  methods: {
    ...mapMutations([
      'PUSH_MSG_DATA'
    ]),
    selectUserDm(userDm) {
      this.userDm = userDm;
      if (this.userId != userDm.user_id) {
        this.userDm = {
            dm_id: userDm.dm_id,
            user_id: userDm.other_id,
            other_id: userDm.user_id,
            recent_message: userDm.recent_message
        }
      }
      this.$store.dispatch('FETCH_DIRECTMESSAGELIST', this.userDm);
      // this.$joinRoom(this.userDm);
      this.check2 = true;
      return false;
      // this.computedChatings.forEach(chat => {
      //   if (chat.dm_id === chating.dm_id) {
      //     chat.cnt = 0;
      //   }
      // });
    },
    add() {
        this.check = !this.check;
    },
    close() {
        this.check2 = false;
    },
    insertUserDm(follow) {
      const userDm = {
        user_id: this.userId,
        other_id: follow,
      };
      this.fetchedUserDmList.push(userDm);
      // console.log(this.fetchedUserDmList, userDm);
      this.$store.dispatch('INSERT_USERDM', userDm);
    },
    insertDirectMessage(message) {
      // 소켓으로 메시지 전송
      // this.$sendMessage(message);

      this.socket.emit('chat', message);
      // window.console.log(message);

      this.$store.dispatch('INSERT_DIRECTMESSAGE', message);
      this.$store.dispatch('UPDATE_RECENTMESSAGE', message);

    },
    sendNotification(){
      // this.$socket.emit('notification', {
      //   user_id: 'kimbh',
      //   target_user_id: 'kimbh1',
      //   category: 'like'
      // });
    },
  },

  // beforeCreate() {
  //   this.$socket.emit('disconnectEvt', function(){});
  // },
  mounted() {
    this.userId = this.$store.state.user_id;

    // this.$socket.emit('disconnectEvt', function(){
    //   // this.$socket.disconnect(true);
    // });
    
    this.socket = io('http://192.168.100.41:3000');
    
    this.socket.emit('login', {
      user_id : this.userId
    });
    // this.$socket.socket = io('http://192.168.100.41:3000');
    // this.$login({
    //                   user_id : this.userId
    //                 });

    // http
    //   .get(`/userDm/allDmList`)
    //   .then(response => {
    //     this.$initRoom(response.data.resvalue);
    //   })
    //   .catch(e => console.log(e))

    // this.$store.dispatch('FETCH_ALLDMLIST');
    
    // window.console.log(this.$store.state.allDmList);
    // this.$initRoom(this.$store.state.allDmList);

    // 소켓에서 메시지 받음
    // const $ths = this;
    this.socket.on('chat', (data) => {
      if((this.userDm.user_id == data.send_id && this.userDm.other_id == data.receive_id) || (this.userDm.user_id == data.receive_id && this.userDm.other_id == data.send_id)){
        this.PUSH_MSG_DATA(data);
        window.console.log(data);
      }
      // $ths.datas.push(data);
      this.$store.commit('SET_RECENTMESSAGE', data);

      // this.$store.state.userDmList.forEach(element => {
      //   if(element.dm_id === data.dm_id){
      //     element.recent_message = data.message;
      //   }
      // })
      // this.$socket.disconnect();
    });

    // this.socket.on('notification', (data) => {
    //   // window.console.log('notification', data, this.$store.state.user_id);
    //   if(data.target_user_id == this.$store.state.user_id){
    //     this.$snotify.simple('알림을 확인해보세요!', 'Like!', {
    //         icon : '/favicon.ico',
    //         // html : '<div>Like!</div><div>알림을 확인해보세요!</div> <input type="button" @click="sendNotification" value="Login" class="btn btn-sm">'
    //       });
    //   }
    // });

    this.$store.dispatch('FETCH_FOLLOWLIST', this.userId);
    this.$store.dispatch('FETCH_USERDMLIST', this.userId);
  },
  created() {
    // 선택한 유저가 있을 때
    if (this.targetDm) {
      this.selectUserDm(this.targetDm);
      console.log(this.targetDm)
    }
  },
  beforeDestroy(){
    this.socket.emit('disconnectEvt', function(){});
    // this.$socket = io('http://192.168.100.41:3000');
    // this.$socket.on('notification', (data) => {
    //   // window.console.log('notification', data, this.$store.state.user_id);
    //   if(data.target_user_id == this.$store.state.user_id){
    //     this.$snotify.simple('알림을 확인해보세요!', 'Like!', {
    //         icon : '/favicon.ico',
    //         // html : '<div>Like!</div><div>알림을 확인해보세요!</div> <input type="button" @click="sendNotification" value="Login" class="btn btn-sm">'
    //       });
    //   }
    // });
  }
}
</script>

<style scoped>
    #container::-webkit-scrollbar {
        display: none !important;
    }
</style>