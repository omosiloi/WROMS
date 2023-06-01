package edu.scau.mis.sale.service.impl;

import edu.scau.mis.sale.domain.OrderDetail;
import edu.scau.mis.sale.mapper.OrderDetailMapper;
import edu.scau.mis.sale.service.IOrderDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderDetailServiceImpl implements IOrderDetailService {
    @Autowired
    private OrderDetailMapper orderDetailMapper;

    @Override
    public OrderDetail selectOrderDetailById(Long orderDetailId) {
        return orderDetailMapper.selectOrderDetailById(orderDetailId);
    }

    @Override
    public List<OrderDetail> selectAllOrderDetail(){
        return orderDetailMapper.selectAllOrderDetail();
    }
    @Override
    public List<OrderDetail> getSales(){
        return orderDetailMapper.getSales();
    }
    @Override
    public List<OrderDetail> getMonthlySales(){
        return orderDetailMapper.getMonthlySales();
    }
    @Override
    public List<OrderDetail> selectOrderDetailList(OrderDetail orderDetail) {
        return orderDetailMapper.selectOrderDetailList(orderDetail);
    }

    @Override
    public int deleteOrderDetailById(Long orderDetailId) {
        return orderDetailMapper.deleteOrderDetailById(orderDetailId);
    }

    @Override
    public int insertOrderDetail(OrderDetail orderDetail) {
        return orderDetailMapper.insertOrderDetail(orderDetail);
    }

    @Override
    public int updateOrderDetail(OrderDetail orderDetail) {
        return orderDetailMapper.updateOrderDetail(orderDetail);
    }
    @Override
    public List<OrderDetail> selectOrderDetailByDishId(Long dishId){
        return orderDetailMapper.selectOrderDetailByDishId(dishId);
    };
    @Override
    public List<OrderDetail> selectOrderDetailByOrderId(Long orderId){
        return orderDetailMapper.selectOrderDetailByOrderId(orderId);
    };
}
