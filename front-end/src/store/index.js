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
    message : ''
  },
  getters: {
    
    getLoginChk(state) {
      if (localStorage.loginChk) return localStorage.loginChk
      return state.loginChk
    },
    getEmail(state) {
      if (localStorage.email) return localStorage.email
      return state.email
    },
    getUserIdx(state) {
      if (localStorage.userIdx) return localStorage.userIdx
      return state.userIdx
    },
    getUserName(state) {
      if (localStorage.userNickname) return localStorage.userNickname
      return state.userNickname
    },
    getMessage(state) {
      return state.message
    },

  },
  mutations: {
    // payload 가 response
    LOGIN(state, payload) {
      // state.accessToken = accessToken
      if (payload['status'] === false) {
        // 로그인 실패
        this.loginChk = false
        state.message = payload['data']
      } else {
        state.loginChk = true
        state.email = payload['object'].email;
        localStorage.email = state.email
        state.userNickname = payload['object'].nickname;
        localStorage.userNickname = state.userNickname
        state.userIdx = payload['object'].idx
        localStorage.userIdx = state.userIdx
        localStorage.accessToken = payload['data']
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
        context.commit('LOGIN', response.data)
      })
    },
    LOGOUT(context) {
      context.commit('LOGOUT')
    },
  },
  modules: {},
})