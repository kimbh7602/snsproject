<template>
    <div class="container-fluid photos">
        <div class="row justify-content-center">

            <div class="col-md-8 pt-4">
                <!-- 제목 -->
                <div class="row mb-5" data-aos="fade-up">
                    <div class="col-12">
                        <h2 class="text-white mb-4 text-center">친구 찾기</h2>
                    </div>
                </div>
                
                <!-- 친구 검색 바 -->
                <div class="d-flex justify-content-center" v-if="IdButton">
                    <input type="search" v-model="inputIdData" class="col-7 btn btn-white text-danger" placeholder="아이디를 입력해주세요">
                </div>
                <div class="" v-else>
                    <div class="col-12 d-flex justify-content-center">
                        <input type="search" class="col-7 btn btn-white text-danger" @keyup.enter.prevent='searchInput' @keydown.space.prevent='searchInput' id="itrlone" v-model="inputInterestData" placeholder="관심사를 입력해주세요">
                        <div class="btn-toolbar" role="toolbar" aria-label="Toolbar with button groups">
                            <div class="btn-group mx-1 col-4" role="group" aria-label="First group">
                                <button type="submit" class="btn btn-primary" @click="getInterests()">찾기</button>
                                <button type="button" class="btn btn-danger" @click="resetData()">리셋</button>
                            </div>
                        </div>
                    </div>
                    <div class="mt-5 text-white col-12 d-flex justify-content-center">
                        <p v-if="search">관심사 입력 후 스페이스바 혹은 엔터 키를 눌러주세요</p>
                        <div class="btn-toolbar" role="toolbar" aria-label="Toolbar with button groups">
                            <div v-for="s in searchData" :key="s.id" class="btn-group mx-1" role="group" aria-label="First group">
                                <button type="button" class="btn btn-info">{{s}}</button>
                            </div>
                        </div>
                    </div>
                </div>

                <!-- 친구 찾기 목록 -->
                <div class="mt-5 row justify-content-center">
                    <div @click="IdClick()" class="d-block col-6">
                        <button v-show="IdButton" type="button" class="btn btn-primary col-12 btn-lg">ID</button> 
                        <button v-show="!IdButton" type="button" class="btn btn-dark col-12 btn-lg">ID</button>
                    </div>
                    <div @click="InterestClick()" class="d-block col-6">
                        <button v-show="!InterestButton" type="button" class="btn btn-dark col-12 btn-lg">INTEREST</button>
                        <button v-show="InterestButton" type="button" class="btn btn-primary col-12 btn-lg">INTEREST</button>
                    </div>

                    <div class="m-3">
                        <div v-show="IdButton" v-for="rid in resultIds" :key="rid.id" class="my-4">
                            <div v-if="rid" class="media position-relative">
                                <!-- <span class="border border-white">
                                    <img src="" class="mr-3" alt="">
                                </span> -->
                                <div class="media-body">
                                    <h4 class="mt-0 text-white">{{ rid }} 님</h4>
                                    <p>blah blah</p>
                                    <a href="#" class="stretched-link">회원정보보기</a>
                                </div> 
                            </div>
                        </div>
                        <div v-show="IdButton">
                            <h3 class=" m-3 text-white">{{idErrorMsg}}</h3>
                        </div>

                        <div v-show="InterestButton" v-for="r in resultInterests" :key="r.id" class="my-4">
                            <div class="media position-relative">
                                <!-- <span class="border border-white">
                                    <img src="" class="mr-3" alt="">
                                </span> -->
                                <div class="media-body">
                                    <h5 class="mt-0 text-white">{{r.rid}}님</h5>
                                    <p>{{r.ritr}}</p>
                                    <a href="#" class="stretched-link">회원정보보기</a>
                                </div> 
                            </div>
                        </div>
                        <div v-show="InterestButton">
                            <h3 class=" m-3 text-white">{{interestErrorMsg}}</h3>
                        </div>
                    </div>
                </div>
            </div>

        </div>

        <!-- Modal -->
        <!-- <p id="modalBtn" style="display:none;" data-toggle="modal" data-target="#myModal"></p>
        <div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
            <div class="modal-dialog" role="document">
                <div class="modal-content">
                    <div class="modal-body" style="text-align:center;">
                        {{modalText}}
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-danger text-white" data-dismiss="modal">닫기</button>
                    </div>
                </div>
            </div>
        </div>       -->
    </div>
</template>

<script>
import http from '../http-common';
import $ from "jquery"
export default {
    data() {
        return {
            IdButton: true,
            InterestButton: false,
            inputIdData: "", // 검색창에 입력한 내용
            inputInterestData: "",
            resultIds: [], // 검색창에 입력한 내용과 같은 아이디 찾기
            resultInterests: [],
            idErrorMsg: "",
            interestErrorMsg: "",
            searchData: [],
            search: true,
            modal : false,
            modalText:"",
        }
    },
    methods: {
        IdClick() {
            this.IdButton = true;
            this.InterestButton = false;
        },
        InterestClick() {
            this.IdButton = false;
            this.InterestButton = true;
        },
        getIds() {
            var searchIdData = new RegExp(this.inputIdData);
            http
                .get('/user/searchByUserId/' + this.inputIdData)
                .then((res) => {
                    if (res.data.resmsg == "아이디 검색 성공") {
                        for (var i=0; i<res.data.resValue.length; i++) {
                            var id = res.data.resValue[i].user_id
                            if (searchIdData.test(id) === true) {
                                this.resultIds.push(id)
                            }
                        }
                    } else if (res.data.resmsg == "아이디 검색 실패") {
                        this.idErrorMsg = "아이디가 일치하는 친구가 없습니다."
                    }
                })
                    .catch(() => {
                        this.errored = true;
                    })
                    .finally(() => (this.loading = false));
        },
        searchInput() {
            var text = document.getElementById('itrlone').value;
            if (text == "") {
                return;
            }
            if (this.searchData.indexOf(text)) {
                this.searchData.push(text)
            }
            if (this.searchData === []) {
                this.search = true;
            } else {
                this.search = false;
            }
            this.inputInterestData = ""
        },
        resetData() {
            this.searchData = [],
            this.resultInterests = []
            this.interestErrorMsg = ""
            this.search = true;
        },
        getInterests() {
            this.loading = true;
            http
                .post('/user/searchByInterest', this.searchData)
                .then((res) => {
                    if (res.data.resmsg == "관심사 검색 성공") {
                        this.interestErrorMsg = ""
                        this.resultInterests = []
                        for (var i = 0; i < res.data.resValue.length; i++) {
                            this.resultInterests.push({ritr: res.data.resValue[i].interest, rid: res.data.resValue[i].user_id})
                        }
                    } else {
                        this.interestErrorMsg = "관심사가 일치하는 친구가 없습니다.";
                    }
                })
                    .catch(() => {
                        this.errored = true;
                    })
                    .finally(() => (this.loading = false));
        },
    },
    computed: {

    },
    watch: {
        inputIdData: function (inputId) {
            if (inputId === "") {
                this.resultIds = []
            } else {
                this.getIds()
                this.resultIds = []
                this.idErrorMsg = ""
            }
        },
    },
    mounted(){
        $('html').scrollTop(0);
    },
}
</script>

<style>

</style>