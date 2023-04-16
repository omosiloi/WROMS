import request from '@/utils/request'

// 查询列表
export function list(query) {
  return request({
    url: '//list',
    method: 'get',
    params: query
  })
}

// 分页查询列表
export function listByPage(query) {
  return request({
    url: '//page',
    method: 'get',
    params: query
  })
}

// 查询
export function get(id) {
  return request({
    url: '//' + id,
    method: 'get'
  })
}

// 新增
export function add(data) {
  return request({
    url: '/',
    method: 'post',
    data: data
  })
}

// 修改
export function update(data) {
  return request({
    url: '/',
    method: 'put',
    data: data
  })
}

// 删除
export function delete(id) {
  return request({
    url: '//delete/' + id,
    method: 'delete'
  })
}
