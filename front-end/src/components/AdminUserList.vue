<template>
    <div class="container-fluid photos">
        <div class="row justify-content-center">

            <div class="col-md-6 pt-4" data-aos="fade-up">
                <h2 class="text-white mb-4">UserList</h2>


                <div class="row">
                    <div class="col-md-12">
                        <p class="mb-5">Lorem ipsum dolor sit amet, consectetur <a href="#">adipisicing</a> elit.</p>
                        <div class="row">
                            <div class="col-md-12">
                                <form action="" method="post">
                                    <!-- LogList -->
                                    <div class="row form-group">
                                        <div class="col-md-12">
                                            <table>
                                                <tr>
                                                    <th>아이디</th>
                                                    <th>마지막접속시간</th>
                                                    <th>삭제</th>
                                                </tr>
                                                <tr v-for="user in alluser" :key="user.user_id">
                                                    <td>{{user.user_id}}</td>
                                                    <td>{{user.timestamp}}</td>
                                                    <td><input type="button" class="site-logo btn btn-danger btn-md text-white" value="삭제" @click="del(user)" /></td>
                                                </tr>
                                            </table>
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
    table {
        width: 100%;
    }

    th,
    td {
        text-align: center;
    }
</style>

<script>
    // import EventBus from "../event-bus"
    import http from "../http-common"
    export default {
        name: "AllUserList",
        data() {
            return {
                alluser: [],
                uid: this.$store.state.user_id,
            }
        },
        mounted() {
            http
                .get("/user/userList")
                .then(response => {
                    if (response.data['resmsg'] == "조회성공") {
                        this.alluser = response.data['resValue'];
                    } else {
                        this.$store.commit('setModalText', "회원조회 실패!");
                        document.getElementById('modalBtn').click();
                        this.$router.push("/");
                    }
                })
                .catch((error) => {
                    this.errored = true;
                    alert(error);
                })
                .finally(() => (this.loading = false));
        },
        methods: {
            del(tmp) {
                if (tmp.user_id == this.uid) {
                    this.$store.commit('setModalText', "본인은 삭제할 수 없습니다.");
                    document.getElementById('modalBtn').click();
                } else {
                    http
                        .delete("user/delete/" + tmp.user_id)
                        .then(response => {
                            if (response.data['resmsg'] == "삭제성공"){
                                this.$store.commit('setModalText', "회원삭제 성공!");
                                document.getElementById('modalBtn').click();
                            }
                            else{
                                this.$store.commit('setModalText', "회원삭제 실패!");
                                document.getElementById('modalBtn').click();
                            }
                            this.$router.push("/");
                        })
                        .catch((error) => {
                            this.errored = true;
                            alert(error);
                        })
                        .finally(() => (this.loading = false));
                }
            }
        }
    }
</script>