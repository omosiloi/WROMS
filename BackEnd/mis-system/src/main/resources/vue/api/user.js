import request from '@/utils/request'

// 查询user列表
export function listUser(query) {
  return request({
    url: '/user/list',
    method: 'get',
    params: query
  })
}

// 分页查询user列表
export function listUserByPage(query) {
  return request({
    url: '/user/page',
    method: 'get',
    params: query
  })
}

// 查询user
export function getUser(id) {
  return request({
    url: '/user/' + id,
    method: 'get'
  })
}

// 新增user
export function addUser(data) {
  return request({
    url: '/user',
    method: 'post',
    data: data
  })
}

// 修改user
export function updateUser(data) {
  return request({
    url: '/user',
    method: 'put',
    data: data
  })
}

// 删除user
export function deleteUser(id) {
  return request({
    url: '/user/delete/' + id,
    method: 'delete'
  })
}
