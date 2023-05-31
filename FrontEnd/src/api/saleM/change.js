import request from '@/utils/request'
// 查询所有table
export function selectAllTable() {
    return request({
      url: '/table/listAll',
      method: 'get'
    })
}
// 根据dishId查询dish
export function selectDishById(dishId) {
  return request({
    url: 'dish/' + dishId,
    method: 'get',
    params: dishId
  })
}
// 查询所有菜品类别
export function selectAllCategory() {
  return request({
    url: '/dish/listAllCategory',
    method: 'get'
  })
}
// 查询所有dish
export function selectAllDish() {
  return request({
    url: '/dish/listAll',
    method: 'get'
  })
}
// 根据dishName查询dish
export function selectDishByDishName(query) {
  return request({
    url: 'dish/dishName/' + query,
    method: 'get',
    params: query 
  })
}
// 根据orderId查询order
export function selectOrderByOrderId(orderId) {
    return request({
      url: 'order/' + orderId,
      method: 'get'
    })
}
// 根据tableId和status查询订单
export function selectOrderByTableIdAndStatus(tableId, status) {
    return request({
      url: 'order',
      method: 'get',
      params: {
        tableId: tableId,
        status: status
      }
    })
}
// 修改table
export function updateTable(data) {
    return request({
      url: '/table',
      method: 'put',
      data: data
    })
}
// 修改order
export function updateOrder(data) {
    return request({
      url: '/order',
      method: 'put',
      data: data
    })
}
// 修改orderDetail
export function updateOrderDetail(data) {
  return request({
    url: '/orderDetail',
    method: 'put',
    data: data
  })
}
// 根据tableId查询table
export function selectTableByTableId(tableId) {
  return request({
    url: 'table/' + tableId,
    method: 'get',
    params: tableId
  })
}
// 新增orderDetail
export function addOrderDetail(data) {
  return request({
    url: '/orderDetail',
    method: 'post',
    data: data
  })
}
// 删除orderDetail
export function deleteOrderDetail(dishId) {
  return request({
    url: '/orderDetail/delete/' + dishId,
    method: 'delete'
  })
}