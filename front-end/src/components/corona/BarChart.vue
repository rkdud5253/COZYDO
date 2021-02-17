<!-- vue chart.js를 이용하여 bar chart 생성 -->
<script>
  import { Bar } from 'vue-chartjs'
  import ChartDataLabels from 'chartjs-plugin-datalabels';
  export default {
    extends: Bar,
    props: {
        local: Array,
        overflow: Array,
        datetime: Array
    },
    data () {
      return {
        datacollection: {
          labels: this.datetime,
          datasets: [
            {
              label: '국내발생',
              backgroundColor: [
                "rgba(252, 53, 93, 0.8)",
                "rgba(252, 53, 93, 0.8)",
                "rgba(252, 53, 93, 0.8)",
                "rgba(252, 53, 93, 0.8)",
                "rgba(252, 53, 93, 0.8)",
                "rgba(252, 53, 93, 0.8)",
                "rgba(252, 53, 93, 0.8)",
              ],
              barThickness : 10,
              data: this.local,
              datalabels: {
                  display: false
              }
            },
            {
              label: '해외유입',
              backgroundColor: [
                "rgba(211, 211, 211, 0.4)",
                "rgba(211, 211, 211, 0.4)",
                "rgba(211, 211, 211, 0.4)",
                "rgba(211, 211, 211, 0.4)",
                "rgba(211, 211, 211, 0.4)",
                "rgba(211, 211, 211, 0.4)",
                "rgba(211, 211, 211, 0.4)",
              ],
              barThickness : 10,
              data: this.overflow,
              datalabels: {
                  display: true,
                  color: 'black',
                  align: 'top',
                  formatter: (value, context) => {
                        return this.local[context.dataIndex] + this.overflow[context.dataIndex];
                },
                
              }
            },
            
          ]
        },
        options: {
          scales: {
            yAxes: [{
              ticks: {
                beginAtZero: true,
                display: true,
                stepSize: 100
              },
              gridLines: {
                display: true
              },
              stacked: true,
            }],
            xAxes: [ {
              gridLines: {
                display: false
              },
              stacked: true,
            }]
          },
          legend: {
            display: false
          },
          responsive: true,
          maintainAspectRatio: false,
          showLines: false,
        },
        plugin: {ChartDataLabels},
      
      }
    },
    mounted () {
      // 날짜 변환 라이브러리 사용하여 날짜 포맷 변경
      for(var i=0; i<this.datetime.length; i++){
        var moment = require('moment')
        var date = this.datetime[i]
        this.datetime[i] = moment(date).subtract(1,"days").format("M") + '.' + moment(date).subtract(1,"days").format("D")
      }
      this.renderChart(this.datacollection, this.options)
    },
    
  }
</script>
