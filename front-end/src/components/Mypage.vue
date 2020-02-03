<template>
    <div class="container-fluid photos">
      <div class="row justify-content-center">

        <div class="col-md-8 pt-4">
            <div class="mb-5 text-center" data-aos="fade-up">
                <h2 class="text-white mb-5">Mypage</h2>
                <img class="rounded-circle mb-2" width="150px" height="150px" style="object-fit: cover;" :src="userInfo.profile_url || 'https://t1.daumcdn.net/qna/image/1542632018000000528'">
                <div class="row">
                    <div class="col-md-5"></div>
                    <h4 class="text-white col-md-2">{{userId}}</h4>
                    <span v-if="myId != userId && myFollowList.includes(userId)" @click="deleteFollow(userId)" class="col-md-1 btn btn-outline-primary">팔로잉</span>
                    <span v-if="myId != userId && !myFollowList.includes(userId)" @click="insertFollow(userId)" class="col-md-1 btn btn-primary">팔로우</span> 
                </div>
               
                <div class="m-3">
                    <p>{{userInfo.description}}</p>
                    관심사 : <span v-for="(item, index) in userInfo.interestList" :key="`item${index}`">{{item}} </span>
                </div>
                
                <div class="d-flex justify-content-center">
                    <div class="col-md-4"></div>
                    <div class="col-md-1 text-center">게시물
                        <h2>0</h2>
                    </div>
                    <div class="col-md-1 text-center">
                        <a href="" type="button" data-toggle="modal" data-target="#followerModal">팔로워</a>
                        <h2 v-if="fetchedFollowerList">{{fetchedFollowerList.length}}</h2>
                        <h2 v-else>0</h2>
                    </div>
                    <div class="col-md-1 text-center">
                        <a href="" type="button" data-toggle="modal" data-target="#followModal">팔로우</a>
                        <h2 v-if="fetchedFollowList">{{fetchedFollowList.length}}</h2>
                        <h2 v-else>0</h2>
                    </div>
                    <div class="col-md-2 text-center align-self-center">
                        <router-link v-if="userId == myId" to="/chart"><button class="btn btn-light">통계 📈</button></router-link>
                    </div>
                    <div class="col-md-2"></div>        
                </div>
            </div>
        </div>
      </div>

      <category></category>

      <!-- 팔로워 모달 -->
        <div class="modal mt-5" id="followerModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
            <div class="modal-dialog" role="document">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title text-dark ml-1" id="exampleModalLabel">팔로워</h5>
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                        </button>
                    </div>
                    <div class="modal-body">
                        <div v-for="(follower, index) in fetchedFollowerList" :key="`follow${index}`" class="d-flex justify-content-between mx-1 mb-3">
                            <router-link :to="'/mypage/'+follower" class="d-flex text-dark" @click="fetchUserInfo(follower)"><i class="icon-user-circle mr-2" style="font-size:1.9em;"></i> {{follower}}</router-link>
                            <span v-if="myId != follower && myFollowList.includes(follower)" @click="deleteFollow(follower)" class="btn btn-outline-primary btn-sm">팔로잉</span> 
                            <span v-if="myId != follower && !myFollowList.includes(follower)" class="ml-3 btn btn-primary btn-sm" @click="insertFollow(follower)">팔로우</span>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <!-- 팔로우 모달 -->
        <div class="modal mt-5" id="followModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
            <div class="modal-dialog" role="document">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title text-dark ml-1" id="exampleModalLabel">팔로우</h5>
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                        </button>
                    </div>
                    <div class="modal-body">
                        <div v-for="(follow, index) in fetchedFollowList" :key="`follower${index}`" class="d-flex justify-content-between mx-1 mb-3">
                            <router-link :to="'/mypage/'+follow" class="d-flex text-dark" @click="fetchUserInfo(follow)"><i class="icon-user-circle mr-2" style="font-size:1.9em;"></i> {{follow}}</router-link>
                            <span v-if="myId != follow && myFollowList.includes(follow)" @click="deleteFollow(follow)" class="btn btn-outline-primary btn-sm">팔로잉</span> 
                            <span v-if="myId != follow && !myFollowList.includes(follow)" class="ml-3 btn btn-primary btn-sm" @click="insertFollow(follow)">팔로우</span>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</template>

<script>
import { mapGetters } from 'vuex';
import http from '../http-common.js';
import Category from './Category.vue';

export default {
    components: {
        Category
    },
    data() {
        return {
            myId: this.$store.state.user_id,
            userId: this.$route.params.userId,
            myFollowList: [],
            userInfo: {},
        }
    },
    computed: {
        ...mapGetters([
            'fetchedFollowList',
            'fetchedFollowerList'
        ])
    },
    methods: {
        insertFollow(id) {
            // this.$store.dispatch('INSERT_FOLLOW', {follower_id: this.myId, follow_id: id});
            http
                .post("/follow/insertFollow", {follower_id: this.myId, follow_id: id})
                .then(response => {
                    this.$socket.emit('notification', {
                        user_id: response.data.resValue.user_id,
                        target_user_id: response.data.resValue.target_user_id,
                        category: response.data.resValue.category
                    });
                })
                .catch(e => console.log(e))
            this.myFollowList.push(id);

            if (this.myId == this.userId) {
                this.fetchedFollowList.push(id);
            }
            else if (id == this.userId) {
                this.fetchedFollowerList.push(this.myId);
            }
        },
        deleteFollow(id) {
            if (confirm('팔로우취소 하시겠습니까?')) {
                this.$store.dispatch('DELETE_FOLLOW', {data: {follower_id: this.myId, follow_id: id}});
                const idx = this.myFollowList.indexOf(id);
                if (idx > -1) this.myFollowList.splice(idx, 1);

                if (this.myId == this.userId) {
                    const idx2 = this.fetchedFollowList.indexOf(id);
                    if (idx2 > -1) this.fetchedFollowList.splice(idx2, 1);
                }
                else if (id == this.userId) {
                    const idx2 = this.fetchedFollowerList.indexOf(this.myId);
                    if (idx > -1) this.fetchedFollowerList.splice(idx2, 1);
                }
            }
        },
        fetchMyFollowList() {
            http
                .get(`/follow/followList/${this.myId}`)
                .then(response => {
                    this.myFollowList = response.data.resvalue;
                })
                .catch(e => console.log(e))
        },
        fetchUserInfo(id) {
            http
                .get(`user/info/${id}`)
                .then(response => {
                    this.userInfo = response.data.resvalue;
                })
                .catch(e => console.log(e))
        }
    },
    created() {
        this.$store.dispatch('FETCH_FOLLOWLIST', this.userId);
        this.$store.dispatch('FETCH_FOLLOWERLIST', this.userId);
        this.fetchMyFollowList();
        this.fetchUserInfo(this.userId);
    },
}
</script>

<style scoped>
.modal-dialog { 
    width: 30%; 
    height: 50%;
}
.modal-content {
  height: auto;
  min-height: 100%;
}
</style>