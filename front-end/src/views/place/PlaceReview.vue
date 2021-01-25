<template>
  <v-main>
    <v-container style="margin-top: 30px">
      <h2 style="font-weight: bold; text-align: center; margin-bottom: 10px">
        리뷰 작성
      </h2>
      <v-divider></v-divider>
      <div class="text-center mt-12">
        <v-rating
          v-model="rating"
          background-color="orange lighten-3"
          color="orange"
          medium
        ></v-rating>
      </div>
      <div style="max-width: 400px; margin: 15px 25px">
        <v-textarea
          counter
          label="리뷰를 입력하세요"
          :rules="rules"
          solo
          auto-grow
          rows="4"
          row-height="20"
          depressed
        ></v-textarea
        ><v-btn style="width: 100%; margin-top: 15px" color="primary" dark
          >리뷰 등록
        </v-btn>
      </div></v-container
    ></v-main
  >
</template>
<script>
export default {
  data: () => ({
    rating: 0,
    rules: [(v) => v.length <= 300 || "Max 300 characters"],
  }),
};
</script>
