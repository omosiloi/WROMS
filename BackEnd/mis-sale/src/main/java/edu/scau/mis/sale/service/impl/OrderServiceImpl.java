package edu.scau.mis.sale.service.impl;

import edu.scau.mis.sale.domain.Order;
import edu.scau.mis.sale.mapper.OrderMapper;
import edu.scau.mis.sale.service.IOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderServiceImpl implements IOrderService {
    @Autowired
    private OrderMapper orderMapper;

    @Override
    public Order selectOrderById(Long orderId) {
        return orderMapper.selectOrderById(orderId);
    }

    @Override
    public List<Order> selectAllOrder(){
        return orderMapper.selectAllOrder();
    }

    @Override
    public List<Order> selectOrderList(Order order) {
        return orderMapper.selectOrderList(order);
    }

    @Override
    public int deleteOrderById(Long orderId) {
        return orderMapper.deleteOrderById(orderId);
    }

    @Override
    public Long insertOrder(Order order) {
        orderMapper.insertOrder(order);
        return order.getOrderId();
    }

    @Override
    public int updateOrder(Order order) {
        return orderMapper.updateOrder(order);
    }

    @Override
    public List<Order> selectOrderByTableIdAndStatus(Long tableId, String status) {
        return orderMapper.selectOrderByTableIdAndStatus(tableId, status);
    }

}
