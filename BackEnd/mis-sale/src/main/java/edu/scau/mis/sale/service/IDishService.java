package edu.scau.mis.sale.service;

import edu.scau.mis.sale.domain.Dish;

import java.util.List;

/**
 * 菜品表(Dish)表服务接口
 *
 * @author CJW
 * @since 2023-03-04 00:36:27
 */
public interface IDishService {

    Dish selectDishById(Long dishId);

    List<Dish> selectDishList(Dish dish);

    List<Dish> selectAllDish();

    int deleteDishById(Long dishId);

    int insertDish(Dish dish);

    int updateDish(Dish dish);

    List<Dish> selectDishByCategory(String category);
}
