import Vue from 'vue'
import Vuex from 'vuex'
import http from './http-common.js';

Vue.use(Vuex)

const debug = process.env.NODE_ENV !== 'production';

export default new Vuex.Store({
    strict: debug,
    state:{
        modalText: localStorage.getItem('modalText'),
        user_id:sessionStorage.getItem('user_id'),
        islogin:false,
        first:sessionStorage.getItem("first"),
        allDmList: [],
        userDmList: [],
        directMessageList: [],
        followList: [],
        followerList: [],
        unReadCnt: 0,
        targetDm: {},
    },
    getters:{
        fetchedUserDmList(state) {
            return state.userDmList;
        },
        fetchedDirectMessageList(state) {
            return state.directMessageList;
        },
        fetchedFollowList(state) {
            return state.followList;
        },
        fetchedFollowerList(state) {
            return state.followerList;
        },
        fetchedUnReadCnt(state) {
            return state.unReadCnt;
        },
    },
    mutations:{
        getModalText(state){
            state.modalText = localStorage.getItem('modalText')
        },
        setModalText: function(state, modalText){
            localStorage.setItem("modalText", modalText);
            state.modalText = localStorage.getItem("modalText");
        },
        getId(state){
            state.user_id = sessionStorage.getItem('user_id');
            if(sessionStorage.getItem('islogin')!=null)
                state.islogin = sessionStorage.getItem('islogin');
            // state.first = sessionStorage.getItem('first');
        },
        login(state, user_id) {
            sessionStorage.setItem("user_id", user_id);
            state.user_id = sessionStorage.getItem('user_id');
            sessionStorage.setItem("islogin", true);
            state.islogin = sessionStorage.getItem('islogin');
            sessionStorage.setItem("first", false);
            state.first = sessionStorage.getItem("first");
            localStorage.setItem("modalText", '로그인 되었습니다.');
            state.modalText = localStorage.getItem("modalText");
        },
        setid(state){
            state.user_id = sessionStorage.getItem('user_id');
            state.islogin = sessionStorage.getItem('islogin');
            state.first = sessionStorage.getItem('first');
        },
        logout(state){
            sessionStorage.removeItem("user_id");
            sessionStorage.removeItem("islogin");
            state.user_id='';
            state.islogin=false;
            state.userDmList = [];
            state.directMessageList = [];
            state.followList = [];
            // this.$socket.off('chat');
            // window.console.log();
            localStorage.setItem("modalText", '로그아웃 되었습니다.');
            state.modalText = localStorage.getItem("modalText");
        },
        // REQUIREAUTH() {
        //     if(!this.state.islogin){
        //         alert("로그인이 필요한 페이지입니다.");
        //         localStorage.setItem("modalText", '로그인이 필요한 페이지입니다.');
        //         this.state.modalText = localStorage.getItem("modalText");
        //         this.$router.push("/login");
        //     }
        // },
        PUSH_MSG_DATA(state, $payload) {
            // window.console.log($payload);
            state.directMessageList.push($payload);
        },
        SET_USERDMLIST(state, userDmList) {
            state.userDmList = userDmList;
        },
        SET_ALLDMLIST(state, allDmList) {
            state.allDmList = allDmList;
            this.$sokect.emit('initRoom', allDmList);
            // window.console.log(state.allDmList);
        },
        SET_DIRECTMESSAGELIST(state, directMessageList) {
            state.directMessageList = directMessageList;
        },
        SET_FOLLOWLIST(state, followList) {
            state.followList = followList;
        },
        SET_FOLLOWERLIST(state, followerList) {
            state.followerList = followerList;
        },
        SET_UNREADCNT(state, unReadCnt) {
            state.unReadCnt = unReadCnt;
        },
        SET_RECENTMESSAGE(state, message) {
            state.userDmList.forEach(element => {
                if(element.dm_id == message.dm_id){
                    element.recent_message = message.message;
                }
            })
        },
        SET_TARGETDM(state, dm) {
            state.targetDm = dm;
        },
        REMOVE_TARGETDM(state){
            state.tagetDM = {};
        }
    },
    actions:{
        FETCH_USERDMLIST(context, userId) {
            http
                .get(`/userDm/userDmList/${userId}`)
                .then(response => {
                    // console.log(response.data)
                    context.commit('SET_USERDMLIST', response.data.resvalue);
                    return response
                })
                .catch(e => console.log(e))
        },
        FETCH_ALLDMLIST(context) {
            http
                .get(`/userDm/allDmList`)
                .then(response => {
                    // console.log(response.data)
                    // window.console.log(response.data.resvalue);
                    context.commit('SET_ALLDMLIST', response.data.resvalue);
                    return response
                })
                .catch(e => console.log(e))
        },
        INSERT_USERDM(context, userDm) {
            http
                .post('/userDm/insertUserDm', userDm)
                .then(response => {
                    // console.log(response.data)
                    return response
                })
                .catch(e => console.log(e))
        },

        FETCH_DIRECTMESSAGELIST(context, userDm) {
            http
                .post("/directMessage/directMessageList", userDm)
                .then(response => {
                    // console.log(response.data)
                    context.commit('SET_DIRECTMESSAGELIST', response.data.resvalue);
                    return response
                })
                .catch(e => console.log(e))
        },
        INSERT_DIRECTMESSAGE(context, message) {
            http
                .post('/directMessage/insertDirectMessage', message)
                .then(response => {
                    // console.log(message)
                    return response
                })
                .catch(e => console.log(e))
        },

        FETCH_FOLLOWLIST(context, follower_id) {
            http
                .get(`/follow/followList/${follower_id}`)
                .then(response => {
                    // console.log(response.data)
                    context.commit('SET_FOLLOWLIST', response.data.resvalue);
                    return response
                })
                .catch(e => console.log(e))
        },
        FETCH_FOLLOWERLIST(context, follower_id) {
            http
                .get(`/follow/followerList/${follower_id}`)
                .then(response => {
                    // console.log(response.data)
                    context.commit('SET_FOLLOWERLIST', response.data.resvalue);
                    return response
                })
                .catch(e => console.log(e))
        },
        FETCH_UNREADCNT(context, dm_id) {
            http
                .get(`/directMessage/unReadCnt/${dm_id}`)
                .then(response => {
                    // console.log(response.data)
                    context.commit('SET_UNREADCNT', response.data.resvalue);
                    return response
                })
                .catch(e => console.log(e))
        },

        UPDATE_RECENTMESSAGE(context, message) {
            // window.console.log(message);
            http
                .put("/userDm/updateUserDm", message)
                .then(response => {
                    // console.log(response.data)
                    context.commit('SET_RECENTMESSAGE', message);
                    return response
                })
                .catch(e => console.log(e))
        },
        INSERT_FOLLOW(context, follow) {
            http
                .post("/follow/insertFollow", follow)
                .then(response => {
                    return response
                })
                .catch(e => console.log(e))
        },
        DELETE_FOLLOW(context, follow) {
            http
                .delete("/follow/deleteFollow", follow)
                .then(response => {
                    return response
                })
                .catch(e => console.log(e))
        },
    }
})