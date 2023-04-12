<template>
  <div>
    <h2>게시판 리스트</h2>

    <div class="searchWrap">
      <input type="text" v-model="keyword" @keyup.enter="fnSearch" /> <a href="javascript:;" @click="fnSearch" class="btnSearch btn">검색</a>
    </div>

    <div class="listWrap">
      <table class="tbList">
        <colgroup>
          <col width="6%" />
          <col width="*" />
          <col width="10%" />
          <col width="15%" />
        </colgroup>
        <tr>
          <th>no</th>
          <th>제목</th>
          <th>아이디</th>
          <th>날짜</th>
        </tr>
        <tr v-for="(data, key, index) in list"  v-bind:key="index">
          <td>{{key}}</td>
          <td class="txt_left"><a href="javascript:void(0);" @click="fnView(`${data.idx}`)">{{data.subject}}</a></td>
          <td>{{data.id}}</td>
<!--          <td>{{data.regdate.substring(0,10)}}</td>-->
          <td></td>
        </tr>
        <tr v-if="list.length === 0">
          <td colspan="4">데이터가 없습니다.</td>
        </tr>
      </table>
    </div>

    <div class="pagination" v-if="paging.totalCount > 0">
      <a href="javascript:;" @click="fnPage(1)" class="first">&lt;&lt;</a>
      <a href="javascript:;" v-if="pageIdx > 1" @click="fnPage(pageIdx-1)"  class="prev">&lt;</a>
      <template v-for=" (n,index) in pageNavigation()">
        <template v-if="pageIdx == n">
          <strong :key="index">{{n}}</strong>
        </template>
        <template v-else>
          <a href="javascript:;" @click="fnPage(n)" :key="index">{{n}}</a>
        </template>
      </template>
      <a href="javascript:;" v-if="pageIdx < paging.endPage" @click="fnPage(pageIdx + 1)"  class="next">&gt;</a>
      <a href="javascript:;" @click="fnPage(paging.finalPage)" class="last">&gt;&gt;</a>
    </div>

    <div class="btnRightWrap">
      <a @click="fnAdd" class="btn">등록</a>
    </div>

  </div>
</template>

<script>
export default {

  data(){
    return{
      sch_params : ''
      , code : 'news'
      , list : ''
      , keyword : this.$route.query.keyword
      , pageIdx : this.$route.query.pageIdx ? this.$route.query.pageIdx : 1
      , paging : ''


      , pageNavigation : function(){

          let pageArr = [];

          for (let i = this.paging.startPage; i <= this.paging.endPage ; i++){
            pageArr.push(i);
          }

          return pageArr;
      }

    }
  }
  , mounted() {
    this.fnGetList();

  }
  , methods:{
    fnGetList(){
      this.sch_params = {
        code : this.code
        , keyword : this.keyword
        , pageIdx : this.pageIdx

      }

      this.$axios.get("http://localhost:9090" + "/api/board/list", {params : this.sch_params})
        .then((res)=>{
          console.log('success : ',res);
          this.list = res.data.body;
          this.paging = res.data.pagingInfo;
        })
        .then((err)=>{
          if(err) console.log('err : ', err);
        });
    }
    ,fnAdd() {
      this.$router.push("./write");
    }
    ,getList() {
      this.$axios.get("http://localhost:9090" + "/api/board/list")
        .then((res)=>{
          console.log(res);
        })
        .then((err)=>{
          console.log(err);
        })
    }
    ,fnSearch() { //검색
      this.pageIdx = 1;
      this.fnGetList();
    }
    , fnView(idx){
        let param = {
          // 'idx': idx
          'pageIdx' : this.pageIdx
        };
        this.$router.push({ path:'./view/' + idx, query:param});
    }
    , fnPage(n) {//페이징 이동
      if(this.pageIdx != n) {
        this.pageIdx = n;
        this.fnGetList();
      }
    }


  }

}
</script>
