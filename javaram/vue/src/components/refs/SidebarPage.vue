<template>
    <div class="d-flex flex-column flex-shrink-0" id="sidebar">

        <!-- KTDS logo -->
        <div class="logo-details" id="logo-details" style="margin: 10px;">

            <a href="./Homepage">
                <img v-if="menuLogo" id="logo-icon" :src="menuLogo" alt="menu-logo" class="menu-logo icon">
                <i v-else class="bx icon" :class="menuIcon" />
            </a>
        </div>

        <!-- Profile -->
        <div class="profile" id="profile">

            <div class="profile-details" id="profile-details">
                <img v-if="profileImg" :src="profileImg" alt="profileImg" id="profile_img">
                <i v-else class="bx bxs-user-rectangle" />

                <div class="name" id="profile_name">{{ profileName }}</div>

            </div>
        </div>

        <!-- Menu list -->
        <div
        style="display: flex ; flex-direction:column; justify-content: space-between; flex-grow: 1; max-height: calc(100% - 60px); ">
                <ul class="nav-list" id="nav-list" style="overflow: visible;">

                    <span v-for="(menuItem, index) in menuItems" :key="index">
                        <li>
                            <a :href="menuItem.link">
                                <i class="bx" :class="menuItem.icon || 'bx-square-rounded'" id="menu-icon" />
                                <span class="links_name" id="menu-name">{{ menuItem.name }}</span>
                            </a>
                            <span class="tooltip">{{ menuItem.tooltip || menuItem.name }}</span>
                        </li>
                    </span>

                </ul>

        </div>

    </div>
</template>




<script>
export default {
    name: 'SidebarPage',

    props: {
        menuLogo: {
          type: String,
          default: require('../assets/ktds_ci2.png'),
        },
      
        //! Menu items
        menuItems: {
          type: Array,
          default: () => [
            {
              link: '/ProfilePage',                                    /* 수정필요 */
              name: 'Profile',
              tooltip: 'Profile',
              icon: 'bx-cog',
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
              name: 'Conference',
              tooltip: 'Conference',
              icon: 'bx-chat',
            },
          ],
        },

        //! Profile detailes
        profileImg: {
          type: String,
          default: require('../assets/Javaram.png'),
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




<style>
  /* Google Font Link */
  @import url('https://fonts.googleapis.com/css2?family=Poppins:wght@200;300;400;500;600;700&display=swap');
  @import url('https://unpkg.com/boxicons@2.0.7/css/boxicons.min.css');
* {
    margin: 0;
    padding: 0;
    box-sizing: border-box;
    font-family: 'Poppins', sans-serif;
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
    height: 50px;
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
    height: 60px;
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


</style>