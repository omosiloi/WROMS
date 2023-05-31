package edu.scau.mis.sale.service;

import edu.scau.mis.sale.domain.Order;

import java.util.List;

/**
 * 订单表(Order)表服务接口
 *
 * @author CJW
 * @since 2023-03-04 00:36:27
 */
public interface IOrderService {

    Order selectOrderById(Long orderId);

    List<Order> selectOrderList(Order order);

    List<Order> selectAllOrder();

    int deleteOrderById(Long orderId);

    Long insertOrder(Order order);

    int updateOrder(Order order);
    List<Order> selectOrderByTableIdAndStatus(Long tableId, String status);
}
