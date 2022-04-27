<template>

  <div class="content">

    <div class="container">

      <!-- 자바람개비 로고 -->
      <div class="row" id="home-1st">
        <img src="../assets/Javaram2.png" id="home-left-javaram-img" alt="">
      </div>

      <!-- Todo, 홍보자료 -->
      <div class="row" id="home-2nd">

        <div class="col-6">
          <todo-list />
        </div>

        <div class="col-6">
          <carousel />
        </div>

      </div>

      <!-- 공지사항 -->
      <div class="row" id="home-3rd">
        <notice-list />
      </div>
      
    </div>

  </div>

</template>


<script>
import NoticeList from "../components/notice/component/NoticeList.vue";
import TodoList from '../components/todo/component/TodoList.vue';
import Carousel from '../components/common/component/Carousel.vue';

export default {
  name: "HomePage",
  components: {
    NoticeList,
    TodoList,
    Carousel,
  },

  data(){
    return {
      num : 5,
      currentUser : sessionStorage.getItem('auth')
    };
  },

  methods:{
    goback(){
      if(!this.currentUser){
        this.$router.push('/login')
      }
    }
  },

  mounted(){
    this.goback()
  }

};
</script>



<style scoped>
  h3 {
    text-align: left;
  }

  ul {
    padding-left: 0rem;
  }

  .content {
    margin-left: 130px;
    width: 100%;
  }

  .row {
    display: flex;
    flex-direction: column;
    flex-wrap: wrap;
    align-items: center;
  }

  .row-cols-lg-3>* {
    display: contents;
  }

  #home-2nd {
    display: flex;
    flex-direction: row;
    flex-wrap: wrap;
    margin-top: 80px;
    margin-bottom: 50px;
    align-items: center;
  }

  #home-3rd {
    margin-top: 50px;
    margin-bottom: 50px;
  }

  .todo-list {
    height: 350px;
  }

  #home-left-javaram-img {
    width: 550px;
    height: 100%;
    object-fit: cover;
    /* margin-left: 120px; */
    align-items: center;
  }

  #home-left-notice {
    margin-top: 40px;
  }

  #home-right-todo {
    height: 300px;
  }

  #home-right-promotion {
    margin-top: 30px;
    height: 300px !important;
  }

</style>