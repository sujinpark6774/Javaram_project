<template>

  <div id="notice-list-wrap">
    
    <div id="notice_tab">
        <h3 id="notice_title">Notice</h3>
    </div>

    <div id="notice-list-table">
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
          <tr v-for="(notice,idx) in paginatedData" :key="idx" @click="NoticeClick(notice.notice_id)">
            <th scope="row">{{this.cnt-idx-(pageNum)*pageSize}}</th>
            <td>{{notice.notice_title}}</td>
            <td>{{notice.user_id}}</td>
            <td>{{notice.notice_date}}</td>
          </tr>
        </tbody>
      </table>
    </div>

  </div>
</template>

<script>
import axios from "axios"

export default {
  name: 'paginated-list',
  data() {
    return {
      pageNum: 0,
      cnt: 0,
      pageSize: 8,
      listArray: [],
    }
  },

  methods: {
    nextPage() {
      this.pageNum += 1;
    },
    prevPage() {
      this.pageNum -= 1;
    },
    NoticeList() {
      axios
        .get("/notice")
        .then(response => {
          console.log(response.data);
          this.listArray = response.data;
          this.cnt = response.data.length;
        })
        .catch(e => {
          console.log(e);
        })
    },
    NoticeClick(noticedata) {
      this.$router.push({
        path: `/noticedetailpage/${noticedata}`
      });
    },
  },

  computed: {
    pageCount() {
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
    paginatedData() {
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


<style scoped>
  h3 {
    text-align: left;
    margin-bottom: 20px;
  }

  table {
    width: 100%;
    border-collapse: collapse;
  }

  table th {
    font-size: 0.8rem;
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
    padding: 0.7rem 0;
    font-size: 0.8rem;
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

  #notice-list-table {
    margin-top: 20px;
  }

</style>