<template>
  <div>
    <div class="card-footer input-group p-0" style="border: 0px; height: 50px;">
        <div class="input-group-prepend">
            <span class="input-group-text" style="border: 0px;">✛</span>
        </div>
        <input v-model="msg" type="text" class="form-control p-2 text-dark align-self-center" style="border-bottom: none;" 
            placeholder="메시지를 입력해주세요."
            @keyup:enter="submitMessageFunc">
        <div class="input-group-append" style="border: 0px;">
            <span class="input-group-text" style="border: 0px;" @click="submitMessageFunc">➤</span>
        </div>
    </div>
  </div>
</template>

<script>
export default {
    props: {
        select: Object
    },
    data() {
        return {
            msg: '',
            message: {}
        }
    },
    methods: {
        submitMessageFunc() {
            if (this.msg.length != 0) {
                this.message = {
                    dm_id: 1,
                    send_id: this.select.user_id,
                    receive_id: this.select.other_id,
                    message: this.msg,
                    timestamp: Date.now(),
                    read_check: 0
                };
                this.$emit('submitMessage', this.message);
                this.msg = '';
            }
        },
    },

}
</script>

<style>

</style>