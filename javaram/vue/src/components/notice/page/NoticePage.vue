<template>

    <div class="noticepage_wrap">

        <div id="notice_tab">
            <h3 id="notice_title">Notice</h3>
            <button id="newBtn" type="button" class="btn btn-primary" v-if="this.user_access == 'true'" @click="this.$router.push('/noticewritepage');">등록</button>
        </div>

        <main>
            <PaginatedList listArray="pageArray" />
        </main>

    </div>

</template>

<script>
import axios from "axios"
import PaginatedList from './PaginatedList'

export default {
    name: "NoticePage",
    components: {
        PaginatedList,
    },

    data() {
        return {
            temp: 10,
            currentUser : sessionStorage.getItem('auth'),
            pageArray: [],
            user_access : sessionStorage.getItem('access')
        }
    },

    methods:{
        goback(){
        if(!this.currentUser){
          this.$router.push('/login')
        }
      }
    },

    created () {
        axios.get('/notice')
        .then(response => {
        console.log(response);
        this.pageArray = response.data;
        })
        .catch(err => {
        console.log(err);
        });
    },
    mounted(){
        this.goback()
    }

}
</script>

<style scoped>
    h3 {
        text-align: left;
        margin-bottom: 20px;
    }

    .noticepage_wrap {
        margin-left: 150px;
    }

    #notice_tab {
        display: flex;
        flex-direction: row;
        flex:1;
        border-left-style: solid;
        border-left-width: 5px;
        border-left-color: #00C9BE;
    }

    #notice_title {
        margin-left: 15px;
        align-self: center;
        flex: 6;
    }

    #newBtn {
        flex: 1;
    }

    main {
        margin-top:40px;
        display: flex;
        flex-direction: column;
        flex:17;
    }

    #notice_list {
        flex:4;
    }

</style>