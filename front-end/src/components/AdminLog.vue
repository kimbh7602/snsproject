<template v-slot:default>
    <div class="container-fluid photos">
        <div class="row justify-content-center">

            <div class="col-md-6 pt-4" data-aos="fade-up">
                <h2 class="text-white mb-4">AdminLog</h2>


                <div class="row">
                    <div class="col-md-12">
                        <p class="mb-5">Lorem ipsum dolor sit amet, consectetur <a href="#">adipisicing</a> log.</p>
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
                                                        <tr v-for="item in chain" :key="item.data.user_id">
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
                                <div v-if='this.trust===trust'>
                                    신뢰
                                </div>
                                <div v-else>
                                    신뢰안됨
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
    import $ from "jquery"
    export default {
        data() {
            return {
                chain: [],
                trust:false,

                desserts: [{
                        name: 'Frozen Yogurt',
                        calories: 159,
                    },
                    {
                        name: 'Ice cream sandwich',
                        calories: 237,
                    },
                    {
                        name: 'Eclair',
                        calories: 262,
                    },
                    {
                        name: 'Cupcake',
                        calories: 305,
                    },
                    {
                        name: 'Gingerbread',
                        calories: 356,
                    },
                    {
                        name: 'Jelly bean',
                        calories: 375,
                    },
                    {
                        name: 'Lollipop',
                        calories: 392,
                    },
                    {
                        name: 'Honeycomb',
                        calories: 408,
                    },
                    {
                        name: 'Donut',
                        calories: 452,
                    },
                    {
                        name: 'KitKat',
                        calories: 518,
                    },
                ],
            }
        },
        mounted() {
            $('html').scrollTop(0);
            var tmp;
            http
                .get("admin/info")
                .then(response => {
                    if (response.data['resmsg'] == "조회성공") {
                        tmp = response.data['resvalue'];
                        this.chain = tmp;

                    }
                })
            http
            .get("admin/trust")
            .then(response => {
                if (response.data['resmsg'] == "신뢰") {
                    this.trust=true;
                }
            })
        }
    }
</script>