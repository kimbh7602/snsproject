<template>
    <div class="scroll-body" style="background-color: black; height:600px; max-height:600px; overflow-y: auto;">
        <div class="card-body">
            <div v-for="message in fetchedDirectMessageList" :key="message.id">
                <div class="row my-2" v-if="message.receive_id == userDm.user_id">
                    <div class="col-md-1 d-flex justify-content-center align-items-center ml-2">
                        <img class="rounded-circle ml-2" width="50px" height="50px" style="object-fit: cover;" :src="userDm.user.profile_url || 'https://t1.daumcdn.net/qna/image/1542632018000000528'" alt=""> 
                    </div>
                    <div class="col-md-10 pl-3 p-0 d-flex align-self-center">
                        <!-- <div class="w-100">
                            <span class="m-0">{{ message.send_id }}</span>
                        </div> -->
                        <div class="m-0" style="position:relative;">
                            <h5 class="m-0 mt-1"><span class="badge badge-light p-2 text-left" style="white-space:normal;">{{ message.message }}</span></h5>
                            <small>{{ message.timestamp }}</small>
                        </div>
                    </div>
                </div>
                <div v-else class="text-right">
                    <h5 class="m-0 mt-2"><span class="badge badge-primary p-2 text-left" style="white-space:normal;">{{ message.message }}</span></h5>
                    <small v-if="message.read_check == 0" class="mr-2 align-self-center text-warning">1</small>
                    <small class="align-self-center">{{ message.timestamp }}</small>
                </div>
            </div>
        </div>
    </div>
</template>

<script>

export default {
    props: {
        userDm: Object,
        fetchedDirectMessageList: Array,
    },
    components: {
        // virtualList,
    },
    updated(){
        const div = document.querySelector(".scroll-body");
        div.scrollTop = div.scrollHeight - div.clientHeight;
    }
}
</script>

<style>

</style>