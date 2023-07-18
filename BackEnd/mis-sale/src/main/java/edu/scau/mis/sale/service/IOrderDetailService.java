package edu.scau.mis.sale.service;

import edu.scau.mis.sale.domain.Dish;
import edu.scau.mis.sale.domain.OrderDetail;

import java.util.List;

public interface IOrderDetailService {

    OrderDetail selectOrderDetailById(Long orderDetailId);

    List<OrderDetail> getSales();

    List<OrderDetail> getMonthlySales();

    List<OrderDetail> selectOrderDetailList(OrderDetail orderDetail);

    List<OrderDetail> selectAllOrderDetail();

    int deleteOrderDetailById(Long orderDetailId);

    int insertOrderDetail(OrderDetail orderDetail);

    int updateOrderDetail(OrderDetail orderDetail);

    List<OrderDetail> selectOrderDetailByDishId(Long dishId);
    List<OrderDetail> selectOrderDetailByOrderId(Long orderId);

}
