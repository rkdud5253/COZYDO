import Vue from "vue";
import VueRouter from "vue-router";
import Main from "../views/Main.vue";
import SearchResultList from "../views/search/SearchResultList.vue";
import SearchResultMap from "../views/search/SearchResultMap.vue";
import PlaceDetail from "../components/place/PlaceDetail.vue";
import PlaceModify from "../components/place/PlaceModify.vue";
import PlaceReview from "../components/place/PlaceReview.vue";
import Register from "../views/member/Register.vue";
import Login from "../views/member/Login.vue";
import Information from "../views/Information.vue";
import MyPage from "../views/member/MyPage.vue";
Vue.use(VueRouter);

const routes = [
  {
    path: "/",
    name: "Main",
    component: Main
  },
  {
    path: "/searchResultList",
    name: "SearchResultList",
    component: SearchResultList
  },
  {
    path: "/searchResultMap",
    name: "SearchResultMap",
    component: SearchResultMap
  },
  {
    path: "/placeDetail/:placeIdx/:level",
    name: "PlaceDetail",
    component: PlaceDetail
  },
  {
    path: "/placeModify",
    name: "PlaceModify",
    component: PlaceModify
  },
  {
    path: "/placeReview",
    name: "PlaceReview",
    component: PlaceReview
  },
  {
    path: "/register",
    name: "Register",
    component: Register
  },
  {
    path: "/login",
    name: "Login",
    component: Login
  },
  {
    path: "/information",
    name: "Information",
    component: Information
  },
  {
    path: "/myPage",
    name: "MyPage",
    component: MyPage
  },
];

const router = new VueRouter({
  mode: "history",
  base: process.env.BASE_URL,
  routes
});

export default router;
