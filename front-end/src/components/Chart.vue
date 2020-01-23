<template>
    <div>
        <canvas id="myChart" class="col-xs-12 col-sm-12 col-md-12"></canvas>
    </div>
</template>

<script>
import Chart from "chart.js";
import http from '../http-common';

export default {
  name: 'chart',
  data() {
    return {
      label : '가입자 수',
      labels : ['1월', '2월', '3월', '4월', '5월', '6월', '7월', '8월', '9월', '10월', '11월', '12월'],
      data : [10, 3, 2, 11, 30, 23, 40, 43, 52, 71, 130, 223],
    }
  },

  methods: {
      weekUserChart(){
          http.get("/user/weekChart")
          .then((response) => {
              window.console.log(response);
          })
          .catch((error) => {
              window.console.log(error);
          })
      }
  },

  mounted() {
      const myChart = new Chart('myChart', {
          type : 'line',
          data : {
              labels : [
                  '1월', '2월', '3월', '4월', '5월', '6월', '7월', '8월', '9월', '10월', '11월', '12월' 
              ],

              datasets : [
                  {
                      label : '가입자 수',
                      backgroundColor : [
									'rgba(255, 255, 255, 1)',
                      ],
                      borderColor : 'white',
                      pointBackgroundColor : 'black',
                      pointBorderWidth : 2,
                      pointRadius : 6,
                      pointStyle : 'rectRot',
                      lineTension : 0.1,
                      data : [
                          10, 3, 2, 11, 30, 23, 40, 43, 52, 71, 130, 223
                      ]
                  }
              ]
          },

          options : {
              maintainAspectRatio : true,

              scales : {
                  yAxes : [{
                      ticks : {
                          fontColor : 'white',
                          beginAtZero : true
                      }
                  }],
                  xAxes : [{
                      ticks : {
                          fontColor : 'white',
                        //   beginAtZero : true
                      }
                  }]
              },

              legend : {
                  labels : {
                      fontColor : 'white'
                  }
              }
          }
      })

      myChart;
  }
}
</script>