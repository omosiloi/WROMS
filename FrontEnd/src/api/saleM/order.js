import request from '@/utils/request'

export function listAllDish() {
  return request({
    url: '/dish/listAll',
    method: 'get'
  })
}