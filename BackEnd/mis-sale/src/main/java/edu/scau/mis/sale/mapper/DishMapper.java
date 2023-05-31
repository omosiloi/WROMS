package edu.scau.mis.sale.mapper;

import edu.scau.mis.sale.domain.Dish;
import edu.scau.mis.system.domain.Role;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * DishMapper
 */
@Mapper
public interface DishMapper {
    
    Dish selectDishById(Long id);

    List<Dish> selectDishByName(String dishName);
    List<Dish> selectDishByCategory(String category);

//    List<Dish> selectDishesByOrderId(Long orderId);
//
//    List<Dish> selectDishesByOrderName(String orderName);
//


    List<Dish> selectDishList(Dish dish);

    List<Dish> selectAllDish();

    int deleteDishById(Long dishId);
    
    int deleteDishByIds(Long[] dishIds);
    
    int insertDish(Dish dish);

    int updateDish(Dish dish);

    List<String> selectDescriptionByOrderId(Long orderId);
    List<String> selectDescriptionByDishId(Long dishId);
    List<String> selectAllCategory();
}



