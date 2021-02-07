<template>
  <v-app>

    <v-card
    class="mx-3 my-3"
    outlined
    >
      <v-list-item three-line>
        <v-list-item-content>
          <div class="overline" style="color: #fc355d">
            My Page
          </div>
          <v-list-item-title>
            <p style="font-size: small; font-weight: bolder;"><span style="font-size: x-large;">갓싸피</span>님, 안녕하세요!</p>
          </v-list-item-title>
          <v-list-item-subtitle>
            <h5>godssafy@naver.com</h5>
          </v-list-item-subtitle>
        </v-list-item-content>

        <v-card-actions>
          <v-btn
            outlined
            text
            small
            class="mb-13"
          >
            로그아웃
          </v-btn>
        </v-card-actions>

      </v-list-item>
    </v-card>

    <MyPagePlace />
    <MyPageReview />

    <v-btn
      class="user_delete_btn"
      color="#fc355d"
      dark
      small
      depressed
    >
      회원탈퇴
    </v-btn>

  </v-app>
</template>

<script>
import MyPagePlace from "@/components/mypage/MyPagePlace.vue"
import MyPageReview from "@/components/mypage/MyPageReview.vue"
export default {
  name: "MyPage",
  components: {
    MyPagePlace,
    MyPageReview,
  },
};
</script>

<style>
  .user_delete_btn {
    max-width: 70px;
    align-self: center;
  }
</style>