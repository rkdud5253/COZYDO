<template>
  <v-main>
    <v-container>
      <table id="place">
        <tr>
          <td></td>
          <td style="float:right;">
            <v-col style="float:right" cols="12" sm="3">
              <v-btn
                id="heartIcon"
                v-on:click="heartBtn"
                icon
                v-bind:style="heartStyle"
              >
                <v-icon>mdi-heart</v-icon>
              </v-btn>
            </v-col>
          </td>
          <td style="text-align:right; padding-left:20px;">
            <!-- <v-btn color="primary"><v-icon>mdi-question-mark-circle</v-icon></v-btn> -->
          </td>
        </tr>
        <tr>
          <td rowspan="3" style="max-width:100px;">
            <v-img id="placeIcon" src="../../../public/img/cafe.png"></v-img>
          </td>
          <td colspan="2" style="float:left;">{{ placeName }}</td>
        </tr>
        <tr>
          <td colspan="2" style="float:left;">{{ placeLocation }}</td>
        </tr>
        <tr>
          <td colspan="2" style="float:left;">{{ placePhone }}</td>
        </tr>
        <br />
      </table>
    </v-container>

    <br />
    <div style="margin-left:20px;padding-left:10px;">
      <h3>
        영업시간
        <span style="margin-left:30px;color:grey;">{{ openingHours }}</span>
      </h3>
      <hr style="width:90%;text-align:center;" />
    </div>
    <br />
    <div style="text-align:center;">
      <v-btn
        id="reviewBtn"
        style="padding:0 35px;"
        rounded
        color="secondary"
        dark
        depressed
        outlined
        @click="onClickReview()"
      >
        리뷰 남기기
      </v-btn>
      <v-btn
        id="modifyBtn"
        style="margin-left:30px;"
        rounded
        color="secondary"
        dark
        depressed
        outlined
        @click="onClickModify()"
      >
        가게 정보 수정 요청
      </v-btn>
    </div>
    <v-list two-line>
      <v-subheader>리뷰 {{ reviewNum }}개</v-subheader>
      <v-list-item-group v-model="selected" active-class="pink--text" multiple>
        <template v-for="(item, index) in items">
          <v-list-item :key="item.nickname">
            <!-- <template v-slot:default="{ active }"> -->
            <v-list-item-content>
              <v-rating
                v-model="rating"
                background-color="pink lighten-3"
                color="pink"
                medium
                readonly
              ></v-rating>
              <!-- <v-list-item-title  v-text="item.title"></v-list-item-title> -->
              <div style="padding-left:10px;">
                <v-list-item-nickname>
                  <span v-text="item.nickname"></span>
                  <span
                    style="margin-left: 20px; color:grey;"
                    v-text="item.dates"
                  ></span>
                </v-list-item-nickname>
                <!-- <v-list-item-review
                  class="text--primary"
                  v-text="item.headline"
                ></v-list-item-review> -->
                <br /><br />
                <v-list-item-review v-text="item.review"></v-list-item-review>
              </div>
            </v-list-item-content>

            <!-- <v-list-item-action>
                <v-list-item-action-text
                  v-text="item.action"
                ></v-list-item-action-text> -->

            <!-- <v-icon v-if="!active" color="grey lighten-1">
                  mdi-star-outline
                </v-icon>

                <v-icon v-else color="yellow darken-3">
                  mdi-star
                </v-icon> -->
            <!-- </v-list-item-action> -->
            <!-- </template> -->
          </v-list-item>

          <v-divider v-if="index < items.length - 1" :key="index"></v-divider>
        </template>
      </v-list-item-group>
    </v-list>
  </v-main>
</template>
<script>
export default {
  data() {
    return {
      placeName: "스타벅스",
      placeLocation: "서울시 강남구 역삼동",
      placePhone: "1588-1588",
      heartStyle: "color:grey",
      openingHours: "10:00 - 21:00",
      reviewNum: 3,
      rating: 4,
      items: [
        {
          nickname: "신꼬맹",
          dates: "21.01.03",
          review: "저는 커피 말고 아샷추 추천이요~",
        },
        {
          nickname: "갓오브갓 프론트팀장 가영",
          dates: "21.01.04",
          review:
            "9시 까지 운영하는 줄 알았는데 저희 동네 스타벅스는 8시까지 하더라고요! 참고하세요~",
        },
        {
          nickname: "다운팀장",
          dates: "21.01.21",
          review: "난 커피를 별로 좋아하지 않아요",
        },
      ],
    };
  },
  methods: {
    heartBtn() {
      this.heartStyle = "color:red";
      //  var hIcon = document.getElementById("heartIcon");
      //  hIcon.style.color= "red !important";
    },
    onClickReview(){
        this.$router.replace(`/placeReview`);
    },
    // onClickReview() {()=>this.$router.replace(`/placeReview`)},
    onClickModify() {
      this.$router.replace(`/placeModify`);
    },
  },
};
</script>
<style>
#place {
  border: 1px solid black;
  padding: 10px 10px;
  margin: 20px;
  width: 90%;
}
#placeIcon {
  width: 65%;
}
#reviewBtn,
#modifyBtn {
  background-color: white !important;
  color: black;
}
</style>
