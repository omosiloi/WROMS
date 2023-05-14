package edu.scau.mis.sale.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import edu.scau.mis.core.domain.AjaxResult;
import edu.scau.mis.sale.domain.OrderDetail;
import edu.scau.mis.sale.service.IOrderDetailService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("orderDetail")
public class OrderDetailController {
    @Autowired
    private IOrderDetailService orderDetailService;

    @ApiOperation("分页查询订单明细")
    @GetMapping("/page")
    public AjaxResult page(@RequestParam("pageNum") Integer pageNum, @RequestParam("pageSize") Integer pageSize, OrderDetail orderDetail) {
        PageHelper.startPage(pageNum,pageSize);
        List<OrderDetail> list = orderDetailService.selectOrderDetailList(orderDetail);
        PageInfo<OrderDetail> pageInfo = new PageInfo<>(list);
        return AjaxResult.success(pageInfo);
    }

    @ApiOperation("查询所有订单明细")
    @GetMapping("/listAll")
    public AjaxResult listAll(){
        return AjaxResult.success(orderDetailService.selectAllOrderDetail());
    }

    @ApiOperation("根据ID查询订单明细")
    @GetMapping("/{orderDetailId}")
    public AjaxResult getById(@PathVariable("orderDetailId") Long orderDetailId) {
        return AjaxResult.success(orderDetailService.selectOrderDetailById(orderDetailId));
    }

    @ApiOperation("新增订单明细")
    @PostMapping
    public AjaxResult add(@RequestBody OrderDetail orderDetail) {
        return AjaxResult.success(orderDetailService.insertOrderDetail(orderDetail));
    }

    @ApiOperation("编辑订单明细")
    @PutMapping
    public AjaxResult edit(@RequestBody OrderDetail orderDetail) {
        return AjaxResult.success(orderDetailService.updateOrderDetail(orderDetail));
    }

    @ApiOperation("删除订单明细")
    @DeleteMapping("/delete/{orderDetailId}")
    public AjaxResult deleteById(@PathVariable Long orderDetailId) {
        return AjaxResult.success(orderDetailService.deleteOrderDetailById(orderDetailId));
    }
}
