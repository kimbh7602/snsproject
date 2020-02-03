<template>
  <div>
    <div class="card-footer input-group p-0" style="border: 0px; height: 50px;">
        <div class="input-group-prepend">
            <span class="input-group-text" style="border: 0px;">✛</span>
        </div>
        <input v-model="msg" type="text" class="form-control p-2 text-dark align-self-center" style="border-bottom: none;" 
            placeholder="메시지를 입력해주세요."
            @keyup.enter="insertDirectMessage">
        <div class="input-group-append" style="border: 0px;">
            <span class="input-group-text" style="border: 0px;" @click="insertDirectMessage">➤</span>
        </div>
    </div>
  </div>
</template>

<script>
export default {
    props: {
        userDm: Object
    },
    data() {
        return {
            msg: '',
            message: {}
        }
    },
    methods: {
        // 날짜형식 포맷
        getTimeStamp() {
            var d = new Date();
            var s =
                this.leadingZeros(d.getFullYear(), 4) + '-' +
                this.leadingZeros(d.getMonth() + 1, 2) + '-' +
                this.leadingZeros(d.getDate(), 2) + ' ' +

                this.leadingZeros(d.getHours(), 2) + ':' +
                this.leadingZeros(d.getMinutes(), 2) + ':' +
                this.leadingZeros(d.getSeconds(), 2);

            return s;
        },
        leadingZeros(n, digits) {
            var zero = '';
            n = n.toString();

            if (n.length < digits) {
                for (let i = 0; i < digits - n.length; i++)
                zero += '0';
            }
            return zero + n;
        },
        insertDirectMessage() {
            if (this.msg.length != 0) {
                this.message = {
                    dm_id: this.userDm.dm_id,
                    send_id: this.userDm.user_id,
                    receive_id: this.userDm.other_id,
                    message: this.msg,
                    timestamp: this.getTimeStamp(),
                    read_check: 0
                };
                this.$emit('insertDirectMessage', this.message);
                this.msg = '';
            }
        },
    },

}
</script>

<style>

</style>