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
        <h3 id="mail-write_title"><strong>메일 작성</strong></h3>
        <hr>
          <div class="mb-3">
              <label for="exampleFormControlInput1" class="form-label">제목</label>
              <input v-model="titletext" type="email" class="form-control" id="exampleFormControlInput1" placeholder="제목을 입력해주세요." value="titletext">
          </div>

          <div class="mb-3">
            <label for="exampleFormControlInput1" class="form-label">보낸이 / 받은이</label>
            <div class="input-group mb-3" id="todo-detail-content-title">
              <input v-model="fromemail" type="email" class="form-control" placeholder="보낸이 입력" value="fromemail">
              <span class="input-group-text">→</span>
              <input type="email" class="form-control" v-model="toemail" placeholder="받은이 입력" value="toemail"> 
            </div>
          </div>

          <div class="mb-3">
              <label for="exampleFormControlTextarea1" class="form-label">본문 내용</label>
              <textarea v-model = "messages" class="form-control" rows="15" placeholder="본문 내용을 입력해 주세요"></textarea>
          </div>

          <div class="d-flex flex-row-reverse bd-highlight">
              <button class="btn btn-primary" type="submit" @click="SendMail">
                보내기
              </button>
          </div>


      </div>
  </div>
  </div>
</template>

<script>
import axios from "axios"
export default {
  name : 'MailP',
  setup() {
    
  },

  data() {
    return {
      disabled: 0,
      titletext : '',
      fromemail : sessionStorage.getItem("user_id") + '@ktds.ml',
      toemail : '',
      messages : '',
    }
  },
  methods: {
    onClick(){
      this.kamzoname = true;
    },
    

    mainBtn(){
      //window.open('./MailPage'); 메일 창 새로 띄우기
      this.$router.push({path: `/mailpage`})
    },

    SendMail(){
      axios
      .post("/sendMail", {
          from : this.fromemail,
          to : this.toemail,
          // cc : this.toemail,
          subject : this.titletext,
          message : this.messages,
      })
        .then(response => {
          console.log(response)
          this.$router.push({path: `/mailpage/`})
        })
      },
  },
}
</script>

<style>

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
      background-color: white;
    }
</style>