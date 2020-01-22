import Vue from 'vue'
import Vuex from 'vuex'

Vue.use(Vuex)

export default new Vuex.Store({
    state:{
        modalText: localStorage.getItem('modalText'),
        user_id:sessionStorage.getItem('user_id'),
        islogin:false,
    },
    mutations:{
        getModalText(state){
            state.modalText = localStorage.getItem('modalText')
        },
        setModalText: function(state, modalText){
            localStorage.setItem("modalText", modalText);
            state.modalText = localStorage.getItem("modalText");
        },
        getId(state){
            state.user_id = sessionStorage.getItem('user_id');
            if(sessionStorage.getItem('islogin')!=null)
                state.islogin = sessionStorage.getItem('islogin');
        },
        login(state, user_id) {
            sessionStorage.setItem("user_id", user_id);
            state.user_id = sessionStorage.getItem('user_id');
            sessionStorage.setItem("islogin", true);
            state.islogin = sessionStorage.getItem('islogin');
            localStorage.setItem("modalText", '로그인 되었습니다.');
            state.modalText = localStorage.getItem("modalText");
        },
        setid(state){
            state.user_id = sessionStorage.getItem('user_id');
            state.islogin = sessionStorage.getItem('islogin');
        },
        logout(state){
            sessionStorage.removeItem("user_id");
            sessionStorage.removeItem("islogin");
            state.user_id='';
            state.islogin=false;
            localStorage.setItem("modalText", '로그아웃 되었습니다.');
            state.modalText = localStorage.getItem("modalText");
        },
        // REQUIREAUTH() {
        //     if(!this.state.islogin){
        //         alert("로그인이 필요한 페이지입니다.");
        //         localStorage.setItem("modalText", '로그인이 필요한 페이지입니다.');
        //         this.state.modalText = localStorage.getItem("modalText");
        //         this.$router.push("/login");
        //     }
        // },
    },
    actions:{

    }
})