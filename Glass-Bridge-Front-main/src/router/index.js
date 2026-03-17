import { createRouter, createWebHistory } from 'vue-router'
import Login from '../views/login/index.vue'

const routes = [
  { path: '/', redirect: '/login' },
  { path: '/login', component: Login },
  {
    path: '/index',
    component: () => import('../layout/index.vue'),
    redirect: '/index/dashboard',
    children: [
      { path: 'dashboard', component: () => import('../views/dashboard/index.vue') },
      { path: '/organization/department', component: () => import('../views/organization/department.vue')},
      {
        path: '/organization/staffPosition',
        component: () => import('../views/organization/staffPosition.vue')
      },
      {
        path: '/vehicle/info',
        component: () => import('../views/vehicle/info.vue')
      },
      {
        path: '/dormitory/info',
        component: () => import('../views/dormitory/info.vue')
      },
      {
        path: '/hr/employeesInfo',
        component: () => import('../views/hr/employeesInfo.vue')
      },
      {
        path: '/profile',
        component: () => import('../views/profile.vue')
      },
      {
        path: '/settings',
        component: () => import('../views/settings.vue')
      },
      { path: '/mobile/signin', component: () => import('../views/mobile/signin.vue') },
      { path: '/mobile/functions', component: () => import('../views/mobile/functions.vue') },
      { path: '/mobile/notifications', component: () => import('../views/mobile/notifications.vue') },
      { path: '/scores/myscore', component: () => import('../views/scores/myscore.vue') },
    ]
  }
]

const router = createRouter({
  history: createWebHistory(),
  routes
})

// 全局路由守卫，路由跳转
router.beforeEach((to, from, next) => {
  const token = localStorage.getItem('token')
  // 移动端签到页面和登录页面不需要 token
  if (to.path !== '/login' && to.path !== '/mobile/signin' && !token) 
    next('/login')
  else next()
  // next()
})

export default router
