<template>

  <div class="todo-wrap">

    <!-- Calendar -->
    <div class="todo-calendar-wrap">

      <div class="todo-calendar-title-wrap">
        <h4 id="todo-calendar-title">To-Do</h4>
      </div>

      <TodoCalendar id="TodoCalendar" @clickdateC="clickdateC" />
    </div>


    <div class="todo-sections">

      <!-- Todo List -->
      <div class="todo-list-section">

        <div class="todo-list-wrap">

          <div class="todo-list-tab">

            <h4 id="todo-list-title">List</h4>

            <button type="button" class="addBtn btn btn-primary btn-sm" data-bs-toggle="modal"
              data-bs-target="#staticBackdrop">등록</button>

          </div>

        <hr>

          <div class="todo-list-group">
            <ul id="list-group" v-for="(todo,idx) in todos" :key="idx" style="list-style: none;">
              <li class="list-item" @click="TodoDetail(todo.todo_no)">
                <input class="form-check-input" type="checkbox" value="todo.check_yn" aria-label="...">
                  {{ todo.title }}
              </li>
            </ul>
          </div>

        </div>

      </div>

      <!-- Todo Detail -->
      <div class="todo-detail-section">

        <div class="todo-detail-wrap">

          <div class="todo-detail-tab">
            <h4 id="todo-detail-title">Detail</h4>
            <button type="button" class="updateBtn btn btn-primary btn-sm" @click="TodoUpdate(todoshow.todo_no)">수정</button>
            <button type="button" class="deleteBtn btn btn-danger btn-sm" @click="TodoDelete(todoshow.todo_no)">삭제</button>
          </div>

        <hr>

          <div id="todo-detail-content">

            <h6>Title</h6>
            <div class="input-group mb-3">
                <input type="text" class="form-control" aria-label="Title" aria-describedby="basic-addon1" v-model="title" :value="todoshow.title">
            </div>

            <h6>Date</h6>
            <div class="input-group mb-3" id="todo-detail-content-title">
              <input type="text" class="form-control" aria-label="Start_Date" v-model="start_date" :value="todoshow.start_date">
              <span class="input-group-text">~</span>
              <input type="text" class="form-control" id="todo-detail-content-date" aria-label="End_Date" v-model="end_date" :value="todoshow.end_date">
            </div>          

            <h6>MEMO</h6>
            <div class="input-group mb-3">
                <input type="text" class="form-control" id="todo-detail-content-memo" aria-label="Memo" aria-describedby="basic-addon1" v-model="memo" :value="todoshow.memo">
            </div>

          </div>

        </div>
      </div>
    </div>

    <!-- Modal -->
    <todo-modal />

  </div>
</template>

<script>
import TodoCalendar from "../component/TodoCalendar.vue";
import TodoModal from "../component/TodoModal.vue";
import axios from "axios";
import moment from "moment";
import Swal from 'sweetalert2'

export default {
  name: "TodoPage",

  components: {
    TodoCalendar,
    TodoModal,
  },

  data() {
    return {
      attrs: [{
        key: 'today',
        highlight: 'blue',
        dates: new Date(),
      },],

      
      date: "",

      // user: "sujin",
      user_id : sessionStorage.getItem('user_id'),

      todos: [],
      todoshow: {},
      today_date: moment(String(new Date())).format('YYYY-MM-DD'),
    };
  },

  methods: {
    
    clickdateC(date) {

      // Calendar에서 선택한 날짜 받음
      this.date=date.id;

      // (user, 날짜 기준) todo 데이터 가져옴
      axios
        .get(`/todo/detail?user=${this.user_id}&tododate=${this.date}`)
        .then(response => {
          console.log(response.data);
          this.todos = response.data;
        })
        .catch(e => {
          console.log(e);
        });    
    },

    // (user, 날짜, notice_no 기준) todo 데이터 가져옴
    TodoDetail(todo_no) {
      axios
        .get(`/todo/detail/show?user=${this.user_id}&todono=${todo_no}`)
        .then(response => {
          console.log(response.data);
          this.todoshow = response.data[0];
        })
        .catch(e => {
          console.log(e);
        }); 
      
    },

    // Todo 삭제
    TodoDelete(todo_no) {
      Swal.fire({ 
        title: "일정을 삭제 하시겠습니까?",
        type: 'warning', 
        showCancelButton: true, 
        confirmButtonText: '확인', 
        cancelButtonText: '취소', 
        showCloseButton: true, 
        showLoaderOnConfirm: true, 
      }).then((result) => { 
        if (result.value) { // confirmButtonText의 확인 클릭 시 true 반환 
                axios
                .delete("/tododelete/"+todo_no)
                .then(response => {
                  if(response.status){
                      alert("일정 삭제 성공")
                      this.$router.go()
                  } else {
                      alert("일정 삭제 실패")
                  }
                }).catch(e => {
                  console.log(e);
                })
        } 
      })
    },

    // Todo Detail 수정하기
    TodoUpdate(todo_no) {
      axios
        .put("/todoupdate/"+todo_no, {
            todo_no: todo_no,
            title: this.title,
            start_date: this.start_date,
            end_date: this.end_date,
            memo: this.memo,
        }).then(response => {
            if(response.status){
                alert("일정 수정 성공")
                this.$router.go()
            } else {
                alert("일정 수정 실패")
            }
        }).catch(err => {
            console.log(err)
        })
    },

  },

}
</script>

<style scoped>
  ul {
    padding-left: 0rem;
  }

  hr {
    margin: 10px;
  }

  .todo-wrap {
    display: -webkit-box;
    flex-direction: row;
    margin-left: 30px;
  }

  .todo-calendar-wrap {
    width: 670px;
    height: 850px;
    margin-top: 20px;
    margin-left: 50px;
  }

  .todo-calendar-title-wrap {
    border-left-style: solid;
    border-left-width: 5px;
    border-left-color: #00C9BE;
    margin-bottom: 20px;
  }

  #todo-calendar-title {
    margin-left: 15px;
  }

  .todo-sections {
    flex: 1;
    margin-left: 40px;
    margin-top: 10px;
    display: flex;
    flex-direction: column;
    width:550px;
  }




  /* To-Do list */
  .todo-list-section {
    width: 550px;
    height: 300px;

    border-radius: 8px;
    border-style: solid;
    border-width: 1.2px;
    border-color: #cccccc;
  }

  .todo-list-wrap {
    margin-left: 25px;
    margin-right: 20px;
    margin-top: 15px;
    margin-bottom: 8px;
  }

  .todo-list-tab {
    position: relative;
    border-left-style: solid;
    border-left-width: 5px;
    border-left-color: #00C9BE;
  }

  #todo-list-title{
    margin-left: 15px;
  }

  .addBtn {
    position: absolute;
    top: 0;
    right: 0;
  }

  .todo-list-group {
    margin-top: 21px;
    height: 200px;
    overflow-y: scroll;
  }

  #list-group {
    left: 0;
  }




  /* To-Do Detail */
  .todo-detail-section {
    margin-top: 20px;
    width: 550px;
    height: 410px;

    border-radius: 8px;
    border-style: solid;
    border-width: 1.2px;
    border-color: #cccccc;
  }

  .todo-detail-wrap {
    margin-left: 25px;
    margin-right: 20px;
    margin-top: 15px;
    margin-bottom: 8px;
  }

  #todo-detail-content {
    margin-top: 21px;
    height: 360px;
  }

  .todo-detail-tab {
    position: relative;
    border-left-style: solid;
    border-left-width: 5px;
    border-left-color: #00C9BE;
  }

  #todo-detail-title {
    margin-left: 15px;
  }

  .updateBtn {
    position: absolute;
    top: 0;
    right: 52px;
  }

  .deleteBtn {
    position: absolute;
    top: 0;
    right: 0;
  }

  #todo-detail-content-memo {
    height: 120px;
  }




  /* modal 관련 style*/
  label {
    margin-top: 20px;
  }

  .modal-body {
    margin : 0;
  }


</style>
