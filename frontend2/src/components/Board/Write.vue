<template>
  <div>
    <h1>게시판 등록</h1>

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
              <div v-for="(data, key, index) in previewList"  v-bind:key="index">
                <img :src="data" :width="'100px;'"/>
              </div>
              <input id="customFile" type="file" @change="handleFileChange" multiple="multiple"/>
              <label class="custom-file-label" for="customFile">{{file_name}}</label>
            </td>
          </tr>
        </table>
      </form>
    </div>

    <div class="btnWrap">
      <a href="javascript:;" @click="fnList" class="btn">목록</a>
      <a href="javascript:;" @click="fnAddProc" class="btnAdd btn">등록</a>
    </div>
  </div>
</template>

<script>
export default {
  data() { //변수 생성
    return{
      code:'news'
      ,subject:''
      ,cont:''
      ,file:''

      ,id:'test'
      ,form : new FormData() //form 전송 데이터

      ,file_name:''
      ,previewList:[]
      ,imgList:[]
    }
  }
  ,methods:{
    fnList(){ //리스트 화면으로 이동 함수
      this.$router.push({path:'./list',query:this.body});

    }
    , handleFileChange(e){

      this.file = e.target.files;

      this.file_name = '';
      this.form.delete('files');
      this.previewList = [];

      for(let i=0; i < this.file.length; i++){
        this.file_name += this.file[i].name + ' ';
        this.form.append('files', this.file[i]);
        this.previewList.push( URL.createObjectURL(this.file[i]) );
      }

    }
    ,fnAddProc() {
      if(!this.subject) {
        alert("제목을 입력해 주세요");
        this.$refs.subject.focus();
        return;
      }

      let temp = {
        "code":this.code
        ,"subject":this.subject
        ,"cont":this.cont
        ,"id":this.id
      }

      this.form.append("board",JSON.stringify(temp));
      // this.form.append("board", new Blob([JSON.stringify(temp)] , {type: "application/json"}));



      this.$axios.post("http://localhost:9090" + "/api/board/write", this.form, {
        headers : {
          'Content-Type' : 'multipart/form-data'
          // 'Content-Type' : 'application/json'


        }

      })
        .then((res)=>{
          console.log('res\n' + res);
          alert('등록되었습니다.');
          this.fnList();
        })
        .catch((err)=>{
          console.log('err\n' + err);
          alert("실행중 실패했습니다.\n다시 이용해 주세요");
        })

    }
  }
}
</script>

