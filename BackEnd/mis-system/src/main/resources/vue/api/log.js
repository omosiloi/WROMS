import request from '@/utils/request'

// 查询log列表
export function listLog(query) {
  return request({
    url: '/log/list',
    method: 'get',
    params: query
  })
}

// 分页查询log列表
export function listLogByPage(query) {
  return request({
    url: '/log/page',
    method: 'get',
    params: query
  })
}

// 查询log
export function getLog(id) {
  return request({
    url: '/log/' + id,
    method: 'get'
  })
}

// 新增log
export function addLog(data) {
  return request({
    url: '/log',
    method: 'post',
    data: data
  })
}

// 修改log
export function updateLog(data) {
  return request({
    url: '/log',
    method: 'put',
    data: data
  })
}

// 删除log
export function deleteLog(id) {
  return request({
    url: '/log/delete/' + id,
    method: 'delete'
  })
}
