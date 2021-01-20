import Vue from "vue";
import VueRouter from "vue-router";
import Main from "../views/Main.vue";
import SearchResultList from "../views/search/SearchResultList.vue";
Vue.use(VueRouter);

const routes = [
  {
    path: "/",
    name: "Main",
    component: Main
  },
  {
    path: "/about",
    name: "About",
    // route level code-splitting
    // this generates a separate chunk (about.[hash].js) for this route
    // which is lazy-loaded when the route is visited.
    component: () =>
      import(/* webpackChunkName: "about" */ "../views/About.vue")
  },
  {
    path: "/about2",
    name: "About2",
    component: () =>
      import(/* webpackChunkName: "about" */ "../views/About2.vue")
  },
  {
    path: "/searchResultList",
    name: "SearchResultList",
    component: SearchResultList
  }
];

const router = new VueRouter({
  mode: "history",
  base: process.env.BASE_URL,
  routes
});

export default router;
