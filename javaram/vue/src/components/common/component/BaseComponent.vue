<template>
  <div class="wrap">
    <!-- SideBar -->
    <div class="d-flex flex-column flex-shrink-0" id="sidebar">

      <!-- KTDS logo -->
      <div class="logo-details" id="logo-details" style="margin: 10px;">
        <a href="/home">
          <img v-if="menuLogo" id="logo-icon" :src="menuLogo" alt="menu-logo" class="menu-logo icon">
          <i v-else class="bx icon" :class="menuIcon" />
        </a>
      </div>

      <!-- Profile -->
      <div class="profile" id="profile">
        <div class="profile-details" id="profile-details">
          <img v-if="profileImg" :src="profileImg" alt="profileImg" id="profile_img">
          <i v-else class="bx bxs-user-rectangle" />
          <div class="name" id="profile_name">{{ user_name }}</div>
        </div>
      </div>

      <!-- Menu list -->
      <div
        style="display: flex ; flex-direction:column; justify-content: space-between; flex-grow: 1; max-height: calc(100% - 60px); " v-show="currentUser">
        <ul class="nav-list" id="nav-list" style="overflow: visible;">
          <span v-for="(menuItem, index) in menuItems" :key="index">
            <li>
              <a v-if="menuItem.name == 'Profile'" data-bs-toggle="modal" data-bs-target="#modalRegister">
                <i class="bx" :class="menuItem.icon || 'bx-square-rounded'" id="menu-icon"  data-bs-toggle="modal" data-bs-target="#modalRegister"/>
                  <span class="links_name" id="menu-name">{{ menuItem.name }}</span>
              </a>

              <a :href="menuItem.link" v-else-if="(menuItem.name != 'Profile') && (menuItem.name!='Web Chat' )">
                <i class="bx" :class="menuItem.icon || 'bx-square-rounded'" id="menu-icon" />
                <span class="links_name" id="menu-name">{{ menuItem.name }}</span>
              </a>
              <span class="tooltip">{{ menuItem.tooltip || menuItem.name }}</span>

              <a v-if="menuItem.name == 'Web Chat'" data-bs-toggle="modal" data-bs-target="#createConference">
                 <i class="bx" :class="menuItem.icon || 'bx-square-rounded'" id="menu-icon"  data-bs-toggle="modal" data-bs-target="#createConference"/>
                  <span class="links_name" id="menu-name">{{ menuItem.name }}</span>
              </a>
            </li>  
          </span>
        </ul>
      </div>
    </div>    

    <!-- NavBar -->
    <div id="content">
      <nav class="navbar navbar-expand navbar-dark" style="background-color: #11101d;">
          <li class="nav-item" v-show="currentUser">
            <a href="/login" @click="logout">
              <!-- <font-awesome-icon icon="user" :style="{ color: 'white' }"/> {{ user_name }} -->
              <font-awesome-icon icon="sign-in-alt" id="user_name_area" :style="{ color: 'white' }" /> Log-Out
            </a>
          </li>
          <li class="nav-item" v-show="!currentUser">
            <a href="/login">
              <font-awesome-icon icon="sign-in-alt" :style="{ color: 'white' }" /> Log-In
            </a>
          </li>
      </nav>

      <!-- Profile 탭 클릭시 등장하는 모달 컴포넌트 -->
      
      <ProfileModal/>
      <joinConference/>

 
      <!-- main : 바뀌는 부분 -->
      <div class="container">
        <router-view />
      </div>
    </div>
  </div>
</template>


<script>
import ProfileModal from "../../account/component/ProfileModal.vue";
import joinConference from "../../conference/component/joinConference.vue";

  export default {
    name: 'SidebarPage',
    components : {
      ProfileModal,
      joinConference
    },
    data(){
      return{
        currentUser : sessionStorage.getItem('auth'),
        user_name : sessionStorage.getItem('user_name'),
      }
    },


    methods:{
      logout(){
        sessionStorage.clear()
        this.$router.push('/login')
      }
    },
    mounted(){
    },

    props: {
      menuLogo: {
        type: String,
          default: require('../../../assets/ktds_ci2.png'),
      },
      //! Menu items
      menuItems: {
        type: Array,
        default: () => [
          {
            link: '/info',
            name: 'Profile',
            tooltip: 'Profile',
            icon: 'bx-cog',
          },
          {
            link: '/NoticePage',
            name: 'Notice',
            tooltip: 'Notice',
            icon: 'bx-notification',
          },
          {
            link: '/MailPage',
            name: 'Mail',
            tooltip: 'Mail',
            icon: 'bx-mail-send',
          },
          {
            link: '/TodoPage',
            name: 'Todo',
            tooltip: 'Todo',
            icon: 'bx-calendar-check',
          },
          {
            link: '/ConferPage',
            name: 'Web Chat',
            tooltip: 'Web Chat',
            icon: 'bx-chat',
          },
        ],
      },

      //! Profile detailes
      profileImg: {
        type: String,
        default: require('../../../assets/Javaram.png'),
      },
      profileName: {
        type: String,
        default: 'Javaram',
      },
      profileRole: {
        type: String,
        default: 'Javaram Test',
      },

    },

  }
</script>




<style scoped>
  /* Google Font Link */
  @import url('https://fonts.googleapis.com/css2?family=Poppins:wght@200;300;400;500;600;700&display=swap');
  @import url('https://unpkg.com/boxicons@2.0.7/css/boxicons.min.css');

  * {
    margin: 0;
    padding: 0;
    box-sizing: border-box;
    font-family: 'Poppins', sans-serif;
  }

  .wrap {
    display: flex;
    flex-direction: row;
    height: 100vh;
  }

  #sidebar {
    position: fixed;
    display: flex;
    flex-direction: column;
    left: 0;
    top: 0;
    height: 100%;
    width: 230px;
    min-height: min-content;
    overflow-y: auto;
    background: #11101d;
    z-index: 99;
    padding: 0;
    transition: all 0.5s ease;
  }

  /*** ktds logo ***/
  #logo-details {
    margin: 10px;
    height: 57px;
    display: flex;
    align-items: center;
    position: relative;
    background: #11101d;
  }

  #logo-icon {
    margin-left: 20px;
    text-align: left;
    opacity: 1;
    height: 100%;
    width: 90px;
    transition: all 0.5s ease;
  }

  /*** menu ***/
  #nav-list {
    margin: 10px;
    width: 200px;
    height: 100%;
  }

  #menu-icon {
    color: white;
    margin: 8px;
    opacity: 1;
    height: 100%;
    transition: all 0.5s ease;
  }

  #menu-name {
    color: white;
    font-size: 15px;
    font-weight: 400;
    width: 900px;
    margin: 20px;
  }

  #sidebar li {
    position: relative;
    margin: 8px 8px;
    list-style: none;
  }

  /*** profile ***/
  .profile {
    position: relative;
    height: 80px;
    width: 230px;
    left: 0;
    padding: 5px;
    background: #171627;
  }

  #profile-details {
    left: 0;
    height: 70px;
    width: 220px;
    display: flex;
    align-items: center;
    flex-wrap: nowrap;
  }

  #profile_img {
    height: 100%;
    width: 80px;
    object-fit: cover;
    border-radius: 6px;
    margin-left: 0px;
    margin-right: 10px;
  }

  #profile_name {
    color: white;
  }

  /*** navbar ***/
  .navbar {
    min-height: 77px;
    position: relative;
    height: 77px !important;
  }

  .navbar-dark .navbar-nav .nav-link {
    color: rgb(255, 255, 255) !important;
    font-family: 'Poppins', sans-serif;
    font-size: 15px;
  }

  #content {
    flex: 6;
    display: flex;
    flex-direction: column;
    margin-left: 230px;
  }

  .container {
    margin-top: 50px;
    flex: 1;
    padding: 0px;
    
    display: flex;
    flex-direction: column;
    width: 80%;
  }

  .navbar-expand {
    flex-wrap: nowrap;
    justify-content: flex-end;
    padding-right: 20px;
  }

  .nav-list a {
    text-decoration: none;
  }

  a { 
    text-decoration: none; 
    color : rgb(255, 255, 255)
  }

  #login{
  width: 100%;
  height: 100vh;
  background:#e9e9e9;
  }

  #user_name_area{
    margin-left: 20px;
  }
</style>