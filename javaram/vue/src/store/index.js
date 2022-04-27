import { createStore } from "vuex";
// import { auth } from "./auth.module";
const store = createStore({
  state: {
    auth : false,
    loginInfo : {}
  },
  mutations:{
    setinfo(state, value){
      state.auth = true,
      state.loginInfo = value
    },
    logout(state){
      state.auth = false,
      state.loginInfo = {}
    }
  }
});
export default store;