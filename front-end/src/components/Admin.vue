<template v-slot:default>
    <div class="container-fluid photos">
        <div class="row justify-content-center">

            <div class="col-md-12 pt-4" data-aos="fade-up">
                <h2 class="text-white mb-4">Admin</h2>


                <div class="row">
                    <div class="col-md-12">
                        <v-tabs fixed-tabs background-color="indigo" dark>
                            <v-tab v-on:click="tabclick('calendar')">
                                캘린더
                            </v-tab>
                            <v-tab v-on:click="tabclick('wordcloud')">
                                관심사
                            </v-tab>
                            <v-tab v-on:click="tabclick('today')">
                                방문자수
                            </v-tab>
                            <v-tab v-on:click="tabclick('total')">
                                통계
                            </v-tab>
                            <v-tab v-on:click="tabclick('log')">
                                로그
                            </v-tab>
                        </v-tabs>
                    </div>
                </div>
            </div>
        </div>
        <div class="container-fluid photos">
            <div class="row justify-content-center">
                <div class="col-md-12 pt-4" data-aos="fade-up">
                    <div class="col-md-12">
                        <div class="md-5" id="contents">
                            <div id="calendar" name="calendar" v-bind:style="calendar">
                                <mycalendar></mycalendar>
                            </div>
                            <div id="worldcloud" name="wordcloud" v-bind:style="wordcloud">
                                <wordcloud-vue></wordcloud-vue>
                            </div>
                            <div id="today" name="today" v-bind:style="today">
                                <div>today</div>
                                <div>{{visit}}</div>
                            </div>
                            <div id="total" name="total" v-bind:style="total">
                                <canvas id="myChart" class="col-xs-12 col-sm-12 col-md-12"></canvas>
                            </div>
                            <div id="log" name="total" v-bind:style="log">
                                <adminlog></adminlog>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</template>


<script>
    import Chart from "chart.js";
    import http from "../http-common"
    import $ from "jquery"
    import wordcloudVue from "./WordCloud.vue"
    import mycalendar from "./Calendar.vue"
    import adminlog from"./AdminLog.vue"

    export default {
        components: {
            wordcloudVue,
            mycalendar,
            adminlog
        },
        data() {
            return {
                uid: this.$store.state.user_id,
                visit: "",
                totaldata:[],
                today: {
                    display: 'none',
                },
                wordcloud: {
                    display: 'none',
                },
                calendar: {
                    display: '',
                },
                total: {
                    display: 'none',
                },log: {
                    display: 'none',
                },
                categoryNoti: {},
                date: {
                    'contents': {
                        '01': 0,
                        '02': 0,
                        '03': 0,
                        '04': 0,
                        '05': 0,
                        '06': 0,
                        '07': 0,
                        '08': 0,
                        '09': 0,
                        '10': 0,
                        '11': 0,
                        '12': 0
                    }
                },

            }
        },
        mounted() {
            if(this.uid === "admin1" || this.uid === "admin2" || this.uid === "admin3" || this.uid === "admin4" || this.uid === "admin5"){
            $('html').scrollTop(0);
            http
                .get("admin/totalcontents")
                .then(response => {
                    if (response.data['resmsg'] == "조회성공") {
                        this.totaldata = response.data['resvalue'];
                    this.fetchCategoryNoti('contents');
        
                    const myChart = new Chart('myChart', {
                        type: 'line',
                        data: {
                            labels: [
                                '1월', '2월', '3월', '4월', '5월', '6월', '7월', '8월', '9월', '10월', '11월', '12월'
                            ],
        
                            datasets: [{
                                    label: '월별 게시글 수',
                                    //   backgroundColor : ['rgba(255, 255, 255, 1)'],
                                    borderColor: '#f23a2e',
                                    pointBackgroundColor: 'white',
                                    pointBorderWidth: 2,
                                    pointRadius: 6,
                                    pointStyle: 'rectRot',
                                    lineTension: 0.1,
                                    data: this.totaldata
                                }
                            ]
                        },
                        options: {
                            maintainAspectRatio: true,
        
                            scales: {
                                yAxes: [{
                                    ticks: {
                                        fontColor: 'white',
                                        beginAtZero: true
                                    }
                                }],
                                xAxes: [{
                                    ticks: {
                                        fontColor: 'white',
                                        //   beginAtZero : true
                                    }
                                }]
                            },
                            legend: {
                                labels: {
                                    fontColor: 'white'
                                }
                            }
                        }
                    })
        
                    myChart;
                    }
                })
            http
                .get("admin/todayvisit")
                .then(response => {
                    if (response.data['resmsg'] == "조회성공") {
                        this.visit = response.data['resvalue'];
                    }
                })
            }else{
                this.$router.push("/")
            }
        },
        methods: {
            tabclick(e) {
                if (e === "today") {
                    this.today.display = "";
                    this.wordcloud.display = "none";
                    this.calendar.display = "none";
                    this.total.display = "none";
                    this.log.display="none"
                } else if (e === "wordcloud") {
                    this.today.display = "none";
                    this.wordcloud.display = "";
                    this.calendar.display = "none";
                    this.total.display = "none";
                    this.log.display="none"
                } else if (e === "calendar") {
                    this.today.display = "none";
                    this.wordcloud.display = "none";
                    this.calendar.display = "";
                    this.total.display = "none";
                    this.log.display="none"
                } else if (e === "total") {
                    this.today.display = "none";
                    this.wordcloud.display = "none";
                    this.calendar.display = "none";
                    this.total.display = "";
                    this.log.display="none"
                }else if (e === "log") {
                    this.today.display = "none";
                    this.wordcloud.display = "none";
                    this.calendar.display = "none";
                    this.total.display = "none";
                    this.log.display=""
                }

            },
            weekUserChart() {
                http.get("/user/weekChart")
                    .then((response) => {
                        window.console.log(response);
                    })
                    .catch((error) => {
                        window.console.log(error);
                    })
            },
            fetchCategoryNoti(category) {
                http
                    .get(`/notification/categoryList/${this.$store.state.user_id}/${category}`)
                    .then(response => {
                        this.categoryNoti[category] = response.data.resvalue;
                        this.categoryNoti[category].forEach(e => {
                            const month = e.timestamp.substring(5, 7);
                            this.date[category][month] += 1;
                        });
                        return response;
                    })
                    .catch(e => console.log(e))
            }

        }
    }
</script>