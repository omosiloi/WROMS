/**
 * 将后端请求菜单数据生成路由
 * 将静态路由 + 动态路由生成总路由
 * 将menus作为SideBar的菜单
 */
// 导入静态路由
import { constantRoutes } from '@/router'
// 后端请求路由
import { getRouters } from '@/api/auth'
import Layout from '@/layout/index'

const menu = {
  state: {
    menus: [],
    addMenus: []
  },
  mutations: {
    SET_MENUS: (state, menus) => {
      state.addMenus = menus
      state.menus = constantRoutes.concat(menus)
    }
  },
  actions: {
    // 后端请求生成菜单-路由
    GenerateMenuRoutes ({ commit }) {
      return new Promise(resolve => {
        // 向后端请求路由数据
        getRouters().then(res => {
          let accessedRoutes = []
          if (res.data.length > 0) {
            accessedRoutes = getComponentFromMenus(res.data)
          }
          // 404必须加在路由最后面
          accessedRoutes.push({ path: '*', redirect: '/404', hidden: true })
          commit('SET_MENUS', accessedRoutes)
          resolve(accessedRoutes)
        })
      })
    }
  }
}
/**
 * 遍历Ajax请求响应的菜单JSON数据，转换为组件对象
 * @param {*} asyncMenuMap 
 * @returns 
 */
function getComponentFromMenus (asyncMenuMap) {
  return asyncMenuMap.filter(route => {
    if (route.component) {
      // Layout组件特殊处理
      if (route.component === 'Layout') {
        route.component = Layout
      } else {
        route.component = loadView(route.component)
      }
    }
    if (route.children != null && route.children && route.children.length) {
      route.children = getComponentFromMenus(route.children)
    } else {
      delete route['children']
      delete route['redirect']
    }
    return true
  })
}
/**
 * 路由懒加载
 * @param {*} view 
 * @returns 
 */
export const loadView = (view) => {
  return (resolve) => require([`@/views/${view}`], resolve)
}

export default menu