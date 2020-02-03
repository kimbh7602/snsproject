<template>
    <div class="scroll-body" style="background-color: black; height:600px; max-height:600px; overflow-y: auto;">
        <div class="card-body">
            <div v-for="message in fetchedDirectMessageList" :key="message.id">
                <div class="row my-2" v-if="message.receive_id == userDm.user_id">
                    <div class="col-md-1 d-flex justify-content-center">
                        <h1>🦱</h1>
                    </div>
                    <div class="col-md-11 pl-3">
                        <div class="w-100">
                            <span class="m-0">{{ message.send_id }}</span>
                        </div>
                        <div class="m-0 d-flex">
                            <h5><span class="badge bg-white p-2">{{ message.message }}</span></h5>
                            <small class="ml-2 align-self-center">{{ message.timestamp }}</small>
                        </div>
                    </div>
                </div>
                <div v-else>
                    <div class="m-0 d-flex justify-content-end">
                        <small v-if="message.read_check == 0" class="mr-2 align-self-center text-warning">1</small>
                        <small class="mr-2 align-self-center">{{ message.timestamp }}</small>
                        <h5><span class="badge badge-primary p-2">{{ message.message }}</span></h5>
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