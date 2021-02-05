<template>
  <div>
    <!-- v-app으로 감싸버리면 wrap적용돼서 빈칸생겨서 div로 감쌌다 -->
    <!-- fixed와 app의 차이! app은 위에 여유칸 만들어줌 -->

    <v-bottom-navigation
      :value="value"
      grow
      app
      height="50px"
      color="#fc355d"
      background-color="#F5F5F5"
    >
      <v-btn
        class="botton"
        @click="goInformaion"
      >
        <span><h4>Infomation</h4></span>
        <v-icon>mdi-comment-text-outline</v-icon>
      </v-btn>

      <div class="botton mx-4">
        <v-btn
          @click="goHome"
          class="rounded-circle"
          top
          absolute
          fab
          small
          height="80px"
          color="#fc355d"
          style="box-shadow: 0px 0px 5px 0.3px #fc355d"
        >
          <span style="color: white"><h4>COZYDO</h4></span>
          <v-icon style="color: white">mdi-map-marker</v-icon>
        </v-btn>
      </div>

      <v-btn class="botton">
        <span><h4>My Page</h4></span>
        <v-icon>mdi-account-circle</v-icon>
      </v-btn>
    </v-bottom-navigation>

  </div>
</template>

<script>
export default {
  name: "BottomNav",
  data: () => ({ value: 1 }),
  methods: {
    goHome() {
      // this.$router.go안에 안넣어주면 console에 에러남(같은 페이지기 때문)
      this.$router.go(this.$router.push({name: 'Main'}))
    },
    goInformaion() {
      this.$router.go(this.$router.push({name: 'Information'}))
    },
  }
};
</script>

<style scoped>
  .botton {
    height: 50px !important;
  }
</style>