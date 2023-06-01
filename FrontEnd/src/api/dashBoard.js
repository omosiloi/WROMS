import request from '@/utils/request'


export function getSales() {
    return request({
      url: '/orderDetail/getSales',
      method: 'get'
    })
}
export function getMonthlySales() {
  return request({
    url: '/orderDetail/getMonthlySales',
    method: 'get'
  })
}