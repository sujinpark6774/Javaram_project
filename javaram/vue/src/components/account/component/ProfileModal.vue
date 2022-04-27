<template>
    <div class="modal" id="modalRegister" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
        <div class="modal-dialog d-flex flex-column-reverse">
            <div class="p-2">
            <!-- 모달 창 전체 -->
                <div class="modal-content">
                    <div class="modal-header">
                        <ul class="nav nav-tabs" id="myTab" role="tablist">
                            <li class="nav-item" role="presentation">
                                <button class="nav-link active" id="home-tab" data-bs-toggle="tab" data-bs-target="#home" type="button"
                                    role="tab" aria-controls="home" aria-selected="true">회원정보 변경</button>
                            </li>
                            <!-- <li class="nav-item" role="presentation">
                                <button class="nav-link" id="contact-tab" data-bs-toggle="tab" data-bs-target="#contact" type="button"
                                    role="tab" aria-controls="contact" aria-selected="false">인증정보</button>
                            </li> -->
                        </ul>
                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                    </div>
                    <div class="modal-body">
                        <div class="tab-content" id="myTabContent">
                            <div class="row">
                                <h5> {{ user_name }} 님의 정보</h5>
                            </div>
                            <!-- 계정정보 tab -->
                            <!-- <div class="tab-pane fade show active" id="home" role="tabpanel" aria-labelledby="home-tab"> -->
                            <div class="tab-pane fade show active modal-body" id="home" role="tabpanel" aria-labelledby="home-tab"> 
                                <p style="display:block;">아이디 {{ user_id }}</p>
                                <p>비밀번호</p>
                                <p><input type="password" placeholder= "******" v-show="!update_pw_v" v-model="new_pw" @keyup.enter="update_pw"></p>
                                <button type="button" class="btn btn-secondary btn-sm" v-show="update_pw_v" @click="change_pw">수정</button>
                                <button type="button" class="btn btn-secondary btn-sm" v-show="!update_pw_v" @click="update_pw">확인</button>
                                <br />
                                <p>이메일 {{ user_email }}</p>
                                <button type="button" class="btn btn-secondary btn-sm" v-show="update_email_v" @click="change_email">수정</button>
                                <p><input type="text" placeholder="example@kt.com" v-show="!update_email_v" v-model="new_email" @keyup.enter="update_email">
                                <button type="button" class="btn btn-secondary btn-sm" v-show="!update_email_v" @click="update_email">확인</button></p>
                            </div>
                            <!-- 인증정보 -->
                            <!-- <div class="tab-pane fade" id="contact" role="tabpanel" aria-labelledby="contact-tab"> -->
                            <div class="tab-pane fade modal-body" id="contact" role="tabpanel" aria-labelledby="contact-tab">
                                <p>이메일 {{ user_email }}</p>
                                <button type="button" class="btn btn-secondary btn-sm">수정</button>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
</div>
</template>

<script>
import axios from 'axios';
import * as yup from "yup";
// import store from '../../../store/index.js'

export default {
    data(){
        return {
            // userInfo : store.state.loginInfo
            user_name : sessionStorage.getItem('user_name'),
            user_id : sessionStorage.getItem('user_id'),
            user_email : sessionStorage.getItem('user_email'),
            update_pw_v : true,
            update_email_v : true
        };
    },

    methods:{
        change_pw(){
            this.update_pw_v = !this.update_pw_v
        },
        change_email(){
            this.update_email_v = !this.update_email_v
        },
        update_pw(){
            if(this.new_pw.length >= 6){
                axios.post('/changepw', {
                    user_id : sessionStorage.getItem('user_id'),
                    user_pw : this.new_pw
                }).then(resopnse => {
                    if(resopnse.status==200){
                        alert("회원정보가 수정되었습니다.")
                        this.$router.go()
                    }
                })
            }else{
                alert("입력값을 확인해주세요.")
            }
        },
        update_email(){
            if(!this.emailcheck(this.new_email)=="False"){
                axios.post('/changeemail', {
                    user_id : sessionStorage.getItem('user_id'),
                    user_email : this.new_email
                }).then(resopnse =>{
                    if(resopnse.status==200){
                        sessionStorage.setItem('user_email', this.new_email)
                        alert("회원정보가 수정되었습니다.")
                        this.$router.go()
                    }
                })
            }else{
                alert("입력값을 확인해주세요.")
            }
                
        },

        emailcheck(email){
            email = yup.string().email("False")
            return email
        }
    }
  };
</script>

<style scoped>
    h3{
        text-align: left;
    }

    p {
        display: inline-block;
    }

    .tab-pane{
        margin-top: 20px;
    }
    
    button {
        margin-left: 10px;
    }

    img {
        width: 140px;
        max-height: 140px;
        margin-bottom: 20px;
        border-radius: 30%;
    }
    modal-body{
        color : white;
    }
    #modal{
        width: 120%
    }

</style>
