<template>
  <div class="infomation">
    <div><h6 class="today_text"><span><v-icon class="today_icon">mdi-radiobox-marked</v-icon></span>일일확진자<span class="today_info">{{ today_DecideCnt }}</span>명</h6></div>
  </div>
</template>

<script>
import axios from 'axios';
export default {
  name: "CoronaTopNav",
  data: function() {
    return {
      crawlingTodayConfirmed: [],
      today_DecideCnt: '',
      createDt: '',
    }
  },
  created() {
    this.loadInfo();
  },
  methods: {
      loadInfo() {
        axios
        .get("https://i4a201.p.ssafy.io:8080/crawling/todayconfirmed", {
          params: {
          },
        })
        .then((res) => {
          console.log(res);
          this.crawlingTodayConfirmed = res.data.object

          // 일일 확진자 수
          this.createDt = res.data.object.createDt
          this.today_DecideCnt = res.data.object.today_DecideCnt.toLocaleString('ko-KR')
          
        //   var moment = require('moment')
        //   var date = this.createDt
        //   this.createDt = moment(date).format("M") + '월 ' + moment(date).format("D") + '일'
        })
        .catch((err) => {
          console.log(err);
        });
      }
  }
}
</script>

<style scoped>
  .infomation {
    text-align: center;
    margin-top: 3px;
  }
  .today_text {
    margin-bottom: 0px;
    color: #424242;
  }
  .today_info {
    font-weight: bolder;
    color: #fc355d;
    display: inline-flex;
    margin-left: 2px;
  }
  .today_icon {
    font-size: x-small;
    color: #fc355d;
    margin-right: 2px;
  }
</style>