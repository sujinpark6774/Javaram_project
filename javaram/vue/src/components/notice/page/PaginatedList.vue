<template>
  <div>
    <table>
        
        <thead>
            <tr>
                <th scope="col">No.</th>
                <th scope="col">Title</th>
                <th scope="col">Writer</th>
                <th scope="col">Date</th>
            </tr>
            </thead>

            <tbody>
            <!-- @click="NoticeClick(notice.notice_id)" -->
            <tr v-for="(notice,idx) in paginatedData" :key="idx" @click="NoticeClick(notice.notice_id)"> 
                <th scope="row">{{this.cnt-idx-(pageNum)*pageSize}}</th>
                <td>{{notice.notice_title}}</td>
                <td>{{notice.user_id}}</td>
                <td>{{notice.notice_date}}</td>
            </tr>
        </tbody>
    </table>
    <!-- page-btn -->
    <div class="btn-cover">
      <button :disabled="pageNum === 0" @click="prevPage" class="btn btn-primary"> 
        이전
      </button>
      <span class="page-count">{{ pageNum + 1 }} / {{ pageCount }} 페이지</span>
      <button :disabled="pageNum >= pageCount - 1" @click="nextPage" class="btn btn-primary">
        다음
      </button>
    </div>
  </div>
</template>

<script>
import axios from "axios"
export default {
  name: 'paginated-list',
  data () {
    return {
      pageNum: 0,
      cnt : 0,
      pageSize : 10,
      listArray : [],
    }
//   },
//   props: {
//     listArray: {
//       type: Array,
//       required: true
//     }
    // },
    // pageSize: {
    //   type: Number,
    //   required: false,
    //   default: 10
    // }
  },

  methods: {
    nextPage () {
      this.pageNum += 1;
    },
    prevPage () {
      this.pageNum -= 1;
    },
    NoticeList(){
      axios
        .get("/notice")
        .then(response => {
          console.log(response.data);
          this.listArray = response.data;
          this.cnt= response.data.length;
        })
        .catch(e => {
          console.log(e);
        })
    },
    NoticeClick(noticedata) {
      this.$router.push({path: `/noticedetailpage/${noticedata}`});
    },
  },

  computed: {
    pageCount () {
      let listLeng = this.listArray.length,
          listSize = this.pageSize,
          page = Math.floor(listLeng / listSize);
      if (listLeng % listSize > 0) page += 1;
      
      /*
      아니면 page = Math.floor((listLeng - 1) / listSize) + 1;
      이런식으로 if 문 없이 고칠 수도 있다!
      */
      return page;
    },
    paginatedData () {
      const start = this.pageNum * this.pageSize,
            end = start + this.pageSize;
      return this.listArray.slice(start, end);
    },


  },
  mounted() {
    this.NoticeList();
  },
}
</script>

<style>

table {
  width: 100%;
  border-collapse: collapse;
}
table th {
  font-size: 0.9rem;
}
table tr {
  height: 2rem;
  text-align: center;
  border-bottom: 1px solid #505050;
}
table tr:first-of-type {
  border-top: 2px solid #404040;
}
table tr td {
  padding: 1rem 0;
  font-size: 0.9rem;
}
.btn-cover {
  margin-top: 1.5rem;
  text-align: center;
}
.btn-cover .page-btn {
  width: 5rem;
  height: 2rem;
  letter-spacing: 0.5px;
}
.btn-cover .page-count {
  padding: 0 1rem;
}



    h3 {
        text-align: left;
        margin-bottom: 20px;
    }

    .noticepage_wrap {
        margin-left: 150px;
    }

    .notice_tab {
        display: flex;
        flex-direction: row;
        flex:1;
    }

    #notice_title {
        margin: 0;
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

    /* #notice_pagination {
        flex: 1;
    } */

</style>