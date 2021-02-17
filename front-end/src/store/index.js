import Vue from 'vue'
import Vuex from 'vuex'
import axios from 'axios'

Vue.use(Vuex)

export default new Vuex.Store({
  state: {
    email: '',
    userNickname: '',
    userIdx: '',
    loginChk: false,
  },
  getters: {
    
    getLoginChk(state) {
      if (localStorage.loginChk) return localStorage.loginChk
      return state.loginChk
    },
    getEmail(state) {
      console.log('로그인 email')
      if (localStorage.email) return localStorage.email
      return state.email
    },
    getUserIdx(state) {
      console.log('로그인 userIdx')
      if (localStorage.userIdx) return localStorage.userIdx
      return state.userIdx
    },
    getUserName(state) {
      console.log("state.userNicname은"+state.userNickname)
      if (localStorage.userNickname) return localStorage.userNickname
      return state.userNickname
    },

  },
  mutations: {
    // payload 가 response
    LOGIN(state, payload) {
      // state.accessToken = accessToken
      if (payload['status'] === false) {
        // 로그인 실패
        console.log('로그인 실패')
        this.loginChk = false
        this.message = payload['data']
      } else {
        console.log('로그인 성공')
        state.loginChk = true
        state.email = payload['object'].email;
        localStorage.email = state.email
        state.userNickname = payload['object'].nickname;
        localStorage.userNickname = state.userNickname
        state.userIdx = payload['object'].idx
        console.log(payload['object'].idx)
        console.log(state.userIdx)
        localStorage.userIdx = state.userIdx
        localStorage.accessToken = payload['data']
        console.log(localStorage.accessToken)
      }
    },
    LOGOUT(state) {
      state.userNickName = ''
      localStorage.clear()
      state.loginChk = false
    },
  },
  actions: {
    LOGIN(context, user) {
      return axios({
        method: 'post',
        url: 'https://i4a201.p.ssafy.io:8080/user/login',

        params: {
          email: user.email,
          password: user.password,
        },
      }).then((response) => {
        console.log(response)
        context.commit('LOGIN', response.data)
      })
    },
    LOGOUT(context) {
      context.commit('LOGOUT')
    },
  },
  modules: {},
})
