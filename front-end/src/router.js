import Vue from "vue";
import Router from "vue-router";
import store from "./store";

import Index from "./components/Index.vue"
import Bio from "./components/Bio.vue"
import Blog from "./components/Blog.vue"
import Single from "./components/Single.vue"
import Category from "./components/Category.vue"
import Register from "./components/Register.vue"
import Chating from "./components/Chating.vue"
import AddImage from "./components/AddImage.vue"
import ImageFilter from "./components/ImageFilter.vue"
import WriteContent from "./components/WriteContent.vue"
import Password from "./components/Password.vue"
import FindFriend from "./components/FindFriend.vue"
import UserEdit from "./components/UserEdit.vue"
import Login from "./components/Login.vue"
import FindContent from "./components/FindContent.vue"
import WordCloud from "./components/WordCloud.vue"
import Chart from "./components/Chart.vue"
import PwConfirm from "./components/PwConfirm.vue"
import AdminLog from "./components/AdminLog.vue"
import AdminUserList from "./components/AdminUserList.vue"
import Mypage from "./components/Mypage.vue"
import Calendar from "./components/Calendar.vue"
import Crawling from "./components/Crawling.vue"
import Notification from "./components/Notification.vue"
import Admin from "./components/Admin.vue"
import Editing from "./components/Editing.vue"
import ContentListHashtag from "./components/ContentListHashtag.vue"
import updateContent from "./components/updateContent.vue"
import Recommend from "./components/Recommend.vue"
import AdminReportContents from "./components/AdminReportContents.vue"

Vue.use(Router);
// const modal = { template: '<p id="modalBtn" style="display:none;" data-toggle="modal" data-target="#myModal"></p><div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel"><div class="modal-dialog" role="document"><div class="modal-content"><div class="modal-body" style="text-align:center;">{{$store.state.modalText}}</div><div class="modal-footer"><button type="button" class="btn btn-danger text-white" data-dismiss="modal">닫기</button></div></div></div></div>'}
export const router = new Router({
    mode: "history",
    routes: [{
            path: "/",
            name: "index",
            alias: "/Index",
            component: Index,
            props: true,
            beforeEnter: function (to, from, next) {
                store.commit('setid');
                if (store.state.islogin) {
                    next();
                } else {
                    // store.commit('setModalText', "아이디 또는 비밀번호를 확인해주세요.");
                    // document.getElementById('modalBtn').click();
                    alert("로그인해주세요.");
                    router.push("/login")
                }
            },

        },
        {
            path: "/category",
            name: "category",
            alias: "/Category",
            component: Category,
            props: true,
            beforeEnter: function (to, from, next) {
                store.commit('setid');
                if (store.state.islogin) {
                    next();
                } else {
                    alert("로그인해주세요.");
                    router.push("/login")
                }
            },
        },
        {
            path: "/bio",
            name: "bio",
            alias: "/Bio",
            component: Bio,
            props: true,
            beforeEnter: function (to, from, next) {
                store.commit('setid');
                // store.commit("getId");
                if (store.state.islogin) {
                    next();
                } else {
                    alert("로그인해주세요.");
                    router.push("/login")
                }
            },
        },
        {
            path: "/blog",
            name: "blog",
            alias: "/Blog",
            component: Blog,
            props: true,
            beforeEnter: function (to, from, next) {
                store.commit('setid');
                if (store.state.islogin) {
                    next();
                } else {
                    alert("로그인해주세요.");
                    router.push("/login")
                }
            },
        },
        {
            path: "/single",
            name: "single",
            alias: "/Single",
            component: Single,
            props: true,
            beforeEnter: function (to, from, next) {
                store.commit('setid');
                if (store.state.islogin) {
                    next();
                } else {
                    alert("로그인해주세요.");
                    router.push("/login")
                }
            },
        },
        {
            path: "/register",
            name: "register",
            alias: "/Register",
            component: Register,
            props: true,
        },
        {
            path: "/chating",
            name: "chating",
            alias: "/Chating",
            component: Chating,
            beforeEnter: function (to, from, next) {
                store.commit('setid');
                if (store.state.islogin) {
                    next();
                } else {
                    alert("로그인해주세요.");
                    router.push("/login")
                }
            },
        },
        {
            path: "/addimage",
            name: "addimage",
            alias: "/AddImage",
            component: AddImage,
            props: true,
            beforeEnter: function (to, from, next) {
                store.commit('setid');
                if (store.state.islogin) {
                    next();
                } else {
                    alert("로그인해주세요.");
                    router.push("/login")
                }
            },
        },
        {
            path: "/imagefilter",
            name: "imagefilter",
            alias: "/ImageFilter",
            component: ImageFilter,
            props: true,
            // beforeEnter: function(to, from, next){
            //     store.commit('setid');
            //     if(store.state.islogin){
            //         next();
            //     }else{
            //         alert("로그인해주세요.");
            //         router.push("/login")
            //     }
            // },
        },
        {
            path: "/writecontent",
            name: "writecontent",
            alias: "/WriteContent",
            component: WriteContent,
            props: true,
            beforeEnter: function (to, from, next) {
                store.commit('setid');
                if (store.state.islogin) {
                    next();
                } else {
                    alert("로그인해주세요.");
                    router.push("/login")
                }
            },
        },
        {
            path: "/password",
            name: "password",
            alias: "/Password",
            component: Password,
            props: true,
        },
        {
            path: "/findfriend",
            name: "findfriend",
            alias: "/Findfriend",
            component: FindFriend,
            props: true,
            beforeEnter: function (to, from, next) {
                store.commit('setid');
                if (store.state.islogin) {
                    next();
                } else {
                    alert("로그인해주세요.");
                    router.push("/login")
                }
            },
        },
        {
            path: "/useredit",
            name: "useredit",
            alias: "/UserEdit",
            component: UserEdit,
            props: true,
            beforeEnter: function (to, from, next) {
                store.commit('setid');
                if (store.state.islogin) {
                    next();
                } else {
                    alert("로그인해주세요.");
                    router.push("/login")
                }
            },
        },
        {
            path: "/login",
            name: "login",
            alias: "/Login",
            component: Login,
            props: true,
        },
        {
            path: "/findcontent",
            name: "findcontent",
            alias: "/Findcontent",
            component: FindContent,
            props: true,
            beforeEnter: function (to, from, next) {
                store.commit('setid');
                if (store.state.islogin) {
                    next();
                } else {
                    alert("로그인해주세요.");
                    router.push("/login")
                }
            },
        },
        {
            path: "/wordcloud",
            name: "wordcloud",
            alias: "/WordCloud",
            component: WordCloud,
            beforeEnter: function (to, from, next) {
                store.commit('setid');
                if (store.state.islogin) {
                    next();
                } else {
                    alert("로그인해주세요.");
                    router.push("/login")
                }
            },
        },
        {
            path: "/chart",
            name: "chart",
            alias: "/Chart",
            component: Chart,
            beforeEnter: function (to, from, next) {
                store.commit('setid');
                if (store.state.islogin) {
                    next();
                } else {
                    alert("로그인해주세요.");
                    router.push("/login")
                }
            }
        },
        {
            path: "/pwconfirm",
            name: "pwconfirm",
            alias: "/PwConfirm",
            component: PwConfirm,
            props: true,
        },
        {
            path: "/admin",
            name: "admin",
            alias: "/Admin",
            component: Admin,
            props: true,
            
        },
        {
            path: "/adminlog",
            name: "adminlog",
            alias: "/AdminLog",
            component: AdminLog,
            props: true,
        },
        {
            path: "/adminuserlist",
            name: "adminuserlist",
            alias: "/AdminUserList",
            component: AdminUserList,
        }, {
            path: "/adminreportcontents",
            name: "adminreportcontents",
            alias: "/AdminReportContents",
            component: AdminReportContents,
        },
        {
            path: "/mypage/:userId",
            name: "mypage",
            alias: "/Mypage",
            component: Mypage,
            props: true,
        },
        {
            path: "/calendar",
            name: "calendar",
            alias: "/Calendar",
            component: Calendar,
            props: true,
        },
        {
            path: "/crawling",
            name: "crawling",
            alias: "/Crawling",
            component: Crawling,
            props: true,
        },
        {
            path: "/notification",
            name: "notification",
            alias: "/Notification",
            component: Notification,
            props: true,
        },
        {
            path: "/editing",
            name: "editing",
            alias: "/Editing",
            component: Editing,
            props: true,
        },
        {
            path: "/contentListHashtag",
            name: "contentListHashtag",
            alias: "/ContentListHashtag",
            component: ContentListHashtag,
            props: true,
        },
        {
            path: "/updatecontent",
            name: "updatecontent",
            alias: "/Updatecontent",
            component: updateContent,
            props: true,
        },
        {
            path: "/recommend",
            name: "recommend",
            alias: "/Recommend",
            component: Recommend,
            props: true,
        },
    ]
});

router.beforeEach(function (to, from, next) {
    store.commit("getId");
    next();
});