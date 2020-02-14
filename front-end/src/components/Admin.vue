<template v-slot:default>
    <div class="container-fluid photos">
        <div class="row justify-content-center">

            <div class="col-md-12 pt-4" data-aos="fade-up">
                <h2 class="text-white mb-4">Admin</h2>


                <div class="row">
                    <div class="col-md-12">
                        <div class="col-md-12">
                            <v-tabs fixed-tabs background-color="indigo" dark>
                                <v-tab v-on:click="tabclick('calendar')">
                                    일정
                                </v-tab>
                                <v-tab v-on:click="tabclick('wordcloud')">
                                    관심사
                                </v-tab>
                                <v-tab v-on:click="tabclick('register')">
                                    가입자 현황
                                </v-tab>
                                <v-tab v-on:click="tabclick('total')">
                                    게시물 통계
                                </v-tab>
                                <v-tab v-on:click="tabclick('report')">
                                    신고 게시물
                                </v-tab>
                                <v-tab v-on:click="tabclick('log')">
                                    로그
                                </v-tab>
                            </v-tabs>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <div class="container-fluid photos">
            <div class="row justify-content-center">
                <div class="col-md-12 pt-4" data-aos="fade-up">
                    <div class="col-md-12">
                        <div class="col-md-12">
                            <div class="md-5" id="contents">
                                <div id="calendar" name="calendar" v-bind:style="calendar">
                                    <mycalendar></mycalendar>
                                </div>
                                <div id="worldcloud" name="wordcloud" v-bind:style="wordcloud">
                                    <wordcloud-vue></wordcloud-vue>
                                </div>
                                <div id="register" name="register" v-bind:style="register">

                                    <h2 class="text-white mb-4">월별유저현황</h2>
                                    <canvas id="monUser" class="col-xs-12 col-sm-12 col-md-12"></canvas>
                                    <h2 class="text-white mb-4">최근한달 유저현황</h2>
                                    <canvas id="dayUser" class="col-xs-12 col-sm-12 col-md-12"></canvas>
                                </div>
                                <div id="total" name="total" v-bind:style="total">
                                    <h2 class="text-white mb-4">월별</h2>
                                    <canvas id="myChart" class="col-xs-12 col-sm-12 col-md-12"></canvas>
                                    <br /><br /><br />
                                    <h2 class="text-white mb-4">주별</h2>
                                    <canvas id="monChart" class="col-xs-12 col-sm-12 col-md-12"></canvas>
                                </div>
                                <div id="report" name="report" v-bind:style="report">
                                    <adminreportcontents></adminreportcontents>
                                </div>
                                <div id="log" name="log" v-bind:style="log">
                                    <adminlog></adminlog>
                                </div>
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
    import adminlog from "./AdminLog.vue"
    import adminreportcontents from "./AdminReportContents.vue"

    export default {
        components: {
            wordcloudVue,
            mycalendar,
            adminlog,
            adminreportcontents,
        },
        data() {
            return {
                uid: this.$store.state.user_id,
                visit: "",
                totaldata: [],
                monthdata: [],
                monthuser: [],
                dayuser: [],
                register: {
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
                },
                report: {
                    display: 'none',
                },
                log: {
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
            if (this.uid === "admin1" || this.uid === "admin2" || this.uid === "admin3" || this.uid === "admin4" || this
                .uid === "admin5") {
                $('html').scrollTop(0);
                // 월별차트

                http
                    .get("admin/totalcontents")
                    .then(response => {
                        if (response.data['resmsg'] == "조회성공") {
                            this.totaldata = response.data['resvalue'];
                            // this.fetchCategoryNoti('contents');

                            const myChart = new Chart('myChart', {
                                type: 'line',
                                data: {
                                    labels: [
                                        '1월', '2월', '3월', '4월', '5월', '6월', '7월', '8월', '9월', '10월',
                                        '11월', '12월'
                                    ],

                                    datasets: [{
                                        label: '월별 게시글 수',
                                        borderColor: '#f23a2e',
                                        pointBackgroundColor: 'white',
                                        pointBorderWidth: 2,
                                        pointRadius: 6,
                                        pointStyle: 'rectRot',
                                        lineTension: 0.1,
                                        data: this.totaldata
                                    }]
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
                // 주별차트
                http
                    .get("admin/moncontents")
                    .then(response => {
                        if (response.data['resmsg'] == "조회성공") {
                            this.monthdata = response.data['resvalue'];

                            const monChart = new Chart('monChart', {
                                type: 'line',
                                data: {
                                    labels: [
                                        '4주전', '3주전', '2주전', '1주전', '이번주'
                                    ],

                                    datasets: [{
                                        label: '주별 게시글 수',
                                        borderColor: '#13424a',
                                        pointBackgroundColor: 'white',
                                        pointBorderWidth: 2,
                                        pointRadius: 6,
                                        pointStyle: 'rectRot',
                                        lineTension: 0.1,
                                        data: this.monthdata
                                    }]
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

                            monChart;
                        }
                    })
                //월별 가입자수
                http
                    .get("admin/totaluser")
                    .then(response => {
                        if (response.data['resmsg'] == "조회성공") {
                            this.monthuser = response.data['resvalue'];


                            const monUser = new Chart('monUser', {
                                type: 'line',
                                data: {
                                    labels: [
                                        '1월', '2월', '3월', '4월', '5월', '6월', '7월', '8월', '9월', '10월',
                                        '11월', '12월'
                                    ],

                                    datasets: [{
                                        label: '월별 가입자수',
                                        borderColor: '#f23a2e',
                                        pointBackgroundColor: 'white',
                                        pointBorderWidth: 2,
                                        pointRadius: 6,
                                        pointStyle: 'rectRot',
                                        lineTension: 0.1,
                                        data: this.monthuser
                                    }]
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

                            monUser;
                        }
                    })

                //최근 한달 가입자수
                http
                    .get("admin/dayuser")
                    .then(response => {
                        if (response.data['resmsg'] == "조회성공") {
                            this.dayuser = response.data['resvalue'];


                            const dayUser = new Chart('dayUser', {
                                type: 'line',
                                data: {
                                    labels: [
                                        '29일전', '28일전', '27일전', '26일전', '25일전', '24일전', '23일전', '22일전',
                                        '21일전',
                                        '20일전', '19일전', '18일전', '17일전', '16일전', '15일전', '14일전', '13일전',
                                        '12일전', '11일전',
                                        '10일전', '9일전', '8일전', '7일전', '6일전', '5일전', '4일전', '3일전', '2일전',
                                        '1일전', '오늘'
                                    ],

                                    datasets: [{
                                        label: '최근한달 가입자수',
                                        borderColor: '#13424a',
                                        pointBackgroundColor: 'white',
                                        pointBorderWidth: 2,
                                        pointRadius: 6,
                                        pointStyle: 'rectRot',
                                        lineTension: 0.1,
                                        data: this.dayuser
                                    }]
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

                            dayUser;
                        }
                    })

            } else {
                this.$store.commit('setModalText', '관리자만 접근할 수 있습니다.');
                document.getElementById('modalBtn').click();
                this.$router.push("/")
            }
        },
        methods: {
            tabclick(e) {
                if (e === "register") {
                    this.register.display = "";
                    this.wordcloud.display = "none";
                    this.calendar.display = "none";
                    this.total.display = "none";
                    this.report.display = "none";
                    this.log.display = "none"
                } else if (e === "wordcloud") {
                    this.register.display = "none";
                    this.wordcloud.display = "";
                    this.calendar.display = "none";
                    this.total.display = "none";
                    this.report.display = "none";
                    this.log.display = "none"
                } else if (e === "calendar") {
                    this.register.display = "none";
                    this.wordcloud.display = "none";
                    this.calendar.display = "";
                    this.total.display = "none";
                    this.report.display = "none";
                    this.log.display = "none"
                } else if (e === "total") {
                    this.register.display = "none";
                    this.wordcloud.display = "none";
                    this.calendar.display = "none";
                    this.total.display = "";
                    this.report.display = "none";
                    this.log.display = "none"
                } else if (e === "report") {
                    this.register.display = "none";
                    this.wordcloud.display = "none";
                    this.calendar.display = "none";
                    this.total.display = "none";
                    this.report.display = "";
                    this.log.display = "none";
                } else if (e === "log") {
                    this.register.display = "none";
                    this.wordcloud.display = "none";
                    this.calendar.display = "none";
                    this.total.display = "none";
                    this.report.display = "none";
                    this.log.display = ""
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
            }
        }
    }
</script>