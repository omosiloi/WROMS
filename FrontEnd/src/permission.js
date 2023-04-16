import router from './router'
import store from './store'
import { Message } from 'element-ui'
import NProgress from 'nprogress' // progress bar
import 'nprogress/nprogress.css' // progress bar style
import { getToken } from '@/utils/auth' // get token from cookie
import getPageTitle from '@/utils/get-page-title'

NProgress.configure({ showSpinner: false }) // NProgress Configuration

const whiteList = ['/login'] // no redirect whitelist
/**
 * 路由守卫
 */
router.beforeEach(async (to, from, next) => {
  // start progress bar
  NProgress.start()

  // set page title
  document.title = getPageTitle(to.meta.title)

  // determine whether the user has logged in
  const hasToken = getToken()

  if (hasToken) {
    if (to.path === '/login') {
      // if is logged in, redirect to the home page
      next({ path: '/' })
      NProgress.done()
    } else {
      const hasRoles = store.getters.roles && store.getters.roles.length > 0
      if (hasRoles) {
        next()
      } else {
        try {
          // 获取用户信息
          // 注意：roles必须为数组 如: ['admin'] or ,['developer','editor']
          await store.dispatch('user/getInfo')

          // 基于角色生成动态路由
          const accessRoutes = await store.dispatch('GenerateMenuRoutes')
          // 动态添加可以访问的路由
          router.addRoutes(accessRoutes)

          // hack method 确保添加路由工作addRouter完成
          // 设置replace为true, 导航不会留下历史记录
          next({ ...to, replace: true })
        } catch (error) {
          // 去除token返回登录页重新登录
          await store.dispatch('user/resetToken')
          Message.error(error || '您的登录已经过期，请重新登录!')
          next(`/login?redirect=${to.path}`)
          NProgress.done()
        }
      }
    }
  } else {
    /* has no token*/

    if (whiteList.indexOf(to.path) !== -1) {
      // in the free login whitelist, go directly
      next()
    } else {
      // other pages that do not have permission to access are redirected to the login page.
      next(`/login?redirect=${to.path}`)
      NProgress.done()
    }
  }
})

router.afterEach(() => {
  // finish progress bar
  NProgress.done()
})