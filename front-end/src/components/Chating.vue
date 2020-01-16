<template>
  <div class="container-fluid photos">
      <div class="row justify-content-center">

        <div class="col-md-8 pt-4">
            <div class="row mb-5" data-aos="fade-up">
                <div class="col-12">
                    <h2 class="text-white mb-4 text-center">Chating</h2>
                </div>
            </div>
            
            <div class="row">
                <div class="col-md-12" data-aos="fade-up">
                    <p class="mb-5">Lorem ipsum dolor sit amet, consectetur <a href="#">adipisicing</a> elit. Ipsa explicabo quasi cum, laudantium neque at veniam itaque atque <a href="#">necessitatibus</a> temporibus! Beatae sit soluta magni neque autem, suscipit dolorem, quo alias.</p>              
                    <div class="row">
                        <!-- chatinglist -->
                        <div class="col-md-5">
                            <div class="bg-danger">
                                <h5 class="text-white p-3">List</h5>
                            </div>
                            
                            <div class="list-group">
                                <virtual-list :size="70" :remain="8">
                                    <a v-for="chating in chatings" :key="chating.id" href="#" class="list-group-item list-group-item-action">
                                        <div class="row">
                                            <div class="col-md-2 d-flex justify-content-center align-self-center">
                                                <h1><i class="fas fa-user-circle"></i></h1>
                                            </div>
                                            <div class="col-md-10">
                                                <div class="d-flex w-100 justify-content-between">
                                                    <p class="mb-0">{{ chating.title }}</p>
                                                    <small>{{ chating.timestamp }}</small>
                                                </div>
                                                <div class="d-flex justify-content-between">
                                                    <small>{{ chating.content }}</small>
                                                    <span class="badge badge-primary badge-pill align-self-center"> {{ chating.cnt }}</span>
                                                </div>
                                            </div>
                                        </div>
                                    </a>
                                </virtual-list>
                            </div>
                        </div>
                        <!-- chating -->
                        <div class="card col-md-7 p-0">
                            <!-- header -->
                            <div class="card-header bg-light content-align-center" style="height: 60px;">
                                <h5 class="text-dark mt-1">싸피 </h5>
                            </div>

                            <!-- body -->
                            <virtual-list :size="80" :remain="8" style="background-color: black;">
                                <div class="card-body">
                                    <div v-for="message in messages" :key="message.id">
                                        <div class="row my-2 mx-1" v-if="message.receive_id == 'admin'">
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
                            <!-- footer -->
                            <div class="card-footer input-group p-0" style="border: 0px; height: 50px;">
                                <div class="input-group-prepend">
                                    <span class="input-group-text" style="border: 0px;">✛</span>
                                </div>
                                <input v-model="msg" type="text" class="form-control p-2 text-dark align-self-center" style="border-bottom: none;" placeholder="메시지를 입력해주세요.">
                                <div class="input-group-append" style="border: 0px;">
                                    <span class="input-group-text" style="border: 0px;">➤</span>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        
      </div>
  </div>
</template>

<script>
import virtualList from 'vue-virtual-scroll-list'

export default {
    components: {
        virtualList,
    },
    data() {
        return {
            msg: '',
            chatings: [
                {
                    title: '싸피',
                    content: 'Donec id elit non mi porta.',
                    timestamp: '2:00',
                    cnt: 14
                },
                {
                    title: '싸피2',
                    content: 'Donec id elit non mi porta.',
                    timestamp: '2:00',
                    cnt: 14
                },
                {
                    title: '싸피3',
                    content: 'Donec id elit non mi porta.',
                    timestamp: '3:00',
                    cnt: 14
                }
            ],
            messages: [
                {
                    dm_id: 1,
                    send_id: 'user1',
                    receive_id: 'admin',
                    message: 'ㅎㅇ',
                    timestamp: '2:59',
                    read_check: 1
                },
                {
                    dm_id: 1,
                    send_id: 'user2',
                    receive_id: 'admin',
                    message: 'ㅎㅇㅎㅇ',
                    timestamp: '2:59',
                    read_check: 1
                },
                {
                    dm_id: 1,
                    send_id: 'user3',
                    receive_id: 'admin',
                    message: 'ㅎㅇ',
                    timestamp: '2:59',
                    read_check: 1
                },
                {
                    dm_id: 1,
                    send_id: 'admin',
                    receive_id: 'user1',
                    message: 'ㅎㅇ',
                    timestamp: '2:59',
                    read_check: 0
                },
                {
                    dm_id: 1,
                    send_id: 'admin',
                    receive_id: 'user2',
                    message: 'ㅎㅇㅎㅇㅎㅇ',
                    timestamp: '2:59',
                    read_check: 0
                },
            ]
        }
    }
}
</script>

<style>
</style>