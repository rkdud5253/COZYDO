<!-- 로그인 -->
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
              alert(this.$store.getters.getUserName + "님 환영합니다.")
              this.$router.replace(`/`)
            }
            else{
              alert(this.$store.getters.getMessage)
              this.user.password = ''
            }
          }
        )
        .catch(({ message }) => (this.msg = message))
    },
    
    goRegister() {
      this.$router.replace(`/register`)
    },
  },
}
</script>