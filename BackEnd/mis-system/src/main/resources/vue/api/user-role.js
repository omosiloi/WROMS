import request from '@/utils/request'

// 查询userRole列表
export function listUserRole(query) {
  return request({
    url: '/userRole/list',
    method: 'get',
    params: query
  })
}

// 分页查询userRole列表
export function listUserRoleByPage(query) {
  return request({
    url: '/userRole/page',
    method: 'get',
    params: query
  })
}

// 查询userRole
export function getUserRole(id) {
  return request({
    url: '/userRole/' + id,
    method: 'get'
  })
}

// 新增userRole
export function addUserRole(data) {
  return request({
    url: '/userRole',
    method: 'post',
    data: data
  })
}

// 修改userRole
export function updateUserRole(data) {
  return request({
    url: '/userRole',
    method: 'put',
    data: data
  })
}

// 删除userRole
export function deleteUserRole(id) {
  return request({
    url: '/userRole/delete/' + id,
    method: 'delete'
  })
}
