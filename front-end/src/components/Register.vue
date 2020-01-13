<template>
    <div class="container-fluid photos">
      <div class="row justify-content-center">
        
        <div class="col-md-6 pt-4"  data-aos="fade-up">
          <h2 class="text-white mb-4">Register</h2>

          <div class="row">
            <div class="col-md-12">
              <p class="mb-5">Lorem ipsum dolor sit amet, consectetur <a href="#">adipisicing</a> elit. Ipsa explicabo quasi cum, laudantium neque at veniam itaque atque <a href="#">necessitatibus</a> temporibus! Beatae sit soluta magni neque autem, suscipit dolorem, quo alias.</p>              
              <div class="row">
                <div class="col-md-12">                
                  <form action="#">
                    <div style="display:none">
                        <input type="submit" onclick="return false;" />
                        <input type="text"/>
                    </div>                
                    <div class="row form-group">
                      <div class="col-md-12">
                        <label class="text-white" for="uid">ID</label>
                        <input type="text" v-model="uid" id="uid" class="form-control">
                      </div>
                    </div>
                    <div class="row form-group">
                      <div class="col-md-12">
                        <label class="text-white" for="upw">PW</label>
                        <input type="password" v-model="upw" id="upw" class="form-control">
                      </div>
                    </div>

                    <div class="row form-group">
                      
                      <div class="col-md-12">
                        <label class="text-white" for="utel">Tel</label> 
                        <input type="tel" id="tel" v-model="utel" class="form-control" pattern="(010)-\d{3,4}-\d{4}" placeholder="010-XXXX-XXXX">
                      </div>
                    </div>

                    <div class="row form-group">
                      
                      <div class="col-md-12">
                        <label class="text-white" for="uemail">Email</label> 
                        <input type="email" id="email" v-model="uemail" class="form-control" pattern="[a-z0-9._%+-]+@[a-z0-9.-]+\.[a-z]{2,4}$" placeholder="XXXXX@XXXX.XXX">
                      </div>
                    </div>

                    <div class="row form-group">
                      
                      <div class="col-md-12" id="parentItrl">
                        <label class="text-white" for="subject">Interest</label>
                        <div id="" style="display:inline-block"></div>
                        <input type="subject" autocomplete='off' @keyup.enter.prevent='appendDiv' @keydown.space.prevent='appendDiv' id="interest" class="form-control">
                        <div id="itrl"></div>
                      </div>
                    </div>

                    <div class="row form-group mb-5">
                      <div class="col-md-12">
                        <label class="text-white" for="uintr">Introduce</label> 
                        <textarea name="message" id="message" v-model="uintr" cols="30" rows="7" class="form-control" style="resize:none;" placeholder="Please introduce yourself"></textarea>
                      </div>
                    </div>

                    <div class="row form-group">
                      <div class="col-md-12">
                        <input type="submit" value="Register" class="btn btn-primary btn-md text-white">
                        <input type="reset" value="Reset" @click="deleteDiv" class="btn btn-danger btn-md text-white">
                      </div>
                    </div>
                  </form>
                </div>
                
              </div>
            </div>
          </div>
        </div>

      </div>
    </div>
</template>

<style>
    .roundedge{
        border-radius: 5px;
        display: inline-block;
        margin-top: 5px;
        margin-right:3px;
        border-color:white;
        border-style:solid;
        border-width: 1px;
    }
</style>

<script>
export default {
    name: "register",
    data(){
        return{
            uid:"",
            upw:"",
            utel:"",
            uemail:"",
            uintr:"",
            itrlist:[],
            // itr:"",
            // index = 0,
        }
    },
    methods:{
        appendDiv() {
            var colorCode  = "#" + Math.round(Math.random() * 0xffffff).toString(16);
            var div = document.createElement('div');
            var span = document.createElement('span');
            var bold = document.createElement('bold')
            var text = document.getElementById('interest').value;
            this.itrlist.push(text);
            bold.innerText = text;
            // bold.innerText = "qwe";
            div.style.background=colorCode;
            div.classList.add('roundedge');
            div.classList.add('text-white');
            span.classList.add('icon-remove')
            // span.setAttribute('v-on:click','deleteOne()');
            span.addEventListener("click", function(event){
              event.target.parentNode.remove();
            });
            div.appendChild(bold);
            div.appendChild(span);
            // div.appendChild("<b>"+text+"</b>&nbsp;");
            // var inner = "<b>"+text+"</b>&nbsp;<span class='icon-remove' @click='deleteOne'></span>&nbsp;  "
            // div.innerHTML = inner;
            document.getElementById('itrl').appendChild(div);
            // document.getElementById('itrl').appendChild(span);
            document.getElementById('interest').value='';
            document.getElementById('interest').focus();
        },
        deleteDiv() {
            var r = document.getElementById('itrl');
            document.getElementById('parentItrl').removeChild(r);
            var rediv = document.createElement('div');
            rediv.id = 'itrl';
            document.getElementById('parentItrl').appendChild(rediv);
            // alert(this.itrlist[1]);
        },
        // deleteOne() {
        //     alert("asdf");
        // },
        // sub() {
        //     alert(this.uid + this.upw + this.utel + this.uemail + this.uintr);
        // }
    },
    mounted() {
      // this.appendDiv();
    }
}
</script>