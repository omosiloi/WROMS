import request from '@/utils/request'
// 分页查询menu列表
export function listMenu (query) {
  return request({
    url: '/menu/list',
    method: 'get',
    params: query
  })
}

export function treeMenu () {
  return request({
    url: '/menu/tree',
    method: 'get'
  })
}

// 查询menu
export function getMenu (menuId) {
  return request({
    url: '/menu/' + menuId,
    method: 'get'
  })
}

// 新增menu
export function addMenu (data) {
  return request({
    url: '/menu',
    method: 'post',
    data: data
  })
}

// 修改menu
export function updateMenu (data) {
  return request({
    url: '/menu',
    method: 'put',
    data: data
  })
}

// 删除menu
export function deleteMenu (menuId) {
  return request({
    url: '/menu/delete/' + menuId,
    method: 'delete'
  })
}