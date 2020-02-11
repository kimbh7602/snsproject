<template>
  <div class="container-fluid photos">
      <div class="row justify-content-center">

        <div class="col-md-8 pt-4 px-0">
            <div class="row mb-5" data-aos="fade-up">
                <div class="col-12">
                    <h2 class="text-white mb-4 text-center">Direct Message</h2>
                </div>
            </div>
            
            <div class="row">
                <div class="col-md-12" data-aos="fade-up">
                    <div class="row">
                        <!-- chatinglist -->
                        <div class="col-md-4 p-0 pr-3">
                            <div class="bg-danger d-flex justify-content-between align-items-center text-light rounded mb-1">
                                <h5 class="text-white p-3">List</h5>
                                <i v-if="check" @click="add" class="icon-plus m-3" style="font-size:1.5em;"></i>
                                <i v-else @click="add" class="icon-minus m-3" style="font-size:1.5em;"></i>
                            </div>
                            
                            <div class="list-group">
                              <virtual-list :size="80" :remain="8" v-if="check">
                                  <a v-for="(userDm, index) in fetchedUserDmList" :key="`userDm${index}`" :value="`userDm${index}`" @click="selectUserDm(userDm);" class="m-0 list-group-item list-group-item-action py-0">
                                      <div class="row pl-2">
                                          <div class="col-2 d-flex justify-content-center align-self-center">
                                            <img class="rounded-circle ml-2" width="50px" height="50px" style="object-fit: cover;" :src="userDm.user.profile_url || 'https://t1.daumcdn.net/qna/image/1542632018000000528'" alt="">
                                          </div>
                                          <div class="col-8">
                                              <div class="d-flex w-100 justify-content-between">
                                                  <p v-if="userId == userDm.user_id" class="mb-0 ml-2">{{ userDm.other_id }}</p>
                                                  <p v-else class="mb-0 ml-2">{{ userDm.user_id }}</p>
                                                  <small>{{ userDm.timestamp }}</small>
                                              </div>
                                              <div class="d-flex justify-content-between ml-2" style="position: relative; height:20px; overflow: hidden;">
                                                  <small v-if="userDm.recent_message != null" style="position: absolute; word-break:break-all;">{{ userDm.recent_message}}</small>
                                                  <span class="badge badge-primary badge-pill align-self-center" v-if="fetchedUnReadCnt.cnt > 0"> {{ userDm.cnt }}</span>
                                              </div>
                                          </div>
                                          <div class="col-1">
                                            <span id="deleteBtn" @click="targetDeleteDm = userDm" data-toggle="modal" data-target="#deleteUserDmModal"><i class="icon-delete" style="font-size: 1.2em;"></i></span>
                                          </div>
                                      </div>
                                  </a>
                              </virtual-list>
                              <virtual-list :size="70" :remain="8" v-else>
                                  <a v-for="(follow, index) in fetchedFollowList" :key="index" class="m-0 list-group-item list-group-item-action">
                                    <div class="row pl-2">
                                      <div class="col-md-2 d-flex justify-content-center p-0">
                                        <img class="rounded-circle ml-2" width="50px" height="50px" style="object-fit: cover;" :src="follow.profile_url || 'https://t1.daumcdn.net/qna/image/1542632018000000528'" alt=""> 
                                      </div>
                                      <div class="d-flex col-md-10 justify-content-between align-self-center">
                                        <p class="mb-0 ml-2">{{ follow.user_id }}</p>
                                        <button class="btn btn-sm btn-info" @click="insertUserDm(follow);">선택</button>
                                      </div>
                                    </div>
                                  </a>
                              </virtual-list>
                            </div>
                        </div>
                        <!-- chating -->
                        <div v-show="check2" class="card col-md-8 p-0">
                            <!-- header -->
                            <div class="card-header bg-light d-flex justify-content-between align-items-center" style="height: 60px;">
                                <h4 class="text-dark mt-1 mx-auto">{{ userDm.other_id }} </h4><span @click="close"><i class="icon-close" style="font-size:1.5em;"></i></span>
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
      <div class="modal" id="deleteUserDmModal" tabindex="-1" role="dialog">
          <div class="modal-dialog col-12" role="document">
              <div class="modal-content">
              <div class="modal-body">
                  <p>정말 나가시겠습니까?</p>
              </div>
              <div class="modal-footer p-2">
                  <button type="button" class="btn btn-secondary btn-sm" data-dismiss="modal">취소</button>
                  <button type="button" class="btn btn-primary btn-sm" @click="deleteUserDm(targetDeleteDm)" data-dismiss="modal">확인</button>
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
import http from "../http-common.js";

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
        targetDm: this.$store.state.targetDm,
        targetDeleteDm: {}
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
            recent_message: userDm.recent_message,
            user: userDm.user
        }
      }
      this.$store.dispatch('FETCH_DIRECTMESSAGELIST', this.userDm);
      // this.$joinRoom(this.userDm);
      this.check2 = true;

      http
        .put('/directMessage/readCheck', this.userDm)
        .then(response => {
          return response
        })
        .catch(e => console.log(e))
      return false;
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
        other_id: follow.user_id,
        recent_message: '',
        user: follow
      };
      this.fetchedUserDmList.push(userDm);
      // console.log(this.fetchedUserDmList, userDm);
      this.$store.dispatch('INSERT_USERDM', userDm);
      this.check = true;
      this.selectUserDm(userDm);
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
    deleteUserDm(userDm) {
      http
        .delete(`/userDm/deleteUserDm/${userDm.dm_id}`)
        .then(response => {
          return response
        })
        .catch(e => console.log(e))

      const idx = this.fetchedUserDmList.indexOf(userDm);
      if (idx > -1) this.fetchedUserDmList.splice(idx, 1);
    }
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
        if(data.receive_id == this.$store.state.user_id){
           http
            .put('/directMessage/readCheck', this.userDm)
            .then(response => {
              return response
            })
            .catch(e => console.log(e))
        }
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
      // console.log(this.targetDm)
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
#deleteUserDmModal .modal-dialog {
    margin-top: 130px;
    width: 25%;
    height: 50%;
}
#deleteUserDmModal .modal-content{
    height: auto;
    min-height: 30%;
}
#deleteBtn:hover {
    color: #F15F5F;
}
</style>