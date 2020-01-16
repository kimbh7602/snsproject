<template>
<div>
    <div style="height:10px;"></div>
    <div :class="filterType" id="img-select" class="img-fluid"
          :style="{ backgroundImage: 'url(' + imgs[imgs.length-1].base64 + ')' }"></div>
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
    <div style="margin-top:1%; margin-left:5%;margin-right:5%;">
        <div style="display:inline-block; float :left">
        <input type="button" value="이전" @click="goPrev" class="btn btn-primary btn-md text-white">
        </div>
        <div style="display:inline-block; float:right">
        <input type="button" value="다음" @click="goNext" class="btn btn-success btn-md text-white">
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
            this.$router.go(-1);
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
            console.log(this.imgs);
        }
    }
}
</script>