import Vue from "vue";
import VueRouter from "vue-router";
import Main from "../views/Main.vue";
import SearchResultList from "../views/search/SearchResultList.vue";
import SearchResultMap from "../views/search/SearchResultMap.vue";
import PlaceDetail from "../views/place/PlaceDetail.vue";
import PlaceModify from "../views/place/PlaceModify.vue";
import PlaceReview from "../views/place/PlaceReview.vue";
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
    path: "/placeDetail/:placeIdx",
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
];

const router = new VueRouter({
  mode: "history",
  base: process.env.BASE_URL,
  routes
});

export default router;
