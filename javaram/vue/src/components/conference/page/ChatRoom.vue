<template>

<div id="container">

  <div id = "mail_tab">
    <h3 id = "mail_title">Web Chat</h3>
      <!-- txt 파일 저장하기 버튼 -->
    <button type="button" class="btn btn-outline-success" onclick="alert('대화 내용 저장 완료')">저장하기</button>
  </div>

  <hr>

  <table>

    <div>
      <!-- 채팅방 -->
      <td class="table_chat">
        
        <div class="main_right">
          <div class="chat_right">
            <!-- 아이디 확인(구별하기 위해) 아이디>>실제 아이디 값으로 변경-->
            <div v-for="(item, idx) in recvList" :key="idx">
                <div v-if="item.userName !== userName" class="other">{{ item.userName }} : {{ item.content }}</div>
                <!-- {{this.appendText(item.userName, item.connect)}}  추후 테스트-->
                <div v-else class="me">{{ item.content }} </div>
            </div>
          </div>

          <div class="form">
            <!-- <textarea style="background-color:transparent" class="form__input" id="text-data" type="text" placeholder="메세지를 입력하세요." v-model="textData" @keyup="sendMessage"> -->
            <!-- <textarea style="background-color:transparent" class="form__input" id="text-data" type="text" placeholder="메세지를 입력하세요." v-model="message" @keyup="sendMessage"> -->
            <input class="form__input" id="text-data" type="text" placeholder="메세지를 입력하세요." v-model="message" @keyup="sendMessage">
            <!-- <div @click="submitMessage" class="form__submit"> -->
           

              <!-- 보내기 아이콘 비행기 영역 -->
              <svg width="30" height="30" viewBox="0 0 68 68" fill="#CCCCCC">
                <g clip-path="url(#clip0_26_10)">
                  <path fill-rule="evenodd" clip-rule="evenodd"
                    d="M48.0833 19.799C48.619 20.3347 48.806 21.127 48.5665 21.8457L35.8385 60.0294C35.5946 60.7614 34.9513 61.2877 34.1855 61.382C33.4198 61.4763 32.6681 61.1217 32.2539 60.4707L22.593 45.2893L7.41158 35.6285C6.76065 35.2142 6.40604 34.4625 6.50031 33.6968C6.59458 32.931 7.12092 32.2878 7.85287 32.0438L46.0366 19.3159C46.7553 19.0763 47.5476 19.2633 48.0833 19.799ZM26.5903 44.1204L33.3726 54.7782L42.0926 28.6181L26.5903 44.1204ZM39.2642 25.7897L23.7619 41.292L13.1041 34.5097L39.2642 25.7897Z"
                    fill=""/>
                </g>
                <defs>
                  <clipPath id="clip0_26_10">
                    <rect width="48" height="48" fill="white" transform="translate(33.9412) rotate(45)"/>
                  </clipPath>
                </defs>
              </svg>  
          </div>
        </div>
      </td>
    </div>

  </table>
</div>
</template>


<script>
import Stomp from 'webstomp-client'
import SockJS from 'sockjs-client'

export default {
  name: 'ChatRoom',
  data() {
    return {
      userName: sessionStorage.getItem('user_name'),
      message: "",
      recvList: [],
      textData: ""
    }
  },
  created() {
    this.connect()
  },

  methods: {
    sendMessage (e) {
      if(e.keyCode === 13 && this.userName !== '' && this.message !== ''){
        this.send()
        this.message = ''
        
      }
    },    
    send() {
      console.log("Send message:" + this.message);
      if (this.stompClient && this.stompClient.connected) {
        const msg = { 
          userName: this.userName,
          content: this.message 
        };
        this.stompClient.send("/receive", JSON.stringify(msg), {});
      }
    },    
    connect() {
      const serverURL = "http://localhost:8085"
      let socket = new SockJS(serverURL);
      this.stompClient = Stomp.over(socket);
      console.log(`소켓 연결 시도. 서버 주소: ${serverURL}`)
      this.stompClient.connect(
        {},
        frame => {
          // 소켓 연결 성공
          this.connected = true;
          console.log('소켓 연결 성공', frame);
  
          this.stompClient.subscribe("/send", res => {
            console.log('메시지 내용.', res.body);
            this.recvList.push(JSON.parse(res.body))
          });
        },
        error => {
          // 소켓 연결 실패
          console.log('소켓 연결 실패', error);
          this.connected = false;
        }
      );        
    },

    // fnRun() {
    //   let objWSH = new ActiveXObject("WScript.Shell");
    //   objWSH.Run("C:/javaram_chat/result.txt");
    // }
  }
}
</script>

<style scoped>

h3 {
  margin-right: 0px;
  margin-top: 0px;
  margin-bottom: 0px;
}

table tr td {
    padding: 0;
}

.form {
  display: flex;
  justify-content: space-between;
  padding: 1rem;
  background: #ffffff;
  border-radius: 30px 30px 24px 24px;
  /* border: solid aquamarine; */
  box-shadow: 0px -5px 30px rgba(0, 0, 0, 0.05);
}

.form__input {
  border: none;
  padding: 0.5rem;
  font-size: 16px;
  width: calc(100% - 60px);
  padding: 5px;
}

.form__input:focus {
  outline: none;
}

.form__submit {
  display: flex;
  align-items: center;
  cursor: pointer;
}

svg {
  transition: 0.3s;
}

svg:hover {
  fill: #999999;
}


/* 해당 영역 채팅 배경 */

/* 메인 */

.main_left1 {
  margin: auto;
  /* margin-top: 5%;
  margin-bottom: 5%;
  margin-left: 40%; */
  border-radius: 20px;
  background-color: aquamarine;
  text-align: center;
  width: 90%;
  height: 95%;
  font-size: 25px;
  padding: 8px;
}
.main_left2 {
  margin: auto;
  border-radius: 10px;
  background-color: white;
  text-align: left;
  width: 85%;
  height: 90%;
  border: solid aquamarine;
  padding: 15px;
  font-size: 20px;
}

.main_right {
  margin: auto;
  border-radius: 5px;
  background-color: #f1f1f1;
  text-align: center;
  width: 90%;
  height: 95%;
  padding: 10px;
}


/* 채팅 영역 */
.chat_right {
  height: 90%;
  width: 100%;
  overflow-y: auto;
}


.table_list {
  width: 500px;
  height: 900px;
}

.table_chat {
  width: 1200px;
  height: 700px;
}



/* 접속 알림 */
.connect {
  width: 90%;
  margin: auto;
  background-color: aquamarine; /*색깔은 추후에 변경*/
  font-weight: bold; /*글자 굵게*/
  text-align: center;
  margin-top: 10px;
}

/* 접속 종료 알림 */
.disconnect {
  width: 90%;
  margin: auto;
  background-color: a;
  font-weight: bold; /*글자 굵게*/
  text-align: center;
  margin-top: 10px;
}

/* 내가 보낸 메시지 */
.me {
  width: 98%;
  margin: 10px;
  background-color: rgb(255, 255, 255);
  text-align: right;
  border-radius: 5px;
  margin-top: 10px;
  margin-right: 5px;
  font-size: 15px;
  padding-top: 8px;
  padding-bottom: 8px;
  padding-right: 10px;
}

/* 상대방이 보낸 메시지 */
.other {
  width: 98%;
  margin: 10px;
  background-color: #abd1cf;
  text-align: left;
  border-radius: 5px;
  margin-top: 10px;
  margin-left: 5px;
  font-size: 15px;
  padding-top: 8px;
  padding-bottom: 8px;
  padding-left: 10px;
}

#container{
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
</style>
