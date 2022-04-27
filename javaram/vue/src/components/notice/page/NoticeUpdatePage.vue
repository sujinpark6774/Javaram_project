<template>

    <div class="notice-update-wrap">

        <div id="notice_tab">
            <h3 id="notice_title">Notice</h3>
        </div>

        <div class="notice-update-box">

          <div class="notice-update-box-cont">

            <div class="input-group mb-3">
                <input type="text" class="form-control" aria-label="Title" aria-describedby="basic-addon1" v-model="notice_title" :value="detail.notice_title">
            </div>
    
            <hr>

            <div class="input-group mb-3" id="notice-update-fixed" style="text-align:left">
                <span class="input-group-text" id="basic-addon1">작성자 : {{ detail.user_id }}<br>작성일 : {{ detail.notice_date }}</span>
            </div>

            <hr>

            <div class="input-group mb-3">
                <input type="text" class="form-control" aria-label="Content" aria-describedby="basic-addon1" id="notice-update-content" style="text-align:top" v-model="notice_content" :value="detail.notice_content">
            </div>

            <div class="notice-update-btn" align="right">
              <button id="updateBtn" type="button" class="btn btn-primary" @click="NoticeUpdate">수정</button>
            </div>

          </div>

        </div>

    </div>

</template>

<script>
import axios from "axios";
import { object } from 'yup/lib/locale';

export default {
  name: 'NoticeUpdatePage',   

  props: {
    id: Number
  },
  
  data() {
    return {
      detail: object,
    }
  },

  methods:{
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

    // Notice Detail 수정하기
    NoticeUpdate() {
        axios
            .put("/noticeupdate/"+this.id, {
                notice_id: this.id,
                notice_title: this.notice_title,
                notice_content: this.notice_content,
            }).then(response => {
                if(response.status){
                    alert("공지사항 수정 성공")
                    this.$router.push("/noticepage")
                } else {
                    alert("공지사항 수정 실패")
                }
            }).catch(err => {
                console.log(err)
            })
    },

  },

  mounted() {
    this.NoticeDetail();
  },

}
    
</script>

<style scoped>

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

  .notice-update-box{
    border-radius: 8px;
    border-style: solid;
    border-width: 1.2px;
    border-color: #cccccc;
    height: 600px;
  }

  .notice-update-box-cont{
    margin: 20px;
  }

  .input-group-text {
    text-align: left;
  }

  .notice-update-wrap {
    margin-left: 150px;
  }

  #notice-update-fixed {
    text-align: left;
  }

  #notice-update-content {
    text-align: left;
    height: 392px;
    overflow-y: scroll;
  }

  .notice-update-btn {
    margin-top: 39px;

  }

  #updateBtn {
      flex: 1;
  }
</style>