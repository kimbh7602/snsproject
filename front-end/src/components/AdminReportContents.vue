<template>
    <div class="container-fluid photos">
        <div class="col-12 row">
            <div class="col-1">
                <button @click="getData()">전체보기</button>
            </div>
            <div class="col-2">
                <button @click="getData('선정적인 게시물')">선정적인 게시물</button>
            </div>
            <div class="col-2">
                <button @click="getData('폭력적인 게시물')">폭력적인 게시물</button>
            </div>
            <div class="col-2">
                <button @click="getData('혐오적인 게시물')">혐오적인 게시물</button>
            </div>
            <div class="col-2">
                <button @click="getData('허위 게시물')">허위 게시물</button>
            </div>
            <div class="col-2">
                <button @click="getData('스팸 게시물')">스팸 게시물</button>
            </div>
            <div class="col-1">
                <button @click="getData('기타')">기타</button>
            </div>
        </div>
        <div class="row align-items-stretch">
            <!-- 게시물 하나 -->
            <div class="col-12 col-md-6 col-lg-4" data-aos="fade-up" style="padding: 10px 10px" v-for="con in contents"
                :key="con.contentId">
                <div class="d-block photo-item">
                    <!-- 이미지 처리 -->
                    
                    <div class="polaroid " v-if="con.dislike >= 5 && !readContents.includes(con.contentId) ">
                        <div :class="con.images[0].filter" style="width:100%; height:100%">
                            <img :src="con.images[0].imageUrl" alt="Image" class="img-fluid pa m-0"/>
                        </div>
                        
                    </div>
                    <!-- 마우스 오버 했을 때 -->
                    <div class="photo-text-more" v-if="con.dislike >= 5 && !readContents.includes(con.contentId)" style="background-color:#000;">
                        <div style="width:100%; height:100%">
                            <img alt="Image" class="img-fluid pa blur " style="box-shadow: 3px 3px 3px;" />
                            <div class="row">
                                <div class="value col-12">
                                    <div class='top col-12 row'>
                                        <div class="col-6">
                                            <p class="centertext text-white ">신고횟수 : {{con.dislike}}</p>
                                        </div>
                                        <div class="col-6">
                                            <p class="centertext text-white ">ID : {{con.userId}}</p>
                                        </div>
                                    </div>
                                    <div class='cate col-12'>
                                        <div class="col-12 comm" v-for="category in con.report_category"
                                            :key="category">
                                            <div v-if="category=='기타'">
                                                <p class="button centertext text-white"
                                                    @click="exval(con.report_exval)">{{category}} : </p><br>
                                            </div>
                                            <div v-else>
                                                <p class="centertext text-white">{{category}} : </p><br>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="val col-12">
                                        <div class="col-12 comm" v-for="value in con.report_value" :key="value">
                                            <p class="centertext text-white">{{value}}</p><br>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <button class="rightbutton btn btn-primary btn-sm"
                                @click="goDetail(con.contentId)">상세보기</button>
                            <button class="leftbutton btn btn-primary btn-sm"
                                @click="deleteContent(con.contentId)">삭제</button>
                        </div>
                    </div>

                </div>
            </div>


        </div>

        <div class="text-white text-center" v-if="this.contentErrorMsg">
            <h5>{{this.contentErrorMsg}}</h5>
        </div>

    </div>
</template>

<script>
    import $ from "jquery"
    import http from "../http-common"
    import store from '../store'
    export default {
        data() {
            return {
                uid: this.$store.state.user_id,
                temp_contents: [],
                contents: [{
                    contentId: "",
                    contentValue: "",
                    timestamp: "",
                    userId: "",
                    imageLength: 0,
                    dislike: 0,
                    images: [{
                        imageUrl: "",
                        filter: "",
                    }],
                    profileUrl: "",
                    profileFilter: "",
                    report_category: [],
                    report_value: [],
                    report_exval: [],
                }],
                loginId: store.state.user_id,
                contentErrorMsg: "",
                errored: false,
                readContents: [],
                status: "",
            }
        },
        methods: {
            deleteContent(e) {
                http
                    .delete("content/deleteContent/" + e +","+ this.uid)
                    .then(response => {
                        if (response.data['resmsg'] == "게시물 삭제 성공") {
                            this.$store.commit('setModalText', '게시물 삭제 성공');
                            document.getElementById('modalBtn').click();
                            this.$router.go(-1);
                        } else {
                            this.$store.commit('setModalText', "게시물 삭제 실패");
                            document.getElementById('modalBtn').click();
                        }

                    })
                    .catch((error) => {
                        this.errored = true;
                        alert(error);
                    })
                    .finally(() => (this.loading = false));
            },
            exval(msg) {
                this.$store.commit('setModalText', msg);
                document.getElementById('modalBtn').click();
            },
            readReCon(cid) {
                this.readContents.push(cid)
            },
            getData(e) {
                if (e === undefined) {
                    this.contents = []
                    http
                        .get('/userReport/adminreportContentList')
                        .then((res) => {
                            if (res.data.resValue.length > 0) {
                                this.contentErrorMsg = ""
                                // var tmp;
                                // for (var idx = 0; idx < res.data.resValue.length; idx++) {  
                                res.data.resValue.forEach(element => {
                                    http
                                        .get('/userReport/getreportcategory/' + element.content_id)
                                        .then((result) => {
                                            this.contents.push({
                                                contentId: element.content_id,
                                                contentValue: element.content_val.replace(
                                                    /\n/g,
                                                    "<br />"),
                                                timestamp: element.timestamp,
                                                userId: element.user_id,
                                                imageLength: element.imageLength,
                                                images: [{
                                                    imageUrl: element.imageList[0]
                                                        .image_url,
                                                    filter: element.imageList[0]
                                                        .filter,
                                                }],
                                                dislike: element.dislike,
                                                profileUrl: element.profile_url,
                                                profileFilter: element.profile_filter,
                                                report_category: result.data.resvalue,
                                                report_value: result.data.resvalue1,
                                                report_exval: result.data.resvalue2,
                                            })
                                        })
                                        .catch(() => {
                                            this.errored = true;
                                        })
                                });
                            } else {
                                this.contentErrorMsg = "타임라인이 없습니다."
                            }
                        })
                        .catch(() => {
                            this.errored = true;
                        })
                } else {
                    this.contents = []
                    http
                        .get('/userReport/adminreportContentList')
                        .then((res) => {
                            if (res.data.resValue.length > 0) {
                                this.contentErrorMsg = ""
                                // var tmp;
                                // for (var idx = 0; idx < res.data.resValue.length; idx++) {  
                                res.data.resValue.forEach(element => {
                                    http
                                        .get('/userReport/getreportcategory/' + element.content_id)
                                        .then((result) => {
                                            if (result.data.resvalue.includes(e)) {
                                                this.contents.push({
                                                    contentId: element.content_id,
                                                    contentValue: element.content_val.replace(
                                                        /\n/g,
                                                        "<br />"),
                                                    timestamp: element.timestamp,
                                                    userId: element.user_id,
                                                    imageLength: element.imageLength,
                                                    images: [{
                                                        imageUrl: element.imageList[0]
                                                            .image_url,
                                                        filter: element.imageList[0]
                                                            .filter,
                                                    }],
                                                    dislike: element.dislike,
                                                    profileUrl: element.profile_url,
                                                    profileFilter: element.profile_filter,
                                                    report_category: result.data.resvalue,
                                                    report_value: result.data.resvalue1,
                                                    report_exval: result.data.resvalue2,
                                                })
                                            }
                                        })
                                        .catch(() => {
                                            this.errored = true;
                                        })
                                });
                            } else {
                                this.contentErrorMsg = "타임라인이 없습니다."
                            }
                        })
                        .catch(() => {
                            this.errored = true;
                        })

                }

            },
            goDetail: function (con_id) {
                this.$router.push({
                    name: 'bio',
                    params: {
                        cid: con_id
                    }
                })
            },

        },
        mounted() {
            $('html').scrollTop(0);
            this.$nextTick(() => {
                // 모든 화면이 렌더링된 후 호출됩니다.
                // console.log(document.querySelectorAll("ul").length)
                $('.js-clone-nav').each(function () {
                    var $this = $(this);
                    $this.clone().attr('class', 'site-nav-wrap').appendTo('.site-mobile-menu-body');
                });
                document.querySelector("ul").remove();
                // document.querySelector("ul").remove();
            });
        },
    }
</script>
<style scoped>
    .ellipsis {
        overflow: hidden;
        text-overflow: ellipsis;
        display: -webkit-box;
        -webkit-line-clamp: 3;
        -webkit-box-orient: vertical;
        word-wrap: break-word;
        line-height: 2;
        height: 6rem;
    }

    .pa {
        position: relative;
    }

    .cate {
        position: absolute;
        top: 80%;
        left: -5%;
    }

    .val {
        position: absolute;
        top: 80%;
        left: 12%;
    }

    .comm {
        margin-bottom: -5%;
    }
    .top{
        margin-bottom: -7%;
    }

    .centertext {
        position: absolute;
        text-align: center;
        padding: 5px;
        display: block;
        top: 37%;
        left: 50%;
        background-color: #000;
        font-size: 13px;
        transform: translateY(-50%);
        transform: translateX(-50%);
    }

    .value {
        position: absolute;
        display: block;
        top: -5%;
    }

    .rightbutton {
        position: absolute;
        text-align: center;
        display: block;
        top: 80%;
        left: 85%;
        background-color: #333;
        transform: translateY(-50%);
        transform: translateX(-50%);
    }

    .leftbutton {
        position: absolute;
        text-align: center;
        display: block;
        top: 80%;
        left: 15%;
        background-color: #333;
        transform: translateY(-50%);
        transform: translateX(-50%);
    }

    .blur {
        -webkit-filter: blur(5px);
        -moz-filter: blur(5px);
        -o-filter: blur(5px);
        -ms-filter: blur(5px);
        filter: blur(5px);
        opacity: 0.7;
        /* transform: scale(1); */
        overflow: hidden;
        display: block;
        width: 80%;
        height: 80%;
    }

    .modal-dialog {
        width: 30%;
        height: 50%;
    }

    .modal-content {
        height: auto;
        min-height: 100%;
    }

    .mydiv {
        margin-top: 7em;
    }

    .size {
        font-size: 1em;
    }
</style>