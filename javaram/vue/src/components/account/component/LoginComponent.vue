<template>

<div class="wrap_login">

  <!-- Javaram IMG -->
  <div class="banner">
    <img src="../../../assets/Javaram2.png" alt="Banner_Img" id="banner_img">
  </div>

  <!-- Login -->
  <div class="form-signin">

    <div class="form-floating" id="id_detail">
      <input type="id" class="form-control" id="floatingInput" placeholder="User ID" v-model="user_id" font-size="13px"
        @keyup.enter="submit">
    </div>

    <div class="form-floating" id="pwd_detail">
      <input type="password" class="form-control" id="floatingPassword" placeholder="Password" v-model="user_pw"
        @keyup.enter="submit">
    </div>

    <button class="w-100 btn btn-md" @click="submit" id="login_button" type="submit">Log in</button>

    <div class="d-grid gap-2 d-md-block" id="buttons">

      <button class="w-47 btn btn-sm" @click="search" type="button" data-bs-toggle="modal"
        data-bs-target="#exampleModal" id="button_search">Search ID/PWD</button>

      <!-- <button class="w-47 btn btn-sm" @click="this.$router.push('/registerpage')" type="button"
        id="button_regist">Regist</button> -->
      <button class="w-47 btn btn-sm" @click="regist" type="button" data-bs-toggle="modal"
        data-bs-target="#registModal" id="button_regist">Regist</button>

      <div v-show="searchV">
        <ModalTemplate />
      </div>

      <div v-show="registV">
        <ModalRegist />
      </div>

    </div>

  </div>
</div>

</template>


<script>
import ModalTemplate from "../../common/component/modal/ModalTemplate.vue"
import ModalRegist from "../../common/component/modal/ModalRegist.vue"
import axios from 'axios'

export default {
  components : {
    ModalTemplate,
    ModalRegist
   },

   data(){
     return{
       searchV : false,
       registV : false,
     }
   },

   methods:{
     search(){
       this.searchV = true
     },
     regist(){
       this.registV = true
     },



     submit(){
       axios.post('/loginCheck', {
          user_id : this.user_id,
          user_pw : this.user_pw
       }).then(response => {
          console.log(response.data)
          
          if( !response.data ){ //로그인 실패 시
            alert("아이디와 비밀번호를 확인해주세요.")
          }else{ //로그인 성공 시
            sessionStorage.setItem('auth', true)
            sessionStorage.setItem('user_name', response.data.user_name)
            sessionStorage.setItem('user_id', response.data.user_id)
            sessionStorage.setItem('user_email', response.data.user_email)
            sessionStorage.setItem('access', response.data.access)
            this.$router.push('/home')
            this.$router.go()
          }
          
       })
     },

     togoHome(){
       if(sessionStorage.getItem('auth')){
         this.$router.push('/home')
       }
     }


     
   },

   mounted(){
     this.togoHome()
   }
};
</script>


<style scoped>

  body {
    background-color: #e9e9e9;
  }
    
  .wrap_login {
    min-width: min-content;
    align-items: center;
    padding: 20px;
    position: absolute;
    top: 32%;
    left: 40%
  }

  .banner {
    /* margin-left: 50px; */
    width: 222px;
  }

  #banner_img {
    margin: 10px;
    width: 200px;
    display: flex;
    align-items: center;
    position: relative;
  }

  .form-signin {
    width: 222px;
    /* margin: 20px 50px 50px 50px; */
  }

  #login_button {
    margin-top: 8px;
    font-size: 14px;
    /* width: 222px; */
    color: white;
    background: #00C9BE;
  }

  #id_detail {
    margin-top: 10px;
    margin-bottom: 10px;
    text-align: left;
    /* width: 222px; */
  }

  #pwd_detail {
    margin-top: 10px;
    text-align: left;
    /* width: 222px; */
  }

  ::-webkit-input-placeholder {
    font-size: 13px;
  }

  #buttons {
    margin-top: 6px;
    display: flex;
  }

  #button_search {
    display: inline-block;
    flex: 1;
    margin-right: 6px;
    width: 108px;
    font-size: 11px;
    background: #bbbbbb;
    color: white;
  }

  #button_regist {
    display: inline-block;
    flex: 1;
    width: 108px;
    font-size: 12px;
    background: #bbbbbb;
    color: white;
  }

.body {
    display: flex;
    align-items: center;
    padding-top: 40px;
    padding-bottom: 40px;
    background-color: #f5f5f5;
}

</style>