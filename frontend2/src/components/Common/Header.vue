<template>
  <v-app>

    <v-navigation-drawer v-model="drawer" app ><!-- temporary -->
      <v-list v-for="(data, idx) in menuList" :key="idx" >
        <!--        <v-list-item :="data.title" :to="data.link" link></v-list-item>-->
        <v-list-item :to="data.link" link>

          <v-list-item-icon>
            <v-icon>{{ data.icon }}</v-icon>
          </v-list-item-icon>

          <v-list-item-content>
            <v-list-item-title>{{ data.title }}</v-list-item-title>
          </v-list-item-content>

        </v-list-item>
      </v-list>

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

<!--      <v-app-bar-title>App Title</v-app-bar-title>-->

      <v-spacer></v-spacer>

<!--      <span class="mr-2"><router-link to="/board/list">게시판</router-link></span>-->
<!--      <span class="mr-2"><router-link to="/sort/list">Sort</router-link></span>-->
<!--      <span class="mr-2"><router-link to="/test/test1">Test</router-link></span>-->

<!--      <v-btn-->
<!--          href="https://github.com/vuetifyjs/vuetify/releases/latest"-->
<!--          target="_blank"-->
<!--          text-->
<!--      >-->
<!--        <span class="mr-2">Latest Release</span>-->
<!--        <v-icon>mdi-open-in-new</v-icon>-->
<!--      </v-btn>-->

    </v-app-bar>

  </v-app>
</template>

<!--<template>
  <header>
    <h1><router-link to="/" class="logo"><img alt="Vue logo" src="../../assets/logo.png" width="80"></router-link></h1>
    <div class="menuWrap">
      <ul class="menu">
        <li><router-link to="/board/list">게시판</router-link></li>
        <li><router-link to="/sort/list">Sort</router-link></li>
        <li><a href="javascript:;">메뉴3</a></li>
        <li><a href="javascript:;">메뉴4</a></li>
      </ul>
    </div>
  </header>
</template>-->

<script>
// import menuList from '../menuList';

export default {

  // components : {
  //   menuList
  // },
  data: () => ({
    drawer : null
    , menuList : []
  }),
  mounted() {
    this.fnGetMenuList();

  },
  methods:{
    fnGetMenuList(){
      this.menuList.push({icon : '/', title : '게시판', link : '/board/list'});
      this.menuList.push({icon : '/', title : 'Sort', link : '/sort/list'});
      this.menuList.push({icon : '/', title : 'test', link : '/test/test1'});
      this.menuList.push({icon : '/', title : 'test4', link : '/test/test1'});
      this.menuList.push({icon : '/', title : 'test5', link : '/test/test1'});
    }
  }

}
</script>
