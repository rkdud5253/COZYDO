<!-- 상세페이지  -->
<template>
  <v-main>
    <v-container>
      <!-- 3단계 집합금지일 때, modal 창 구현 -->
      <v-dialog v-if="items.meetProhibition" width="500">
        <v-card>
          <v-card-title style="color:pink;" class="headline grey lighten-2">
            3단계 집합 금지
          </v-card-title>

          <v-card-text style="margin-top:30px;">
            <div style="width:100%;padding-left:15px;">
              <h4>
                <i style="color:#ff0055;" class="material-icons">no_meeting_room</i>
                중점관리시설, 일반 및 기타 시설 중 필수시설 외 집합금지 이외 시설도 운영 제한 국공립
                시설은 실내외 구분없이 운영 중단
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
      <table
        id="place"
        style="border: 1px solid black; padding-left:18px; border-radius:4px; margin-bottom:10px;"
      >
        <tr>
          <td></td>
          <td>
            <!-- 가게 상세페이지 정보 좋아요버튼, 아이콘 버튼, 장소상세정보, 거리두기 단계별 아이콘 -->
            
            <!-- 좋아요 버튼 안눌렀을 때 -->
            <v-btn
              style="float:right;"
              id="heartIcon"
              v-if="this.items.isLike === 0"
              v-on:click="heartBtn"
              icon
              v-bind:style="heartStyle"
            >
              <v-icon>mdi-heart</v-icon>
            </v-btn>

            <!-- 좋아요 버튼 눌렀을 때 -->
            <v-btn
              style="float:right;"
              id="heartIcon"
              v-if="this.items.isLike === 1"
              v-on:click="heartBtn"
              icon
              v-bind:style="clickedHeartStyle"
            >
              <v-icon>mdi-heart</v-icon>
            </v-btn>

          </td>
          <td style="width:27px;">
            <div style="float:right;" class="text-center">
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
                      <i style="color:#ffa31a;float:left;" class="material-icons">no_food</i>
                      <h4
                        style="padding-top: 7px;
    padding-left: 42px;"
                      >
                        음식 섭취 금지
                      </h4>
                      <br />
                      <i style="color:#3333cc;float:left;" class="material-icons">6_ft_apart</i>
                      <h4
                        style="padding-top: 7px;
    padding-left: 42px;"
                      >
                        거리두기 (ex. 한칸 띄어 앉기, 인원제한)
                      </h4>
                      <br />
                      <i style="color:black;float:left;" class="material-icons">qr_code_2</i>
                      <h4
                        style="padding-top: 7px;
    padding-left: 42px;"
                      >
                        출입부 명단 작성
                      </h4>
                    </div>
                    <div style="width:50%;float:left;padding-left:15px;">
                      <i style="color:#ff0055;float:left;" class="material-icons"
                        >no_meeting_room</i
                      >
                      <h4
                        style="padding-top: 7px;
    padding-left: 42px;"
                      >
                        집합금지
                      </h4>
                      <br />
                      <i style="color:#4d88ff;float:left;" class="material-icons">bathtub</i>
                      <h4
                        style="padding-top: 7px;
    padding-left: 42px;"
                      >
                        목욕탕,찜질방 이용시 목욕만 이용 가능
                      </h4>
                      <br />

                      <i style="color:#ffff1a;float:left;" class="material-icons"
                        >family_restroom
                      </i>
                      <h4
                        style="padding-top: 7px;
    padding-left: 42px;"
                      >
                        가족만 참석 가능(장례식장)
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
          <td colspan="3">
            <h3>{{ items.placeName }}</h3>
          </td>
        </tr>
        <tr>
          <td colspan="3">
            <h5>{{ items.roadAddressName }}</h5>
          </td>
        </tr>
        <!-- 전화번호 추가 부분 -->
        <tr>
          <td colspan="3">
            <h5 v-if="items.tel">{{ items.tel }}</h5>
          </td>
        </tr>
        <tr>
          <td colspan="3" style="float:left;">
            <div style="margin-top:10px;">
              <li class="iconStyle" style="list-style: none;" v-if="items.meetProhibition">
                <i style="color:#ff0055;" class="material-icons">no_meeting_room</i>
              </li>
              <li class="iconStyle" style="list-style: none;" v-if="items.notFood">
                <i style="color:#ffa31a;" class="material-icons">no_food</i>
              </li>
              <li class="iconStyle" style="list-style: none;" v-if="items.seatOneApart">
                <i style="color:#3333cc;" class="material-icons">6_ft_apart</i>
              </li>

              <li class="iconStyle" style="list-style: none;" v-if="items.showerOnly">
                <i style="color:#4d88ff;" class="material-icons">bathtub</i>
              </li>
              <li class="iconStyle" style="list-style: none;" v-if="items.qrCode">
                <i style="color:black;" class="material-icons">qr_code_2</i>
              </li>
              <li class="iconStyle" style="list-style: none;" v-if="items.familyOnly">
                <i style="color:#ffff1a;" class="material-icons">family_restroom </i>
              </li>
            </div>
          </td>
        </tr>
        <br />
        <tr>
          <td colspan="3">
            <!-- 리뷰 modal -->
            <v-dialog v-if="this.userIdx !== 0" v-model="reviewDialog" persistent max-width="400px">
              <template v-slot:activator="{ on, attrs }">
                <v-btn
                  style="padding:0 35px; width:45%;"
                  color="#fc355d"
                  dark
                  v-bind="attrs"
                  v-on="on"
                >
                  리뷰 남기기
                </v-btn>
              </template>
              <v-card>
                <v-card-title class="justify-center">
                  <span class="headline">리뷰 작성</span>
                </v-card-title>
                <v-card-text>
                  <v-container>
                    <v-row>
                      <v-col cols="12">
                        <v-rating
                          v-model="rating"
                          style="text-align:center;"
                          background-color="orange lighten-3"
                          color="orange"
                          medium
                        ></v-rating>
                      </v-col>

                      <v-col cols="12">
                        <v-textarea
                          counter
                          label="리뷰를 입력하세요"
                          solo
                          auto-grow
                          rows="4"
                          row-height="20"
                          depressed
                          v-model="content"
                        ></v-textarea
                      ></v-col>
                      <v-col cols="12">
                        <v-card-actions>
                          <v-btn
                            style="width: 100%; margin-top: 15px"
                            color="primary"
                            dark
                            @click="reviewSubmit"
                            >리뷰 등록
                          </v-btn></v-card-actions
                        ></v-col
                      >
                    </v-row>
                  </v-container>
                </v-card-text>
              </v-card>
            </v-dialog>
            <!-- 수정제안 modal -->
            <v-dialog v-if="this.userIdx !== 0" v-model="modifyDialog" persistent max-width="400px">
              <template v-slot:activator="{ on, attrs }">
                <v-btn
                  style="margin-left:25px;width:33%;width:45%;"
                  color="#fc355d"
                  dark
                  v-bind="attrs"
                  v-on="on"
                  @click="dialog = false"
                >
                  가게 정보 수정
                </v-btn>
              </template>
              <v-card>
                <v-card-title class="justify-center">
                  <span class="headline">수정 제안</span>
                </v-card-title>
                <v-card-text>
                  <v-container>
                    <v-row>
                      <v-col cols="12">
                        <p>
                          허위 정보는 제안이 거절될 수 있습니다. 서비스의 원할한 운영을 위해
                          신중하게 정보를 제안해 주시기 바랍니다.
                        </p>
                      </v-col>
                      <v-col cols="12">
                        <div style="float: left; margin-top: 20px; margin-right: 13px">
                          영업 시간
                        </div>

                        <v-text-field
                          prepend-icon="mdi-alarm"
                          style="float: left; width: 70%; max-width"
                          v-model="message1"
                          clearable
                        ></v-text-field>
                      </v-col>

                      <v-col cols="12">
                        <div style="float: left; margin: 13px 13px 20px 0">
                          폐업 여부
                        </div>
                        <v-btn-toggle
                          v-model="toggle_exclusive"
                          class="transparent"
                          mandatory
                          color="primary"
                          style="width: 70%; margin-bottom: 15px"
                        >
                          <v-btn :value="1" style="width: 50%">
                            운영중
                          </v-btn>

                          <v-btn :value="2" style="width: 50%">
                            폐업
                          </v-btn>
                        </v-btn-toggle>
                      </v-col>
                      <v-col cols="12">
                        <v-card-actions>
                          <v-btn
                            style="width: 100%; margin-top: 15px"
                            class="ma-2"
                            outlined
                            @click="modifySubmit"
                            color="indigo"
                            >작성 완료
                          </v-btn>
                        </v-card-actions></v-col
                      >
                    </v-row>
                  </v-container>
                </v-card-text>
              </v-card>
            </v-dialog>
          </td>
        </tr>
      </table>
    </v-container>

    <div style="margin-left:20px;padding-left:13px;">
      <h3 style="margin-left: 4px;">
        영업시간
        <span style="margin-left:30px;color:grey;">{{ openingHours }}</span>
      </h3>
      <hr style="width:90%;text-align:center;" />
    </div>

    <br />
    <h4 style="margin-left:25px;padding-left:10px;margin-bottom:10px;">
      장소 리뷰
    </h4>
    <!-- 리뷰 불러오기 부분 -->
    <template v-for="item in items.reviewList">
      <v-card
        style="margin-bottom:10px; margin-left:20px !important; margin-right:20px !important;"
        class="mx-auto"
        :key="item.reviewIdx"
        outlined
      >
        <v-rating
          :value="item.reviewScore"
          background-color="#FC355D"
          length="5"
          dense
          color="#FC355D"
          size="20"
          readonly
          style="padding-left: 15px;
                padding-top: 10px;"
        ></v-rating>
        
        <v-card-text>
          <h4 style="margin-bottom:5px;">{{ item.content }}</h4>

          <span style="margin-right:10px;">
            {{ item.userNickname }}
          </span>
          {{ item.writeTime.substring(0, 10) }}
          <v-icon style="float: right; color:#e6e3e3" v-if="item.userIdx == userIdx" @click.stop="[reviewDeleteDialog = true, reviewIndex = item.reviewIdx]">
            mdi-delete
          </v-icon>

          <!-- 리뷰 삭제 모달창 -->
          <v-dialog v-model="reviewDeleteDialog" max-width="290">
            <v-card>
              <v-card-title></v-card-title>

              <v-card-text class="review_delete_modal_text">
                <h4>정말 삭제하시겠습니까?</h4>
              </v-card-text>

              <v-card-actions class="review_delete_modal_btn">
                <v-btn class="mb-3" color="grey lighten-1" dark depressed small @click="reviewDeleteDialog = false">
                  취소하기
                </v-btn>

                <v-btn class="mb-3" color="#fc355d" dark depressed small @click="[reviewDeleteDialog = false, reviewDelete(reviewIndex)]">
                  삭제하기
                </v-btn>
              </v-card-actions>
            </v-card>
          </v-dialog>
        </v-card-text>

      </v-card>
    </template>
  </v-main>
</template>
<script>
import axios from 'axios'
import 'material-design-icons-iconfont/dist/material-design-icons.css'

export default {
  data() {
    return {
      Publishers: {},
      heartStyle: 'color:grey',
      clickedHeartStyle: 'color:red',
      openingHours: '10:00 - 21:00',
      placeIdx: this.$route.params.placeIdx, // 검색 결과 list에서 넘어오는 장소 Idx
      level: this.$route.params.level,
      items: [],
      icons: {
        iconfont: 'md',
      },
      itemIcon: [],
      dialog: false,
      reviewDialog : false,
      reviewDeleteDialog : false,
      // 리뷰 modal관련 data
      userIdx: this.$store.getters.getUserIdx, // 회원 닉네임 store에서 불러오기
      content: '',
      rating: 0,
      // 수정제안 modal 관련 data
      modifyDialog: false,
      message1: '10:00 - 21:00',
      toggle_exclusive: 1,
      rules: [
        (value) => !value || value.size < 2000000 || '2 MB보다 작은 이미지 파일을 올려주세요',
      ],
      reviewIndex: 0,
    }
  },

  created() {
    // this.loadPublishers()
    if(this.userIdx === null || this.userIdx === '') {
      this.userIdx = 0
    }

    axios
      .get('https://i4a201.p.ssafy.io:8080/map/detail', {
        //get방식, url 확인
        params: {
          // 넘겨줄 파라미터
          placeIdx: this.placeIdx,
          level: this.level,
          userIdx: this.userIdx
        },
      })
      .then((res) => {
        // 통신 성공하면
        this.items = res.data // 데이터 받아온다
      })
      .catch((err) => {
        console.log(err)
      })
  },
  methods: {
    // loadPublishers() {

    // },
    heartBtn() {
      // 장소 찜을 삭제하려고 할 경우
      if (this.items.isLike === 1) {
        this.heartStyle = 'color:grey'

        axios.delete('https://i4a201.p.ssafy.io:8080/likes/delete', {  
          params: {                                        
            placeIdx: this.placeIdx,
            userIdx: this.userIdx
          }
        })
        .then(() => {
          // 새로고침
          history.go(0)
        });

      // 장소 찜을 하려는 경우
      } else {
        this.heartStyle = 'color:red'

        axios
        .post('https://i4a201.p.ssafy.io:8080/likes/save', {
          placeIdx: this.placeIdx,
          userIdx: this.userIdx
        })
        .then(() => {
          // 새로고침
          history.go(0)
        })

      }
    },
    reviewSubmit() {
      axios
        .post('https://i4a201.p.ssafy.io:8080/review/save', {
          content: this.content,
          placeIdx: this.placeIdx,
          reviewScore: this.rating,
          userIdx: this.userIdx
        })
        .then(() => {
          // 리뷰 등록하면 장소상세페이지 refresh
          this.$router.go({
            name: 'PlaceDetail',
            params: { placeIdx: this.placeIdx, level: this.level },
          })
        })

      this.reviewDialog = false
      this.content = ''
      this.rating = 0
    },
    modifySubmit() {
      // 수정 제안 DB연동 후 데이터 저장 코드 추가
      this.modifyDialog = false
    },
    reviewDelete(reviewIdx) {
      axios
      .delete('https://i4a201.p.ssafy.io:8080/review/delete/' + reviewIdx, {
      })
      .then(() => {
        history.go(0)
      })
      .catch((err) => {
        console.log(err)
      })


    }
  },
  
}
</script>
<style>
#place {
  padding: 10px 10px;
  margin: 0 0 5px 20px;
  width: 90%;
}
/* #reviewBtn,
#modifyBtn {
  background-color: white !important;
  color: black;
} */
.iconStyle {
  margin: 0 0 0 0;
  padding: 0 0 0 0;
  float: left;
  padding-left: 10px;
}
.material-icons {
  font-size: 32px;
}
.v-application p {
  margin-bottom: 7px;
}

.v-rating{
  max-width: 50%;
  white-space: none;
  margin-right:0%;
}

.review_delete_btn {
  max-width: 70px;
  align-self: center;
}
.review_delete_modal_text {
  text-align: center;
}
.review_delete_modal_btn {
  justify-content: center;
}
</style>
