import request from '@/utils/request'
// 登录校验
export function login(data) {
  return request({
    url: '/auth/login',
    method: 'post',
    data
  })
}

// 获取登录用户信息
export function getInfo() {
  return request({
    url: '/auth/getInfo',
    method: 'get'
  })
}

// 退出登录
export function logout() {
  return request({
    url: '/auth/logout',
    method: 'get'
  })
}

// 获取动态路由
export const getRouters = () => {
  return request({
    url: '/auth/getRouters',
    method: 'get'
  })
}