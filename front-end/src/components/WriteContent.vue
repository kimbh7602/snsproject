<template>
<div class="col-md-12">
    <div class="offset-md-1 col-md-10">
        <div style="height:10px;"></div>
        <div class="all-scroll pos-relative mt-50">
            <h5 class="mb-50"><b>Image</b></h5>                                            
            <div class="swiper-scrollbar"></div>
            <div class="swiper-container oflow-visible" data-slide-effect="flip" data-autoheight="false" 
                                    data-swiper-speed="200" data-swiper-margin="25" data-swiper-slides-per-view="1"
                                    data-swiper-breakpoints="true" data-scrollbar="true" data-swiper-loop="false"
                                    data-swpr-responsive="[1, 2, 1, 2]">
                <div class="swiper-wrapper">
                        <div :class="img.filter" id="img-select" class="img-fluid swiper-slide" v-for="img in imgs" :key="img.filter"
            :style="{ backgroundImage: 'url(' + img.base64 + ')' }"></div>
                </div>
                <div v-if="exist" class="swiper-pagination"  slot="pagination"></div>
                <div v-if="exist" class="swiper-button-prev" slot="button-prev"></div>
                <div v-if="exist" class="swiper-button-next" slot="button-next"></div>
            </div>
        </div>
        <div class="row form-group">                    
            <div class="col-md-12" id="parentItrl">
            <label class="text-white" for="itrlist">Hashtag</label>
            <div id="" style="display:inline-block"></div>
            <input type="subject" autocomplete='off' @keyup.enter.prevent='appendDiv' @keydown.space.prevent='appendDiv' id="interest" class="form-control">
            <div id="itrl"></div>
            </div>
        </div>
        <div class="row form-group mb-5">
            <div class="col-md-12">
            <label class="text-white" for="uintr">Story</label> 
            <textarea name="message" id="message" v-model="intro" cols="30" rows="7" class="form-control" style="resize:none;" placeholder="Write your story"></textarea>
            </div>
        </div>
        <div style="margin-top:1%; margin-left:5%;margin-right:5%; height:50px;">
            <div style="display:inline-block; float :left">
            <input type="button" value="처음으로" @click="goPrev" class="btn btn-primary btn-md text-white">
            </div>
            <div style="display:inline-block; float:right">
            <input type="button" value="다음" @click="goNext" class="btn btn-success btn-md text-white">
            </div>
        </div>
    </div>
</div>
</template>

<script>
import http from "../http-common"
export default {
    name:"writecontent",
    props:["imgs"],
    data(){
        return{
            intro:"",
            filterType:"",
            itrlist:[],
            defaultImag:{base64:"",filter:"normal"},
            errored:false,
            loading:true,
            exist:false,
        }
    },
    methods:{
        appendDiv() {
            var colorCode  = "#" + Math.round(Math.random() * 0xffffff).toString(16);
            var div = document.createElement('div');
            var span = document.createElement('span');
            var bold = document.createElement('bold')
            var text = document.getElementById('interest').value;
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
        goPrev() {
            this.$router.push("/addimage");
        },
        goNext() {
            // this.imgs[this.imgs.length-1].filter = this.filterType;
            var itrltemp = document.getElementsByClassName('itrlone');
            for(var i=0; i<itrltemp.length; i++){
                this.itrlist[i] = itrltemp[i].innerText;
            }
            http
              .post("/content/insertContent",{
                content_val:this.intro,
                user_id:"test",
                hashtagList:this.itrlist,
                imageList:this.imgs,
              })
              .then(response => {
                if (response.data['resmsg'] == "게시물 추가 성공")
                    alert("등록 성공!");
                else
                    alert("등록 실패!");
                this.$router.push("/");
              })
              .catch(() => {
                  this.errored = true;
                  alert("error");
              })
              .finally(() => (this.loading = false));
        },
    },
    mounted(){
        if(this.imgs.length==0){
            this.imgs.push(this.defaultImag);
        }
        else{
            this.filterType = this.imgs[this.imgs.length-1].filter;
            this.exist=true;
            console.log(this.imgs);
        }
        let recaptchaScripta = document.createElement('script')
        recaptchaScripta.setAttribute('type',"text/javascript")
        recaptchaScripta.setAttribute('src', "./theme/js/script.js")
        document.body.appendChild(recaptchaScripta)
        let recaptchaScript = document.createElement('script')
        recaptchaScript.setAttribute('type',"text/javascript")
        recaptchaScript.setAttribute('src', "./theme/js/swiper.js")
        document.body.appendChild(recaptchaScript)
    }
}
</script>