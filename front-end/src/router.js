import Vue from "vue";
import Router from "vue-router";
import Index from "./components/Index.vue"
import Category from "./components/Category.vue"
import Bio from "./components/Bio.vue"
import Blog from "./components/Blog.vue"
import Single from "./components/Single.vue"
import Register from "./components/Register.vue"
import Vfor from "./components/Vfor.vue"
<<<<<<< HEAD
import Calendar from "./components/Calendar.vue"
=======
import Chating from "./components/Chating.vue"
import AddImage from "./components/AddImage.vue"
import ImageFilter from "./components/ImageFilter.vue"
<<<<<<< HEAD
import WriteContent from "./components/WriteContent.vue"
import Password from "./components/Password.vue"
=======
>>>>>>> 3d39533856459d5d910c3fd720b658a64e945fcb
>>>>>>> d63b044d45e42f036273065155d0b27682f9b11b

Vue.use(Router);

export const router = new Router({
    mode: "history",
    routes: [
        {
            path: "/",
            name: "index",
            alias: "/Index",
            component: Index,
            props: true,
        },
        {
            path: "/category",
            name: "category",
            alias: "/Category",
            component: Category,
            props: true,
        },
        {
            path: "/bio",
            name: "bio",
            alias: "/Bio",
            component: Bio,
            props: true,
        },
        {
            path: "/blog",
            name: "blog",
            alias: "/Blog",
            component: Blog,
            props: true,
        },
        {
            path: "/single",
            name: "single",
            alias: "/Single",
            component: Single,
            props: true,
        },
        {
            path: "/register",
            name: "register",
            alias: "/Register",
            component: Register,
            props: true,
        },
        {
            path: "/vfor",
            name: "vfor",
            alias: "/Vfor",
            component: Vfor,
            props: true,
        },
        {
<<<<<<< HEAD
            path: "/calendar",
            name: "calendar",
            alias: "/Calendar",
            component: Calendar,
            // props: true,
=======
            path: "/chating",
            name: "chating",
            alias: "/Chating",
            component: Chating,
        },
        {
            path: "/addimage",
            name: "addimage",
            alias: "/AddImage",
            component: AddImage,
            props: true,
        },
        {
            path: "/imagefilter",
            name: "imagefilter",
            alias: "/ImageFilter",
            component: ImageFilter,
            props: true,
>>>>>>> 3d39533856459d5d910c3fd720b658a64e945fcb
        },
        {
            path: "/writecontent",
            name: "writecontent",
            alias: "/WriteContent",
            component: WriteContent,
            props: true,
        },
        {
            path: "/password",
            name: "password",
            alias: "/Password",
            component: Password,
            props: true,
        },
    ]
});

router.beforeEach(function (to, from, next){
    // store.commit('getChatId');

    next();
});