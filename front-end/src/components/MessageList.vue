<template>
  <div>
      <virtual-list :size="80" :remain="8" style="background-color: black;">
        <div class="card-body">
            <div v-for="message in computedMessages" :key="message.id">
                <div class="row my-2 mx-1" v-if="message.receive_id == select.user_id">
                    <div class="col-md-1 d-flex justify-content-center">
                        <h1><i class="fas fa-user-circle"></i></h1>
                    </div>
                    <div class="col-md-11">
                        <div class="w-100">
                            <span class="m-0 ">{{ message.send_id }}</span>
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
    </virtual-list>
  </div>
</template>

<script>
import virtualList from 'vue-virtual-scroll-list';

export default {
    props: {
        select: Object
    },
    components: {
        virtualList,
    },
    data() {
        return {
            messages: [
                {
                    dm_id: 1,
                    send_id: 'aaa',
                    receive_id: 'bbb',
                    message: 'ㅎㅇ',
                    timestamp: '2:59',
                    read_check: 1
                },
                {
                    dm_id: 2,
                    send_id: 'aaa',
                    receive_id: 'ccc',
                    message: 'ㅎㅇㅎㅇ',
                    timestamp: '2:59',
                    read_check: 1
                },
                {
                    dm_id: 3,
                    send_id: 'bbb',
                    receive_id: 'aaa',
                    message: 'ㅎㅇ',
                    timestamp: '2:59',
                    read_check: 1
                },
                {
                    dm_id: 4,
                    send_id: 'ccc',
                    receive_id: 'aaa',
                    message: 'ㅎㅇ',
                    timestamp: '2:59',
                    read_check: 0
                },
                {
                    dm_id: 5,
                    send_id: 'aaa',
                    receive_id: 'bbb',
                    message: 'ㅎㅇㅎㅇㅎㅇ',
                    timestamp: '2:59',
                    read_check: 0
                },
            ]
        }
    },
    computed: {
        computedMessages() {
            // console.log(this.select)
            return this.messages.filter(message => {
                return message.send_id === this.select.other_id || message.receive_id === this.select.other_id;
            })
        }
    },
}
</script>

<style>

</style>