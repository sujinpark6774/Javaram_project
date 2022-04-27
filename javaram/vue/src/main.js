import {  createApp } from "vue";
import App from "./App.vue";
import router from "./router";
import store from "./store";
import "bootstrap/dist/css/bootstrap.min.css";
// 부트스트랩 추가
import "bootstrap";
import { FontAwesomeIcon } from './plugins/font-awesome'
import Tabs from 'vue3-tabs';

// v-calendar 관련 추가
import 'v-calendar/dist/style.css';
import { SetupCalendar, Calendar, DatePicker } from 'v-calendar';
// axios 추가
import axios from 'axios';

// axios 관련 설정
axios.defaults.headers.common['Access-Control-Allow-Origin'] = '*';
axios.defaults.headers.get['Content-Type'] = 'application/json;charset=utf-8';
const app = createApp(App);
app.use(router)
app.use(store)
app.use(Tabs);
app.component("font-awesome-icon", FontAwesomeIcon);
// v-calendar
app.use(SetupCalendar, {})
app.component('CalendarComp', Calendar)
app.component('DatePicker', DatePicker)
// axios
app.config.globalProperties.axios = axios;


app.mount("#app");
