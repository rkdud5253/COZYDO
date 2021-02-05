<template>
  <v-app>
  <v-form style="margin: 50px 50px;" ref="form" v-model="valid" lazy-validation>
    <v-text-field
      class="pt-3 pb-3"
      v-model="member.email"
      :counter="30"
      :rules="emailRules"
      label="이메일"
      required
      type="email"
      hint="이메일 주소 입력"
      prepend-icon="mdi-email"
    ></v-text-field>
    <v-text-field
      class="pt-3 pb-3"
      v-model="member.name"
      :counter="10"
      :rules="nameRules"
      label="이름"
      required
      type="text"
      prepend-icon="mdi-account"
    ></v-text-field>
    <v-text-field
      class="pt-3 pb-3"
      v-model="member.nickname"
      :counter="6"
      :rules="nicknameRules"
      label="닉네임"
      required
      hint="한글, 영문, 숫자 2~6자 이내"
      type="text"
      prepend-icon="mdi-face"
    ></v-text-field>
    <!-- <v-text-field
      v-model="email"
      :rules="emailRules"
      label="E-mail"
      required
    ></v-text-field> -->
    <v-text-field
      class="pt-3 pb-3"
      :rules="passwordRules"
      required
      v-model="member.password"
      label="비밀번호"
      type="password"
      :counter="20"
      prepend-icon="mdi-lock"
      hint="영문, 숫자 조합 8~20자 이내"
    ></v-text-field>
    <v-text-field
      class="pt-3 pb-3"
      :rules="validatePasswordRules"
      required
      v-model="member.validatePassword"
      :counter="20"
      label="비밀번호 확인"
      type="password"
      prepend-icon="mdi-lock"
    ></v-text-field>
    <!-- <v-select
      v-model="select"
      :items="items"
      :rules="[v => !!v || 'Item is required']"
      label="Item"
      required
    ></v-select> -->

    <!-- <v-checkbox
      v-model="checkbox"
      :rules="[v => !!v || 'You must agree to continue!']"
      label="Do you agree?"
      required
    ></v-checkbox> -->

    <!-- <v-btn :disabled="!valid" color="success" class="mr-4" @click="validate">
      회원가입
    </v-btn> -->
    <v-btn
      style="width:100%; height:40px; margin-top: 30px;"
      color="#fc355d"
      dark
      @click="joinRequest"
      >회원가입</v-btn
    >
    <!-- :loading="loadingState" -->

    <!-- <v-btn
      color="error"
      class="mr-4"
      @click="reset"
    >
      Reset Form
    </v-btn> -->

    <!-- <v-btn
      color="warning"
      @click="resetValidation"
    >
      Reset Validation
    </v-btn> -->
  </v-form>
    <p style="margin: 0 auto;" class="text--secondary">
      이미 회원이신가요? <a> 로그인 </a>
    </p>
  </v-app>
</template>
<script>
export default {
  data() {
    return {
      valid: true,
      nameRules: [
        (v) => !!v || "이름을 입력해주세요",
        (v) => (v && v.length <= 10) || "10자리 이하 입력",
      ],
      passwordRules: [
        (v) => !!v || "비밀번호를 입력해주세요",
        // (v) => (v && v.length >= 8 && v.length <= 20) || "8~20자리 입력",
        (v) => /^(?=.*[A-Za-z])(?=.*\d)[A-Za-z\d$@$!%*#?&]{8,20}$/.test(v) || "문자, 숫자 조합 8~20자리 입력",
      ],
      validatePasswordRules: [
        (v) => !!v || "비밀번호를 입력해주세요",
        (v) => (v === this.member.password) || "비밀번호 불일치",
      ],
      emailRules: [
          (v) => !!v || "이메일을 입력해주세요",
          (v) => /.+@.+\..+/.test(v) || "유효한 이메일이 아닙니다",
      ],
      nicknameRules: [
        (v) => !!v || "닉네임을 입력해주세요",
        (v) => (v && v.length >= 2 && v.length <= 10) || "2~6자리 입력",
      ],
      member: {
        email: "",
        password: "",
        name: "",
        nickname: "",
        validatePassword: "",
        // role: "USER",
      },
        
      //   select: null,
      //   items: [
      //     'Item 1',
      //     'Item 2',
      //     'Item 3',
      //     'Item 4',
      //   ],
      //   checkbox: false,
    };
  },
  methods: {
    validate() {
      this.$refs.form.validate();
    },
    joinRequest(){
        if(this.$refs.form.validate()){
            // this.START_LOADING();
            // this.REQUEST_JOIN(this.member);
            console.log('회원가입 가능')
        }else{
          console.log('회원가입 불가')
        }
    },
    //   reset () {
    //     this.$refs.form.reset()
    //   },
    //   resetValidation () {
    //     this.$refs.form.resetValidation()
    //   },
  },
};
</script>
