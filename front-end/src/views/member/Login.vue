<template>
  <div>
    <v-form style="margin: 50px 40px" ref="form" v-model="valid" lazy-validation>
      <v-text-field
        v-model="user.email"
        :rules="emailRules"
        label="이메일"
        required
        type="text"
        prepend-icon="mdi-account"
      ></v-text-field>
      <v-text-field
        :rules="passwordRules"
        required
        v-model="user.password"
        label="비밀번호"
        type="password"
        prepend-icon="mdi-lock"
        @keypress.enter="loginRequest"
      ></v-text-field>
      <v-btn
        style="width: 100%; margin-top: 45px; padding: 25px 0"
        color="#fc355d"
        dark
        @click="loginRequest"
        >로그인</v-btn
      >

      <!-- <div id="kakao-login">

        <button @click="kakaoLogin">
            <svg
                xmlns="http://www.w3.org/2000/svg"
                width="55"
                height="55"
                viewBox="0 0 55 55">
                <g id="그룹_247" data-name="그룹 247" transform="translate(-237 -406)">
                    <g id="구성_요소_2" data-name="구성 요소 2" transform="translate(237 406)">
                        <g id="타원_6" data-name="타원 6" fill="#ffd800" stroke="#eee" stroke-width="1">
                            <circle cx="27.5" cy="27.5" r="27.5" stroke="none"/>
                            <circle cx="27.5" cy="27.5" r="27" fill="none"/>
                        </g>
                    </g>
                    <g id="kakaotalk_1_" data-name="kakaotalk (1)" transform="translate(254 424)">
                        <path
                            id="패스_288"
                            data-name="패스 288"
                            d="M76.01,74.216h1.229l-.615-1.746Z"
                            transform="translate(-68.211 -65.034)"/>
                        <path
                            id="패스_289"
                            data-name="패스 289"
                            d="M10.671,0C4.777,0,0,3.767,0,8.414c0,3,2,5.643,5,7.129-.163.563-1.051,3.626-1.086,3.867,0,0-.022.181.1.249a.322.322,0,0,0,.254.015c.337-.047,3.9-2.546,4.515-2.976a13.5,13.5,0,0,0,1.89.128c5.894,0,10.671-3.767,10.671-8.414S16.565,0,10.671,0ZM5.39,7.108c-.013,1.19.01,2.442-.009,3.614-.006.375-.222.486-.513.593a.193.193,0,0,1-.1.007c-.333-.066-.6-.185-.607-.6-.024-1.171.007-2.425-.009-3.615-.282-.011-.684.011-.946,0a.577.577,0,0,1-.6-.6.565.565,0,0,1,.606-.6c.962-.014,2.155-.014,3.117,0a.561.561,0,0,1,.605.6.578.578,0,0,1-.6.6C6.074,7.119,5.673,7.1,5.39,7.108Zm5.171,4.15a.948.948,0,0,1-.392.085.505.505,0,0,1-.513-.272l-.308-.8H7.475l-.308.8a.506.506,0,0,1-.513.272.94.94,0,0,1-.391-.085c-.17-.078-.333-.293-.147-.874L7.593,6.507A.923.923,0,0,1,8.414,5.9a.931.931,0,0,1,.821.608l1.471,3.874C10.894,10.965,10.731,11.184,10.561,11.258Zm3.111,0H11.7a.579.579,0,0,1-.59-.564V6.516a.629.629,0,0,1,1.257,0v3.617h1.308a.565.565,0,1,1,0,1.129Zm4.823-.464a.616.616,0,0,1-1.1.287L15.947,9.17l-.213.213v1.343a.616.616,0,1,1-1.231,0V6.52a.616.616,0,0,1,1.231,0v1.32l1.718-1.718a.476.476,0,0,1,.342-.137.624.624,0,0,1,.605.573.482.482,0,0,1-.134.375l-1.406,1.4,1.516,2.008a.616.616,0,0,1,.118.456Z"/>
                    </g>
                </g>
            </svg>

        </button>
    </div> -->
      <!-- <v-btn
      style="width: 100%; margin-top: 20px; padding:25px 0;"
      color="#FFE812"
      dark
      @click="kakaoLogin"
      >카카오계정으로 로그인</v-btn
    > -->
      <div style="width: 100%; margin-top: 30px">
        <v-btn style="width: 49%" text color="grey"> 비밀번호 찾기 </v-btn>
        <v-btn style="width: 49%" @click="goRegister" text color="#fc355d"> 회원가입 </v-btn>
      </div>
    </v-form>
  </div>
</template>
<script>
import Vue from 'vue'
import VueRouter from 'vue-router'
Vue.use(VueRouter)
export default {
  data() {
    return {
      valid: true,
      emailRules: [
        (v) => !!v || '이메일을 입력해주세요',
        (v) => /.+@.+\..+/.test(v) || '유효한 이메일이 아닙니다',
      ],
      passwordRules: [
        (v) => !!v || '비밀번호를 입력해주세요',
        // (v) => (v && v.length >= 8 && v.length <= 20) || "8~20자리 입력",
        (v) =>
          /^(?=.*[A-Za-z])(?=.*\d)[A-Za-z\d$@$!%*#?&]{8,20}$/.test(v) ||
          '문자, 숫자 조합 8~20자리 입력',
      ],

      user: {
        email: '',
        password: '',
      },
      message: '',
    }
  },
  methods: {
    loginRequest() {
      this.$store
        .dispatch('LOGIN', this.user)
        .then(
          () => {
          if(this.$store.getters.getLoginChk == true){
              alert(localStorage.userNickname + " 님 환영합니다.")
              this.$router.replace(`/`)
            }
            else{
              alert("아이디와 비밀번호를 확인하세요.")
              this.user.password = ''
            }
          }
        )
        .catch(({ message }) => (this.msg = message))
      // this.$router.replace(`/login`)

      // 로그인 DB와 연동해서 구현, store안썼을 때,
      // axios({
      //   method: "post",
      //   url: "https://i4a201.p.ssafy.io:8080/user/login",

      //   params: {
      //     email: this.email,
      //     password: this.password,
      //   },
      // })
      //   .then((res) => {

      //     if (res.data.status == true) {
      //       console.log("로그인 완료");
      //       this.$router.replace(`/`);
      //     } else if (res.data.status == false) {

      //       alert("로그인 실패");
      //       console.log("로그인 실패");
      //       // this.$router.go(`/login`);
      //       history.go(0);
      //     }
      //   })
      //   .catch((err) => {
      //     console.log(err);
      //   });

      //     redirect(){
      //       const { search } = window.location
      // const tokens = search.replace(/^\?/, "").split("&")
      // const { returnPath } = tokens.reduce((qs, tkn) => {
      //   const pair = tkn.split("=")
      //   qs[pair[0]] = decodeURIComponent(pair[1])
      //   return qs
      // }, {})

      // // 리다이렉트 처리
      // this.$router.push(returnPath)
      //     },
      // kakaoLogin() {
      //             // console.log(window.Kakao);
      //             window.Kakao.Auth.login({
      //                 scope : 'account_email, gender',
      //                 success: this.GetMe,
      //             });
      //         },
      //         GetMe(authObj){
      //             // console.log(authObj);
      //             window.Kakao.API.request({
      //                 url:'/v2/user/me',
      //                 success : res => {
      //                     const kakao_account = res.kakao_account;
      //                     const userInfo = {
      //                         nickname : kakao_account.profile.nickname,
      //                         email : kakao_account.email,
      //                         password : '',
      //                         account_type : 2,
      //                     }

      //                      axios.post(`http://localhost:8080/account/kakao`,{
      //                          email : userInfo.email,
      //                          nickname : userInfo.nickname
      //                      })
      //                      .then(res => {
      //                         console.log(res);
      //                         console.log("데이터베이스에 회원 정보가 있음!");
      //                      })
      //                      .catch(err => {
      //                          console.log(err);
      //                         console.log("데이터베이스에 회원 정보가 없음!");
      //                      })
      //                     console.log(userInfo);
      //                     alert("로그인 성공!");
      //                     this.$bvModal.hide("bv-modal-example");
      //                 },
      //                 fail : error => {
      //                     this.$router.push("/errorPage");
      //                     console.log(error);
      //                 }
      //             })
      //         },
    },
    // reset() {
    //   alert('가입하지 않은 아이디이거나, 잘못된 비밀번호입니다. 다시 로그인해주세요')
    //   history.go(0)
    // },
    goRegister() {
      this.$router.replace(`/register`)
    },
  },
}
</script>
