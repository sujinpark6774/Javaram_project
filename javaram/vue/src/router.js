import { createWebHistory, createRouter } from "vue-router";
import HomePage from "./components/HomePage.vue";
import BaseComponent from "./components/common/component/BaseComponent.vue"; 
import RegisterPage from "./components/account/page/RegisterPage.vue";

import TodoPage from "./components/todo/page/TodoPage.vue";
import TodoCalendar from "./components/todo/component/TodoCalendar.vue";
import TodoList from "./components/todo/component/TodoList.vue";
import TodoModal from "./components/todo/component/TodoModal.vue";

import MailPage from "./components/mail/page/MailPage.vue";
import ConferPage from "./components/conference/page/ConferPage.vue";
import ChatRoom from "./components/conference/page/ChatRoom.vue";
import NoticePage from "./components/notice/page/NoticePage.vue";
import NoticeDetailPage from "./components/notice/page/NoticeDetailPage.vue";
import NoticeWritePage from "./components/notice/page/NoticeWritePage.vue";
import NoticeUpdatePage from "./components/notice/page/NoticeUpdatePage.vue";
import PaginatedList from "./components/notice/page/PaginatedList.vue";
import NoticeList from "./components/notice/component/NoticeList.vue";

import InformationPage from "./components/account/page/InformationPage.vue";

// component
import ModalPage from "./components/common/component/modal/ModalPage.vue";
import LoginComponent from "./components/account/component/LoginComponent.vue";
import MailSend from "./components/mail/page/MailSend.vue";
import MailDetailPage from "./components/mail/page/MailDetailPage.vue";

import ChatRoom2 from "./components/conference/page/ChatRoom2.vue";

const routes = [
  {
    path: "/",
    name: "BaseComponent",
    component: BaseComponent,
  },
  {
    path: "/home",
    component: HomePage,
  },
  {
    path: "/registerpage",
    component: RegisterPage,
    name: "Regist"
  },
  {
    path: "/login",
    component: LoginComponent,
  },
  {
    path: "/info",
    component: InformationPage,
  },
  {
    path: "/modalpage",
    component: ModalPage,
  },
  {
    path: "/mailsend",
    name: "MailSend",
    component: MailSend,
  },
  {
    path: "/maildetailpage/:id",
    name: "MailDetailPage",
    component: MailDetailPage,
    props: route => ({
      id: Number(route.params.id)
    })
  },
  {
    path: "/mailpage/:pageNumber",
    component: MailPage,
    props: route => ({
      pageNumber: Number(route.params.pageNumber)
    }) 
  },

  {
    path: "/mailpage",
    component: MailPage,
  },
  {
    path: "/conferpage",
    component: ConferPage,
  },

  {
    path: "/noticepage",
    component: NoticePage,
  },
  {
    path: "/noticedetailpage/:id",
    name: "NoticeDetailPage",
    component: NoticeDetailPage,
    props: route => ({
      id: Number(route.params.id)
    })
  },
  {
    path: "/noticeupdatepage/:id",
    name: "NoticeUpdatePage",
    component: NoticeUpdatePage,
    props: route => ({
      id: Number(route.params.id)
    })
  },
  {
    path: "/noticewritepage",
    component: NoticeWritePage,
  },
  {
    path: "/paginatedlist",
    component: PaginatedList,
  },
  {
    path: "/noticelist",
    component: NoticeList,
  },


  


  
  {
    path: "/todopage",
    component: TodoPage,
  },
  {
    path: "/todocalendar",
    component: TodoCalendar,
  },
  {
    path: "/todolist",
    component: TodoList,
  },
  {
    path: "/todomodal",
    component: TodoModal,
  },
  {
    path: "/chat",
    name: "chat",
    // lazy-loaded
    component: ChatRoom,
  },
  {
    path: "/chat/:keyvalue",
    name: "chat2",
    // lazy-loaded
    component: ChatRoom2,
    props: route => ({
      keyvalue: String(route.params.keyvalue)
    })
  }
];
const router = createRouter({
  history: createWebHistory(),
  routes,
});


//router.beforeEach((to, from, next) => {
//    const publicPages = ['/loginpage', '/registerpage', '/homepage'];
//    const authRequired = !publicPages.includes(to.path);
//    const loggedIn = localStorage.getItem('user');
    // trying to access a restricted page + not logged in
    // redirect to login page
//    if (authRequired && !loggedIn) {
//      next('/loginpage');
//    } else {
//      next();
//    }
//  });
  
export default router;

