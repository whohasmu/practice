import Vue from 'vue'
import Router from 'vue-router'
Vue.use(Router)

export default new Router({
  routes: [
    /*{
      path: '/',
      name: 'HelloWorld',
      component: HelloWorld
    },*/
    {
      path: '/board/list',
      name: 'List',
      component:() => import('@/components/Board/List')
    },
    {
      path: '/board/write',
      name: 'Write',
      component:() => import('@/components/Board/Write')
    },
    {
      path: '/board/view/:idx',
      name: 'View',
      component:() => import('@/components/Board/View')
    },
    {
      path: '/board/mod/:idx',
      name: 'Mod',
      component:() => import('@/components/Board/Modify')
    },
    {
      path: '/sort/list',
      name: 'sortList',
      component:() => import('@/components/sortTest/sortList')
    },
    {
      path: '/menu',
      name: 'menu',
      component: () => import('@/components/Menu/menuMng'),
      // children : [
      //   {
      //     path : '/childRoute',
      //     name : 'childRoute',
      //     component : () => import('@/components/test/childRoute'),
      //
      //   }
      // ]
    },
    {
      path: '/test/test1',
      name: 'test1',
      component:() => import('@/components/test/test1'),
      children : [
        {
          path : '/childRoute',
          name : 'childRoute',
          component : () => import('@/components/test/childRoute'),

        }
      ]

    },


  ]
})
