import Vue from 'vue'
import Router from 'vue-router'
Vue.use(Router)
import Layout from '@/layout'

export const constantRoutes = [
  // 登录页面
  {
    path: '/login',
    component: () => import('@/views/login/index'),
    hidden: true
  },
  // 404页面
  {
    path: '/404',
    component: () => import('@/views/error/404'),
    hidden: true
  },
  // 首页
  {
    path: '/',
    component: Layout,
    redirect: '/home',
    children: [{
      path: 'home',
      name: 'Home',
      component: () => import('@/views/home/index'),
      meta: { title: '首页', icon: 'el-icon-s-home', affix: true }
    }]
  },
  {
    path: '/dashboard',
    component: Layout,
    redirect: '/dashboard/index',
    children: [{
      path: 'index',
      name: 'DashBoard',
      component: () => import('@/views/dashboard/index'),
      meta: { title: '数据仪表盘', icon: 'el-icon-s-data' }
    }]
  }
]

const createRouter = () => new Router({
  scrollBehavior: () => ({ y: 0 }),
  routes: constantRoutes
})

const router = createRouter()

export function resetRouter() {
  const newRouter = createRouter({
  })
  router.matcher = newRouter.matcher
}

export default router
