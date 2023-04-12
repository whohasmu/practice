<template>
  <v-app id="app">
<!--    <Header/>-->
    <v-navigation-drawer v-model="drawer" app > <!-- temporary -->
      <v-list v-for="(data, idx) in menuList" :key="idx" >

        <v-list-item :to="data.pathUrl" link>

          <v-list-item-icon>
            <v-icon>{{ data.icon }}</v-icon>
          </v-list-item-icon>

          <v-list-item-content>
            <v-list-item-title>{{ data.title }}</v-list-item-title>
          </v-list-item-content>

        </v-list-item>
      </v-list>

<!--      <v-list>-->
<!--        <v-list-item-->
<!--            v-for="item in menuList"-->
<!--            :key="item.title"-->
<!--            :title="item.title"-->
<!--        ></v-list-item>-->
<!--      </v-list>-->

    </v-navigation-drawer>

    <v-app-bar app dark color="primary" >

      <v-app-bar-nav-icon @click="drawer = !drawer"></v-app-bar-nav-icon>

      <router-link tag="div" class="d-flex align-center" :to="'/'">
        <v-img
            alt="Vuetify Logo"
            class="shrink mr-2"
            contain
            src="https://cdn.vuetifyjs.com/images/logos/vuetify-logo-dark.png"
            transition="scale-transition"
            width="40"
        />

        <v-img
            alt="Vuetify Name"
            class="shrink mt-1 hidden-sm-and-down"
            contain
            min-width="100"
            src="https://cdn.vuetifyjs.com/images/logos/vuetify-name-dark.png"
            width="100"
        />
      </router-link>

      <v-spacer></v-spacer>

      <v-btn text href="https://github.com/vuetifyjs/vuetify/releases/latest" target="_blank">
        <span class="mr-2">Latest Release</span>
        <v-icon>mdi-open-in-new</v-icon>
      </v-btn>

    </v-app-bar>
    <v-main>
      <router-view/>
    </v-main>

<!--    <Footer/>-->
    <v-footer></v-footer>
  </v-app>
</template>



<script>
import Header from './components/Common/Header.vue'
import Footer from './components/Common/Footer.vue'

export default {
  name: 'App',
  components: {
    Header,
    Footer
  },
  data: () => ({
    drawer : null
    , menuList : []
  }),
  mounted() {
    this.fnGetMenuList();

  },
  methods:{
    fnGetMenuList(){
      // this.menuList.push({icon : '/', title : '게시판', link : '/board/list'});
      // this.menuList.push({icon : '/', title : 'Sort', link : '/sort/list'});
      // this.menuList.push({icon : '/', title : 'test', link : '/test/test1'});
      // this.menuList.push({icon : '/', title : 'test4', link : '/childRoute'});
      // this.menuList.push({icon : '/', title : 'test5', link : '/test/test1'});

      this.sch_params = {

      }

      this.$axios.get("http://localhost:9090" + "/api/menu/list", {params : this.sch_params})
          .then((res)=>{
            console.log('success : ',res);
            this.menuList = res.data.body;

          })
          .then((err)=>{
            if(err) console.log('err : ', err);
          });

    }
  }
};
</script>
