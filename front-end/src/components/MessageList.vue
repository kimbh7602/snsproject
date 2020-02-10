<template>
    <div class="scroll-body" style="background-color: black; height:600px; max-height:600px; overflow-y: auto;">
        <div class="card-body">
            <div v-for="message in fetchedDirectMessageList" :key="message.id">
                <div class="row my-2" v-if="message.receive_id == userDm.user_id">
                    <div class="col-md-1 d-flex justify-content-center align-items-center">
                        <img class="rounded-circle ml-2" width="50px" height="50px" style="object-fit: cover;" :src="userDm.user.profile_url || 'https://t1.daumcdn.net/qna/image/1542632018000000528'" alt=""> 
                    </div>
                    <div class="col-md-11 pl-3">
                        <div class="w-100">
                            <span class="m-0">{{ message.send_id }}</span>
                        </div>
                        <div class="m-0 d-flex" style="position:relative;">
                            <h5><span class="badge bg-white p-2" style="position:absolute; word-break:break-all;">{{ message.message }}</span></h5>
                            <small class="ml-2 align-self-center">{{ message.timestamp }}</small>
                        </div>
                    </div>
                </div>
                <div v-else class="d-flex justify-content-end" style="width:100%; height:auto;">
                    <div>
                        <small v-if="message.read_check == 0" class="mr-2 align-self-center text-warning">1</small>
                        <small class="mr-2 align-self-center">{{ message.timestamp }}</small>
                    </div>
                    <div style="word-break: break-all; word-wrap: break-word;">
                        <span class="badge badge-primary p-2">{{ message.message }}</span>
                    </div>
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