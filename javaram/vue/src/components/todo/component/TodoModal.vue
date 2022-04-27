<template>
    <!-- Modal -->
  <div class="modal fade" id="staticBackdrop" data-bs-backdrop="static" data-bs-keyboard="false" tabindex="-1"
    aria-labelledby="staticBackdropLabel" aria-hidden="true">
    <div class="modal-dialog">
      <div class="modal-content">
        <div class="modal-header">
          <h5 class="modal-title" id="staticBackdropLabel">일정 추가</h5>
          <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
        </div>
        <div class="modal-body">

          <label for="title">일정 제목</label>
          <input class="form-control" type="text" v-model="title" placeholder="제목을 입력해주세요">

          <label for="startDate">시작 일자</label>
          <input id="startDateTime" type="datetime-local" class="form-control" v-model="start_date">

          <label for="endDate">종료 일자</label>
          <input id="endDateTime" type="datetime-local" class="form-control" v-model="end_date">

          <div class="mb-3">
            <label for="memo" class="form-label">메모</label>
            <textarea class="form-control" id="memo" rows="3" v-model="memo"></textarea>
          </div>

        </div>
        <div class="modal-footer">
          <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">취소</button>
          <button type="button" class="btn btn-primary" @click="TodoAdd">등록</button>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import axios from "axios";

export default {
    name : "TodoModal",

    data() {
      return {
        // user_id: "sujin",
        user_id : sessionStorage.getItem('user_id'),
      }
    },

    methods: {

        // Todo 신규 등록
        TodoAdd() {
            axios
                .post("/todoadd", {
                    user_id: this.user_id,
                    title: this.title,
                    start_date: this.start_date.substr(0,10),
                    end_date: this.end_date.substr(0,10),
                    memo: this.memo,

                }).then(response => {
                    if(response.status){
                        alert("일정이 등록되었습니다.")
                        this.$router.go()
                    } else {
                        alert("일정 등록 실패")
                    }
                }).catch(err => {
                    console.log(err)
                })
        },

    },
}
</script>