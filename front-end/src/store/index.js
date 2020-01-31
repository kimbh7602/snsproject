  
// import Vue from 'vue';
// import Vuex from 'vuex';
// import http from '../http-common.js';

// Vue.use(Vuex);

// const debug = process.env.NODE_ENV !== 'production';

// export default new Vuex.Store({
//     strict: debug,

//     state: {
//         userDmList: [],
//         directMessageList: [],
//         followList: [],
//         unReadCnt: 0,
//     },
//     getters: {
//         fetchedUserDmList(state) {
//             return state.userDmList;
//         },
//         fetchedDirectMessageList(state) {
//             return state.directMessageList;
//         },
//         fetchedFollowList(state) {
//             return state.followList;
//         },
//         fetchedUnReadCnt(state) {
//             return state.unReadCnt;
//         }
//     },
//     mutations: {
//         PUSH_MSG_DATA(state, $payload) {
//             // console.log($payload);
//             state.directMessageList.push($payload);
//         },
//         SET_USERDMLIST(state, userDmList) {
//             state.userDmList = userDmList;
//         },
//         SET_DIRECTMESSAGELIST(state, directMessageList) {
//             state.directMessageList = directMessageList;
//         },
//         SET_FOLLOWLIST(state, followList) {
//             state.followList = followList;
//         },
//         SET_UNREADCNT(state, unReadCnt) {
//             state.unReadCnt = unReadCnt;
//         },
//     },
//     actions: {
//         FETCH_USERDMLIST(context, userId) {
//             http
//                 .get(`/userDm/userDmList/${userId}`)
//                 .then(response => {
//                     // console.log(response.data)
//                     window.console.log(response.data.resvalue);
//                     context.commit('SET_USERDMLIST', response.data.resvalue);
//                     return response
//                 })
//                 .catch(e => console.log(e))
//         },
//         INSERT_USERDM(context, userDm) {
//             http
//                 .post('/userDm/insertUserDm', userDm)
//                 .then(response => {
//                     // console.log(response.data)
//                     return response
//                 })
//                 .catch(e => console.log(e))
//         },

//         FETCH_DIRECTMESSAGELIST(context, userDm) {
//             http
//                 .post("/directMessage/directMessageList", userDm)
//                 .then(response => {
//                     // console.log(response.data)
//                     context.commit('SET_DIRECTMESSAGELIST', response.data.resvalue);
//                     return response
//                 })
//                 .catch(e => console.log(e))
//         },
//         INSERT_DIRECTMESSAGE(context, message) {
//             http
//                 .post('/directMessage/insertDirectMessage', message)
//                 .then(response => {
//                     console.log(message)
//                     return response
//                 })
//                 .catch(e => console.log(e))
//         },

//         FETCH_FOLLOWLIST(context, follower_id) {
//             http
//                 .get(`/follow/followList/${follower_id}`)
//                 .then(response => {
//                     // console.log(response.data)
//                     context.commit('SET_FOLLOWLIST', response.data.resvalue);
//                     return response
//                 })
//                 .catch(e => console.log(e))
//         },
//         FETCH_UNREADCNT(context, dm_id) {
//             http
//                 .get(`/directMessage/unReadCnt/${dm_id}`)
//                 .then(response => {
//                     // console.log(response.data)
//                     context.commit('SET_UNREADCNT', response.data.resvalue);
//                     return response
//                 })
//                 .catch(e => console.log(e))
//         },

//         UPDATE_RECENTMESSAGE() {
//             http
//                 .put("/userDm/updateUserDm")
//                 .then(response => {
//                     // console.log(response.data)
//                     return response
//                 })
//                 .catch(e => console.log(e))
//         }
//     }
// });
