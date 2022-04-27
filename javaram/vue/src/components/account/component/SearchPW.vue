<template>
  <div>

    <div class="form-group">
      <label for="id">ID</label>
      <Field id="input_area" name="id" type="id" class="form-control" v-model="user_id" />
      <ErrorMessage name="id" class="error-feedback" />
    </div>

    <div class="form-group">
      <label for="username">이름</label>
      <Field id="input_area" name="username" type="text" class="form-control" v-model="user_name" />
      <ErrorMessage name="username" class="error-feedback" />
    </div>

    <div class="form-group">
      <label for="email">Email</label>
      <Field id="input_area" name="email" type="text" class="form-control" v-model="user_email" />
      <ErrorMessage name="email" class="error-feedback" />
    </div>

    <div class="form-group">
      <div class="row">

        <div class="col-6"></div>

        <div id="btn-group">          

          <button class="btn btn-primary" @click="FindID" id="home-tab-search" data-bs-toggle="tab" type="button"
            role="tab" aria-controls="home" aria-selected="true">
            PW 찾기
          </button>

          <button @click="this.$router.go()" id="home-tab" class="btn btn-secondary" data-bs-toggle="tab"
            type="button" role="tab" aria-controls="home" aria-selected="true">
            닫기
          </button>
          
        </div>

      </div>
    </div>
  </div>
  
</template>

<script>
import {  Field, ErrorMessage } from "vee-validate";
import * as yup from "yup";
import axios from 'axios';

export default {
  name: "SearchPW",
  
  methods:{
    FindPW(){
      axios.post('/FindUserPW', {
          user_id : this.user_id,
          user_name : this.user_name,
          user_email : this.user_email
      }).then(response => {
        if(response.data){
          alert("회원님의 비밀번호는 " + response.data + "입니다.")
        }else{
          alert("조회하신 정보의 회원 정보가 없습니다.")
        }
      })
    }
  },
  components: {
    // Form,
    Field,
    ErrorMessage,
  },
  data() {
    const schema = yup.object().shape({
      username: yup
        .string()
        .required("Username is required!")
        .min(3, "Must be at least 3 characters!")
        .max(20, "Must be maximum 20 characters!"),
      id: yup
        .string()
        .required("Id is required!")
        .min(6, "Must be at least 6 characters!")
        .max(40, "Must be maximum 40 characters!"),
      email: yup
        .string()
        .required("Email is required!")
        .email("Email is invalid!")
        .max(50, "Must be maximum 50 characters!"),
    });
    return {
      successful: false,
      loading: false,
      message: "",
      schema,
    };
  },
  computed: {
    }
  };
</script>

<style scoped>
  @import url('https://fonts.googleapis.com/css2?family=Poppins:wght@200;300;400;500;600;700&display=swap');

* {
    font-family: 'Gowun Dodum', sans-serif;
}

p{
  margin-bottom: 0px;
}

.authCheckForm{ 
  display: block;
  width: 70%;
  height: calc(1.5em + 0.75rem + 2px);
  padding: 0.375rem 0.75rem;
  font-size: 1rem;
  font-weight: 400;
  color: #495057;
  background-color: #fff;
  background-clip: padding-box;
  border: 1px solid #ced4da;
  border-radius: 0.25rem;
  transition: border-color .15s ease-in-out,box-shadow .15s ease-in-out;
}

#input_area{
  margin-top: 10px;
  margin-bottom: 10px;
}

#home-tab{
  margin-left: 10px;
}

#btn-group {
  display: flex;
  justify-content: flex-end;
}

</style>
