<template>
  <!-- Modal -->
  <div class="modal fade" id="exampleModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
    <div class="modal-dialog d-flex flex-column-reverse">
      <div class="p-2">
        <!-- 모달 창 전체 -->
        <div class="modal-content">

          <div class="modal-header">

            <div id="search_tab">
                <h3 id="search_title">ID/비밀번호 찾기</h3>
            </div>

            <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>

          </div>


          <!-- 클릭한 요소에 따라 컴포넌트 전환 -->
          
          <div class="modal-body">

            <!-- ID/PW찾기 버튼  -->
            <ul class="nav nav-tabs" id="myTab" role="tablist">

              <li class="nav-item" role="presentation">
                <button class="nav-link active" id="home-tab" @click="clickID" data-bs-toggle="tab" type="button"
                  role="tab" aria-controls="home" aria-selected="true">
                  <p class="text-secondary"> ID 찾기</p>
                </button>
              </li>

              <li class="nav-item" role="presentation">
                <button class="nav-link" id="profile-tab" @click="clickPW" data-bs-toggle="tab" type="button" role="tab"
                  aria-controls="profile" aria-selected="false">
                  <p class="text-secondary"> 비밀번호 찾기 </p>
                </button>
              </li>

            </ul>

            <div id="content-wrap">
              <div v-if="pw_on_click&(!auth_suceed)">
                <SearchPW />
              </div>
              <div v-if="id_on_click&(!auth_suceed)">
                <SearchID />
              </div>
              <div v-if="auth_suceed&id_on_click">
                <KnownUserID />
              </div>
              <div v-if="auth_suceed&pw_on_click">
                <KnownUserPW />
              </div>
            </div>

          </div>
          
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import SearchID from "../../../account/component/SearchID.vue"
import SearchPW from "../../../account/component/SearchPW.vue"
import KnownUserID from "../../../account/component/KnownUserID.vue"
import KnownUserPW from "../../../account/component/KnownUserPW.vue";

export default {
  // name: "FindModal",
  components : {
    SearchID,
    SearchPW,
    KnownUserID,
    KnownUserPW
  },
  data(){
    return {
      pw_on_click : false,
      id_on_click : true,
      footer_msg : "ID 찾기",
      auth_suceed : false
    }
  },
  methods:{
    clickPW(){
      this.id_on_click = !this.id_on_click;
      this.pw_on_click = !this.pw_on_click;
      this.footer_msg = "Password 찾기";
    },
    clickID(){
      this.id_on_click = !this.id_on_click;
      this.pw_on_click = !this.pw_on_click;
      this.footer_msg = "ID 찾기";
      },
    authCheck(){
      this.auth_suceed = !this.auth_suceed;
    }

  }
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
      font-size: 17px;
  }

  .modal-body {
    margin: 0 15px 20px 15px;
  }

  #content-wrap {
    margin-top: 15px;
  }

  #login_button {
    margin-top: 8px;
    font-size: 14px;
    /* width: 222px; */
    color: white;
    background: #00C9BE;
  }

  #search_tab {
      display: flex;
      flex-direction: row;
      flex:1;
      border-left-style: solid;
      border-left-width: 5px;
      border-left-color: #00C9BE;
  }

  #search_title {
      margin-left: 15px;
      align-self: center;
      flex: 6;
  }
</style>