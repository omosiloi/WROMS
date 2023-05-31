import request from '@/utils/request'
// 查询所有dish
export function listAllDish() {
  return request({
    url: '/dish/listAll',
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
// 根据category查询dish
export function listDishByCategory(query) {
  return request({
    url: 'dish/category/' + query,
    method: 'get'
  })
}
// 查询所有table
export function listAllTable() {
  return request({
    url: '/table/listAll',
    method: 'get'
  })
}

// 查询所有菜品类别
export function listAllCategory() {
  return request({
    url: '/dish/listAllCategory',
    method: 'get'
  })
}
// 新增order
export function addOrder(data) {
  return request({
    url: '/order',
    method: 'post',
    data: data
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
// 修改table
export function updateTable(data) {
  return request({
    url: '/table',
    method: 'put',
    data: data
  })
}
