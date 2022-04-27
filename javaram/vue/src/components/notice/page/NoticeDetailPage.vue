<template>

  <div id="notice_detail_wrap">

      <div id="notice_tab">
        <h3 id="notice_title">Notice</h3>
      </div>

      <div class="notice-detail-box">

        <div class="notice-detail-box-cont">
    
          <div> 
            <h3 id="notice_detail_title-h">{{ detail.notice_title }}</h3>
          </div>

          <hr>

          <div class="notice_text_tab">
              <div id="notice_text_info">
                <h6>작성자 : {{ detail.user_id }}</h6>
                <h6>작성일 : {{ detail.notice_date }}</h6>
              </div>
              
              <!-- 관리자 권한을 가졌을 경우에만 실행 가능 -->
              <button id="updateBtn" type="button" class="btn btn-primary" v-if="this.user_access == 'true'" @click="NoticeUpdate(detail.notice_id)">수정</button>
              <button id="deleteBtn" type="button" class="btn btn-danger" v-if="this.user_access == 'true'" @click="NoticeDelete(detail.notice_id)">삭제</button>
          </div>

          <hr>

          <p>{{ detail.notice_content }}</p>

        </div>

      </div>

  </div>

</template>

<script>
import axios from "axios";
import { object } from 'yup/lib/locale';
import Swal from 'sweetalert2'

export default {
  name: 'NoticeDetailPage',   

  props: {
    id: Number
  },
  
  data() {
    return {
      detail: object,
      user_access : sessionStorage.getItem('access'),
    }
  },

  methods:{

    // 뒤로가기 버튼
    clickback(){
      this.$router.go(-1);
    },

    // Notice Detail 데이터 가져오기
    NoticeDetail(){
      axios
        .get("/notice/detail?id="+this.id)
        .then(response => {
          console.log(response.data);
          this.detail = response.data;
        })
        .catch(e => {
          console.log(e);
        })
    },

    // Notice 삭제하기
    NoticeDelete(notice_id){
      Swal.fire({ 
        title: "게시물을 삭제 하시겠습니까?",
        type: 'warning', 
        showCancelButton: true, 
        confirmButtonText: '확인', 
        cancelButtonText: '취소', 
        showCloseButton: true, 
        showLoaderOnConfirm: true, 
      }).then((result) => { 
        if (result.value) { // confirmButtonText의 확인 클릭 시 true 반환 
          axios
            .delete("/noticedelete/"+notice_id)
            .then(response => {
              if(response.status){
                  alert("게시물 삭제 성공")
                  this.$router.push("/noticepage")
              } else {
                  alert("게시물 삭제 실패")
              }
            }).catch(e => {
              console.log(e);
            })
        } 
      })
    },

    NoticeUpdate(detail_id) {
      this.$router.push({path: `/noticeupdatepage/${detail_id}`});
    },

  },

  mounted() {
    this.NoticeDetail();
  },

}
    
</script>



<style scoped>
  h3,
  h6 {
      text-align: left;
  }

  #notice_tab {
    border-left-style: solid;
    border-left-width: 5px;
    border-left-color: #00C9BE;
    margin-bottom: 30px;
  }

  #notice_title {
    margin-left: 15px;
    align-self: center;
    flex: 6;
  }

  .notice-detail-box {
    border-radius: 8px;
    border-style: solid;
    border-width: 1.2px;
    border-color: #cccccc;
    height: 600px;
  }

  .notice-detail-box-cont {
    margin: 20px;
  }


  #notice_detail_wrap {
    margin-left: 150px;
  }

  #notice_detail_title-h {
    font-size: 1.4rem;
  }

  .notice_text_tab {
      display: flex;
      flex-direction: row;
  }

  #notice_text_info {
      flex: 10;
      align-self: center;
      margin-top: 10px;
  }

  #updateBtn {
      margin-left: 20px;
      margin-top: 12px;
      flex: 1;
      height: 40px;
  }

  #deleteBtn {
      margin-left: 20px;
      margin-top: 12px;
      flex: 1;
      height: 40px;
  }

  
  /* 삭제 POPUP 이벤트 관련 */
  .swal2-title {
    font-size: 1.0em !important;
  }

</style>