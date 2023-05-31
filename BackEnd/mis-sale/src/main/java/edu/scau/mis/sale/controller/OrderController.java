package edu.scau.mis.sale.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import edu.scau.mis.core.domain.AjaxResult;
import edu.scau.mis.sale.domain.Order;
import edu.scau.mis.sale.service.IOrderService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("order")
public class OrderController {
    @Autowired
    private IOrderService orderService;
    @ApiOperation("分页查询订单")
    @GetMapping("/page")
    public AjaxResult page(@RequestParam("pageNum") Integer pageNum, @RequestParam("pageSize") Integer pageSize, Order order) {
        PageHelper.startPage(pageNum,pageSize);
        List<Order> list = orderService.selectOrderList(order);
        PageInfo<Order> pageInfo = new PageInfo<>(list);
        return AjaxResult.success(pageInfo);
    }

    @ApiOperation("查询所有订单")
    @GetMapping("/listAll")
    public AjaxResult listAll(){
        return AjaxResult.success(orderService.selectAllOrder());
    }

    @ApiOperation("根据ID查询订单")
    @GetMapping("/{orderId}")
    public AjaxResult getById(@PathVariable("orderId") Long orderId) {
        return AjaxResult.success(orderService.selectOrderById(orderId));
    }

    @ApiOperation("根据tableId和status查询订单")
    @GetMapping
    public AjaxResult getByTableIdAndStatus(@RequestParam("tableId") Long tableId, @RequestParam("status") String status) {
        // 调用服务层方法，根据tableId和status查询订单
        List<Order> orderList = orderService.selectOrderByTableIdAndStatus(tableId, status);
        return AjaxResult.success(orderList);
    }


    @ApiOperation("新增订单")
    @PostMapping
    public AjaxResult add(@RequestBody Order order) {
        Long orderId = orderService.insertOrder(order); // 调用insertOrder方法插入订单并获取生成的订单ID
        return AjaxResult.success(orderId); // 将生成的订单ID作为响应数据返回给前端
    }

    @ApiOperation("编辑订单")
    @PutMapping
    public AjaxResult edit(@RequestBody Order order) {
        return AjaxResult.success(orderService.updateOrder(order));
    }

    @ApiOperation("删除订单")
    @DeleteMapping("/delete/{orderId}")
    public AjaxResult deleteById(@PathVariable Long orderId) {
        return AjaxResult.success(orderService.deleteOrderById(orderId));
    }

}
