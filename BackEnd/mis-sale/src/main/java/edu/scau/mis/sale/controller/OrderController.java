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

    @ApiOperation("新增订单")
    @PostMapping
    public AjaxResult add(@RequestBody Order order) {
        return AjaxResult.success(orderService.insertOrder(order));
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
