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
                        <div class="btn-toolbar col-auto" role="toolbar" aria-label="Toolbar with button groups">
                            <div class="btn-group" role="group" aria-label="First group">
                                <button type="submit" class="btn btn-primary px-3 py-1" @click="getInterests()">
                                    <i class="icon-search"></i>
                                </button>
                                <button type="button" class="btn btn-danger px-3 py-1" @click="resetData()">
                                    <i class="icon-refresh"></i>
                                </button>
                            </div>
                        </div>
                    </div>
                    <div class="mt-5 text-white col-12 d-flex justify-content-center">
                        <p v-if="search">관심사 입력 후 스페이스바 혹은 엔터 키를 눌러주세요</p>
                        <div class="btn-toolbar" role="toolbar" aria-label="Toolbar with button groups">
                            <div v-for="data in searchData" :key="data.id" class="btn-group mx-1" role="group" aria-label="First group">
                                <button type="button" class="btn btn-info px-3 py-1">{{ data }}</button>
                            </div>
                        </div>
                    </div>
                </div>

                <!-- 친구 찾기 목록 -->
                <div class="mt-5 row justify-content-center">
                    <div @click="IdClick()" class="d-block col-6">
                        <button v-show="IdButton" type="button" class="btn btn-info col-12 btn-lg">ID</button> 
                        <button v-show="!IdButton" type="button" class="btn btn-dark col-12 btn-lg">ID</button>
                    </div>
                    <div @click="InterestClick()" class="d-block col-6">
                        <button v-show="!InterestButton" type="button" class="btn btn-dark col-12 btn-lg">INTEREST</button>
                        <button v-show="InterestButton" type="button" class="btn btn-info col-12 btn-lg">INTEREST</button>
                    </div>

                    <div>
                        <div v-show="IdButton" v-for="result in resultIds" :key="result.id" class="my-4">
                            <div v-if="result.user_id" class="media position-relative">
                                <img v-if="result.profile_url" :src="result.profile_url" class="rounded-circle mb-3" width="130px" height="130px" style="object-fit: cover;">
                                <img v-else src="https://t1.daumcdn.net/qna/image/1542632018000000528" class="mb-3" width="130px" height="130px" style="object-fit: cover;">
                                <div class="media-body">
                                    <div class="notification align-self-center ml-3">
                                        <h4 class="mt-2 text-white">{{ result.user_id }} 님</h4>
                                        <p v-if="result.description">{{ result.description }}</p>
                                        <p v-else> 반갑습니다 </p>
                                        <router-link :to="'/mypage/'+ result.user_id" class="text-primary">Go {{result.user_id}} page</router-link>
                                    </div>
                                </div> 
                            </div>
                        </div>
                        <div v-show="IdButton">
                            <h3 class="m-3 text-white">{{idErrorMsg}}</h3>
                        </div>

                        <div v-show="InterestButton" v-for="result in resultInterests" :key="result.id" class="my-4">
                            <div v-if="result.user_id" class="media position-relative">
                                <img v-if="result.profile_url" :src="result.profile_url" class="rounded-circle mb-3" width="130px" height="130px" style="object-fit: cover;">
                                <img v-else src="https://t1.daumcdn.net/qna/image/1542632018000000528" class="mt-2" width="130px" height="130px" style="object-fit: cover;">
                                <div class="media-body">
                                    <div class="notification align-self-center ml-3">
                                        <h4 class="mt-2 text-white ml-2">{{ result.user_id }} 님</h4>
                                        <div class="row">
                                            <div class="bbb " v-for="r in result.interest.split(' ', 2)" :key="r.id">
                                                <button class="blob-btn">
                                                    {{ r }}
                                                    <span class="blob-btn__inner">
                                                        <span class="blob-btn__blobs">
                                                            <span class="blob-btn__blob"></span>
                                                            <span class="blob-btn__blob"></span>
                                                            <span class="blob-btn__blob"></span>
                                                            <span class="blob-btn__blob"></span>
                                                        </span>
                                                    </span>
                                                </button>
                                            </div>
                                        </div>
                                        <div class="buttons">
                                            <router-link class="px-0 mx-0" :to="'/mypage/'+ result.user_id">
                                                <button class="slide">Go {{result.user_id}} page → </button>
                                            </router-link>
                                        </div>
                                    </div>
                                </div> 
                            </div>
                        </div>
                        <div v-show="InterestButton">
                            <h3 class="m-3 text-white">{{interestErrorMsg}}</h3>
                        </div>
                    </div>
                </div>
            </div>

        </div>
        <svg xmlns="http://www.w3.org/2000/svg" version="1.1">
            <defs>
                <filter id="goo">
                    <feGaussianBlur in="SourceGraphic" result="blur" stdDeviation="10"></feGaussianBlur>
                    <feColorMatrix in="blur" mode="matrix" values="1 0 0 0 0 0 1 0 0 0 0 0 1 0 0 0 0 0 21 -7" result="goo"></feColorMatrix>
                    <feBlend in2="goo" in="SourceGraphic" result="mix"></feBlend>
                </filter>
            </defs>
        </svg>

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
                    console.log(res)
                    if (res.data.resmsg == "아이디 검색 성공") {
                        for (var i=0; i<res.data.resValue.length; i++) {
                            var id = res.data.resValue[i].user_id
                            if (searchIdData.test(id) === true) {
                                this.resultIds.push({
                                    user_id: id,
                                    description: res.data.resValue[i].description,
                                    profile_url: res.data.resValue[i].profile_url,
                                    profile_filter: res.data.resValue[i].profile_filter,
                                })
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
                    console.log(res)
                    if (res.data.resmsg == "관심사 검색 성공") {
                        this.interestErrorMsg = ""
                        this.resultInterests = []
                        for (var i = 0; i < res.data.resValue.length; i++) {
                            this.resultInterests.push({
                                user_id: res.data.resValue[i].user_id,
                                interest: res.data.resValue[i].interest,
                                description: res.data.resValue[i].description,
                                profile_url: res.data.resValue[i].profile_url,
                                profile_filter: res.data.resValue[i].profile_filter,
                            })
                        }
                    } else {
                        this.interestErrorMsg = "관심사가 일치하는 친구가 없습니다.";
                        this.resultInterests = []
                    }
                })
                    .catch(() => {
                        this.errored = true;
                    })
                    .finally(() => (this.loading = false));
        },
    },
    watch: {
        inputIdData: function (inputId) {
            if (inputId === "") {
                this.resultIds = []
                this.idErrorMsg = ""
            } else {
                this.getIds()
                this.resultIds = []
                this.idErrorMsg = ""
            }
        },
    },
    mounted() {
        $('html').scrollTop(0);
    },
}
</script>

<style lang="scss" scoped>
// blob_btn
*, *:before, *:after {
  box-sizing: border-box;
  margin: 0;
  padding: 0;
}
.bbb {
  margin-left: 20px;
  margin-top: 3px;
//   text-align: center;
}
.blob-btn {
  z-index: 1;
  position: relative;
  padding: 5px 15px;
  margin-bottom: 2px;
  text-align: center;
  text-transform: uppercase;
  color:#c291e0;
  font-size: 13px;
  font-weight: bold;
  background-color: transparent;
  outline: none;
  border: none;
  -webkit-transition: color 0.5s;
  transition: color 0.5s;
  cursor: pointer;
}
.blob-btn:before {
  content: "";
  z-index: 1;
  position: absolute;
  left: 0;
  top: 0;
  width: 100%;
  height: 100%;
//   border: 2px solid #0fe0f5;
  border: 1px solid #c291e0;

}
.blob-btn:after {
  content: "";
  z-index: -2;
  position: absolute;
  left: 7px;
  top: 7px;
  width: 100%;
  height: 100%;
  border: 2px solid #222;
  -webkit-transition: all 0.3s 0.2s;
  transition: all 0.3s 0.2s;
}
.blob-btn:hover {
  color: #222;
}
.blob-btn:hover:after {
  -webkit-transition: all 0.3s;
  transition: all 0.3s;
  left: 0;
  top: 0;
}
.blob-btn__inner {
  z-index: -1;
  overflow: hidden;
  position: absolute;
  left: 0;
  top: 0;
  width: 100%;
  height: 100%;
}
.blob-btn__blobs {
  position: relative;
  display: block;
  height: 100%;
  -webkit-filter: url("#goo");
          filter: url("#goo");
}
.blob-btn__blob {
  position: absolute;
//   top: 6px;
  width: 25%;
  height: 200%;
//   background: #0fe0f5;
  background:#c291e0;
  border-radius: 100%;
  -webkit-transform: translate3d(0, 150%, 0) scale(1.7);
          transform: translate3d(0, 150%, 0) scale(1.7);
  -webkit-transition: -webkit-transform 0.45s;
  transition: -webkit-transform 0.45s;
  transition: transform 0.45s;
  transition: transform 0.45s, -webkit-transform 0.45s;
}
@supports ((-webkit-filter: url("#goo")) or (filter: url("#goo"))) {
  .blob-btn__blob {
    -webkit-transform: translate3d(0, 150%, 0) scale(1.4);
            transform: translate3d(0, 150%, 0) scale(1.4);
  }
}
.blob-btn__blob:nth-child(1) {
  left: 0%;
  -webkit-transition-delay: 0s;
          transition-delay: 0s;
}
.blob-btn__blob:nth-child(2) {
  left: 30%;
  -webkit-transition-delay: 0.08s;
          transition-delay: 0.08s;
}
.blob-btn__blob:nth-child(3) {
  left: 60%;
  -webkit-transition-delay: 0.16s;
          transition-delay: 0.16s;
}
.blob-btn__blob:nth-child(4) {
  left: 90%;
  -webkit-transition-delay: 0.24s;
          transition-delay: 0.24s;
}
.blob-btn:hover .blob-btn__blob {
  -webkit-transform: translateZ(0) scale(1.7);
          transform: translateZ(0) scale(1.7);
}
@supports ((-webkit-filter: url("#goo")) or (filter: url("#goo"))) {
  .blob-btn:hover .blob-btn__blob {
    -webkit-transform: translateZ(0) scale(1.4);
            transform: translateZ(0) scale(1.4);
  }
}

// slide
.slide:hover,
.slide:focus {
  box-shadow: inset 20em 0 0 0 var(--hover);
}
$colors: (
  fill: #a972cb,
  pulse: #ef6eae, 
  close: #ff7f82, 
  raise: #ffa260, 
  up: #e4cb58, 
  slide: #8fc866, 
  offset: #19bc8b
);
@each $button, $color in $colors {
  .#{$button} {
    --color: #{$color};
    --hover: #{adjust-hue($color, 45deg)};
  }
}
.slide {  
  color: var(--color);
  transition: 1s;
  &:hover,
  &:focus { 
    border-color: var(--hover);
    color: #fff;
  }
}
.slide {
  background: none;
  border: 0px solid;
  line-height: 1;
  margin-top: 10px;
  margin-left: 8px;
  padding: 1em 2em;
//   padding-left: 10px;  // 글씨 왼쪽의 패딩 줄임.
}
</style>