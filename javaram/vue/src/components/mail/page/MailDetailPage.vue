<template>
  <div id="mail-write-wrap">
    <div id="mail_tab">
      <h3 id="mail_title">Mail</h3>
    </div>

    <div id="mail-write-box">

      <div id="mail-write-box-cont">
        <div class="d-flex flex-row-reverse bd-highlight">
            <button type="button" @click="mainBtn" class="btn-close" data-bs-dismiss="alert" aria-label="Close">
            </button>
        </div>
        <h3 id="mail-write_title"><strong>받은 메일</strong></h3>
        <hr>
          <div class="mb-3">
              <label for="exampleFormControlInput1" class="form-label">제목</label>
              <input type="email" class="form-control" v-model="detail.title" id="exampleFormControlInput1" disabled>
          </div>

          <div class="mb-3">
            <label for="exampleFormControlInput1" class="form-label">보낸이 / 받은이</label>
            <div class="input-group mb-3" id="todo-detail-content-title">
              <input type="text" class="form-control" v-bind:placeholder="detail.from" disabled>
              <span class="input-group-text">→</span>
              <input type="text" class="form-control" v-bind:placeholder="detail.to" disabled> 
            </div>
          </div>

          <div class="mb-3">
              <label for="exampleFormControlTextarea1" class="form-label">본문 내용</label>
              <textarea class="form-control" v-model="detail.message" id="exampleFormControlTextarea1" rows="15" disabled></textarea>
          </div>
      </div>
    </div>
  </div>
</template>

<script>
import axios from "axios";
import {object} from "yup/lib/locale"

export default {
  name : 'MailDetailPage',
  setup() {
    
  },
  props: {
    id : Number,
  },


  data() {
    return {
      disabled: 0,
      detail : object,
    }
  },  
  methods: {
    onClick(){
      this.kamzoname = true;
    },

    MailDetail(){
      axios({
        method : 'GET',
        url : '/readMail',
        params: {
          user : sessionStorage.getItem('user_id'),
          boxName : "inbox",
          messageNumber : this.$route.params.id
        }
        })
        .then(response => {
          console.log(response.data);
          this.detail = response.data;
        })
        .catch(function (error) {
           console.log("이게뭐야");
          console.log(error);
         
        }
        );
    },
    mainBtn(){
      //window.open('./MailPage'); 메일 창 새로 띄우기
      this.$router.push({path: `/mailpage`})
    },
  },
  mounted(){
  this.MailDetail();
  }
}
</script>


<style scope>

    h3,
    h6 {
        text-align: left;
    }

    #mail_tab {
        border-left-style: solid;
        border-left-width: 5px;
        border-left-color: #00C9BE;
        margin-bottom: 30px;
    }

    #mail_title {
        margin-left: 15px;
        align-self: center;
        flex: 6;
    }

    #mail-write_title {
        font-size: 1.3rem;
        margin-bottom: 15px;
    }

    #mail-write-wrap{
        margin-left: 150px;
    }

    #mail-write-box {
        border-radius: 8px;
        border-style: solid;
        border-width: 1.2px;
        border-color: #cccccc;

        margin-bottom: 50px;
    }

    #mail-write-box-cont {
        margin: 20px;
    }

    .mail_text_tab {
        display: flex;
        flex-direction: row;
    }

    #mail_text_info {
        flex: 10;
        align-self: center;
        margin: 0;
    }

    #deleteBtn {
        flex: 1;
    }

    #buttons {
        display: flex;
        justify-content: flex-end;
    }
    input[disabled] {
      background-color: white !important ;
    }
    textarea[disabled] {
      background-color: white !important ;
    }
</style>