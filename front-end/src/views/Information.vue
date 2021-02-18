<!-- 코로나 정보-->
<template>
  <v-app>
    <CoronaInformation :todayInfo="this.items[0]"/>
    <CoronaChart :chartList="this.items[2]"/>
    <CoronaSidoList :sidoList="this.items[1]"/>
  </v-app>
</template>

<script>
import CoronaInformation from "@/components/corona/CoronaInformation.vue";
import CoronaChart from "@/components/corona/CoronaChart.vue";
import CoronaSidoList from "@/components/corona/CoronaSidoList.vue";
import axios from "axios";
export default {
  name: "Information",
  components: {
      CoronaInformation,
      CoronaChart,
      CoronaSidoList
  },
  data: function() {
    return {
      items:[],
    }
  },
  created() {
      axios.get('https://i4a201.p.ssafy.io:8080/crawling/confirmed')
            .then((res) => {
              this.items = res.data.object

              // 일일 확진자 수 기준으로 내림차순 정렬
              this.items[1].sort(function(a, b) { 
                return a.today_DecideCnt > b.today_DecideCnt ? -1 : a.today_DecideCnt < b.today_DecideCnt ? 1 : 0; 
              });

              // gubun이 '검역'인 field 삭제
              const itemToFind = this.items[1].find(function(item) {return item.gubun === '검역'}) 
              const idx = this.items[1].indexOf(itemToFind) 
              if (idx > -1) this.items[1].splice(idx, 1)
          }).catch((err) => {
            console.log(err)
          })
  }
};
</script>
