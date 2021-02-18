<!-- 로고 밑 일일 확진자 정보-->
<template>
  <div class="infomation">
    <div>
      <h6 class="today_text">
        <span><v-icon class="today_icon">mdi-radiobox-marked</v-icon></span
        >일일확진자<span class="today_info">{{ today_DecideCnt }}</span
        >명
      </h6>
    </div>
  </div>
</template>

<script>
import axios from 'axios'
export default {
  name: 'CoronaTopNav',
  data: function() {
    return {
      today_DecideCnt: '',
    }
  },
  created() {
    this.loadInfo()
  },
  methods: {
    loadInfo() {
      axios
        .get('https://i4a201.p.ssafy.io:8080/crawling/confirmed', {
          params: {},
        })
        .then((res) => {
          this.today_DecideCnt = res.data.object[0][0].today_DecideCnt.toLocaleString('ko-KR')
        })
        .catch((err) => {
          console.log(err)
        })
    },
  },
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
