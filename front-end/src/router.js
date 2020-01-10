import Vue from "vue";
import Router from "vue-router";
import Index from "./components/Index.vue"
import Category from "./components/Category.vue"
import Bio from "./components/Bio.vue"
import Blog from "./components/Blog.vue"
import Single from "./components/Single.vue"

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
        }
    ]
});

router.beforeEach(function (to, from, next){
    // store.commit('getChatId');

    next();
});