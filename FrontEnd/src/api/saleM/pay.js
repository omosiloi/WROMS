import request from '@/utils/request'
// 查询所有table
export function selectAllTable() {
    return request({
      url: '/table/listAll',
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