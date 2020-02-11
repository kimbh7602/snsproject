<template>
    <div data-aos="fade-up" style="width:100%">
        <div class="offset-3 col-6 cropper-container" style=" height:480px; width:auto;">
            <img ref="image" :src="imgs[imgs.length-1].base64" crossorigin style="height:80%;width:80%;">
            <!-- <img ref="image" src="../../public/theme/images/temp.jpg" crossorigin> -->
        </div>
        <div class="offset-4 col-6" style="padding-left:7%;padding-top:30px;">
            <span @click="rotatep45" class="col-1 icon-mail-forward"></span>
            <span @click="rotatem45" class="col-1 icon-mail-reply"></span>
            <span @click="rotatep90" class="col-1 icon-rotate-right"></span>
            <span @click="rotatem90" class="col-1 icon-rotate-left"></span>
            <span @click="vertical_scale" class="col-1 icon-arrows-v"></span>
            <span @click="horizontal_scale" class="col-1 icon-arrows-h"></span>
        </div>
        <!-- <div class="offset-3 col-6" style="padding-left:4%;">
            <img :src="destination" class="img-preview">
        </div> -->
        <div style="margin-top:1%; margin-left:5%;margin-right:5%; height:50px;">
          <div v-if="prevpage=='addimage'" class="offset-3 col-2" style="display:inline-block;">
            <input type="button" value="이전" @click="goAddImage" class="btn btn-primary btn-md text-white">
          </div>
          <div v-else class="offset-3 col-2" style="display:inline-block;">
            <input type="button" value="이전" @click="goPrev" class="btn btn-primary btn-md text-white">
          </div>
          <div class="offset-3 col-2" style="display:inline-block;">
            <input type="button" value="다음" @click="goNext" class="btn btn-success btn-md text-white">
          </div>
        </div>
    </div>
</template>

<script>
    import 'cropperjs/dist/cropper.css';
    import 'cropperjs/dist/cropper.min.css';
    import Cropper from "cropperjs";
    export default {
        name: "editing",
        props: ["imgs","prevpage","oldpw","items"],
        data() {
            return {
                cropper: {},
                destination: {},
                image: {},
                src:"../../public/theme/images/ai.jpg",
                scale_vertical:false,
                scale_horizontal:false,
                ratio:1,
            }
        },
        methods:{
            goAddImage(){
                this.step = 1;
                this.$router.push("/addimage");
            },
            goPrev() {
                this.image = "";
                this.caption = "";
                this.filterType = "normal";
                this.step = 1;
                this.$router.go(-1);
            },
            goNext() {
                // this.imgs[this.imgs.length-1].filter = this.filterType;
                this.imgs[this.imgs.length-1].base64 = this.destination;
                console.log(this.items)
                this.$router.push({
                    name: 'imagefilter', 
                    params: {
                    imgs: this.imgs,
                    prevpage: this.prevpage,
                    oldpw: this.oldpw,
                    items: this.items,
                    }
                });
            },
            rotatep45(){
                this.cropper.rotate(45);
            },
            rotatem45(){
                this.cropper.rotate(-45);
            },
            rotatep90(){
                this.cropper.rotate(90);
            },
            rotatem90(){
                this.cropper.rotate(-90);
            },
            vertical_scale(){
                if(!this.scale_vertical){
                    this.scale_vertical = !this.scale_vertical;
                    this.cropper.scaleY(-1);
                }
                else{
                    this.scale_vertical = !this.scale_vertical;
                    this.cropper.scaleY(1);
                }
            },
            horizontal_scale(){
                if(!this.scale_horizontal){
                    this.scale_horizontal = !this.scale_horizontal;
                    this.cropper.scaleX(-1);
                }
                else{
                    this.scale_horizontal = !this.scale_horizontal;
                    this.cropper.scaleX(1);
                }
            },
        },
        mounted() {
                    console.log(this.items)

            this.image = this.$refs.image;
            if(this.prevpage=="addimage"){
                this.ratio = 4/3;
            }else if(this.prevpage=="contentupdate"){
                this.ratio = 4/3;
            }
            this.cropper = new Cropper(this.image, {
                aspectRatio: this.ratio,
                dragMode:'move',
                // minContainerHeight:1000,
                crop: () => {
                    const canvas = this.cropper.getCroppedCanvas();
                    this.destination = canvas.toDataURL("image/png");
                }
            });
        }
    }
</script>

<style scoped>
    .img-container {
        width: auto;
        height: auto;
    }
    .img-preview {
        width: auto;
        height: 300px;
    }
    span{
        color:orange;
        /* padding-right:20px; */
    }
    span:hover {
	/* background-color: #5BC0DE; */
	color: orangered;
	font-style: normal;
}
</style>