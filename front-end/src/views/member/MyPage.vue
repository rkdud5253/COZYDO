<!-- 마이페이지 -->
<template>
  <v-app>
    <v-card class="mx-5 mt-4 mb-2" outlined>
      <v-list-item three-line>
        <v-list-item-content>
          <div class="overline" style="color: #fc355d">
            My Page
          </div>
          <v-list-item-title>
            <p style="font-size: small; font-weight: bolder;">
              <span style="font-size: x-large;">{{ nickname }}</span
              >님, 안녕하세요!
            </p>
          </v-list-item-title>
          <v-list-item-subtitle>
            <h5>{{ email }}</h5>
          </v-list-item-subtitle>
        </v-list-item-content>

        <v-card-actions>
          <v-btn @click="onClickLogout" outlined text small class="mb-13">
            로그아웃
          </v-btn>
        </v-card-actions>
      </v-list-item>
    </v-card>

    <MyPagePlace :level="currentLevel"/>
    <MyPageReview :level="currentLevel"/>

    <v-btn
      class="user_delete_btn mt-8 mb-12"
      color="#fc355d"
      dark
      small
      depressed
      @click.stop="dialog = true"
    >
      회원탈퇴
    </v-btn>

    <!-- 탈퇴 모달창 -->
    <v-dialog v-model="dialog" max-width="290">
      <v-card>
        <v-card-title></v-card-title>

        <v-card-text class="user_delete_modal_text">
          <h4>정말 탈퇴하시겠습니까?</h4>
        </v-card-text>

        <v-card-actions class="user_delete_modal_btn">
          <v-btn class="mb-3" color="grey lighten-1" dark depressed small @click="dialog = false">
            취소하기
          </v-btn>

          <v-btn class="mb-3" color="#fc355d" dark depressed small @click="deleteUser">
            탈퇴하기
          </v-btn>
        </v-card-actions>
      </v-card>
    </v-dialog>
  </v-app>
</template>

<script>
import axios from "axios";
import MyPagePlace from '@/components/mypage/MyPagePlace.vue'
import MyPageReview from '@/components/mypage/MyPageReview.vue'
export default {
  name: 'MyPage',
  components: {
    MyPagePlace,
    MyPageReview,
  },
  data() {
    return {
      dialog: false,
      nickname: this.$store.getters.getUserName, // 회원 닉네임 store에서 불러오기
      email: this.$store.getters.getEmail,
      currentLevel: '',
    }
  },
  created() {
    axios
        .get("https://i4a201.p.ssafy.io:8080/crawling/location", {
          params: {
            lat: 37.5002441,
            lon: 127.0354716,
          },
        })
        .then((res) => {
          if (res.data) {
            // 서울 지역 레벨 받아오기 (1,2,3,4,5)
            this.currentLevel = parseFloat(res.data.object.level)*2-1
          }
        })
        .catch((err) => {
          console.log(err);
        }),
         axios
        .get("https://i4a201.p.ssafy.io:8080/mypage/review/list", {
          params: {
            userIdx: this.$store.getters.getUserIdx
          },
          headers:{
            "X-AUTH-TOKEN": localStorage.accessToken
          }
        })
        .then(() => {
        })
        .catch((err) => {
          console.log(err);
          alert("로그인 후 이용가능합니다.")
          this.$router.go(this.$router.push({ name: 'Login' }))
          localStorage.clear()
          
        });
  },
  methods: {
    // 로그아웃 기능 구현
    onClickLogout() {
      localStorage.clear()
      this.$store.dispatch('LOGOUT').then(() => this.$router.go(this.$router.replace(`/`)))
    },
    deleteUser(){
      axios.delete('https://i4a201.p.ssafy.io:8080/user/delete', {  
          params: {                                        
            email: this.$store.getters.getEmail
          }
        })
        .then((res) => {
          if(res.data.status == true){
               alert(res.data.data + "님 탈퇴 완료")
               this.$router.replace(`/`)
            }
            if(res.data.status == false){
              alert(res.data.data)
              this.$router.replace(`/`)
            }
          history.go(0)
        });
    }
  },
}
</script>

<style scoped>
.user_delete_btn {
  max-width: 70px;
  align-self: center;
}
.user_delete_modal_text {
  text-align: center;
}
.user_delete_modal_btn {
  justify-content: center;
}
</style>
