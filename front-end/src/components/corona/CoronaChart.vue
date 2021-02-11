<template>
    
   <div class="column" style="margin:20px;">
        <bar-chart style="height:350px;" v-bind:local="local" v-bind:overflow="overflow" v-bind:datetime="datetime" v-if="loaded"></bar-chart>
   </div>     
   
</template>

<script>
  import BarChart from '@/components/corona/BarChart'
  import axios from "axios";
  export default {
    name: 'CoronaChart',
    components: {
      BarChart,
    },
    data: function() {
    return {
            local: [],
            overflow: [],
            datetime: [],
            loaded: false
        }
    },
    methods: {
        // getData: () => {
        //     axios.get('https://i4a201.p.ssafy.io:8080/crawling/weekconfirmed')
        //     .then((res) => {
        //       console.log(res)
        //   }).catch((err) => {
        //     console.log(err)
        //   })
        // }
    },
    mounted() {
        // this.getData()
    },
   created() {
     axios.get('https://i4a201.p.ssafy.io:8080/crawling/weekconfirmed')
            .then((res) => {
              console.log(res)
              const len = res.data.object.length
              for(var i=0; i<len; i++){
                this.local[i] = res.data.object[len-i-1].local
                this.overflow[i] = res.data.object[len-i-1].overflow
                this.datetime[i] = res.data.object[len-i-1].createDt
              }
              this.loaded = true
          }).catch((err) => {
            console.log(err)
          })
   }
    
  }
</script>
