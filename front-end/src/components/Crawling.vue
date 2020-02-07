<template>
    <div class="container-fluid photos">
        <div class="row justify-content-center">

            <div class="col-md-8 pt-4">
                <!-- 제목 -->
                <div class="row mb-5" data-aos="fade-up">
                    <div class="col-12">
                        <h2 class="text-white mb-4 text-center">Search In Instagram</h2>
                    </div>
                </div>
                
                <!-- 친구 검색 바 -->
                <div class="row form-group mb-5">
                    <div class="col-md-12">
                    <label class="text-white">Instagram Hashtag Search</label>
                    <input style="text-align:center;" v-model="searchKeyword" type="text" class="form-control"><br>
                    <!-- <input type="subject" autocomplete='off' @keyup.enter.prevent='appendDiv' @keydown.space.prevent='appendDiv' id="interest" class="form-control">
                    <div style="margin-top:10px; margin-bottom:10px;" id="itrl"></div> -->
                    <input @click="searchHashtag" type="button" value="Search" class="btn btn-outline-light btn-block text-white">
                    </div>
                </div>
                <div v-if="!isLoading&&(contents == null || contents.length == 0)" style="text-align:center;">
                    검색 결과가 없습니다.
                </div>
                <div style="text-align:center;" v-if="isLoading&&(contents == null || contents.length == 0)">
                    <img class="col-md-12" src="/theme/images/loading10.gif" />
                </div>
                <div v-for="(content, index) in contents" :key="index" v-else class="container-fluid photos">
                    <div style="font-size:20px; display:block;" class="text-white">#{{content.tag}}</div><br>
                    <!-- <div class="row align-items-stretch">
                            <div class="col-6 col-md-6 col-lg-4" data-aos="fade-up"  v-for="(img, index) in content.list" :key="index">
                                <div class="d-block photo-item">
                                    <img :src="img.img_url" alt="Image" class="img-fluid my-0">
                                    <div class="photo-text-more">
                                        
                                    </div>
                                </div>
                            </div>
                    </div> -->

                    <div class="row align-items-stretch">
                        <div class="col-6 col-md-6 col-lg-4" style="padding: 10px 10px" data-aos="fade-up"  v-for="(img, index) in content.list" :key="index">
                        <div class="d-block photo-item">
                            <div class="all-scroll pos-relative mt-50">
                            <div class="swiper-scrollbar"></div>
                            <div class="swiper-container oflow-visible" data-slide-effect="coverflow" data-autoheight="false"  data-swiper-wheel-control="true"
                                                        data-swiper-speed="1000" data-swiper-margin="25" data-swiper-slides-per-view="1"
                                                        data-swiper-breakpoints="true" data-swiper-autoplay="false" data-scrollbar="true"
                                                        data-swiper-loop="false" data-swpr-responsive="[1, 2, 1, 2]">
                                <div class="swiper-wrapper">
                                        <div class="" style="width:100%; height:300px">
                                        <img :src="img.img_url" style="box-shadow: 3px 3px 3px;" alt="Image"/>
                                        <!-- <div style="text-align:right;">from {{item.user_id}}</div> -->
                                        </div>
                                </div>
                            </div>
                            </div>
                            <div class="photo-text-more">
                                <div class="" data-aos="fade-up">
                                <div class="d-block photo-item">
                                    <div class="postcard">
                                    <div class="content">
                                        <!-- 우표 -->
                                        <div>
                                        <a :href="img.href">
                                        <div class="stamp-cover" style="background:black; height:52px; width:52px;">
                                            <div class="stamp" style=" margin:1px; float:right; background-color:white; height:50px; width:50px;">
                                            </div>
                                        </div>
                                        <img src="../../public/theme/images/ai.jpg" style="width:37px;height:37px;" class="stamp-img"/>
                                        <img src="../../public/theme/images/stamp1.png" style="width:45px;height:45px;" alt="Postage mark" class="postmark">
                                        <!-- 끝 -->
                                        <div class="mail-title offset-1 col-9" style="text-align:left;"><p style="color:black; font-size:2em; font-family: loveson;">Dear You</p></div>
                                        <div class="mail-message offset-2 col-8 ellipsis" style="color:black; font-family: loveson; word-break:break-all;text-align:left;">Visit this Instagram?</div>
                                        <div class="col-11 col-offset-1" style="color:black; font-family: loveson; word-break:break-all;text-align:right;">from Stranger</div>
                                        </a>
                                        </div>
                                    </div>
                                    </div>
                                </div>
                                </div>
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
// import http from '../http-common';
import axios from 'axios'
export default {
    data() {
        return {
            contents: [],
            isLoading:false,
            isLocation:false,
            isLocationSelect:false,
            searchKeyword:"",
            resultList:[],
            keywordList:[],
        }
    },
    methods: {
        searchHashtag(){
            if(this.searchKeyword.includes(" ")){
                this.$store.commit('setModalText', "단어로만 검색하세요!");
                document.getElementById('modalBtn').click();
                return;
            }
            this.isLoading = true;
            this.contents = [];
            this.keywordList = [];
            this.resultList = [];
            if(this.searchKeyword != ""){
                axios.post("https://translation.googleapis.com/language/translate/v2?key=AIzaSyAcnkt6IBUt-bGIMw4u-VEIYpesgw4-2Lk",{
                          "q": [this.searchKeyword],
                          "target": "en"
                        })
                            .then((response)=>{
                                const keyword = response.data.data.translations[0].translatedText;
                                
                                axios.get("http://192.168.100.41:5000/searchByKeyword/" + keyword)
                                    .then((response) => {
                                        this.keywordList = response.data;
                                        axios.post("https://translation.googleapis.com/language/translate/v2?key=AIzaSyAcnkt6IBUt-bGIMw4u-VEIYpesgw4-2Lk",{
                                            "q": this.keywordList,
                                            "target": "ko"
                                            })
                                            .then((response) => {
                                                response.data.data.translations.some( element => {
                                                    if(!element.translatedText.includes(" ")&&!this.resultList.includes(element.translatedText)){
                                                        this.resultList.push(element.translatedText);
                                                    }
                                                    return(this.resultList.length>=3);
                                                });
                                                this.resultList.forEach(keyword => {
                                                    const url = "http://192.168.100.41:5000/instagram/"+keyword;

                                                    axios.get(url)
                                                        .then((response) => {
                                                            this.contents.push(response.data);
                                                            this.isLoading = false;
                                                        })
                                                        .catch((error) => {
                                                            window.console.log(error);
                                                        })
                                                });
                                            })
                                    })
                            })
                            .catch((error)=>{
                              alert(error)
                            })
            }
            

            

            // let keyword = this.searchKeyword;
            // keyword.replace(" ", "+");

            // const url = "http://localhost:5000/location/"+keyword;
            
            // axios.get(url)
            //     .then((response)=>{
            //         this.locationList = response.data.results;
            //         this.center.lat = parseFloat(this.locationList[0].geometry.location.lat);
            //         this.center.lng = parseFloat(this.locationList[0].geometry.location.lng);

            //         this.locationList.forEach(element => {
            //             let addr = "";
            //             axios.post("https://translation.googleapis.com/language/translate/v2?key=AIzaSyAcnkt6IBUt-bGIMw4u-VEIYpesgw4-2Lk",{
            //               "q": [element.formatted_address],
            //               "target": "ko"
            //             })
            //                 .then((response)=>{
            //                     addr = response.data.data.translations[0].translatedText;
            //                     this.markers.push({
            //                         position : {
            //                             lat: parseFloat(element.geometry.location.lat),
            //                             lng: parseFloat(element.geometry.location.lng)
            //                         },
            //                         title : element.name,
            //                         info : {
            //                             title: element.name,
            //                             text: addr
            //                         }
            //                     })
            //                 })
            //                 .catch((error)=>{
            //                   alert(error)
            //                 })
            //         })
            //     })
            //     .catch((error)=>{
            //         alert(error)
            //     })
        },
        appendDiv() {
            var colorCode  = "#" + Math.round(Math.random() * 0xffffff).toString(16);
            var div = document.createElement('div');
            var span = document.createElement('span');
            var bold = document.createElement('bold')
            var text = document.getElementById('interest').value;
            if(text==""){
              return;
            }
            // this.itrlist.push(text);
            bold.innerText = text;
            div.style.background=colorCode;
            div.classList.add('roundedge');
            div.classList.add('itrlone');
            div.classList.add('text-white');
            span.classList.add('icon-remove')
            span.addEventListener("click", function(event){
              event.target.parentNode.remove();
            });
            div.appendChild(bold);
            div.appendChild(span);
            document.getElementById('itrl').appendChild(div);
            document.getElementById('interest').value='';
            document.getElementById('interest').focus();
        },
        deleteDiv() {
            var r = document.getElementById('itrl');
            var u = document.getElementById('uitrl');
            document.getElementById('parentUitrl').removeChild(u);
            document.getElementById('parentItrl').removeChild(r);
            var rediv = document.createElement('div');
            var rediv2 = document.createElement('div');
            rediv.id = 'itrl';
            rediv2.id = 'uitrl';
            document.getElementById('parentItrl').appendChild(rediv);
            document.getElementById('parentUitrl').appendChild(rediv2);
            //관심사 리셋되며 배열도 리셋
            this.itrlist.splice(0);
        },
    }
}
</script>

<style>

</style>