<template>
    <div class="container-fluid photos">
        <div class="row justify-content-center">

            <div class="col-md-8 pt-4">
                <!-- 제목 -->
                <div class="row mb-5" data-aos="fade-up">
                    <div class="col-12">
                        <h2 class="text-white mb-4 text-center">게시물 찾기</h2>
                    </div>
                </div>
                
                <!-- 친구 검색 바 -->
                <div v-if="isLocation&&!isLocationSelect" class="row form-group mb-5">
                    <div class="col-md-12">
                    <label class="text-white">Location Search</label> 
                    <input v-model="searchKeyword" type="text" class="form-control">
                    <br>
                    <input data-toggle="modal" data-target="#mapModal" @click="searchLocation" type="button" value="Search" class="btn btn-outline-light btn-block text-white">
                    </div>
                </div>
                <div v-if="!isLocation&&!isLocationSelect">
                    <input @click="isLocation=!isLocation" type="button" value="위치정보 등록" class="btn btn-outline-light btn-block">
                    <br/><br/><br/>
                </div>
                <div v-if="isLocationSelect" class="row form-group mb-5">
                    <div class="col-md-12">
                    <label class="text-white">Selected Location</label>
                    <input style="text-align:center;" v-model="selectedLocation.name" type="text" class="form-control"><br>
                    <input style="text-align:center;" v-model="distString" type="text" class="form-control"><br>
                    <div class="row form-group mb-5 justify-content-center">
                        <div class="dropdown">
                        <button class="btn btn-secondary dropdown-toggle" type="button" id="dropdownMenuButton" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                            Select Search Distance
                        </button>
                        <div class="dropdown-menu" aria-labelledby="dropdownMenuButton">
                            <a class="dropdown-item" href="#" @click="dist=0.01; distString='1 Km'">1 Km</a>
                            <a class="dropdown-item" href="#" @click="dist=0.005; distString='500 m'">500 m</a>
                            <a class="dropdown-item" href="#" @click="dist=0.003; distString='300 m'">300 m</a>
                        </div>
                        <!-- <select v-model="dist" class="form-control" aria-placeholder="Select Search Distance" style="font-color:white; background-color:black; border:none; text-align:center;">
                            <option disabled value="" selected class="form-control" style="border:none;">Select Search Distance</option>
                            <option value="0.01" class="form-control">1 Km</option>
                            <option value="0.005" class="form-control">500 m</option>
                            <option value="0.003" class="form-control">300 m</option>
                        </select> -->
                        </div>
                    </div>
                    <input @click="isLocationSelect=!isLocationSelect; dist=0.01; distString='1 Km'" type="button" value="다시 위치검색" class="btn btn-outline-warning btn-block">
                    <input @click="findContentByLocation" type="button" value="게시물 검색" class="btn btn-outline-info btn-block">
                    <br/><br/><br/>
                    </div>
                </div>
                
                <div class="modal fade" id="mapModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
                    <div class="modal-dialog" role="document">
                        <div class="modal-content">
                            <div class="modal-body" style="text-align:center;">
                                <gmap-map
                                :center="center"
                                :zoom="15"
                                style="width:100%;  height: 400px;"
                                >
                                <gmap-info-window :options="infoOptions" :position="infoWinPos" :opened="infoWinOpen" @closeclick="infoWinOpen=false">
                                    <br><h4 style="align:left">{{infoContent.title}}</h4>
                                    {{infoContent.text}}<br>
                                    <img width="150px" :src="infoContent.img" />
                                </gmap-info-window>

                                <gmap-marker
                                    :key="index"
                                    v-for="(m, index) in markers"
                                    :position="m.position"
                                    :title="m.title"
                                    :info="m.info"
                                    @click="placeClick(m, index)"
                                ></gmap-marker>
                                </gmap-map>
                            </div>
                            <div class="modal-footer">
                                <button type="button" class="btn btn-success text-white" @click="selectLocation" data-dismiss="modal">선택</button>
                                <button type="button" class="btn btn-danger text-white" data-dismiss="modal">닫기</button>
                            </div>
                        </div>
                    </div>
                </div>
                <div v-if="contents == null" style="text-align:center;">
                    검색 결과가 없습니다.
                </div>
                <div v-else class="container-fluid photos">
                    <div class="row align-items-stretch">
                            
                            <div class="col-6 col-md-6 col-lg-4" data-aos="fade-up"  v-for="content in contents" :key="content.content_id">
                                <div class="d-block photo-item">
                                    <img :src="content.imageList[0].image_url" alt="Image" class="img-fluid my-0">
                                    <div class="photo-text-more">
                                        <h3 class="heading mx-2 ellipsis" v-on:click="goDetail()">{{content.content_val}}</h3>
                                        <!-- <span class="meta">
                                        <div class="mb-3 my-3 d-flex justify-content-around size">
                                            <div v-on:click="clickHeart()">
                                            <i class="icon-heart" v-if="like"></i>
                                            <i class="icon-heart-o" v-else></i>
                                            </div>
                                            <div v-on:click="clickFollow()">
                                            <i class="icon-check" v-if="follow"></i>
                                            <i class="icon-user-plus" v-else></i>
                                            </div>
                                            <div v-on:click="clickBell()">
                                            <i class="icon-bell" v-if="bell"></i>
                                            <i class="icon-bell-o" v-else></i>
                                            </div>
                                        </div>
                                        </span> -->
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
import http from '../http-common';
import axios from 'axios'
import $ from "jquery"
export default {
    data() {
        return {
            contents: null,
            isLocation:false,
            isLocationSelect:false,
            searchKeyword:"",
            locationList:[],
            center: { lat: 45.508, lng: -73.587 },
            markers: [],
            places: [],
            res:[],
            infoWinPos : null,
            infoWinOpen : false,
            infoContent : {
                title : null,
                text : null,
                img : null
            },
            currentIdx : null,
            currentPlace: null,
            infoOptions: {
                pixelOffset: {
                width: 0,
                height: -35
                }
            },
            selectedLocation: null,
            dist:0.01,
            distString:"1 Km"
        }
    },
    methods: {
        searchLocation(){
            let keyword = this.searchKeyword;
            keyword.replace(" ", "+");

            const url = "http://localhost:5000/location/"+keyword;
            
            axios.get(url)
                .then((response)=>{
                    this.locationList = response.data.results;
                    this.center.lat = parseFloat(this.locationList[0].geometry.location.lat);
                    this.center.lng = parseFloat(this.locationList[0].geometry.location.lng);

                    this.locationList.forEach(element => {
                        let addr = "";
                        axios.post("https://translation.googleapis.com/language/translate/v2?key=AIzaSyAcnkt6IBUt-bGIMw4u-VEIYpesgw4-2Lk",{
                          "q": [element.formatted_address],
                          "target": "ko"
                        })
                            .then((response)=>{
                                addr = response.data.data.translations[0].translatedText;
                                this.markers.push({
                                    position : {
                                        lat: parseFloat(element.geometry.location.lat),
                                        lng: parseFloat(element.geometry.location.lng)
                                    },
                                    title : element.name,
                                    info : {
                                        title: element.name,
                                        text: addr
                                    }
                                })
                            })
                            .catch((error)=>{
                              alert(error)
                            })
                    })
                })
                .catch((error)=>{
                    alert(error)
                })
        },

        placeClick(marker, index){
          this.infoWinPos = marker.position;
          this.infoContent = marker.info;

          if(this.currentIdx == index){
              this.infoWinOpen = !this.infoWinOpen;
          }else{
              this.infoWinOpen = true;
              this.currentIdx = index;
              this.selectedLocation = {
                  position: marker.position,
                  name: marker.title,
                  addr: marker.info.text
              };
          }
      },

      selectLocation(){
          if(this.selectedLocation != null){
              this.isLocationSelect = true;
          }else{
              this.isLocation = false;
          }
      },

      findContentByLocation(){
          http.post("/content/findByLocation",{
              lat: this.selectedLocation.position.lat,
              lng: this.selectedLocation.position.lng,
              dist: this.dist
          })
          .then((response)=>{
              this.contents = response.data.resValue;

          })
      }
    },
    computed: {

    },
    watch: {
        
    },
    mounted(){
        $('html').scrollTop(0);
    }
}
</script>

<style>

</style>