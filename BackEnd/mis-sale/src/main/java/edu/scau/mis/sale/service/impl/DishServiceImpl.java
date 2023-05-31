package edu.scau.mis.sale.service.impl;

import edu.scau.mis.sale.domain.Dish;
import edu.scau.mis.sale.mapper.DishMapper;
import edu.scau.mis.sale.service.IDishService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DishServiceImpl implements IDishService {
    @Autowired
    private DishMapper dishMapper;
    
    @Override
    public Dish selectDishById(Long dishId) {
        return dishMapper.selectDishById(dishId);
    }
    public List<Dish> selectDishByName(String dishName) {
        return dishMapper.selectDishByName(dishName);
    }
    public List<Dish> selectDishByCategory(String category) {
        return dishMapper.selectDishByCategory(category);
    }
    @Override
    public List<Dish> selectAllDish(){
        return dishMapper.selectAllDish();
    }

    @Override
    public List<Dish> selectDishList(Dish dish) {
        return dishMapper.selectDishList(dish);
    }

    @Override
    public int deleteDishById(Long dishId) {
        return dishMapper.deleteDishById(dishId);
    }

    @Override
    public int insertDish(Dish dish) {
        return dishMapper.insertDish(dish);
    }

    @Override
    public int updateDish(Dish dish) {
        return dishMapper.updateDish(dish);
    }

    public List<String> selectDescriptionByOrderId(Long orderId){
        return dishMapper.selectDescriptionByOrderId(orderId);
    };
    public List<String> selectDescriptionByDishId(Long dishId){
        return dishMapper.selectDescriptionByDishId(dishId);
    };
    public List<String> selectAllCategory(){
        return dishMapper.selectAllCategory();
    };
}
