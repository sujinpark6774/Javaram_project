<template>

  <!-- Modal -->
  <div class="modal fade" id="registModal" tabindex="-1" aria-labelledby="registModalLabel" aria-hidden="true">
      <div class="modal-dialog d-flex flex-column-reverse">
          <div class="p-2">

              <!-- 모달 창 전체 -->
              <div class="modal-content">

                  <div class="modal-header">

                      <div id="regist_tab">
                          <h3 id="regist_title">Regist</h3>
                      </div>

                      <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                  </div>

                  <!-- 클릭한 요소에 따라 컴포넌트 전환 -->
                  <div class="modal-body">
                    <div class="regist-content">
                        
                    <Form @submit="handleRegister" :validation-schema="schema">
                        <div v-if="!successful">
                        <div class="form-group">
                            <label for="id">ID</label>
                            <Field name="id" type="text" class="form-control" v-model="user_id" />
                            <ErrorMessage name="id" class="error-feedback" />
                        </div>
                        <div class="form-group">
                            <label for="username">Name</label>
                            <Field name="username" type="username" class="form-control" v-model="user_name" />
                            <ErrorMessage name="username" class="error-feedback" />
                        </div>
                        <div class="form-group">
                            <label for="password">Password</label>
                            <Field name="password" type="password" class="form-control" autocomplete="off" v-model="user_pw" />
                            <ErrorMessage name="password" class="error-feedback" />
                        </div>
                        <div class="form-group">
                            <label for="email">Email</label>
                            <Field name="email" type="email" class="form-control" v-model="user_email" />
                            <ErrorMessage name="email" class="error-feedback" />
                        </div>

                        <div class="form-group" id="buttons">
                            <button type="button" class="btn btn-primary" @click="submit" id="button_signup">가입</button>

                            <router-link to="/login">
                                <button type="button" class="btn btn-secondary" id="button_cancel" @click="close">취소</button>
                            </router-link>
                        </div>
                        </div>
                    </Form>

                    <div
                        v-if="message"
                        class="alert"
                        :class="successful ? 'alert-success' : 'alert-danger'"
                    >
                        {{ message }}
                    </div>
                    </div>
                  </div>



              </div>
          </div>
      </div>
  </div>
</template>


<script>
import { Form, Field, ErrorMessage } from "vee-validate";
import * as yup from "yup";
import axios from 'axios';

export default {
    name: "RegisterPage",

    components: {
        Form,
        Field,
        ErrorMessage,
    },

    data() {
        const schema = yup.object().shape({
            username: yup
                .string()
                .matches(/[a-z0-9]/g, "You only insert lower alphabet or number!")
                .required("Username is required!")
                .min(3, "Must be at least 3 characters!")
                .max(20, "Must be maximum 20 characters!"),
            id: yup
                .string()
                .matches(/[a-zA-Z0-9]/g, "You only insert alphabet or number!")
                .required("Id is required!")
                .min(6, "Must be at least 6 characters!")
                .max(40, "Must be maximum 40 characters!"),
            email: yup
                .string()
                .required("Email is required!")
                .email("Email is invalid!")
                .max(50, "Must be maximum 50 characters!"),
            password: yup
                .string()
                .required("Password is required!")
                .min(6, "Must be at least 6 characters!")
                .max(40, "Must be maximum 40 characters!"),
        });
        return {
            successful: false,
            loading: false,
            message: "",
            schema,
        };
    },

    methods: {
        handleRegister(user) {
            this.message = "";
            this.successful = false;
            this.loading = true;
            this.$store.dispatch("auth/register", user).then(
                (data) => {
                    this.message = data.message;
                    this.successful = true;
                    this.loading = false;
                },
                (error) => {
                    this.message =
                        (error.response &&
                            error.response.data &&
                            error.response.data.message) ||
                        error.message ||
                        error.toString();
                    this.successful = false;
                    this.loading = false;
                }
            );
        },

        submit() {
            axios.post('/insertUser', {
                user_id: this.user_id,
                user_name: this.user_name,
                user_pw: this.user_pw,
                user_email: this.user_email
            }).then(response => {
                if (response.status) {
                    alert("회원가입에 성공하셨습니다.")
                    this.$router.go()
                } else {
                    alert("회원가입에 실패하셨습니다. 다시 시도해주십시오.")
                }
            }).catch(err => {
                console.log(err)
            })
        },

        close() {
            this.$router.go()
        },
    },
};

</script>


<style scoped>
  @import url('https://fonts.googleapis.com/css2?family=Poppins:wght@200;300;400;500;600;700&display=swap');

    * {
        font-family: 'Poppins', sans-serif;
    }

    h3 {
        text-align: left;
        margin-bottom: 0px;
        color: black !important;
        font-size: 25px;
    }

    label {
        margin-bottom: 5px;
    }

    .form-group {
        margin: 10px;
    }

    .error-feedback {
        color: red;
    }

    #regist_tab {
        display: flex;
        flex-direction: row;
        flex:1;
        border-left-style: solid;
        border-left-width: 5px;
        border-left-color: #00C9BE;
    }

    #regist_title {
        margin-left: 15px;
        align-self: center;
        flex: 6;
    }

    #buttons {
        margin-top: 20px;
    }

    #button_cancel {
        margin-left: 5px;
    }



</style>