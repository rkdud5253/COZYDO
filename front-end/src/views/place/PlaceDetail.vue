<template>
  <v-main>
    <v-container>
      <table id="place">
        <tr>
          <td></td>
          <td style="float:right;">
            <v-btn
              id="heartIcon"
              v-on:click="heartBtn"
              icon
              v-bind:style="heartStyle"
            >
              <v-icon>mdi-heart</v-icon>
            </v-btn>
          </td>
          <td>
            <div class="text-center">
              <v-dialog v-model="dialog" width="500">
                <template v-slot:activator="{ on, attrs }">
                  <v-btn
                    outlined
                    dark
                    v-bind="attrs"
                    icon
                    color="blue lighten-2"
                    v-on="on"
                    style="width:26px; height:26px;"
                  >
                    <v-icon style="font-size:18px;">mdi-help</v-icon>
                  </v-btn>
                </template>
                <v-card>
                  <v-card-title class="headline grey lighten-2">
                    코로나 거리두기 단계별 아이콘
                  </v-card-title>

                  <v-card-text style="margin-top:30px;">
                    <div
                      style="width:50%;float:left; padding-right:5px;border-right: 3px solid grey;"
                    >
                      <h4>
                        <i style="color:#ffa31a;" class="material-icons"
                          >no_food</i
                        >
                        음식 섭취 금지
                      </h4>
                      <br />
                      <h4>
                        <i style="color:#3333cc;" class="material-icons"
                          >6_ft_apart</i
                        >
                        거리두기 (ex. 한칸 띄어 앉기, 인원제한)
                      </h4>
                      <br />
                      <i style="color:black;float:left;" class="material-icons"
                        >qr_code_2</i
                      >
                      <h4
                        style="float: left;
    margin-left: 5px;"
                      >
                        출입부 명단 작성
                      </h4>
                    </div>
                    <div style="width:50%;float:left;padding-left:15px;">
                      <h4>
                        <i style="color:#ff0055;" class="material-icons"
                          >no_meeting_room</i
                        >
                        집합금지
                      </h4>
                      <br />
                      <h4>
                        <i style="color:#4d88ff;" class="material-icons"
                          >bathtub</i
                        >
                        목욕탕,찜질방 이용시 목욕만 이용 가능
                      </h4>
                      <br />
                      <h4>
                        <i style="color:#ffff1a;" class="material-icons"
                          >family_restroom </i
                        >가족만 참석 가능(장례식장)
                      </h4>
                    </div>
                  </v-card-text>
                  <v-card-actions>
                    <v-spacer></v-spacer>
                    <v-btn color="primary" text @click="dialog = false">
                      확인
                    </v-btn>
                  </v-card-actions>
                </v-card>
              </v-dialog>
            </div>
          </td>
        </tr>
        <tr>
          <td rowspan="3" style="max-width:100px;">
            <v-img
              id="placeIcon"
              src="../../../public/img/building.png"
            ></v-img>
          </td>
          <td colspan="2" style="float:left;">{{ items.placeName }}</td>
        </tr>
        <tr>
          <td colspan="2" style="float:left;">{{ items.roadAddressName }}</td>
        </tr>
        <tr>
          <td colspan="2" style="float:left;">
            <div style="margin-top:10px;">
              <li
                class="iconStyle"
                style="list-style: none;"
                v-if="items.meetProhibition"
              >
                <i style="color:#ff0055;" class="material-icons"
                  >no_meeting_room</i
                >
              </li>
              <li
                class="iconStyle"
                style="list-style: none;"
                v-if="items.notFood"
              >
                <i style="color:#ffa31a;" class="material-icons">no_food</i>
              </li>
              <li
                class="iconStyle"
                style="list-style: none;"
                v-if="items.seatOneApart"
              >
                <i style="color:#3333cc;" class="material-icons">6_ft_apart</i>
              </li>

              <li
                class="iconStyle"
                style="list-style: none;"
                v-if="items.showerOnly"
              >
                <i style="color:#4d88ff;" class="material-icons">bathtub</i>
              </li>
              <li
                class="iconStyle"
                style="list-style: none;"
                v-if="items.qrCode"
              >
                <i style="color:black;" class="material-icons">qr_code_2</i>
              </li>
              <li
                class="iconStyle"
                style="list-style: none;"
                v-if="items.familyOnly"
              >
                <i style="color:#ffff1a;" class="material-icons"
                  >family_restroom
                </i>
              </li>
            </div>
          </td>
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
    <br />
    <h4 style="margin-left:20px;padding-left:10px;">리뷰 {{ reviewNum }}개</h4>
    <v-card
      class="overflow-y-auto"
      max-height="570"
      outlined
      style="margin:20px;"
    >
      <v-list>
        <v-list-item-group>
          <template v-for="(item, index) in reviews">
            <v-list-item v-if="index <= 2" :key="item.nickname">
              <template>
                <v-list-item-content>
                  <v-list-item-nickname
                    ><span v-text="item.nickname"></span>
                    <span
                      style="margin-left: 20px; color:grey;"
                      v-text="item.dates"
                    ></span
                  ></v-list-item-nickname>

                  <v-rating
                    :value="item.star"
                    background-color="red lighten-3"
                    length="5"
                    dense
                    color="red"
                    size="20"
                    readonly
                  ></v-rating>
                  <v-list-item-review
                    class="text--primary"
                    v-text="item.review"
                  ></v-list-item-review>
                </v-list-item-content>
              </template>
            </v-list-item>
            <v-divider v-if="index < 2" :key="index"></v-divider>
          </template>
        </v-list-item-group>
      </v-list>
    </v-card>
  </v-main>
</template>
<script>
import axios from "axios";
import "material-design-icons-iconfont/dist/material-design-icons.css";

export default {
  data() {
    return {
      placeName: "스타벅스",
      placeLocation: "서울시 강남구 역삼동",
      placePhone: "1588-1588",
      heartStyle: "color:grey",
      openingHours: "10:00 - 21:00",
      reviewNum: 3,
      placeIdx: this.$route.params.placeIdx, // 검색 결과 list에서 넘어오는 장소 Idx
      level: 4,
      items: [],
      dialog: false,
      icons: {
        iconfont: "md",
      },
      itemIcon: [],
      reviews: [
        {
          nickname: "신꼬맹",
          dates: "21.01.03",
          review: "저는 커피 말고 아샷추 추천이요~",
          rating: 0,
        },
        {
          nickname: "갓오브갓 프론트팀장 가영",
          dates: "21.01.04",
          review:
            "9시 까지 운영하는 줄 알았는데 저희 동네 스타벅스는 8시까지 하더라고요! 참고하세요~",
          rating: 0,
        },
        {
          nickname: "다운팀장",
          dates: "21.01.21",
          review: "난 커피를 별로 좋아하지 않아요",
          rating: 0,
        },
      ],
    };
  },

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
    heartBtn() {
      if (this.heartStyle == "color:red") {
        this.heartStyle = "color:grey";
      } else {
        this.heartStyle = "color:red";
      }
    },
    onClickReview() {
      // this.$router.push({
      //   name: "placeReview",
      //   query: { placeIdx: placeIdx, level: level },
      // });
      this.$router.replace(`/placeReview`);
    },
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
.iconStyle {
  margin: 0 0 0 0;
  padding: 0 0 0 0;
  float: left;
  padding-left: 10px;
}
</style>
