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
                        <div class="col-md-4 p-0 pr-2">
                            <div class="bg-danger d-flex justify-content-between align-items-center text-light rounded mb-1">
                                <i v-if="check" @click="add" class="icon-plus m-3" style="font-size:1.5em;"></i>
                                <i v-else @click="add" class="icon-minus m-3" style="font-size:1.5em;"></i>
                            </div>
                            
                            <div class="list-group">
                              <virtual-list :size="80" :remain="8" v-if="check" class="bg-white">
                                  <a v-for="(userDm, index) in fetchedUserDmList" :key="`userDm${index}`" :value="`userDm${index}`" @click="selectUserDm(userDm);" class="m-0 list-group-item list-group-item-action py-0">
                                      <div class="row pl-2">
                                          <div class="col-2 d-flex justify-content-center align-self-center">
                                            <img class="rounded-circle ml-2" width="50px" height="50px" style="object-fit: cover;" :src="userDm.user.profile_url || 'https://t1.daumcdn.net/qna/image/1542632018000000528'" alt="">
                                          </div>
                                          <div class="col-8">
                                              <div class="w-100 d-flex">
                                                  <p v-if="userId == userDm.user_id" class="mb-0 mx-2">{{ userDm.other_id }}</p>
                                                  <p v-else class="mb-0 mx-2">{{ userDm.user_id }}</p>
                                                  <span class="badge badge-primary badge-pill align-self-center" v-text="userDm.cnt"></span>
                                                  <!-- <small>{{ userDm.timestamp }}</small> -->
                                              </div>
                                              <div class="d-flex justify-content-between ml-2" style="position: relative; height:20px; overflow: hidden;">
                                                  <small v-if="userDm.recent_message != null" style="position: absolute; word-break:break-all;">{{ userDm.recent_message}}</small>
                                              </div>
                                          </div>
                                          <div class="col-1">
                                            <span id="deleteBtn" @click="targetDeleteDm = userDm" data-toggle="modal" data-target="#deleteUserDmModal"><i class="icon-delete" style="font-size: 1.2em;"></i></span>
                                          </div>
                                      </div>
                                  </a>
                              </virtual-list>
                              <virtual-list :size="80" :remain="8" v-else class="bg-white">
                                <div class="d-flex align-items-center" style="background-color: #efe9e5;">
                                  <!-- 친구검색 -->
                                  <i class="icon-search m-3"></i>
                                  <input type="search" v-model="inputIdData" class="mx-0" placeholder="아이디를 입력해주세요">
                                </div>

                                <!-- 친구검색결과 -->
                                <a v-show="inputIdData" v-for="result in resultIds" :key="result.index" :value="`result${result.id}`" class="m-0 list-group-item list-group-item-action py-0">
                                  <div v-if="result.user_id && isChating(result.user_id)" class="media position-relative px-1">
                                    <div class="media-body">
                                      <div class="notification align-self-center row">
                                        <div class="col-2 d-flex justify-content-center">
                                          <img v-if="result.profile_url" :src="result.profile_url" class="rounded-circle mx-4" width="50px" height="50px" style="object-fit: cover;">
                                          <img v-else src="https://t1.daumcdn.net/qna/image/1542632018000000528" class="rounded-circle mx-4" width="auto" height="50px" style="object-fit: cover;">
                                        </div>
                                        <div class="col-8" style="position: relative; overflow: hidden;">
                                          <p class="mb-0 ml-1">{{ result.user_id }}</p>
                                          <small v-if="result.description" class="ml-1" style="position: absolute; word-break:break-all;">{{ result.description }}</small>
                                        </div>
                                        <div class="col-2 p-0 d-flex align-items-center">
                                          <button v-if="isChating(result.user_id)" class="  btn btn-sm btn-info" @click="insertUserDm(result);">선택</button>
                                        </div>
                                        <!-- <p v-else> 반갑습니다 </p> -->
                                        <!-- <router-link :to="'/mypage/'+ result.user_id" class="text-primary">Go {{result.user_id}} page</router-link> -->
                                      </div>
                                    </div> 
                                  </div>
                                </a>

                                <div v-show="!inputIdData">
                                  <div class="bg-white pl-2 text-info d-flex align-items-center"><small>친구목록</small></div>
                                  <a v-for="follow in fetchedFollowList" :key="follow.index" class="m-0 list-group-item list-group-item-action py-0">
                                    <div class="row py-2">
                                      <div class="col-2 d-flex justify-content-center align-self-center p-0">
                                        <img class="rounded-circle ml-2" width="50px" height="50px" style="object-fit: cover;" :src="follow.profile_url || 'https://t1.daumcdn.net/qna/image/1542632018000000528'" alt=""> 
                                      </div>
                                      <div class="col-8 py-0" style="position: relative; overflow: hidden;">
                                        <p class="mb-0 ml-2">{{ follow.user_id }}</p>
                                        <small v-if="follow.description" class="ml-2" style="position: absolute; word-break:break-all;">{{ follow.description }}</small>
                                      </div>
                                      <div class="col-2 px-0 d-flex align-items-center">
                                        <button v-if="isChating(follow.user_id)" class="btn btn-sm btn-info" @click="insertUserDm(follow);">선택</button>
                                      </div>
                                    </div>
                                  </a>
                                </div>
                                <div v-show="inputIdData">
                                    <p class="m-3">{{idErrorMsg}}</p>
                                </div>
                              </virtual-list>
                            </div>
                        </div>
                        <!-- chating -->
                        <div v-show="check2" class="card col-md-8 p-0 ">
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
        targetDeleteDm: {},
        inputIdData: '',
        resultIds: [],
        idErrorMsg: '',
        userDmList:[],
    }
  },
  computed: {
    ...mapGetters([
      'fetchedUserDmList',
      'fetchedDirectMessageList',
      'fetchedFollowList',
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
      
      this.socket.emit('read', {
        send_id: userDm.other_id,
        receive_id: userDm.user_id
      });
      this.$store.commit('REMOVE_TARGETDM');
      this.userDm.cnt = 0;
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
      // this.$store.dispatch('INSERT_USERDM', userDm);
      http
        .post('/userDm/insertUserDm', userDm)
        .then(response => {
          userDm.dm_id = response.data.resValue.dm_id;
          this.fetchedUserDmList.push(userDm);
          this.check = true;
          this.selectUserDm(userDm);
        })
        .catch(e => console.log(e))
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
      this.close();
    },

    isChating(user_id) {
      let flag = true;
      this.fetchedUserDmList.forEach(dm => {
        if(dm.user_id == user_id || dm.other_id == user_id){
          flag = false;
        }
      })

      return flag;
    },
    getIds() {
      var searchIdData = new RegExp(this.inputIdData);
      http
        .get('/user/searchByUserId/' + this.inputIdData)
        .then((res) => {
            // console.log(res)
            if (res.data.resmsg == "아이디 검색 성공") {
                for (var i=0; i<res.data.resValue.length; i++) {
                    var id = res.data.resValue[i].user_id
                    if (id != this.userId && searchIdData.test(id) === true) {
                      this.resultIds.push({
                        user_id: id,
                        description: res.data.resValue[i].description,
                        profile_url: res.data.resValue[i].profile_url,
                        profile_filter: res.data.resValue[i].profile_filter,
                      })
                    }
                }
            } else if (res.data.resmsg == "아이디 검색 실패") {
                this.idErrorMsg = "아이디가 일치하는 친구가 없습니다."
            }
        })
        .catch(() => {
          this.errored = true;
        })
        .finally(() => (this.loading = false));
    },
  },
  watch: {
    inputIdData: function(inputId) {
      if (inputId === "") {
        this.resultIds = []
        this.idErrorMsg = ""
      } else {
        this.getIds()
        this.resultIds = []
        this.idErrorMsg = ""
      }
    },
  },
  mounted() {
    this.userId = this.$store.state.user_id;

    this.socket.on('chat', (data) => {
      if((this.userDm.user_id == data.send_id && this.userDm.other_id == data.receive_id) || (this.userDm.user_id == data.receive_id && this.userDm.other_id == data.send_id)){
        this.PUSH_MSG_DATA(data);
        if(data.receive_id == this.$store.state.user_id){
           http
            .put('/directMessage/readCheck', this.userDm)
            .then(response => {
              return response
            })
            .catch(e => console.log(e))

          this.socket.emit('read', data);
        }
      }
      // this.fetchedUserDmList.forEach(dm => {
      //   http
      //       .post(`/directMessage/unReadCnt`, dm)
      //       .then((res) => {
      //         const cnt = res.data.resvalue;
      //         dm.cnt = cnt;
      //         window.console.log(cnt);
      //       })
      // })
      this.$store.commit('SET_RECENTMESSAGE', data);

    });

    this.socket.on('read', (data) => {
      if((this.userDm.user_id == data.send_id && this.userDm.other_id == data.receive_id) || (this.userDm.user_id == data.receive_id && this.userDm.other_id == data.send_id)){
        if(data.send_id == this.$store.state.user_id){
          this.fetchedDirectMessageList.forEach(element => {
            if(element.send_id == this.$store.state.user_id){
              element.read_check = true;
            }
          });
        }
      }
    })

    // this.socket.on('notification', (data) => {
    //   // window.console.log('notification', data, this.$store.state.user_id);
    //   if(data.target_user_id == this.$store.state.user_id){
    //     this.$snotify.simple('알림을 확인해보세요!', 'Like!', {
    //         icon : '/favicon.ico',
    //         // html : '<div>Like!</div><div>알림을 확인해보세요!</div> <input type="button" @click="sendNotification" value="Login" class="btn btn-sm">'
    //       });
    //   }
    // });
  },
  created() {
    this.socket = io('http://192.168.100.41:3000');
    
    this.socket.emit('login', {
      user_id : this.userId
    });

    http
      .get(`/userDm/userDmList/${this.userId}`)
      .then(response => {
        this.userDmList = response.data.resvalue;
        this.userDmList.forEach(dm => {
          http
            .post(`/directMessage/unReadCnt`, dm)
            .then((res) => {
              const cnt = res.data.resvalue;
              dm.cnt = cnt;
              window.console.log(cnt);
            })
        })
      })
      .catch(e => console.log(e))
      .finally(() => {
        this.$store.commit('SET_USERDMLIST', this.userDmList);
      })
    this.$store.dispatch('FETCH_FOLLOWLIST', this.userId);
  
    // 선택한 유저가 있을 때
    if (this.targetDm != null && this.targetDm != undefined) {
      window.console.log(this.targetDm);
      this.selectUserDm(this.targetDm);
      // console.log(this.targetDm)
    }
  },
  beforeDestroy(){
    this.socket.emit('disconnectEvt', function(){});
    this.cfheck2 = false;
    this.$store.commit('REMOVE_TARGETDM');
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