<template>
  <v-main>
    <v-container style="margin-top: 30px">
      <div>
        <!-- <a href="#" onClick = "history.back();"> -->
        <!-- <v-icon
          class="goback"
          color="pink"
          large
          @click="goBack"
          style="float:left;"
        >
          mdi-chevron-left
        </v-icon> -->
        <h2
          style="font-weight: bold; text-align: center; margin-bottom: 10px; padding-right: 35px;"
        >
          리뷰 작성
        </h2>
      </div>
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
import axios from "axios";
export default {
  data: () => ({
    rating: 0,
    items: [],
    rules: [(v) => v.length <= 300 || "Max 300 characters"],
    // placeIdx: this.$route.query.placeIdx, // 검색 결과 list에서 넘어오는 장소 Idx
    // level: this.$route.query.level,
  }),
  created: function() {
    axios
      .get("http://i4a201.p.ssafy.io:8080/map/detail", {
        //get방식, url 확인
        params: {
          // 넘겨줄 파라미터
          placeIdx: this.placeIdx,
          level: this.level,
        },
      })
      .then((res) => {
        // 통신 성공하면
        console.log(res);
        this.items = res.data; // 데이터 받아온다
      })
      .catch((err) => {
        console.log(err);
      });
  },
  methods: {
    // onChangeDetail() {
    //   this.$router.push({
    //     name: "PlaceDetail",
    //     params: { placeIdx: this.placeIdx, level: this.level },
    //   });
    // },
  },
};
</script>
<style>
.goback {
  margin-bottom: 5%;
}
</style>
