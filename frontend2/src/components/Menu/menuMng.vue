<template>
  <b-row>
    <b-col>
      <b-card title="메뉴">


        <button @click="addNode">Add Node</button>


        <!--            @change-name="onChangeName"-->
        <!--            @delete-node="onDel"-->

        <vue-tree-list
            @click="onClickNode"
            @add-node="onAddNode"
            :model="menuList"
            default-tree-node-name="새로운 메뉴"
            default-leaf-node-name="new leaf"

            v-bind:default-expanded="false"
        >
          <template v-slot:leafNameDisplay="slotProps">
            <span>
              {{ slotProps.model.name }}
            </span>
          </template>

          <!--  📂🌲＋📃✂ -->
          <span class="icon" slot="addTreeNodeIcon">＋</span>
          <span class="icon" slot="addLeafNodeIcon">＋</span>
          <span class="icon" slot="delNodeIcon">✂</span>
          <span class="icon" slot="leafNodeIcon">📃</span>
          <span class="icon" slot="treeNodeIcon">📂</span>
        </vue-tree-list>

      </b-card>

    </b-col>

    <b-col>
      <b-card title="메뉴 정보">

        <b-form @submit="">
          <b-row>
            <b-col cols="6">
              <b-form-group
                  id="input-group-3"
                  label="상위메뉴*"
                  label-for="input-3">
                <!-- :options="pidOptions" -->
                <b-form-input
                    id="input-3"
                    v-model="form.pTitle"
                    value-field="value"
                    text-field="text"
                    readonly
                    required
                ></b-form-input>
                <b-form-input
                    id="input-3"
                    hidden
                    v-model="form.pIdx"
                    value-field="value"
                    text-field="text"
                    required
                ></b-form-input>
              </b-form-group>
            </b-col>
          </b-row>
          <b-row>
            <b-col cols="6">
              <b-form-group
                  id="input-group-1"
                  label="메뉴명"
                  label-for="input-2">
                <b-form-input
                    id="input-1"
                    v-model="form.title"
                    placeholder="메뉴 클릭"
                    required
                ></b-form-input>
              </b-form-group>
            </b-col>
            <b-col cols="6">
              <b-form-radio-group
                  id="input-group-2"
                  label="사용여부*"
                  label-for="input-2"
                  v-model="form.useYn"
                  :options="useYnOptions"
                  required >

<!--                  <b-form-radio-->
<!--                      id="input-2"-->
<!--                      :value="'Y'"-->
<!--                      v-model="form.useYn"-->
<!--                  >사용</b-form-radio>-->
<!--                  <b-form-radio-->
<!--                      id="input-2"-->
<!--                      :value="'N'"-->
<!--                      v-model="form.useYn"-->
<!--                  >미사용</b-form-radio>-->

              </b-form-radio-group>
            </b-col>
          </b-row>
          <b-row>
            <b-col cols="6">
              <b-form-group
                  id="input-group-4"
                  label="URL"
                  label-for="input-4">
                <b-form-input
                    id="input-4"
                    v-model="form.pathUrl"
                    value-field="value"
                    text-field="text"
                ></b-form-input>
              </b-form-group>
            </b-col>
          </b-row>
          <b-row>
            <b-col cols="6">
              <b-form-group
                  id="input-group-5"
                  label="정렬순서*"
                  label-for="input-5">
                <b-form-input
                    id="input-5"
                    v-model="form.sortOrder"
                    required
                ></b-form-input>
              </b-form-group>
            </b-col>
          </b-row>
          <div>
            <div style="text-align-last: center; margin : 10px;">
              <b-button v-if="newMenuList.includes(form.idx)" @click="onAddBtn">등록</b-button>
              <b-button v-if="!newMenuList.includes(form.idx)" @click="onAddBtn">수정</b-button>
              <b-button @click="onAddBtn">삭제</b-button>
            </div>
          </div>
        </b-form>

      </b-card>
    </b-col>

  </b-row>

<!--  <b-row>-->
<!--    <b-col>-->
<!--      <b-card title="new">-->

<!--        <button @click="getNewTree">Get new tree</button>-->
<!--        <pre>-->
<!--          {{newTree}}-->
<!--        </pre>-->

<!--      </b-card>-->
<!--    </b-col>-->
<!--  </b-row>-->

</template>

<script>
import { VueTreeList, Tree, TreeNode } from 'vue-tree-list'
export default {
  components: {
    VueTreeList
  },
  data() {
    return {
      newTree: {},
      useYnOptions : [
        { text : '사용' , value : 'Y'},
        { text : '미사용' , value : 'N'},
      ],
      newMenuList : [],
      form : {
        idx : 0,
        pIdx : 0,
        title : '',
        pTitle : '',
        pathUrl : '',
        sortOrder : 0,
        useYn : '',

      },
      // menuList: new Tree([
      //   {
      //     name: 'Node 1',
      //     id: 1,
      //     pid: 0,
      //     dragDisabled: true,
      //     addTreeNodeDisabled: true,
      //     addLeafNodeDisabled: true,
      //     editNodeDisabled: true,
      //     delNodeDisabled: true,
      //     children: [
      //       {
      //         name: 'Node 1-2',
      //         id: 2,
      //         isLeaf: true,
      //         pid: 1
      //       }
      //     ]
      //   },
      //   {
      //     name: 'Node 2',
      //     id: 3,
      //     pid: 0,
      //     disabled: true
      //   },
      //   {
      //     name: 'Node 3',
      //     id: 4,
      //     pid: 0
      //   }
      // ]),

      menuList : new Tree([]),
    }
  },
  mounted() {
    this.getMenuList();

  },
  methods: {
    getMenuList(){
      this.sch_params = {

      }

      this.$axios.get("http://localhost:9090" + "/api/menu/mng/list", {params : this.sch_params})
          .then((res)=>{
            console.log('success : ',res);
            this.menuList = new Tree( this.doMakeNode(res.data.body) );

          })
          .then((err)=>{
            if(err) console.log('err : ', err);
          });
    },
    doMakeNode(dataList){
      let treeNodes = [];
      let vt = this;

      dataList.forEach( (item) => {
        let node = this.changeToTreeVO(item);

        if(node.pid == null){
          node.children = makeChildNode(node);
          treeNodes.push(node);
        }

      });

      function makeChildNode(pNode){
        let result = [];

        dataList.forEach( (subItem) => {
          let subNode = vt.changeToTreeVO(subItem)
          if(pNode.id == subNode.pid){
            result.push(subNode);
          }
        });

        if(result.length === 0){
          return result;
        }else{
          result.forEach( (node, index) => {
            result[index].children = makeChildNode(node);
          });
          return result;
        }


      }

      return treeNodes;
    },

    // onDel(node) {
    //   console.log(node)
    //   node.remove()
    // },

    // onChangeName(params) {
    //   console.log(params)
    // },
    //
    onAddNode(params) {
      console.log(params);
      this.newMenuList.push(params.id);
    },

    onClickNode(nodeData) {

      this.form.idx = nodeData.id;
      this.form.pIdx = nodeData.parent.id;
      this.form.title = nodeData.name;
      this.form.pTitle = nodeData.parent.name;
      this.form.pathUrl = nodeData.url;
      this.form.useYn = nodeData.useAt;
      this.form.sortOrder = nodeData.colOrd;


    },

    addNode() {
      // var node = new TreeNode({ name: 'new node', isLeaf: false })
      // if (!this.data.children) this.data.children = []
      // this.data.addChildren(node)


      // let node = new TreeNode({ name: 'new node', isLeaf: false })
      let node = new TreeNode({
        "id": 0,
        "name": '새로운 페이지',
        "children": [],
        "dragDisabled": false,               /*드래그방지*/
        "editNodeDisabled": true,           /*수정방지*/
        "addTreeNodeDisabled" : false,      /*폴더생성방지*/
        "addLeafNodeDisabled" : true,       /*파일생성방지*/
        "delNodeDisabled" : true,
      });

      if (!this.menuList.children) this.menuList.children = []
      this.menuList.addChildren(node)
    },

    onAddBtn(){

      let paramList =  {
        idx : this.form.idx,
        pidx : this.form.pIdx,
        title : this.form.title,
        pathUrl : this.form.pathUrl,
        useYn : this.form.useYn,
        sortOrder : this.form.sortOrder,
      }
    console.log('add  param List : ', paramList);

    // this.$axios.post("http://localhost:9090" + "/api/menu/mng/add", paramList)
    //     .then((res)=>{
    //       console.log('success : ',res);
    //
    //
    //
    //     })
    //     .then((err)=>{
    //       if(err) console.log('err : ', err);
    //     });

    // getNewTree() {
    //   var vm = this
    //   function _dfs(oldNode) {
    //     var newNode = {}
    //
    //     for (var k in oldNode) {
    //       if (k !== 'children' && k !== 'parent') {
    //         newNode[k] = oldNode[k]
    //       }
    //     }
    //
    //     if (oldNode.children && oldNode.children.length > 0) {
    //       newNode.children = []
    //       for (var i = 0, len = oldNode.children.length; i < len; i++) {
    //         newNode.children.push(_dfs(oldNode.children[i]))
    //       }
    //     }
    //     return newNode
    //   }
    //
    //   vm.newTree = _dfs(vm.data)
    // },

    },
    changeToTreeVO(data){
      let returnData = {
        "id": parseInt(data.idx),
        "pid": data.pidx == null ? null : parseInt(data.pidx),
        "name": data.title,
        "useAt": data.useYn == "Y" ? "Y" : "N",
        "url": data.pathUrl,
        "colOrd": data.sortOrder,
        // "isLeaf": data.pIdx == null ? true : false,
        "isLeaf": false,
        "children": [],
        // "isLeaf": data.isLeaf == "Y" ? true : false,
        // "pageSeq": data.pageSeq,
        // "upperMenuNm": data.upperMenuNm,
        // "level": data.level,
        // "dragDisabled": true,               /*드래그방지*/
        "editNodeDisabled": true,           /*수정방지*/
        "addTreeNodeDisabled" : false,      /*폴더생성방지*/
        "addLeafNodeDisabled" : true,       /*파일생성방지*/
      }
      return returnData;
    },
    changeToMenuVO(data){
      return {
        "idx" : data.id,
        "pIdx" : data.pid,
        "title" : data.name,
        "pathUrl" : data.url,
        "useYn" : data.useAt == "사용" ? 'Y' : 'N',
        "sortOrder" : data.colOrd,
      }
    }

  },

}
</script>

<!--<style lang="less" >-->
<!--.vtl {-->
<!--  .vtl-drag-disabled {-->
<!--    background-color: #d0cfcf;-->
<!--    &:hover {-->
<!--      background-color: #d0cfcf;-->
<!--    }-->
<!--  }-->
<!--  .vtl-disabled {-->
<!--    background-color: #d0cfcf;-->
<!--  }-->
<!--}-->
<!--</style>-->

<!--&lt;!&ndash;<style lang="less" rel="stylesheet/less" scoped>&ndash;&gt;-->
<!--<style lang="less" scoped>-->
<!--.icon {-->
<!--  &:hover {-->
<!--    cursor: pointer;-->
<!--  }-->
<!--}-->

<!--.muted {-->
<!--  color: gray;-->
<!--  font-size: 80%;-->
<!--}-->
<!--</style>-->