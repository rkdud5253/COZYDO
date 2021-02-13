<template>
    <div class="sidolist mx-7">
      <v-card
            flat
            class="card"
          >
            <div class="d-flex flex-no-wrap justify-space-between">
              <div>
                <v-card-subtitle style="color:black; font-size:12px; font-weight:500;">지역</v-card-subtitle>
              </div>

              <div>
                <v-card-subtitle style="color:black; font-size:12px; font-weight:500;">총 확진자 수</v-card-subtitle>
              </div>

              <div>
                <v-card-subtitle style="color:black; font-size:12px; font-weight:500;">일일 확진자 수</v-card-subtitle>
              </div>

            </div>
          </v-card>
        <v-col
          v-for="(sido, i) in sidoList"
          :key="i"
          cols="12"
          
        >
          <v-card
            flat
            class="card" :class="{'on':isStateTwo(i)}"
          >
            <div class="d-flex flex-no-wrap">
              <div class="card-sub" style="flex: 1.5 1 0%;">
                <v-card-subtitle v-text="sido.gubun" style="color:black; font-size:14px; font-weight:700;"></v-card-subtitle>
              </div>

              <div class="card-sub" style="flex: 1.8 1 0%;">
                <v-card-subtitle v-text="sido.decideCnt" style="color:black; font-size:14px; font-weight:700;"></v-card-subtitle>
              </div>

              <div class="card-sub" style="flex: 1.0 1 0%;">
                <v-card-subtitle v-text="sido.today_DecideCnt" style="color:black; font-size:14px; font-weight:700;"></v-card-subtitle>
              </div>

            </div>
          </v-card>
        </v-col>
    </div>
</template>
<script>

export default {
  name: "CoronaSidoList",
  components: {},
  props:['sidoList'],
  data: function() {
    return {
      // items: [
      //   {'gubun':'서울', 'decideCnt':'100', 'today_DecideCnt':'23232'}, 
      // {'gubun':'경기', 'decideCnt':'8', 'today_DecideCnt':'232132'},
      // {'gubun':'부산', 'decideCnt':'13', 'today_DecideCnt':'232232'},
      //  {'gubun':'부산', 'decideCnt':'13', 'today_DecideCnt':'232232'},
      //   {'gubun':'부산', 'decideCnt':'13', 'today_DecideCnt':'232232'},
      //    {'gubun':'부산', 'decideCnt':'13', 'today_DecideCnt':'232232'},
      //     {'gubun':'부산', 'decideCnt':'13', 'today_DecideCnt':'232232'},
      //      {'gubun':'부산', 'decideCnt':'13', 'today_DecideCnt':'232232'},
      //       {'gubun':'부산', 'decideCnt':'13', 'today_DecideCnt':'232232'},
      // ],
      colors: ['#fcfcfc', '#f5f2f2']
    };
  },
  methods: {
      isStateTwo: function (i) {
                if(i % 2 === 0) return true;
                else false;
            }
  },
  mounted() {
      // axios.get('https://i4a201.p.ssafy.io:8080/crawling/sidoconfirmed')
      //       .then((res) => {
      //         this.items = res.data.object
              
      //         // 총 확진자 수 내림차순 정렬
      //         this.items.sort(function(a, b) { 
      //           return a.decideCnt > b.decideCnt ? -1 : a.decideCnt < b.decideCnt ? 1 : 0; 
      //         });

      //         // gubun이 '검역'인 field 삭제
      //         const itemToFind = this.items.find(function(item) {return item.gubun === '검역'}) 
      //         const idx = this.items.indexOf(itemToFind) 
      //         if (idx > -1) this.items.splice(idx, 1)


      //         console.log(this.items)
      //     }).catch((err) => {
      //       console.log(err)
      //     })
  }

}
</script>
<style scoped>
.col-12 {
    padding: 1px !important
}
.v-card {
    border: 1px !important;
    border-bottom-left-radius: 5px;

    animation-duration: 0.4s;
    animation-fill-mode: both;
    animation-name: fadeInUp;
    animation-delay: 200ms;
}
.card-sub {
  animation-duration: 0.4s;
    animation-fill-mode: both;
    animation-name: fadeInUp;
    animation-delay: 200ms;
}
@keyframes fadeInUp { /* Safari and Chrome */
    from {
        opacity: 0;
    }
    to {
        opacity: 1;
    }
}
.card {
    /* background : rgb(245, 245, 245); */
    
    background: white;
}

.card.on {
    /* background: #fffcfc; */
    background: #fff1f5;
}

</style>