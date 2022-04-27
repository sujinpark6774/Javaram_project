<template>
    <div>
        <CalendarComp 
          is-expanded 
          :attributes="attributes" 
          :from-date="new Date()"
          @dayclick="onDayClick" />
    </div>
</template>

<script>
import axios from "axios";

export default {
  data() {
    return {
      days: [],
      totals: [],
      
      user_id : sessionStorage.getItem('user_id'),
      user_access : sessionStorage.getItem('access'),

      attributes: [
        {
          dot: true,
          dates: [
            {start: new Date(2022,2,1), end: new Date(2022,2,6)},
            // new Date(2022,2,10),
            // new Date(2022,2,25),
            // new Date(2022-03-06),
          ]
        }
      ],



    };
  },

  methods: {
    
    // Todo 데이터(user 기준) 가져오기 -> 이벤트와 무관하게 기본으로 가져와야 함
    TodoListU() {
      axios
        .get("/todo/?user=" + this.user_id)
        .then(response => {
            console.log(response.data);
            this.totals = response.data;
        })
        .catch(e => {
            console.log(e);
        })
    },

    // Calendar에서 선택한 날짜 받음
    onDayClick(day) {
      const idx = this.days.findIndex(d => d.id === day.id);
      if (idx >= 0) {
        this.days.splice(idx, 1);
      } else {
        this.days = [],
        this.days.push({
          id: day.id,
          date: day.date,
        });
        console.log(this.days);
        this.day=this.days[0];
      }

      // Calendar에서 선택한 날짜를 TodoPage로 보내기 
      this.$emit("clickdateC",this.day);
    },

  },

  mounted() {
    this.TodoListU();
  },
};
</script>