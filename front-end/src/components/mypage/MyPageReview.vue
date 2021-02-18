<!-- 마이페이지 내가 쓴 리뷰 -->
<template>
  <div class="mx-5 my-3">

    <v-card flat>
      <v-card-title>
        <v-icon color="#fc355d">
          mdi-file-document
        </v-icon>
        <h3 class="mx-1">내가 쓴 리뷰</h3>
      </v-card-title>
      
      <v-progress-linear
        color="#fc355d"
        value="45"
        buffer-value="100"
        rounded
      >
      </v-progress-linear>

      <!-- 리뷰 리스트 -->
      <v-expansion-panels 
        class="my-1"
        accordion
        multiple
        hover
      >
        <v-expansion-panel
          v-for="(placeReview,i) in placeReviews"
          :key="i"
        >
          <v-expansion-panel-header>
            <h4>{{ placeReview.placeName }}</h4>
          </v-expansion-panel-header>
          <v-expansion-panel-content class="contentStyle mt-1">
            {{ placeReview.content }}
            <v-icon @click="onChangeDetail(placeReview.placeIdx)" style="float:right;" color="#fc355d">mdi-arrow-right</v-icon>
          </v-expansion-panel-content>
        </v-expansion-panel>
      </v-expansion-panels>
      
    </v-card>

  </div>
</template>

<script>
import axios from 'axios';
export default {
  name: "MyPageReview",
  props: ['level'],
  data: function() {
    return {
      userIdx: this.$store.getters.getUserIdx,
      placeReviews: [],
    }
  },
  created() {
    this.loadReview();
  },
  methods: {
    loadReview() {
      axios.get("https://i4a201.p.ssafy.io:8080/mypage/review/list", {
          params: {
            userIdx: this.userIdx,
          },
           headers:{
            "X-AUTH-TOKEN": localStorage.accessToken
          }
        })
        .then((res) => {
          this.placeReviews = res.data
        })
        .catch((err) => {
          console.log(err);
        });
    },
    onChangeDetail(placeIdx) {
      this.$router.push({
        name: "PlaceDetail",
        params: { placeIdx: placeIdx, level: this.level },
      });
    },
  },
};
</script>

<style scoped>
  .v-expansion-panel--active > .v-expansion-panel-header {
    min-height: 40px;
  }
  .v-item-group {
    position: initial;
  }
  .v-card__subtitle, .v-card__text, .v-card__title {
    padding: 12px;
  }
</style>