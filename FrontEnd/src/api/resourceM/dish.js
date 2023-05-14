import request from '@/utils/request'

export function listAllDish() {
  return request({
    url: '/dish/listAll',
    method: 'get'
  })
}
export function listDishByCategory(query) {
    return request({
      url: 'dish/category/' + query,
      method: 'get'
    })
}
export function listDishById(query) {
  return request({
    url: 'dish/category',
    method: 'get',
    params: query
  })
}
// 新增Dish
export function addDish(data) {
  return request({
    url: '/dish',
    method: 'post',
    data: data
  })
}
// 修改Dish
export function updateDish(data) {
  return request({
    url: '/dish',
    method: 'put',
    data: data
  })
}