// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue'
import App from './App'
import router from './router'
import axios from 'axios'
import VueTreeList from 'vue-tree-list'

import './assets/main.css'
import vuetify from './plugins/vuetify'

// bootstrap
import { BootstrapVue, IconsPlugin } from 'bootstrap-vue'
import 'bootstrap/dist/css/bootstrap.css'
import 'bootstrap-vue/dist/bootstrap-vue.css'

Vue.config.productionTip = false

Vue.prototype.$axios = axios;

Vue.use(VueTreeList);
Vue.use(BootstrapVue)




/* eslint-disable no-new */
new Vue({
  el: '#app',
  router,
  components: { App },
  vuetify,
  template: '<App/>'
})

// new Vue({
//   render: h => h(App),
//   router,
//   components: { App },
//   template: '<App/>'
// }).$mount('#app')
