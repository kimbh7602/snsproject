<template v-slot:default>
    <div class="container-fluid photos">
        <div class="row justify-content-center">
            <div class="col-md-6">
                <div class="row">
                    <div class="col-md-12">
                        <div class="row">
                            <div class="col-md-12">
                                <form action="" method="post" @submit.prevent="confirm">
                                    <div style="display:none">
                                        <input type="submit" onclick="return false;" />
                                        <input type="text" />
                                    </div>
                                    <!-- Log -->
                                    <div class="row form-group">
                                        <div class="col-md-12">
                                            <label class="text-white" for="upw">Log List</label>
                                            <div id="col-md-12">
                                                <table>
                                                    <thead>
                                                        <tr>
                                                            <th class="text-center">Id</th>
                                                            <th class="text-center">Ip</th>
                                                            <th class="text-center">Status</th>
                                                            <th class="text-center">Time</th>
                                                        </tr>
                                                    </thead>
                                                    <tbody>
                                                        <tr v-for="item in computedUsers" :key="item.index">
                                                            <td class="text-center"> {{ item.data.user_id }}</td>
                                                            <td class="text-center">{{ item.data.user_ip }}</td>
                                                            <td class="text-center">{{ item.data.user_status }}</td>
                                                            <td class="text-center">{{ item.timestamp}}</td>
                                                        </tr>
                                                    </tbody>
                                                </table>
                                            </div>
                                        </div>
                                    </div>
                                </form>
                                <div v-if='this.trust===true' style="height: 2px; background-color:green">
                                </div>
                                <div v-else style="height: 2px; background-color:red">
                                    블록 문제발생
                                </div>
                                <div class="row text-center">
                                    <div class="col-md-3">
                                    </div>
                                    <div class="col-md-1  text-center">
                                        <div class="icon-chevron-left" @click="setPage(n-1)" />
                                    </div>
                                    <div class="col-md-1 text-center">
                                        <input type="text" size="4" v-model="n" v-on:keyup.enter="setPage(n)"
                                            style="text-align:center" />
                                    </div>
                                    <div class="col-md-1  text-center">
                                        /
                                    </div>
                                    <div class="col-md-1 text-center">
                                        {{numOfPages}}
                                    </div>
                                    <div class="col-md-1  text-center">
                                        <div class="icon-chevron-right" @click="setPage(n+1)" />
                                    </div>

                                    <div class="col-md-2  text-center" @click.prevent="setPage(n)">이동</div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>

</template>
<style>
    /* #log{
    width:100%;
} */
</style>


<script>
    import http from "../http-common"

    export default {
        data() {
            return {
                n: 1,
                chain: [],
                trust: false,
                currentPage: 1,
                perPage: 25,
            }
        },
        mounted() {
            var tmp;
            http
                .get("admin/info")
                .then(response => {
                    if (response.data['resmsg'] == "조회성공") {
                        tmp = response.data['resvalue'];
                        this.chain = tmp;
                        // console.log(this.chain)
                    }
                })
            http
                .get("admin/trust")
                .then(response => {
                    if (response.data['resmsg'] == "신뢰") {
                        this.trust = true;
                    }
                })
        },
        methods: {
            setPage(n) {
                if (n > 0 && n <= this.numOfPages) {
                    this.currentPage = n;
                    this.n = this.currentPage;
                }
            },
        },
        computed: {
            offset() {
                return ((this.currentPage - 1) * this.perPage);
            },
            limit() {
                return (this.offset + this.perPage);
            },
            numOfPages() {
                return Math.ceil(this.chain.length / this.perPage);
            },
            computedUsers() {
                if (this.offset > this.chain.length) {
                    // this.currentPage = this.numOfPages;
                }
                return this.chain.slice(this.offset, this.limit);
            },
            rows() {
                return this.chain.length;
            }
        }
    }
</script>