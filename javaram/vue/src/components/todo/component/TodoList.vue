<template>

    <div class="todo-list-section">

        <div class="todo-list-wrap">

            <div class="todo-list-tab">
                <h4 id="todo-list-title">Today</h4>
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

</template>

<script>
import axios from "axios";
import moment from 'moment'

export default {
    name: "todoList",
  
    data() {
        return {
            todos: [],
      
            user_id : sessionStorage.getItem('user_id'),
            // user: "sujin",

            today_date: moment(String(new Date())).format('YYYY-MM-DD'),        // 오늘 날짜
        }
    },
    
    methods: {
        TodoToday() {
            // (user, 날짜 기준) todo 데이터 가져옴
            axios
                .get(`/todo/detail?user=${this.user_id}&tododate=${this.today_date}`)
                .then(response => {
                    console.log(response.data);
                    this.todos = response.data;
                })
                .catch(e => {
                console.log(e);
                });    
        },
        
    },

    mounted() {
        this.TodoToday();
    },

}

</script>

<style scoped>

ul {
    padding-left: 0rem;
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

</style>