import { createRouter,createWebHashHistory } from "vue-router";
import Layout from '../layout/Layout.vue'


//配置信息中需要页面的相关配置
const routes = [
  {path: '/Login', name: 'Login',component: () => import("@/views/LoginViewsHome.vue")},
   {path:'/regist',name:'regist',component: () => import("@/views/RegistViewsHome.vue")},
  
  {
    path: '/Layout', name: 'Layout', component: Layout, children: [
        //
        {path: '/home', name: 'Home', component: () => import("@/views/HomeViewsHome.vue")},
        {path: '/floowers', name: 'floowers', component: () => import("@/views/Floowers")},
        {path: '/orderFloower', name: 'orderFloower', component: () => import("@/views/OrderFloower")},
         {path: '/floowerInfo/:id', name: 'floowerInfo', component: () => import("@/views/FloowerInfo")},
        // {path: '/roomInfo', name: 'RoomInfo', component: () => import("@/views/RoomInfo")},
        // {path: '/noticeInfo', name: 'NoticeInfo', component: () => import("@/views/NoticeInfo")},
        // // {path: '/adjustRoomInfo', name: 'AdjustRoomInfo', component: () => import("@//AdjustRoomInfo")},
        // {path: '/repairInfo', name: 'RepairInfo', component: () => import("@/views/RepairInfo")},
        // {path: '/visitorInfo', name: 'VisitorInfo', component: () => import("@/views/VisitorInfo")},
        // //
        // {path: '/myRoomInfo', name: 'MyRoomInfo', component: () => import("@/views/MyRoomInfo")},
        // {path: '/applyRepairInfo', name: 'ApplyRepairInfo', component: () => import("@/views/ApplyRepairInfo")},
        {path: '/TodaySignUp', name: 'TodaySignUp', component: () => import("@/views/TodaySignUp")},

        {path: '/selfInfo', name: 'SelfInfo', component: () => import("@/views/SelfInfo")},
        {path: '/AllUserInfo', name: 'AllUserInfo', component: () => import("@/views/AllUserInfo")},
    ]
},
]


const router = createRouter({

      history:createWebHashHistory(),
      routes
})
//路由守卫
router.beforeEach((to, from, next) => {
  //to 要访问的路径
  //from 代表从哪个路径跳转而来
  // next 是函数，表示放行
  // next() 放行
  // next('/*') 强制跳转
  const user = window.sessionStorage.getItem('user')
  if(to.path === '/regist'){
    console.log("regist");
      return next();
  }
  if (to.path === '/Login') {
    console.log("login");
      return next();
  }
  if (!user) {
    console.log("login");
      return next('/Login')
  }
  if (to.path === '/' && user) {
    console.log("home");
      return next('/home')
  }
  next()
})
export default router;
