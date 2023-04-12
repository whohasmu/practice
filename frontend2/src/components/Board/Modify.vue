<template>
  <div>
    <h1>게시판 수정</h1>

    <div class="AddWrap">
      <form>
        <table class="tbAdd">
          <colgroup>
            <col width="15%" />
            <col width="*" />
          </colgroup>
          <tr>
            <th>제목</th>
            <td><input type="text" v-model="subject" ref="subject" /></td>
          </tr>
          <tr>
            <th>내용</th>
            <td><textarea v-model="cont" ref="cont"></textarea></td>
          </tr>
          <tr>
            <th>첨부파일</th>
            <td>
              <input id="customFile" type="file" @change="handleFileChange" multiple="multiple"/>
              <label class="custom-file-label" for="customFile">{{file_name}}</label>
            </td>
          </tr>
        </table>
      </form>
    </div>

    <div class="btnWrap">
      <a href="javascript:;" @click="fnList" class="btn">목록</a>
      <a href="javascript:;" @click="fnModProc" class="btnAdd btn">수정</a>
    </div>
  </div>
</template>

<script>
export default {
  data() { //변수 생성
    return{
      body:this.$route.query
      ,idx:this.$route.params.idx
      ,code:'news'
      ,subject:''
      ,cont:''
      ,id:'test'
      ,form:'' //form 전송 데이터

      ,imgList:[]
      ,file_name:''
    }
  }
  ,mounted() {
    this.fnGetView();
  }
  ,methods:{
    fnList(){ //리스트 화면으로 이동 함수
      // delete this.body;
      this.$router.push({path:'../list',query:this.body});
    }
    ,fnGetView() {
      this.$axios.get('http://localhost:9090' + '/api/board/view/'+this.idx,{params:this.body})
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
    ,fnModProc() { //등록 프로세스
      if(!this.subject) { //제목이 없다면 값을 입력하라고 알려준다.
        alert("제목을 입력해 주세요");
        this.$refs.subject.focus(); //방식으로 선택자를 찾는다.
        return;
      }

      this.form = { //backend로 전송될 POST 데이터
        idx:this.view.idx
        ,code:this.code
        ,subject:this.subject
        ,cont:this.cont
        ,id:this.id
      }

      this.$axios.post("http://localhost:9090" + "/api/board/mod", this.form)
        .then((res)=>{
          console.log('res\n' + res);
          alert('수정되었습니다.');
          this.fnList();
        })
        .catch((err)=>{
          console.log('err\n' + err);
          alert("실행중 실패했습니다.\n다시 이용해 주세요");
        })

    }
    , handleFileChange(e){

      this.file = e.target.files;

      this.file_name = '';
      this.form.delete('files');

      for(let i=0; i < this.file.length; i++){
        this.file_name += this.file[i].name + ' ';
        this.form.append('files', this.file[i]);
      }

    }
  }
}
</script>


