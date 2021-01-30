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
    
<v-btn
      style="width: 100%; margin-top: 20px; padding:25px 0;"
      color="#2DB400"
      dark
      @click="naverLogin"
      >네이버로 로그인</v-btn
    >
    <!-- <v-btn @click="redirect_to_login"><img src="../../../public/img/final-naver-login.png">
        </v-btn> -->
        <!-- style="height:50px; object-fit:contain;background-size: cover;" -->
        <!-- <v-img style="" src="../../../public/img/final_naver_login.png"></v-img> -->
    <v-btn
      style="width: 100%; margin-top: 20px; padding:25px 0;"
      color="#FFE812"
      dark
      @click="kakaoLogin"
      >카카오계정으로 로그인</v-btn
    >
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
