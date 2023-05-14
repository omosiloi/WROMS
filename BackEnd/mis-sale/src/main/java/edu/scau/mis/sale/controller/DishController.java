package edu.scau.mis.sale.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import edu.scau.mis.core.domain.AjaxResult;
import edu.scau.mis.sale.domain.Dish;
import edu.scau.mis.sale.service.IDishService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("dish")
public class DishController {
    @Autowired
    private IDishService dishService;

    @ApiOperation("分页查询菜品")
    @GetMapping("/page")
    public AjaxResult page(@RequestParam("pageNum") Integer pageNum, @RequestParam("pageSize") Integer pageSize, Dish dish) {
        PageHelper.startPage(pageNum,pageSize);
        List<Dish> list = dishService.selectDishList(dish);
        PageInfo<Dish> pageInfo = new PageInfo<>(list);
        return AjaxResult.success(pageInfo);
    }

    @ApiOperation("查询所有菜品")
    @GetMapping("/listAll")
    public AjaxResult listAll(){
        return AjaxResult.success(dishService.selectAllDish());
    }

    @ApiOperation("根据ID查询菜品")
    @GetMapping("/{dishId}")
    public AjaxResult getById(@PathVariable("dishId") Long dishId) {
        return AjaxResult.success(dishService.selectDishById(dishId));
    }

    @ApiOperation("新增菜品")
    @PostMapping
    public AjaxResult add(@RequestBody Dish dish) {
        return AjaxResult.success(dishService.insertDish(dish));
    }

    @ApiOperation("编辑菜品")
    @PutMapping
    public AjaxResult edit(@RequestBody Dish dish) {
        return AjaxResult.success(dishService.updateDish(dish));
    }

    @ApiOperation("删除菜品")
    @DeleteMapping("/delete/{dishId}")
    public AjaxResult deleteById(@PathVariable Long dishId) {
        return AjaxResult.success(dishService.deleteDishById(dishId));
    }

    @ApiOperation("根据类别查询菜品")
    @GetMapping("/category/{category}")
    public AjaxResult getByCategory(@PathVariable("category") String category) {
        return AjaxResult.success(dishService.selectDishByCategory(category));
    }
}
