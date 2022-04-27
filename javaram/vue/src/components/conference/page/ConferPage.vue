<template>

  <table>
    <tr>
      <!-- <td class="table_list">
        
        <div class="main_left1">
          참가자 목록 
        <div class="main_left2">
       삭제해도 됩니다! http://localhost:8080/chat 해당리크로 옮겨주세요! <br>
        신현아 <br>
        김용호 <br>
        황규진 <br>
        서동민 <br>
        임지호 <br>
        </div>
        </div> 
      </td> -->
      <!-- 채팅방 -->
      <td class="table_chat">
        <div class="main_right">
          
          <div class="chat_right">
            <!-- 채팅 메시지 영역 -->
          </div>


          <div class="form">
            <input class="form__input" type="text" placeholder="메세지를 입력하세요." v-model.trim="msg" @keyup.enter="submitMessage">
            <div @click="submitMessage" class="form__submit">
              
              <!-- 비행기 코드 영역 -->
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
        </div>
      </td>
    </tr>
  </table>
</template>

<script>
export default {
  data() {
    return {
      msg: "",
    };
  },
  methods: {
    submitMessage() {
      if (this.msg) {
        this.$emit("submitMessage", this.msg);
        this.msg = "";
      }
      return;
    },
  },
};
</script>

<style scoped>
.form {
  display: flex;
  justify-content: space-between;
  padding: 1.4rem;
  background: #ffffff;
  border-radius: 30px 30px 24px 24px;
  border: solid aquamarine;
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
  border-radius: 20px;
  background-color: aquamarine;
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
  height: 900px;
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
  width: 90%;
  margin: auto;
  background-color: lemonchiffon;
  text-align: right;
  border-radius: 5px;
  margin-top: 10px;
}

/* 상대방이 보낸 메시지 */
.other {
  width: 90%;
  margin: auto;
  background-color: white;
  text-align: left;
  border-radius: 5px;
  margin-top: 10px;
}

</style>
