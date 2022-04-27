<template>
  <div v-if="this.currentUser == 'true'">
    <BaseComponent/>
  </div>

  <div v-else>
    <LoginComponent/>
  </div>
</template>


<script>
  import BaseComponent from "./components/common/component/BaseComponent.vue";
  import LoginComponent from "./components/account/component/LoginComponent.vue";
  // import DefaultLogin from "./components/account/page/DefaultLogin.vue";
  // import store from './store/index.js';

  export default {
    data(){
      return {
        currentUser : sessionStorage.getItem('auth'),
          // isLogin : store.state.auth
        };
    }, 
    components: {
      BaseComponent,
      LoginComponent
      // DefaultLogin
    },
    methods:{
      goback(){
        if(!sessionStorage.getItem('auth')){
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
#login{
  width: 100%;
  height: 100vh;
  background:#e9e9e9;
}
</style>



