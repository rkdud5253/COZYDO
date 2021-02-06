<template>
  <div>
    <v-form
      style="margin: 50px 40px;"
      ref="form"
      v-model="valid"
      lazy-validation
    >
    <v-text-field
      v-model="member.id"
      :rules="nameRules"
      label="아이디"
      required
      type="text"
      prepend-icon="mdi-account"
    ></v-text-field>
    <v-text-field
      :rules="passwordRules"
      required
      v-model="member.password"
      label="비밀번호"
      type="password"
      prepend-icon="mdi-lock"
    ></v-text-field>
    <v-btn
      style="width: 100%; margin-top: 45px; padding:25px 0;"
      color="#F85F6A"
      dark
      @click="loginRequest"
      >로그인</v-btn
    >
<!--     
<v-btn
      style="width: 100%; margin-top: 20px; padding:25px 0;"
      color="#2DB400"
      dark
      @click="naverLogin"
      >네이버로 로그인</v-btn
    > -->
    <!-- <v-btn @click="redirect_to_login"><img src="../../../public/img/final-naver-login.png">
        </v-btn> -->
        <!-- style="height:50px; object-fit:contain;background-size: cover;" -->
        <!-- <v-img style="" src="../../../public/img/final_naver_login.png"></v-img> -->
  
   <div id="kakao-login">

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
    </div>
    <!-- <v-btn
      style="width: 100%; margin-top: 20px; padding:25px 0;"
      color="#FFE812"
      dark
      @click="kakaoLogin"
      >카카오계정으로 로그인</v-btn
    > -->
    <div style="width:100%; margin-top:30px;">
        <v-btn style="width:49%;"
      text
      color="grey"
    >
      비밀번호 찾기
    </v-btn>
    <v-btn 
    style="width:49%;"
      text
      color="#F85F6A"
    >
      회원가입
    </v-btn>
    </div>
      <!-- <v-text-field
      v-model="member.id"
      :counter="12"
      :rules="nameRules"
      label="아이디"
      required
      type="text"
      hint="영문, 숫자 6~12자 이내"
      prepend-icon="mdi-account"
    ></v-text-field> -->
      <!-- <input placeholder="Enter your ID" v-model="uid" />
      <input placeholder="Enter your password" v-model="password" /> -->
      <!-- <v-btn @click="loginRequest">Login</v-btn> -->
    </v-form>
  </div>
</template>
<script>
import axios from "axios";
export default {
    data() {
        return {
            valid: true,
      name: "",
      nameRules: [
        (v) => !!v || "Name is required",
        (v) => (v && v.length <= 10) || "Name must be less than 10 characters",
      ],
      passwordRules: [(v) => !!v || "Password is required"],
      member: {
        id: "",
        password: "",
        name: "",
        nickname: "",
        role: "USER",
      },
        }
    },

//   data: () => ({
//     uid: "",
//     password: "",
//   }),
  methods: {
    kakaoLogin() {
                // console.log(window.Kakao);
                window.Kakao.Auth.login({
                    scope : 'account_email, gender',
                    success: this.GetMe,
                });
            },
            GetMe(authObj){
                // console.log(authObj);
                window.Kakao.API.request({
                    url:'/v2/user/me',
                    success : res => {
                        const kakao_account = res.kakao_account;
                        const userInfo = {
                            nickname : kakao_account.profile.nickname,
                            email : kakao_account.email,
                            password : '',
                            account_type : 2,
                        }

                         axios.post(`http://localhost:8080/account/kakao`,{
                             email : userInfo.email,
                             nickname : userInfo.nickname
                         })
                         .then(res => {
                            console.log(res);
                            console.log("데이터베이스에 회원 정보가 있음!");
                         })
                         .catch(err => {
                             console.log(err);
                            console.log("데이터베이스에 회원 정보가 없음!");
                         })
                        console.log(userInfo);
                        alert("로그인 성공!");
                        this.$bvModal.hide("bv-modal-example");
                    },
                    fail : error => {
                        this.$router.push("/errorPage");
                        console.log(error);
                    }
                })
            },
    // onSubmit() {
    //   console.log(this.uid);
    //   console.log(this.password);
    // },
    validate() {
      this.$refs.form.validate();
    },
  },
};
</script>
