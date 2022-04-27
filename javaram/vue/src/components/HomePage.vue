<template>

  <div class="content">
    <div class="container-fluid">
      <div class="row">

        <!-- 왼쪽 -->
        <div class="col-xl-8 col-lg-7 col-md-6" id="home-left-wrap">

          <!-- Javaram -->
          <div class="card card" id="home-left-javaram" style="text-align : center;">
            <img src="../assets/Javaram2.png" id="home-left-javaram-img" alt="">
          </div>

          <!-- Notice -->
          <div class="card-header" id="home-left-notice">
            <notice-list />
          </div>

        </div>



        <!-- 오른쪽 -->
        <div class="col-xl-4 col-lg-5 col-md-6" id="home-right-wrap">

          <!-- Today Todo -->
          <div class="card-header" id="home-right-todo">
            <div class="card-body">
                <todo-list />
            </div>
          </div>

          <!-- Promotion -->
          <div class="card-header" id="home-right-promotion">
            <div class="card-body">
              <carousel />
            </div>
          </div>

        </div>

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

  .container-fluid {
    margin-left: 110px;
    margin-top: 60px;
  }

  .todo-list {
    height: 350px;
  }

  .card-header {
    padding: 2rem;
  }

  .card {
    border-radius: 6px;
    margin-bottom: 20px;
    position: relative;
    z-index: 1;
    border: none;
    display: -webkit-box;
    display: -ms-flexbox;
    display: flex;
    -webkit-box-orient: vertical;
    -webkit-box-direction: normal;
    -ms-flex-direction: column;
    flex-direction: column;
    min-width: 0;
    background-clip: border-box;
    border-radius: 0.25rem;
  }

  .card-body {
    padding: 0px;
  }

  #home-left-javaram-img {
    width: 500px;
    height: 100%;
    object-fit: cover;
    margin-left: 120px;
  }

  .main-panel>.content {
    padding: 30px 15px;
    min-height: calc(100% - 123px);
    margin-left: 100px;
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
