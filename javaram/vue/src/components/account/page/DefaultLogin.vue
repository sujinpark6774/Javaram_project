<template>
  <div id="login" class="container">
    <router-view></router-view>
  </div>

</template>


<script>
  export default {
    name: "DefaultLogin",
  };
</script>


<style scoped>
  * {
      background:#e9e9e9;
    }
    
  .wrap_login {
    min-width: min-content;
    align-items: center;
    padding: 20px;
    position: absolute;
    top: 32%;
    left: 40%
  }

  .banner {
    /* margin-left: 50px; */
    width: 222px;
  }

  #banner_img {
    margin: 10px;
    width: 200px;
    display: flex;
    align-items: center;
    position: relative;
  }

  .form-signin {
    width: 222px;
    /* margin: 20px 50px 50px 50px; */
  }

  #login_button {
    margin-top: 8px;
    font-size: 14px;
    /* width: 222px; */
    color: white;
    background: #00C9BE;
  }

  #id_detail {
    margin-top: 10px;
    margin-bottom: 10px;
    text-align: left;
    /* width: 222px; */
  }

  #pwd_detail {
    margin-top: 10px;
    text-align: left;
    /* width: 222px; */
  }

  ::-webkit-input-placeholder {
    font-size: 13px;
  }

  #buttons {
    margin-top: 6px;
    display: flex;
  }

  #button_search {
    display: inline-block;
    flex: 1;
    margin-right: 6px;
    width: 108px;
    font-size: 11px;
    background: #bbbbbb;
    color: white;
  }

  #button_regist {
    display: inline-block;
    flex: 1;
    width: 108px;
    font-size: 12px;
    background: #bbbbbb;
    color: white;
  }

.body {
    display: flex;
    align-items: center;
    padding-top: 40px;
    padding-bottom: 40px;
    background-color: #f5f5f5;
}
#login{
  width: 100%;
  height: 100vh;
  background:#e9e9e9;
}
</style>