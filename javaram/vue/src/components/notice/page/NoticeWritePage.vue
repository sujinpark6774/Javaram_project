<template>
    <div id="notice-write-wrap">

        <div id="notice_tab">
            <h3 id="notice_title">Notice</h3>
        </div>

        <div id="notice-write-box">

            <div id="notice-write-box-cont">

                <h3 id="notice-write_title"><strong>게시글 등록</strong></h3>
                <hr>
                <div class="mb-3">
                    <label for="exampleFormControlInput1" class="form-label">제목</label>
                    <input type="email" class="form-control" v-model="notice_title" id="exampleFormControlInput1" placeholder="제목을 입력해주세요">
                </div>
                <div class="mb-3">
                    <label for="exampleFormControlTextarea1" class="form-label">본문 내용</label>
                    <textarea class="form-control" v-model="notice_content" id="exampleFormControlTextarea1" rows="20" placeholder="본문 내용을 입력해주세요"></textarea>
                </div>
                <div id="buttons" class="mb-3">
                    <button type="button" class="btn btn-danger" style="margin-right: 10px;" v-if="update">삭제</button>
                    <button type="button" class="btn btn-primary" v-if="update">수정</button>
                    <button type="button" class="btn btn-primary" v-if="insert" @click="NoticeWrite">등록</button>
                </div>

            </div>

        </div>
    </div>
</template>

<script>
import axios from "axios";
import moment from "moment";

export default {
    name: "NoticeWritePage",

    data() {
        return {
            insert : true,
            update : false,

            user_id : sessionStorage.getItem('user_id'),
            user_access : sessionStorage.getItem('access'),

            notice_date: moment(String(new Date())).format('YYYY-MM-DD'),
        };
    },
    methods: {

        // 게시판 게시물 등록
        NoticeWrite() {
            axios
                .post("/noticeadd", {
                    user_id: this.user_id,
                    notice_title: this.notice_title,
                    notice_date: this.notice_date,
                    notice_content: this.notice_content,
                }).then(response => {
                    if(response.status){
                        alert("공지사항이 등록되었습니다.")
                        this.$router.push("/noticepage")
                    } else {
                        alert("공지사항 등록 실패")
                    }
                }).catch(err => {
                    console.log(err)
                })
        },

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

    #notice-write_title {
        font-size: 1.3rem;
        margin-bottom: 15px;
    }

    #notice-write-wrap{
        margin-left: 150px;
    }

    #notice-write-box {
        border-radius: 8px;
        border-style: solid;
        border-width: 1.2px;
        border-color: #cccccc;

        margin-bottom: 50px;
    }

    #notice-write-box-cont {
        margin: 20px;
    }

    .notice_text_tab {
        display: flex;
        flex-direction: row;
    }

    #notice_text_info {
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
</style>