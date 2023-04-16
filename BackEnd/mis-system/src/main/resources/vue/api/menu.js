import request from '@/utils/request'

// 查询menu列表
export function listMenu(query) {
  return request({
    url: '/menu/list',
    method: 'get',
    params: query
  })
}

// 分页查询menu列表
export function listMenuByPage(query) {
  return request({
    url: '/menu/page',
    method: 'get',
    params: query
  })
}

// 查询menu
export function getMenu(id) {
  return request({
    url: '/menu/' + id,
    method: 'get'
  })
}

// 新增menu
export function addMenu(data) {
  return request({
    url: '/menu',
    method: 'post',
    data: data
  })
}

// 修改menu
export function updateMenu(data) {
  return request({
    url: '/menu',
    method: 'put',
    data: data
  })
}

// 删除menu
export function deleteMenu(id) {
  return request({
    url: '/menu/delete/' + id,
    method: 'delete'
  })
}
