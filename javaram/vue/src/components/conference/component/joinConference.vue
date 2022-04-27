<template>
<div class="modal" id="createConference" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
        <div class="modal-dialog d-flex flex-column-reverse">
            <div class="p-2">
                <div class="modal-content">
                        <div id = "chat_tab">
                            <h3 id = "chat_title">Web Chat</h3>
                            <div class="d-flex flex-row-reverse bd-highlight">
                             <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                            </div>
                        </div>
                        <hr>

                        <div class="d-flex justify-content-center">
                                        <div class="card" style="width: 28rem;">
                                            <div class="card-body">
                                                <h5 class="card-title">채팅방 이용방법</h5>
                                                <p class="card-text">아래 채팅방 이름 입력란에 방 이름을 입력해주세요.</p>
                                                <p class="card-text">채팅방 이름 입력 후 우측 하단 참가 버튼을 클릭해주세요.</p>
                                            </div>
                                        </div>
                                    </div>

                    <div class="modal-body">
                        <div class="input-group mb-3">
                         </div>

                        <!-- 회의실 생성 폼 -->
                        <div class="d-flex align-items-end flex-column" >
                            <div class="form-group w-100">
                                <form>
                                    <div class="form-group p-2">
                                        <label for="exampleFormControlInput1" class="p-1">채팅방 이름 입력</label>
                                        <input type="text" v-model="keyvalue" class="form-control" id="exampleFormControlInput1" placeholder="채팅방 이름 입력" >
                                    </div>

                                </form>
                            </div>
                        </div>
                    </div>                       
                    <!-- 참가 버튼 -->
                    <div class="modal-footer">          
                        <span>
                                <button class="btn btn-primary" id="button_regist"  @click="sendprop" type="submit"  data-bs-dismiss="modal"> 
                                    {{msg_btn_bottom}}
                                </button>
                        </span>
                    </div>
                </div>

            </div>
        </div>
    </div>

</template>

<script>
export default {
  name: "joinConference",
  components: {
  },
  data(){
      return {
          join_or_create : true, 
          msg_btn_right:'참가',
          msg_btn_bottom:'참가',
          data : ""
      }
  },
  methods: {
    sendprop(){
        this.data = this.keyvalue
        if(this.data){
            this.$router.push({path : `/chat/${this.data}`})
        }else{
            alert("채팅방 이름을 입력해주세요.")
        }
    },
    joinOrCreate(){
        if(this.join_or_create == true){
            this.msg_btn_bottom = '생성하기';
            this.msg_btn_right = '복사';
        }else{
            this.msg_btn_right = '참가';
            this.msg_btn_bottom = '뒤로가기';
        }
        this.join_or_create = !this.join_or_create;
    }
  }
};
</script>

<style scoped>
    h3{
        text-align: left;
    }
    h5{
        text-align: center;
        font-weight: bold;
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
        margin-bottom: 20px;
        max-height: 140px;
        border-radius: 30%;
    }

    .flex-row-reverse {
    flex-direction: row-reverse!important;
    margin-right: 15px;
    }

    #login_button {
        margin-top: 8px;
        font-size: 14px;
        /* width: 222px; */
        color: white;
        background: #00C9BE;
    }

    #chat_tab {
    display: flex;
    flex-direction: row;
    flex:1;
    border-left-style: solid;
    border-left-width: 5px;
    border-left-color: #00C9BE;
    margin-left: 15px;
    margin-top: 15px;
    }

    #chat_title {
        margin-left: 15px;
        /* align-self: center; */
        flex: 6;
        margin-bottom: 0px;
    }

    .modal-body {
        margin: 0px !important;
        position: relative;
        flex: 1 1 auto;
        padding: 0.5rem !important;
    }

    .card-body {
    flex: 1 1 auto;
    padding: 1rem 1rem;
    background: #F7F7F7 !important;
    }
</style>
