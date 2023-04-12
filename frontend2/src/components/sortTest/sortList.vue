<template>
  <div>
    <h2>sort</h2>



    <div class="listWrap">
      <table class="tbList">
        <colgroup>
          <col width="6%" />
          <col width="*" />
          <col width="10%" />
        </colgroup>
        <tr>
          <th>key</th>
          <th>text</th>
          <th>sort</th>
        </tr>
        <!--data , key : 객체명, index : idx , v-bind:key="index" <--최적화, key가 같은 템플릿은 다시 렌더링 하지 않음.
                                                              key값은 객체가 아니라 문자열 또는 숫자와같은 식별가능값이어야함.-->
        <tr v-for="(data, index) in listData" v-bind:key="data.idx" @click="setSelected(data)">
          <td>{{data.idx}}</td>
          <td>{{data.text}}</td>
          <td><input type="text" :value="data.ordd"/></td>
        </tr>
        <tr v-if="listData.length === 0">
          <td colspan="3">데이터가 없습니다.</td>
        </tr>
      </table>
    </div>

    <div class="listWrap">
      idx : <span>{{ selected.idx }}</span><br>
      text : <span>{{ selected.text }}</span><br>
      ordd : <span>{{ selected.ordd }}</span><br>
      newOrder <input type="text" v-model="selected.newOrdd" />
    </div>

    <div class="btnRightWrap">
      <a @click="fnUpdate" class="btn">수정</a>
    </div>

    v-for
    <div v-for="(data, key, index) in selected"  v-bind:key="key">
      <td>{{index }}. {{key}}, {{ data }}</td>
    </div>

  </div>
</template>

<script>
export default {

  data(){
    return{
      listData : []
      , selected : {
        idx:''
        ,text:''
        ,ordd:''
        ,newOrdd:''
      }
    }
  }
  , mounted() {
    this.fnGetListData();

  }
  , methods:{
    fnGetListData(){
      this.sch_params = {

      }

      this.$axios.get("http://localhost:9090" + "/api/sort/list", {params : this.sch_params})
        .then((res)=>{
          console.log('success : ',res);
          this.listData = res.data.body;

        })
        .then((err)=>{
          if(err) console.log('err : ', err);
        });
    }
    ,fnUpdate() {
      console.log('update : ',this.selected);
      this.$axios.post("http://localhost:9090" + "/api/sort/update", this.selected)
          .then((res)=>{
            console.log('success : ',res);
            this.selected.idx = '';
            this.selected.text = '';
            this.selected.ordd = '';
            this.selected.newOrdd = '';
            this.fnGetListData();

          })
          .then((err)=>{
            if(err) console.log('err : ', err);
          });
    }
    ,setSelected(data){
      // this.selected = data;
      this.selected.idx = data.idx;
      this.selected.text = data.text;
      this.selected.ordd = data.ordd;
      // this.selected.newOrdd = data.newOrdd;

    }

  }

}
</script>
