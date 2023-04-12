<template>
  <div>
    <h1>게시판 상세보기</h1>

    <div class="AddWrap">
      <form>
        <table class="tbAdd">
          <colgroup>
            <col width="15%" />
            <col width="*" />
          </colgroup>
          <tr>
            <th>제목</th>
            <td>{{subject}}</td>
          </tr>
          <tr>
            <th>내용</th>
            <td class="txt_cont" v-html="cont"></td>
          </tr>
          <tr>
            <div v-for="(data, key, index) in previewList"  v-bind:key="index">
              <img :src="data" :width="'100px;'"/>
            </div>
            <th>img</th>
            <td v-for="(data, key, index) in imgList"  v-bind:key="index">
              <img :src="'file/'+ data.saveFileNm" :width="'100px;'"/>
            </td>
          </tr>
        </table>
      </form>
    </div>

    <div class="btnWrap">
      <a href="javascript:;" @click="fnList" class="btn">목록</a>
      <a href="javascript:;" @click="fnMod" class="btnAdd btn">수정</a>
    </div>
  </div>
</template>

<script>
export default {
  data() {
    return {
      body:this.$route.query
      ,idx:this.$route.params.idx
      ,subject:''
      ,cont:''
      ,view:''

      ,imgList:[]
    }
  }
  ,mounted() {
    this.fnGetView();
    console.log('pageIdx : ' + this.$pageIdx);
  }
  ,methods:{
    fnGetView() {
      let parameter = this.body;

      this.$axios.get('http://localhost:9090' + '/api/board/view/' + this.idx,{params:parameter})
        .then((res)=>{
          console.log('success : ',res);
          this.view = res.data.body;
          this.subject = this.view.subject;
          this.cont = this.view.cont.replace(/(\n)/g,'<br/>');

          this.imgList = this.view.fileList;

        })
        .catch((err)=>{
          console.log(err);
        })
    }
    ,fnList(){
      this.$router.push({path:'../list',query:this.body});
    }
    ,fnMod() {
      this.$router.push({path:'../mod/' + this.idx, query:this.body}); //등록화면으로 이동하면서 파라미터를 넘겨준다.
    }

  }
}
</script>

