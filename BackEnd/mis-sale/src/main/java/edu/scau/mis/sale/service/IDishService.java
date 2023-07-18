package edu.scau.mis.sale.service;

import edu.scau.mis.sale.domain.Dish;

import java.util.List;

public interface IDishService {

    Dish selectDishById(Long dishId);
    List<Dish> selectDishByName(String dishName);
    List<Dish> selectDishByCategory(String category);
    List<Dish> selectDishList(Dish dish);
    List<Dish> selectAllDish();
    int deleteDishById(Long dishId);
    int insertDish(Dish dish);
    int updateDish(Dish dish);
    List<String> selectDescriptionByOrderId(Long orderId);
    List<String> selectDescriptionByDishId(Long dishId);
    List<String> selectAllCategory();

}
