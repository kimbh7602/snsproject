<template>
    <div class="container-fluid photos">
      <div class="row justify-content-center">
            <div class="col-5 p-4 text-center bg-light" data-aos="fade-up">
                <img class="rounded-circle mt-3 mb-2" width="150px" height="150px" style="object-fit: cover;" :class="userInfo.profile_filter" :src="userInfo.profile_url">
                <h4>{{userId}}</h4>
                <div v-if="userId != myId" class="d-flex justify-content-center row p-2">
                    <span v-show="myFollowList.includes(userId)" @click="targetUser = userInfo" data-toggle="modal" data-target="#deleteFollowModal" class="site-logo btn btn-outline-primary col-5 m-2" style="width:100%;">팔로잉</span>
                    <span v-show="!myFollowList.includes(userId)" @click="insertFollow(userInfo)" class="site-logo btn btn-primary col-5 m-2" style="width:100%;">팔로우</span>
                    <span @click="goChating()" v-if="myId != userId" class="site-logo btn btn-outline-info col-5 m-2" style="width:100%;"><i class="icon-send"></i></span>

                </div>
                <div v-else class="d-flex justify-content-center row">
                    <router-link class="px-2 m-0 col-5" to="/pwconfirm"><button class="site-logo btn btn-outline-info" style="width:100%; height:50px;">정보수정</button></router-link>
                    <router-link class="px-2 m-0 col-5" to="/chart"><button class="site-logo btn btn-outline-primary" style="width:100%; height:50px;">통계</button></router-link>
                </div>

                <div class="text-left mt-4 mx-5">
                    <span v-for="(item, index) in userInfo.interestList" :key="`item${index}`">
                        <span class="mr-2" style="color: #4285f4;" v-if="item!=''">#{{item}}</span>
                    </span>
                </div>
                <div class="rounded my-2 mx-5" style="position: relative; height:100px; border: 1px dotted gray; overflow: auto;">
                    <div class="text-left p-3" style="position: absolute; white-space:pre-wrap;">
                        <div>{{userInfo.description}}</div>
                    </div>
                </div>
            </div>
      </div>
      
      <div class="row justify-content-center mb-4">
        <div class="col-5 d-flex justify-content-center">
            <div class="row">
                <div class="text-center col">
                    <a href="javascript:void(0)" class="m-0 p-0" @click="content()">게시물</a>
                    <h4 v-if="userContent">{{userContent.length}}</h4>
                    <h4 v-else>0</h4>
                </div>
                <div class="text-center col">
                    <a href="" class="m-0 p-0" data-toggle="modal" data-target="#followerModal">팔로워</a>
                    <h4 v-if="fetchedFollowerList">{{fetchedFollowerList.length}}</h4>
                    <h4 v-else>0</h4>
                </div>
                <div class="text-center col">
                    <a href="" class="m-0 p-0" data-toggle="modal" data-target="#followModal">팔로우</a>
                    <h4 v-if="fetchedFollowList">{{fetchedFollowList.length}}</h4>
                    <h4 v-else>0</h4>
                </div>
                <div v-if="userId==myId" class="text-center col">
                    <a href="javascript:void(0)" class="m-0 p-0" @click="scrap()">스크랩</a>
                    <h4 v-if="userScrap">{{userScrap.length}}</h4>
                    <h4  v-else>0</h4>
                </div>
            </div>
        </div>
      </div>
      
      <!-- 카테고리 -->
      <category v-if="check=='content'" :userId="userId" :myPage="true"></category>
      <category v-else :userId="userId" :myPage="false"></category>

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
                        <div v-for="(follower, index) in fetchedFollowerList" :key="`follower${index}`" class="d-flex align-items-center justify-content-between mb-3">
                            <a :href="`/mypage/${follower.user_id}`" class="text-dark d-flex m-0 align-items-center">
                                <img class="rounded-circle mr-3" width="40px" height="40px" style="object-fit: cover;" :src="follower.profile_url || 'https://t1.daumcdn.net/qna/image/1542632018000000528'">
                                {{follower.user_id}}
                            </a>
                            <!-- <router-link :to="'/mypage/'+follower" class="d-flex text-dark" @click="fetchUserInfo(follower); "><i class="icon-user-circle mr-2" style="font-size:1.9em;"></i> {{follower}}</router-link> -->
                            <span v-show="myId != follower.user_id && myFollowList.includes(follower.user_id)" @click="targetUser = follower" data-toggle="modal" data-target="#deleteFollowModal" class="btn btn-outline-primary btn-sm">팔로잉</span> 
                            <span v-show="myId != follower.user_id && !myFollowList.includes(follower.user_id)" class="ml-3 btn btn-primary btn-sm" @click="insertFollow(follower)">팔로우</span>
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
                        <div v-for="(follow, index) in fetchedFollowList" :key="`follow${index}`" class="d-flex align-items-center justify-content-between mb-3">
                             <a :href="`/mypage/${follow.user_id}`" class="text-dark d-flex m-0 align-items-center">
                                <img class="rounded-circle mr-3" width="40px" height="40px" style="object-fit: cover;" :src="follow.profile_url || 'https://t1.daumcdn.net/qna/image/1542632018000000528'">
                                {{follow.user_id}}
                            </a>
                            <span v-show="myId != follow.user_id && myFollowList.includes(follow.user_id)" @click="targetUser = follow" data-toggle="modal" data-target="#deleteFollowModal" class="btn btn-outline-primary btn-sm">팔로잉</span> 
                            <span v-show="myId != follow.user_id && !myFollowList.includes(follow.user_id)" class="ml-3 btn btn-primary btn-sm" @click="insertFollow(follow)">팔로우</span>
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <!-- 팔로우취소 모달 -->
        <div class="modal" id="deleteFollowModal" tabindex="-1" role="dialog">
            <div class="modal-dialog col-12" role="document">
                <div class="modal-content">
                <div class="modal-body">
                    <p>팔로우 취소하시겠습니까?</p>
                </div>
                <div class="modal-footer p-2">
                    <button type="button" class="btn btn-secondary btn-sm" data-dismiss="modal">취소</button>
                    <button type="button" class="btn btn-primary btn-sm" @click="deleteFollow(targetUser)" data-dismiss="modal">확인</button>
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
    props:['userId'],
    data() {
        return {
            myId: this.$store.state.user_id,
            // userId: this.$route.params.userId,
            myFollowList: [],
            myInfo: {},
            userInfo: {},
            userContent: [],
            userScrap: [],
            check: 'content',
            targetUserDm: {},
            possible: true,
            targetUser: {}
        }
    },
    computed: {
        ...mapGetters([
            'fetchedFollowList',
            'fetchedFollowerList',
            'fetchedUserDmList',
        ])
        
    },
    methods: {
        insertFollow(user) {
            // this.$store.dispatch('INSERT_FOLLOW', {follower_id: this.myId, follow_id: id});
            http
                .post("/follow/insertFollow", {follower_id: this.myId, follow_id: user.user_id})
                .then(response => {
                    this.$socket.emit('notification', {
                        user_id: response.data.resValue.user_id,    
                        target_user_id: response.data.resValue.target_user_id,
                        category: response.data.resValue.category,
                        flag: true
                    });
                })
                .catch(e => console.log(e));
            this.myFollowList.push(user.user_id);

            if (this.myId == this.userId) {
                this.fetchedFollowList.push(user);
            }
            else if (user.user_id == this.userId) {
                this.fetchedFollowerList.push(this.myInfo);
            }
        },
        deleteFollow(user) {
            http
                .delete("/follow/deleteFollow", {data: {follower_id: this.myId, follow_id: user.user_id}})
                .then(response => {
                    this.$socket.emit('notification', {
                        user_id: response.data.resValue.user_id,
                        target_user_id: response.data.resValue.target_user_id,
                        category: response.data.resValue.category,
                        flag: false
                    });
                })
                .catch(e => console.log(e))
            const idx = this.myFollowList.indexOf(user.user_id);
            if (idx > -1) this.myFollowList.splice(idx, 1);

            if (this.myId == this.userId) {
                const idx2 = this.fetchedFollowList.indexOf(user);
                if (idx2 > -1) this.fetchedFollowList.splice(idx2, 1);  
            }
            else if (user.user_id == this.userId) {
                const idx2 = this.fetchedFollowerList.indexOf(this.myInfo);
                if (idx > -1) this.fetchedFollowerList.splice(idx2, 1);
            }
        },
        fetchMyFollowList() {
            http
                .get(`/follow/followList/${this.myId}`)
                .then(response => {
                    response.data.resvalue.forEach(user => {
                        this.myFollowList.push(user.user_id)
                    })
                })
                .catch(e => console.log(e))
        },
        fetchMyInfo(id) {
            http
                .get(`user/info/${id}`)
                .then(response => {
                    this.myInfo = response.data.resvalue;
                })
                .catch(e => console.log(e))
        },
        fetchUserInfo(id) {
            http
                .get(`user/info/${id}`)
                .then(response => {
                    this.userInfo = response.data.resvalue;
                    this.userInfo.interestList = this.userInfo.interestList.slice(0, 8);
                    // console.log(this.userInfo)
                })
                .catch(e => console.log(e))
        },
        fetchUserContent(id) {
            http
                .get(`content/contentUserList/${id}`)
                .then(response => {
                    this.userContent = response.data.resValue;
                })
        },
        fetchUserScrap(id) {
            http
                .get(`scrap/scrapList/${id}`)
                .then(response => {
                    this.userScrap = response.data.resvalue;
                })
        },
        content() {
            this.check = 'content';
        },
        scrap() {
            this.check = 'scrap';
        },
        fetchUserDmList() {
            http
                .get(`/userDm/userDmList/${this.myId}`)
                .then(response => {
                    // console.log(response.data)
                    response.data.resvalue.forEach(dm => {
                        if (dm.user_id == this.userId || dm.other_id == this.userId) {
                            this.possible = false;
                            this.targetUserDm = dm;
                        }
                    })
                    return response
                })
                .catch(e => console.log(e))
        },
        fetchNewUserDmList() {
            http
                .get(`/userDm/userDmList/${this.myId}`)
                .then(response => {
                    // console.log(response.data)
                    response.data.resvalue.forEach(dm => {
                        if (dm.user_id == this.userId || dm.other_id == this.userId) {
                            this.possible = false;
                            this.targetUserDm = dm;
                            // console.log(this.targetUserDm);
                            this.$store.commit('SET_TARGETDM', this.targetUserDm);
                            this.$router.push({
                                name: 'chating',
                            })
                        }
                    })
                    return response
                })
                .catch(e => console.log(e))
        },

        insertUserDm(userDm) {
            http
                .post('/userDm/insertUserDm', userDm)
                .then(response => {
                    this.fetchNewUserDmList();
                    return response
                })
                .catch(e => console.log(e))
        },
        goChating() {
            if (this.possible) {
                
                const userDm = {
                    user_id: this.myId,
                    other_id: this.userId,
                    user: this.userInfo
                };
                console.log(this.userInfo)
                this.insertUserDm(userDm);
                // this.$store.commit('SET_TARGETDM', this.targetUserDm);
            } else {
                this.$store.commit('SET_TARGETDM', this.targetUserDm);
                this.$router.push({
                    name: 'chating',
                })
            }
        },
    },
    created() {
        this.$store.dispatch('FETCH_FOLLOWLIST', this.userId);
        this.$store.dispatch('FETCH_FOLLOWERLIST', this.userId);
        this.fetchMyFollowList();
        this.fetchMyInfo(this.myId);
        this.fetchUserInfo(this.userId);
        this.fetchUserContent(this.userId);
        this.fetchUserScrap(this.userId);
        this.fetchUserDmList();
    },
}
</script>

<style scoped>
.modal-dialog { 
    width: 30%; 
    height: 50%;
}
.modal-content{
    height: auto;
    min-height: 100%;
}

#deleteFollowModal .modal-dialog {
    margin-top: 130px;
    width: 25%;
    height: 50%;
}
#deleteFollowModal .modal-content{
    height: auto;
    min-height: 30%;
}
.site-logo {
    box-shadow: 0px 3px 5px 1px grey;
}
</style>