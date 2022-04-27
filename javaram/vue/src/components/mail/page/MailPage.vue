<template>

  <div class = "mailpage_wrap">
    <div id = "mail_tab">
      <h3 id = "mail_title">Mail</h3>
    </div>

    <main>
      <!-- Button gruop -->
      <!-- <div class="btn-group" role="group" aria-label="Basic radio toggle button group">
        <input @click="makeget" type="radio" class="btn-check" name="btnradio" id="btnradio1" autocomplete="off" checked>
        <label class="btn btn-outline-primary" for="btncheck1">받은 메일함</label>
          <span class="badge bg-primary rounded-pill">{{this.countMail}}</span> 
        <input @click="outbox" type="radio" class="btn-check" name="btnradio" id="btnradio2" autocomplete="off">
          <label class="btn btn-outline-primary" for="btnradio2">보낸 메일함</label>
      </div> -->
      <!-- Received Mail list -->
        <table>
          <thead>
            <tr>
              <!-- <th scope="col">Check</th> -->
              <th scope="col">No.</th>
              <th scope="col">Title</th>
              <th scope="col">Date</th>
              <th scope="col">Writer</th>
            </tr>
          </thead>
          <tbody>
            <tr v-for="(mail, index) in mailData.mails.slice().reverse()" :key="index" @click="MailClick(mail.messageNumber)">
              <!-- <th scope="row">{{num - notice.idx + 1}}</th>  -->
              <!-- <td>{{notice.notice_id}}</td> -->
              <!-- <td v-on:click.stop=""><input class="form-check-input me-1" type="checkbox" v-model = "toggle" true-value = "yes" false-value = "no" id = mail.messageNumber value= {{mail.title}}></td> -->
              <td >{{countMail-index-(pageNumber-1)*10}}</td>
              <td>{{mail.title}}</td>
              <td>{{mail.time}}</td>
              <td>{{mail.from}}</td>
            </tr>
          </tbody>
        </table>
      <!-- Delete Write Buttons -->
      <div class="d-flex flex-row-reverse bd-highlight">
        <button id="writeBtn" @click="writeBtn" class="btn btn-primary">
          메일쓰기
        </button>

        <!-- <button @click="MailSend" id="deleteBtn" class="btn btn-primary">
          <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-trash" viewBox="0 0 16 16">
            <path d="M5.5 5.5A.5.5 0 0 1 6 6v6a.5.5 0 0 1-1 0V6a.5.5 0 0 1 .5-.5zm2.5 0a.5.5 0 0 1 .5.5v6a.5.5 0 0 1-1 0V6a.5.5 0 0 1 .5-.5zm3 .5a.5.5 0 0 0-1 0v6a.5.5 0 0 0 1 0V6z"/>
            <path fill-rule="evenodd" d="M14.5 3a1 1 0 0 1-1 1H13v9a2 2 0 0 1-2 2H5a2 2 0 0 1-2-2V4h-.5a1 1 0 0 1-1-1V2a1 1 0 0 1 1-1H6a1 1 0 0 1 1-1h2a1 1 0 0 1 1 1h3.5a1 1 0 0 1 1 1v1zM4.118 4 4 4.059V13a1 1 0 0 0 1 1h6a1 1 0 0 0 1-1V4.059L11.882 4H4.118zM2.5 3V2h11v1h-11z"/>
          </svg>
          
          <component :is="selectedComponent"></component>
        </button> -->
      </div>
          <!-- 네비바 -->
        <div class="d-flex justify-content-center">
          <nav aria-label="Page navigation example" id="notice_pagination" data-v-4f7d534a="" data-v-f077e7ea="">
            <ul class="pagination" data-v-4f7d534a="">
              <li v-for="i in pageCount" :key="i" class="page-item" data-v-4f7d534a="">
                <a class="page-link" v-bind:href="'/MailPage/'+ i" data-v-4f7d534a="">{{i}}</a>
                </li>
            </ul>
          </nav>
        </div>
      </main>
    </div>

</template>


<script>
import axios from "axios";

// import MailSend from './MailSend.vue'
// import MailDetailPageVue from './MailDetailPage.vue';
export default {
  name: "MailPage",
  

  data:() => {
      return {
        receiveV: true,
        sendV : false,
        binV:false,
        mailData : [],
        mailoutData : [],
        pageCount : 1,
        pageoutCount : 1,
        countMail : "1",
        countoutMail : "1",
      }
    },

  components:{
    // MailSend,
    // MailDetailPageVue
  },
  props: {
    pageNumber : {
      Type : Number,
      default : 1
    }
  },

  methods:{
    

    writeBtn(){
      //window.open('./MailPage'); 메일 창 새로 띄우기
      this.$router.push({path: `/mailsend/`})
    },

    receiveBtn(){
      this.receiveV = true
      this.sendV = false
      this.binV = false
    },

    sendBtn(){
      this.receiveV = false
      this.sendV = true
      this.binV = false
    },

    binBtn(){
      this.receiveV = false
      this.sendV = false
      this.binV = true
    },

    makeget(){
      axios({
        method : 'GET',
        url : '/readMails',
        params: {
          user : sessionStorage.getItem('user_id'),
          boxName : "inbox",
          pageNumber : this.pageNumber
        }
        })
        .then(response => {
          this.mailData = response.data;
          this.countMail = response.data.messageCount;
          this.pageCount =  parseInt(this.countMail / 10 + 1);
          console.log(response.data.messageCount);
          console.log(this.mailData);

        })
        .catch(function (error) {
          console.log(error);
        }
        );
    },

    // outbox(){
    //   axios({
    //     method : 'GET',
    //     url : '/readMails',
    //     params: {
    //       user : sessionStorage.getItem('user_id'),
    //       boxName : "sent",
    //       pageNumber : this.pageNumber
    //     }
    //     })
    //     .then(response => {
    //       this.mailoutData = response.data;
    //       this.countoutMail = response.data.messageCount;
    //       this.pageoutCount =  parseInt(this.countoutMail / 10 + 1);
    //       console.log(response.data.messageCount);
    //       console.log(this.mailData);

    //     })
    //     .catch(function (error) {
    //       console.log(error);
    //     }
    //     );
    // },

    // countGet(){
    //   axios({
    //     method : 'GET',
    //     url : '/countMails',
    //     params: {
    //       user : "test",
    //       boxName : "inbox",
    //     }
    //     })
    //     .then(response => {
    //       this.countMail = response.data;
    //       this.pageCount =  parseInt(this.countMail / 10 + 1);
    //       console.log(response);
    //     })
    //     .catch(function (error) {
    //       console.log(error);
    //     }
    //     );
    // },

    // countoutGet(){
    //   axios({
    //     method : 'GET',
    //     url : '/countMails',
    //     params: {
    //       user : "test",
    //       boxName : "outbox",
    //     }
    //     })
    //     .then(response => {
    //       this.countMail = response.data;
    //       this.pageCount =  parseInt(this.countMail / 10 + 1);
    //       console.log(response);
    //     })
    //     .catch(function (error) {
    //       console.log(error);
    //     }
    //     );
    // },

    noticeCont(){
        const urlid = new FormData()
        urlid.append('urlid', this.mails_id)
        
    },
    /* eslint-disable */
    MailClick(mailnum){
      this.$router.push(`/maildetailpage/${mailnum}`);
    },
  },
  mounted(){
    this.makeget();
  },

}
</script>

<style scoped>
table {
  width: 100%;
  border-collapse: collapse;
}
table th {
  font-size: 0.9rem;
}
table tr {
  height: 2rem;
  text-align: center;
  border-bottom: 1px solid #505050;
}
table tr:first-of-type {
  border-top: 2px solid #404040;
}
table tr td {
  padding: 1rem 0;
  font-size: 0.9rem;
}
#writeBtn {
  margin-top: 1.5rem;
  text-align: center;
}

.btn-group {
  margin-bottom: 1.5rem;
}

.mailpage_wrap {
    margin-left: 150px;
}

#mail_tab {
    display: flex;
    flex-direction: row;
    flex:1;
    border-left-style: solid;
    border-left-width: 5px;
    border-left-color: #00C9BE;
}

#mail_title {
    margin-left: 15px;
    /* align-self: center; */
    flex: 6;
}
#newBtn {
    flex: 1;
}

main {
    margin-top:20px;
    display: flex;
    flex-direction: column;
    flex:17;
}

#mail_list {
    flex:4;
}
h3 {
    text-align: left;
    margin-bottom: 0px !important;
}

  /* #notice_pagination {
      flex: 1;
  } */
</style>
