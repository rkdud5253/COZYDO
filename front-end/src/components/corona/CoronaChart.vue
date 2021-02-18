<!-- 지난 일주일 간 코로나 일일 확진자 수 차트  -->
<template>
   <div class="column" style="margin:20px;">
        <bar-chart style="height:350px;" v-bind:local="local" v-bind:overflow="overflow" v-bind:datetime="datetime" v-if="loaded"></bar-chart>
   </div>     
   
</template>

<script>
  import BarChart from '@/components/corona/BarChart'
  export default {
    name: 'CoronaChart',
    components: {
      BarChart,
    },
    props: ['chartList'],
    data: function() {
    return {
            local: [],
            overflow: [],
            datetime: [],
            loaded: false

        }
    },
    methods: {
    },
    watch: {
      chartList()  {
        const len = this.chartList.length
        for(let i=0; i<len; i++){
          this.local[i] = this.chartList[len-i-1].local
          this.overflow[i] = this.chartList[len-i-1].overflow
          this.datetime[i] = this.chartList[len-i-1].createDt
        }
        this.loaded = true
      }
    }
  }
</script>
