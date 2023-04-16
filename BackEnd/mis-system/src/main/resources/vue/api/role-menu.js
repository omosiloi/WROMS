import request from '@/utils/request'

// 查询roleMenu列表
export function listRoleMenu(query) {
  return request({
    url: '/roleMenu/list',
    method: 'get',
    params: query
  })
}

// 分页查询roleMenu列表
export function listRoleMenuByPage(query) {
  return request({
    url: '/roleMenu/page',
    method: 'get',
    params: query
  })
}

// 查询roleMenu
export function getRoleMenu(id) {
  return request({
    url: '/roleMenu/' + id,
    method: 'get'
  })
}

// 新增roleMenu
export function addRoleMenu(data) {
  return request({
    url: '/roleMenu',
    method: 'post',
    data: data
  })
}

// 修改roleMenu
export function updateRoleMenu(data) {
  return request({
    url: '/roleMenu',
    method: 'put',
    data: data
  })
}

// 删除roleMenu
export function deleteRoleMenu(id) {
  return request({
    url: '/roleMenu/delete/' + id,
    method: 'delete'
  })
}
