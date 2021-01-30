<template>
  <v-form style="margin: 30px 40px;" ref="form" v-model="valid" lazy-validation>
    <v-text-field
      v-model="member.id"
      :counter="12"
      :rules="nameRules"
      label="아이디"
      required
      type="text"
      hint="영문, 숫자 6~12자 이내"
      prepend-icon="mdi-account"
    ></v-text-field>
    <v-text-field
      v-model="member.name"
      :counter="10"
      :rules="nameRules"
      label="이름"
      required
      type="text"
      prepend-icon="mdi-account"
    ></v-text-field>
    <v-text-field
      v-model="member.nickname"
      :counter="6"
      :rules="nameRules"
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
      :rules="validatePasswordRules"
      required
      v-model="validatePassword"
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
      style="width: 100%; margin-top: 45px"
      color="#E91E63"
      dark
      @click="joinRequest"
      :loading="loadingState"
      >회원가입</v-btn
    >

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
      //   email: "",
      //   emailRules: [
      //     (v) => !!v || "E-mail is required",
      //     (v) => /.+@.+\..+/.test(v) || "E-mail must be valid",
      //   ],
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
    // joinRequest(){
    //     if(this.$refs.form.validate()){
    //         this.START_LOADING();
    //         this.REQUEST_JOIN(this.member);
    //     }
    // },
    //   reset () {
    //     this.$refs.form.reset()
    //   },
    //   resetValidation () {
    //     this.$refs.form.resetValidation()
    //   },
  },
};
</script>
