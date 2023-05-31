import request from '@/utils/request'

// 查询所有order
export function selectAllOrder() {
  return request({
    url: '/order/listAll',
    method: 'get'
  })
}
// 根据dishId查询dish
export function selectDishByDishId(dishId) {
  return request({
    url: 'dish/' + dishId,
    method: 'get'
  })
}
// 根据tableId查询table
export function selectTableByTableId(tableId) {
  return request({
    url: 'table/' + tableId,
    method: 'get'
  })
}
// 根据orderId查询所有orderDetail
export function selectOrderDetialByOrderId(orderId) {
  return request({
    url: 'orderDetail/selectOrderDetailByOrderId/' + orderId,
    method: 'get'

  })
}

// 根据orderId查询order
export function selectOrderByOrderId(orderId) {
  return request({
    url: 'order/' + orderId,
    method: 'get'
  })
}

// 新增Order
export function addOrder(data) {
  return request({
    url: '/order',
    method: 'post',
    data: data
  })
}
// 修改Order
export function updateOrder(data) {
  return request({
    url: '/order',
    method: 'put',
    data: data
  })
}

// 根据orderId删除Order
export function deleteOrder(orderId) {
  return request({
    url: '/order/delete/' + orderId,
    method: 'delete'
  })
}
