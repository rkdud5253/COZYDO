// import Vue from "vue";
// import Vuex from "vuex";
// import axios from "axios";
// // import router from "../router/index"

// Vue.use(Vuex);

// // const SERVER_URL = "https://localhost:3000"

// export default new Vuex.Store({
//   state: {
//     accessToken:null,
//     // userId:"",
//     // userNickName:"",
//     loginChk:false,
//   },
//   getters:{
//     getAccessToken(state) {
//       if (localStorage.accessToken && typeof localStorage.accessToken != "undefined") 
//           return localStorage.accessToken;
//       else 
//           return state.accessToken;
//       }
//   ,
//   // getUserId(state) {
//   //   console.log("로그인 상태")
//   //     if (localStorage.userId) 
//   //         return localStorage.userId;
//   //     return state.userId;
//   // },
//   // getUserName(state) {
//   //     if (localStorage.userNickName) 
//   //         return localStorage.userNickName;
//   //     return state.userNickName;
//   // }
//   },
//   mutations: {
//     LOGIN(state, payload) {
//       console.log(payload);
//       // state.accessToken = accessToken
//       if (payload["message"] == "false") { // 로그인 실패
//           this.$router.replace(`/`);
//           this.loginChk = false;
//       } else {
//           this.loginChk = true;
//           state.accessToken = payload["auth-token"];
//           // state.userId = payload["email"];
//           // state.userNickName = payload["user-nickname"];
//           localStorage.accessToken = state.accessToken;
//           // localStorage.userId = state.userId;
//           // localStorage.userNickName = state.userNickName;
//       }
//   },
//   LOGOUT(state) {
//       state.accessToken = null;
//       // state.userId = "";
//       // state.userNickName = "";
//       localStorage.clear();
//       this.loginChk = false;
//   },
//   },
//   actions: {
//     LOGIN(context, user) {
//       // axios     .defaults     .headers     .post['Access-Control-Allow-Origin'] =
//       // '*';
//       return axios
      
//           .post("https://i4a201.p.ssafy.io:8080/user/login", user)
//           .then((response) => {
//             console.log("로그인됨");
//               context.commit("LOGIN", response.data);
//               axios
//                   .defaults
//                   .headers
//                   .common["auth-token"] = `${response
//                   .data["auth-token"]}`;
//           });
//   },
//   LOGOUT(context) {
//       context.commit("LOGOUT");
//       axios
//           .defaults
//           .headers
//           .common["auth-token"] = undefined;
//   }
//   },
//   modules: {}
// });
