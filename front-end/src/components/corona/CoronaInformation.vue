<!-- 코로나 누적, 일일 확진자 정보 -->
 <template>
  <div class="mx-2 my-3">

    <!-- 코로나 일일 정보(확진환자, 격리해제, 사망자) -->
    <v-item-group class="mx-6 my-3">
      <v-container>
        <v-row>

          <!-- 확진환자 -->
          <v-col cols="4" style="border-right: 1px solid #edf0f2; padding: 0px;">
            <v-item>
              <v-card
                class="card_menu d-flex align-center"
                flat
              >
                  <div
                    class="flex-grow-1 text-center"
                  >
                    <h5>확진환자</h5>
                    <h3 style= "color: #F50057">{{ decideCnt }}</h3>
                    <h5 class="today_DecideCnt">{{ today_DecideCnt }}</h5>
                  </div>
              </v-card>
            </v-item>
          </v-col>

          <!-- 격리해제 -->
          <v-col cols="4" style="border-right: 1px solid #edf0f2; padding: 0px;">
            <v-item>
              <v-card
                class="card_menu d-flex align-center"
                flat
              >
                  <div
                    class="flex-grow-1 text-center"
                  >
                    <h5>격리해제</h5>
                    <h3 style= "color: #00B0FF">{{ clearcnt }}</h3>
                    <h5 class="today_Clearcnt">{{ today_Clearcnt }}</h5>
                  </div>
              </v-card>
            </v-item>
          </v-col>

          <!-- 사망자 -->
          <v-col cols="4" style="padding: 0px;">
            <v-item>
              <v-card
                class="card_menu d-flex align-center"
                flat
              >
                  <div
                    class="flex-grow-1 text-center"
                  >
                    <h5>사망자</h5>
                    <h3 style= "color: #616161">{{ deathCnt }}</h3>
                    <h5 class="today_DeathCnt">{{ today_DeathCnt }}</h5>
                  </div>
              </v-card>
            </v-item>
          </v-col>

        </v-row>
      </v-container>
    </v-item-group>


    <!-- 일일확진자(국내/해외) -->
    <div class="mx-6 my-5">
      <v-row class="today_menu_row">

          <v-col cols="4" style="border-right: 1px solid #edf0f2; padding: 0px;">
            <v-item>
              <v-card
                class="today_menu d-flex align-center"
                flat
              >
                  <div
                    class="flex-grow-1 text-center"
                  >
                    <p style="font-size: small; color: #616161;">
                      일일확진자
                    </p>
                  </div>
              </v-card>
            </v-item>
          </v-col>

          <v-col cols="4" style="border-right: 1px solid #edf0f2; padding: 0px;">
            <v-item>
              <v-card
                class="today_menu d-flex align-center"
                flat
              >
                  <div
                    class="flex-grow-1 text-center"
                  >
                    <p style="font-size: small; color: #F06292;">
                      국내발생
                      <span class="mx-1" style="font-weight: bolder; color: #F06292;">
                        {{ local }}
                      </span>
                    </p>
                  </div>
              </v-card>
            </v-item>
          </v-col>

          <v-col cols="4" style="padding: 0px;">
            <v-item>
              <v-card
                class="today_menu d-flex align-center"
                flat
              >
                  <div
                    class="flex-grow-1 text-center"
                  >
                    <p style="font-size: small; color: #9575CD;">
                      해외유입
                      <span class="mx-1" style="font-weight: bolder; color: #9575CD;">
                        {{ overflow }}
                      </span>
                    </p>
                  </div>
              </v-card>
            </v-item>
          </v-col>

        </v-row>

    </div>

  </div>
</template>

<script>
export default {
  name: "CoronaInformation",
  props: ['todayInfo'],
  data: function() {
    return {
      crawlingTodayConfirmed: [],
      decideCnt: '', // 누적 확진자 수
      clearcnt: '', // 누적 격리해제 수
      deathCnt: '', // 누적 사망자 수
      today_DecideCnt: '', // 일일 확진자 수
      today_Clearcnt: '', // 일일 격리해제 수
      today_DeathCnt: '', // 일일 사망자 수
      local: '', // 일일 확진자 중 국내발생
      overflow: '', // 일일 확진자 중 해외유입
    };
  },
  watch: {
    todayInfo() {
      // 천의자리마다 쉼표(,) 찍어서 넣어주기
      this.decideCnt = this.todayInfo[0].decideCnt.toLocaleString('ko-KR')
      this.clearcnt = this.todayInfo[0].clearcnt.toLocaleString('ko-KR')
      this.deathCnt = this.todayInfo[0].deathCnt.toLocaleString('ko-KR')
      this.today_DecideCnt = this.todayInfo[0].today_DecideCnt.toLocaleString('ko-KR')
      this.today_Clearcnt = this.todayInfo[0].today_Clearcnt.toLocaleString('ko-KR')
      this.today_DeathCnt = this.todayInfo[0].today_DeathCnt.toLocaleString('ko-KR')
      this.local = this.todayInfo[0].local.toLocaleString('ko-KR')
      this.overflow = this.todayInfo[0].overflow.toLocaleString('ko-KR')
    }
  }
};
</script>

<style scoped>
  .card_menu {
    height: 80px;
  }
  .today_menu {
    height: 35px;
  }
  .today_menu_row {
    border-radius: 4px;
    border: solid 1px #eeeff0;
  }
  .today_DecideCnt {
    color: #F50057;
    background-color: #FCE4EC;
    margin-left: 35px;
    margin-right: 35px;
    border-radius: 6px;
    margin-top: 2px;
  }
  .today_Clearcnt {
    color: #1565C0;
    background-color: #E1F5FE;
    margin-left: 35px;
    margin-right: 35px;
    border-radius: 6px;
    margin-top: 2px;
  }
  .today_DeathCnt {
    color: #424242;
    background-color: #EEEEEE;
    margin-left: 35px;
    margin-right: 35px;
    border-radius: 6px;
    margin-top: 2px;
  }
  .v-application p {
    margin-bottom: 0px;
  }

</style>