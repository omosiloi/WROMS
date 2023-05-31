import request from '@/utils/request'
// 查询所有table
export function listAllTable() {
  return request({
    url: '/table/listAll',
    method: 'get'
  })
}
// 根据status查询table
export function listTableByStatus(status) {
    return request({
      url: 'table/status/' + status,
      method: 'get'
    })
}
// 根据tableName查询table
export function listTableByTableName(tableName) {
  return request({
    url: 'table/tableName/' + tableName,
    method: 'get',
    params: tableName
  })
}
// 根据tableId查询table
export function listTableByTableId(tableId) {
  return request({
    url: 'table/' + tableId,
    method: 'get',
    params: tableId
  })
}

// 新增table
export function addTable(data) {
  return request({
    url: '/table',
    method: 'post',
    data: data
  })
}
// 修改编辑table
export function updateTable(data) {
  return request({
    url: '/table',
    method: 'put',
    data: data
  })
}

// 根据tableId删除Dish
export function deleteTable(tableId) {
  return request({
    url: '/table/delete/' + tableId,
    method: 'delete'
  })
}