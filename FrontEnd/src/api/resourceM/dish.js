import request from '@/utils/request'
// 查询所有dish
export function listAllDish() {
  return request({
    url: '/dish/listAll',
    method: 'get'
  })
}
// 根据category查询dish
export function listDishByCategory(query) {
    return request({
      url: 'dish/category/' + query,
      method: 'get'
    })
}
// 根据dishId查询dish
export function listDishById(dishId) {
  return request({
    url: 'dish/' + dishId,
    method: 'get',
    params: dishId
  })
}
// 根据dishName查询dish
export function listDishByDishName(query) {
  return request({
    url: 'dish/dishName/' + query,
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
// 删除Dish
export function deleteDish(dishId) {
  return request({
    url: '/dish/delete/' + dishId,
    method: 'delete'
  })
}
