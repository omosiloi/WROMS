import request from '@/utils/request'

// 查询role列表
export function listAllRole (query) {
  return request({
    url: '/role/list',
    method: 'get',
    params: query
  })
}

// 分页查询role列表
export function listRoleByPage (query) {
  return request({
    url: '/role/page',
    method: 'get',
    params: query
  })
}

// 查询role
export function getRole (id) {
  return request({
    url: '/role/' + id,
    method: 'get'
  })
}

// 新增role
export function addRole (data) {
  return request({
    url: '/role',
    method: 'post',
    data: data
  })
}

// 修改role
export function updateRole (data) {
  return request({
    url: '/role',
    method: 'put',
    data: data
  })
}

// 删除role
export function deleteRole (id) {
  return request({
    url: '/role/delete/' + id,
    method: 'delete'
  })
}
