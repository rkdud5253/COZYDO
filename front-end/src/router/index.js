import Vue from "vue";
import VueRouter from "vue-router";
import Main from "../views/Main.vue";
import SearchResultList from "../views/search/SearchResultList.vue";
import SearchResultMap from "../views/search/SearchResultMap.vue";
import PlaceDetail from "../components/place/PlaceDetail.vue";
import Register from "../views/member/Register.vue";
import Login from "../views/member/Login.vue";
import Information from "../views/Information.vue";
import MyPage from "../views/member/MyPage.vue";
import Authentication from "../views/member/Authentication.vue";
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
    component: SearchResultList,
    meta: {
      title: '코지도 | 장소검색',
    },
  },
  {
    path: "/searchResultMap",
    name: "SearchResultMap",
    component: SearchResultMap,
    meta: {
      title: '코지도 | 장소검색',
    },
  },
  {
    path: "/placeDetail/:placeIdx/:level",
    name: "PlaceDetail",
    component: PlaceDetail,
    meta: {
      title: '코지도 | 장소상세',
    },
  },
  {
    path: "/register",
    name: "Register",
    component: Register,
    meta: {
      title: '코지도 | 회원가입',
    },
  },
  {
    path: "/login",
    name: "Login",
    component: Login,
    meta: {
      title: '코지도 | 로그인',
    },
  },
  {
    path: "/information",
    name: "Information",
    component: Information,
    meta: {
      title: '코지도 | 코로나정보',
    },
  },
  {
    path: "/myPage",
    name: "MyPage",
    component: MyPage,
    meta: {
      title: '코지도 | 마이페이지',
    },
  },
  {
    path: "/authentication",
    name: "Authentication",
    component: Authentication,
  },
];

const router = new VueRouter({
  mode: "history",
  base: process.env.BASE_URL,
  routes,
  linkActiveClass: 'active-link',
    linkExactActiveClass: 'exact-active-link',
    scrollBehavior (to, from, savedPosition) {
      if (savedPosition) {
        return savedPosition;
      } else {
        return { x: 0, y: 0 };
      }
    },
});
router.beforeEach((to, from, next) => {
  /* It will change the title when the router is change*/
  /* 페이지 제목 바꾸기 */
  if (to.meta.title) {
    document.title = to.meta.title;
  }
  next();
});

export default router;
